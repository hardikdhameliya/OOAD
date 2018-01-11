/*Implement a program to count the frequency of words in a text file. 
The text file is partitioned into N segments. Each segment is processed by a 
separate thread that out- puts the intermediate frequency count for its segment. 
The main process waits until all the threads complete; then it computes the consolidated 
word-frequency data based on the individual threads’ output.*/



#include<stdio.h>
#include<string.h>
#include<pthread.h>
#include<stdlib.h>
#include<unistd.h>
#define MAX 500
pthread_t tid[MAX];

static int thread_count=0;		//Thread counter
struct each_thread			    //Structure to store each thread data
{
	char words[MAX][MAX];
	int count[MAX];
	int wrdcnt;
}thread_num[MAX]; 


/*Delete unnecessary space if any*/
int deletespace(char str[MAX])
{
    int count = 0;
 
    for (int i = 0; str[i]; i++)
        if (str[i] != ' ')
            str[count++] = str[i]; 
    str[count] = '\0';
    if(count ==0)
	return 0;
    else
	return 1;
}

/* Print thread*/
void thread_print(struct each_thread temp)
{
int i=0,j=0;
	for(i=0; i<=temp.wrdcnt; i++)
	{       
		if(deletespace(temp.words[i]))
		{
		printf(" %s-->%d\n", temp.words[i],temp.count[i]);
		}
	}
}

/* Word frequency counter function*/
void* word_count(void *agr)
{
    char *str;
    str=agr;
    char *space=" ";
    char *newline=" ";
    int count = 0, c = 0, i, j = 0, k = 0, word = 0;
    char p[MAX][MAX], ptr1[MAX][MAX],ch;
    char *ptr;
    FILE *fp;
    int lenght = strlen(str);
    
     
    
    for (i = 0;i<strlen(str);i++)
    {
        if ((str[i] == ' ' )||(str[i] == ',')||(str[i] == '.') || (str[i] == '\n'))
        {
            word++;
        }
    }
    for (i = 0, j = 0, k = 0;j < strlen(str);j++)
    {
        if ((str[j] == ' ' )||(str[j] == ',')||(str[i] == '.') || (str[i] == '\n'))  
        {    
            i++;
            k = 0;
        }        
        else if(str[j] != 32 && str[j] != 10 )
             p[i][k++] = str[j];
    }
    k = 0;
    int t;
    for (i = 0;i < word;i++)
    { t=deletespace(p[i]);
	
        for (j = 0;j < word;j++)
        {
            if (i == j)
            {   
		if(t)
		{
                strcpy(ptr1[k], p[i]);
                k++;
		count++;
		}
                break;
            }
            else
            {
		
                if (strcmp(ptr1[j], p[i]) != 0)
                    continue;
                else
                    break;
            }
        }
    }
   for (i = 0;i <=count;i++) 
    {
        for (j = 0;j <=word;j++)
        {
	    
            if (strcmp(ptr1[i], p[j]) == 0)
                c++;
	    
        }
	strcpy(thread_num[thread_count].words[i], ptr1[i]);
	thread_num[thread_count].count[i]=c;
	c = 0;
    }
    i=0;
    while(thread_num[thread_count].count[i] > 0)
    {
	i++;
	thread_num[thread_count].wrdcnt=i;
	
    }
    thread_print(thread_num[thread_count]);
    thread_count++;
    return NULL;
}


int main(void)
{
    int i = 0, k=0;
    int error;
    char line[MAX][MAX];
    FILE *fp;
    char c,b[MAX][MAX];
    struct each_thread agr;
    int words[MAX];
    int j=0;
    int flag=1;
    int word=0;
    int lines=0;
	
        fp = fopen("datafile.txt", "r");                           //Open the file in read mode
		
    /* Calculate numbers of lines in the given file */
	while((c = fgetc(fp)) != EOF)                      
	{
		b[i][k++]=c;
		if (c == '\n')
		{ 	
			b[i][k] = '\0';
			strcat(line[lines],b[i]);		
			lines++;
			i++;
			k=0;
			continue;
		}else if (c == ' ') 
		 {
			b[i][k]= '\0';
		 	word++;
			continue;			
		 }

		
	}
    

    fclose(fp);                                                         //close the file
    i=0;
    while(i < lines)
    {
        error = pthread_create(&tid[i],NULL, &word_count, line[i]);		//Create thread for each line of input file.
	
        if (error != 0)                         						//Check the error in the creartion of threads.
            printf("\n cannot create thread :[%s]", strerror(error));
        else
            printf("\n %dth Thread created successfully\n", i+1);
	    
	pthread_join(tid[i], NULL);
        i++;
    }
    
/*Consolidate data based upon each thread's output*/

    for(i=0;i<thread_num[0].wrdcnt;i++)
    {
    strcpy(agr.words[i],thread_num[0].words[i]);
    agr.count[i]=thread_num[0].count[i];
    }
    agr.wrdcnt=thread_num[0].wrdcnt;
    i=1;

    while(i <= thread_count)
    {	
	for ( j=0; j<=thread_num[i].wrdcnt; j++)
	{	
		for (k=0; k<=agr.wrdcnt; k++)
		{
	        	if(strcmp(agr.words[k],thread_num[i].words[j])==0)
		        {
		         agr.count[k]+=thread_num[i].count[j];
			 k=agr.wrdcnt+1;
			 flag=0;
		        }	
		
		}
		if(flag)
		{
		agr.wrdcnt++;
		strcpy(agr.words[agr.wrdcnt], thread_num[i].words[j]);
		agr.count[agr.wrdcnt]=thread_num[i].count[j];
		}
		flag=1;
	}
	i++;
	
	
    }
    printf("\nfinal aggregate data\n");
    thread_print(agr);												//Print the final aggregate data.
    
    
    return 0;
}
