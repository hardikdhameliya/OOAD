/*
I have created 2 buffers(buff1 and buff2) to store the data and 3 threads to perform computation task. 1st thread (thread_a) will read data from file and store in buff1. 2nd thread ( thread_b) will copy the data from buff1 to buff2. 3rd thread(thread_c) will read buff2 and print it. Inter thread communication is performed by 4 semaphores.
*/ 

#include<stdio.h>
#include<semaphore.h>
#include<string.h>
#include<pthread.h>
#include<stdlib.h>
#include<unistd.h>
#define MAX 5000

//Declaration of semaphore.
sem_t empty2;
sem_t empty1;
sem_t full1;
sem_t full2;

//Declaration of buffers.
char *buff1,*buff2;

//Declaration of threads
pthread_t thread_a;
pthread_t thread_b;
pthread_t thread_c;


// This function is associated with thread_a and will read the data from file and copy it into buff1
void* Read_A(void *agr)
{  
char *s;
s=agr;
char *buff;	
	sem_wait(&empty1);
	FILE *fp1;
	fp1 = fopen(s, "r");
	if (fp1 != NULL) 
	{
	 /* Go to the EOF. */
	    if (fseek(fp1, 0L, SEEK_END) == 0) 
	     {
              /* Get the size of the file. */
              long bufsize = ftell(fp1);
              if (bufsize == -1) { /* Error */ }

              /* Allocate buffer to that size. */
              buff = malloc(sizeof(char) * (bufsize + 1));

              /* Go back to the start of the file. */
              if (fseek(fp1, 0L, SEEK_SET) == 0) { /* Error */ }

              /* Read the entire file into memory. */
              size_t newLen = fread(buff, sizeof(char), bufsize, fp1);
              if (newLen == 0) 
	       {
               fputs("Error reading file", stderr);
               } else 
		 {
                 buff[++newLen] = '\0'; 
                 }
             }
    
         }
	buff1=buff;
	sem_post(&full1);
	return 0;
}

// This funcation is associated with thread_b and will copy the data from buff1 to buff2.	
void* Copy_B(char *in) 
	{
	   char temp[MAX];
	   sem_wait(&full1);
	   sem_wait(&empty2);
	   strcpy(temp,in);
	   buff2=temp;
	   sem_post(&empty1);
	   sem_post(&full2);
	   return 0;
	}

//This funcation is associated with thread_c and will print the data available in buff2.
void* Print_C( char *print_data) 
	{
	 sem_wait(&full2);
	 printf("%s", print_data);
	 sem_post(&empty2);
	 return 0;
	}

int main()
{ 

//Intialization of semaphores
sem_init(&empty1, 0, 1);
sem_init(&empty2, 0, 1);
sem_init(&full1, 0, 0);
sem_init(&full2, 0, 0);

//File on the disk
char *a="datafile.txt";

//Creation of threads
pthread_create (&thread_a, NULL, (void *) &Read_A, a);
pthread_join(thread_a, NULL);
pthread_create (&thread_a, NULL, (void *) &Copy_B, buff1);
pthread_join(thread_a, NULL);
pthread_create (&thread_a, NULL, (void *) &Print_C, buff2);
pthread_join(thread_a, NULL);

// Destroy the semaphores
sem_destroy(&empty1);
sem_destroy(&empty2);
sem_destroy(&full1);
sem_destroy(&full2);

return 0;
}	

	

	
