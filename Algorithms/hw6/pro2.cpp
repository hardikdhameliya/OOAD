#include <iostream>
#include <algorithm>
#include<string>
#define DENO 3			//Number of Denomination 
#define TOTAL 4			//Total Sum which we want to generate
#define INFINITY 1000000	
#define NEG_INFINITY -10000
using namespace std;

int d[DENO]={1,3,5};		//Given Denomination
int a[DENO]={7,3,1};		//Available Number of coins
int table[DENO][TOTAL+1];	//Array to store the previous results

int min(int a,int b)
{
	return (a<b)?a:b;
}

//Search for minimum number in last column of table
int search(){
int minimum=INFINITY;
for( int j=0;j<DENO;j++){
	if(table[j][TOTAL]<minimum)
		minimum=table[j][TOTAL];
}			
return minimum;	
}

//Lookup for previous store values in table
int lookup( int column, int row)
{
int mini=NEG_INFINITY;
if(column==0)
	return 0;
if(row==0)
	return mini;
else
	return table[row-1][column];
}


int cg(){

int temp=0;
int r,j,i;
for(j=0;j<DENO;j++){
	for(i=0;i<TOTAL+1;i++){
		table[j][i]=INFINITY;
		}
}
/** For each denomination, this loop calculates smallest number of coins for 
    sub TOTAL using previous stored value in table
**/
for(j=0;j<DENO;j++){
	table[j][0]=0;
	for(r=1;r<=a[j];r++){
		for(i=1;i<=TOTAL;i++){	
			if(i-r*d[j]>=0){
			table[j][i]=min(table[j][i],r+lookup(i-r*d[j],j)); 
				if(table[j][i]<0){
					table[j][i]=INFINITY;
					break;	
				}
			}
		}
	}		
}

//Print the result of table
for( int j=0;j<DENO;j++)
	for(int i=0;i<TOTAL+1;i++){
		cout<< " table[" << j << "][" << i << "]="<< table[j][i] << endl;
	}
return search();
}

int main()
{
cout<< "smallest number of coins are " << cg() <<endl;
}
