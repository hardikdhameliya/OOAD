#include <stdio.h>
#include<string.h>

void main()
{
FILE *fp;
fp=fopen("Output.txt", "w+");					//open output file in write mode
int Max[10][10], need[10][10], alloc[10][10];
int avail[10], completed[10], safeSequence[10];
int p=1, r, i, j, process, count;
char dummy;
count = 0;
for(i = 0; i< p; i++)
completed[i] = 0;

/* Asking for number of client */
printf("\n\nEnter the no of client : ");
scanf("%d", &r);

/* Asking for MAX credit of each client */
printf("\n\nEnter the Max credit for each client : ");
for(i = 0; i < p; i++)
{
for(j = 0; j < r; j++)
scanf("%d", &Max[i][j]);
}

/* Asking for credit of each client */
printf("\n\nEnter the credit for each client : ");
for(i = 0; i < p; i++)
{
for(j = 0; j < r; j++)
scanf("%d", &alloc[i][j]);
}

/* Asking for Available credit of each client */
printf("\n\nEnter the Available credit : ");
for(i = 0; i < r; i++)
scanf("%d", &avail[i]);

/* Printing  credit need of each client */
printf("\n\nClient's current need is : ");
for(i = 0; i < p; i++)
{	for(j = 0; j < r; j++)
	{ need[i][j]=Max[i][j]-alloc[i][j];
	  printf("%d\t", need[i][j]);
	}
}
do
{
	fputs("Max Credit:\n", fp);		//Store maximum credit the data in output file
 
	for(i = 0; i < p; i++)
	{
	for( j = 0; j < r; j++)
	fprintf(fp, "%d\t",Max[i][j]);
	}

	fputs("\nNeeded credit:\n", fp);	//Store Needed credit the data in output file
	for(i = 0; i < p; i++)
	{
	for( j = 0; j < r; j++)
	fprintf(fp,"%d\t",need[i][j]);
	}	

	fputs("\nAllocated credit:\n", fp);	//Store Allocated credit the data in output file
	for(i = 0; i < p; i++)
	{
	for( j = 0; j < r; j++)
	fprintf(fp,"%d\t",alloc[i][j]);
	}
	
/* Cheacking for wheather system is safe or not */
process = -1;
	for(i = 0; i < p; i++)
	{
		if(completed[i] == 0)	        
		{
		   process = i ;
		    for(j = 0; j < r; j++)	
		    {
		     if(avail[j] < need[i][j])
		     {
		      process = -1;
		      break;
		     }	
		    }
	        }
        if(process != -1)
        break;
        }
 
	if(process != -1)
	{
	safeSequence[count] = process + 1;
	count++;
		for(j = 0; j < r; j++)
		{
		avail[j] += alloc[process][j];
		alloc[process][j] = 0;
		Max[process][j] = 0;
		completed[process] = 1;
		}
	}
}while(count != p && process != -1);

if(count == p)
{
printf("\nThe system is in a safe state and all the client can allocated!!\n");
}
else
printf("\nAll the client cannot be allocated!!\n");
fclose(fp);
}
