#include<stdio.h>
#include <stdlib.h>
#define MAX 1000

int pageframe[MAX];
int pagecount[MAX];
int pagetable[1000];
int fault;
int nopage;

/* Pagereplacement function*/
void pagereplace(int newpageframe)
{
int i,j,location,min;


min = pagecount[0];
 
    for ( int c = 1 ; c < nopage ; c++ ) 
    {
        if ( pagecount[c] < min ) 
        {
           min = pagecount[c];
           location = c;
        }
    } 
    pageframe[location]=newpageframe;
    pagecount[location]=0;
    fault++;
    
}

/* Checking the availibality of storage */
int chekingstorage(int inputframe)
{	int lastpage=1;
	int i;
	for(i=0;i<nopage;i++)
	{
		if(inputframe!=pageframe[i])
		{
			pagecount[i]=pagecount[i]>>1;
			if(pageframe[i]==0)
			{
			pageframe[i]=inputframe;
			pagecount[i]=0;		
			fault++;
			lastpage=-1;
			break;
			}
		}else if(inputframe==pageframe[i]){
			pagecount[i]=pagecount[i]>>1;
			pagecount[i]=pagecount[i]|0x8000;
			lastpage = -1;
			break;
		     }	
	}
	
	
return lastpage;
}

int main()
{

int index,j,i=0,max[MAX],n;
int inputframe;
int counts=0;
int counter=0;
index=0;
inputframe=0;
int pagefault[MAX];
int a;
int p[50], ch;
int count;
time_t t;
n = 1000; // Size of the memory reference. More than 1000 memory reference cause segmentation faullt in linux( As I have used linux in VMwzare)
   
/* Here I have created one file and number of frames are stored in the file using rand() function*/
FILE *fp;
   fp = fopen("frame.txt", "w+");
	srand((unsigned) time(&t));
	for( i = 0 ; i < n ; i++ ) 
   	{
	if((a=(rand() % 50)))
      	fprintf(fp, "%d\n", a);
   	}
   	fclose(fp);
    	fp = fopen("frame.txt", "r");
	i=0;

	/* Taking the input from file */
	while((ch=fgetc(fp))!=EOF)	
	{
		if(ch != 32 && ch !=10 && ch != '\n')
		{
		fseek( fp, -1, SEEK_CUR);
		fscanf(fp, "%d", &count);
		p[i]=count;	
		i++;
		}
	}
	
	int givenpage=i;
	/*asking for number of pageframe */

	printf("How many inputs? ");
	scanf("%d", &counts);


while(counter<counts)
{	
	printf("What is the fram size for input %d?", counter+1);
	scanf("%d", &max[counter]);
	nopage=max[counter];
		
	for(j=0; j<nopage; j++)
	{
	pageframe[j]=0;
	pagecount[j]=0;
	}	
	int lastpage;
	int dummy=0;
	for(j=0;j<givenpage;j++)
	{
	
		lastpage = chekingstorage(p[j]);
		if(lastpage!=-1)
		pagereplace(p[j]);
		
				
	}

		
	pagefault[counter]=fault;
	fault=0;
	counter++;
	
}	

/*Print the result */
printf("Output\nPage frame --> page fault\n");
for(int k=0;k<counts;k++)
{
	printf("%d --> %d\n", max[k],pagefault[k]);
}

char * commandsForGnuplot[] = {"set title \"Pgae Fault vs Page Frame\"", " set xlabel \"Page Frames\""," set ylabel \"Page Fault\"","plot 'data.temp'"};
FILE * temp = fopen("data.temp", "w");
/*Opens an interface that one can use to send commands as if they were typing into the
    gnuplot command line.  "The -persistent" keeps the plot open even after your
    C program terminates. */
     

FILE * gnuplotPipe = popen ("gnuplot -persistent", "w");
fprintf(gnuplotPipe, "plot '-' with lines, '-' with lines\n");
for (int i = 0; i < counts; i++)
{
  fprintf(gnuplotPipe, "%d %d\n", max[i], pagefault[i]);
}

fprintf(gnuplotPipe, "e");
return 0;

}

