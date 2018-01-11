#include <iostream>
#include <algorithm>
using namespace std;

int count( int a[],int value,int lo,int hi)
{
int freq=0;
int n=hi-lo+1;
	for(int i=0;i<n;i++)
	{	if(a[i]==value)
			freq++;
	}
	return freq; 
}

int repeat(int a[],int lo, int hi)
{
	int output;
	int output_left=0;
	int output_right=0;
	int n=hi-lo+1;
	if(lo==hi)
		return a[lo];
	if(lo<hi)
	{
		int mid=(lo+hi)/2;
		int left=repeat(a,lo, mid);
		output_left=count(a,left,lo,hi);
		int right=repeat(a,mid+1, hi);
		output_right=count(a,right,lo,hi);
		output=(output_left>output_right)?left:right;
	}
	return output;
}

int morethanhalf(int a[],int n)
{
	int size = n+1;
	int number=repeat(a,0,n);
	if(count(a,number,0,n)>size/2)
		return number;
	else
		return 0;	
}

int main()
{
int a[]={1,1,3,5,1,5,5,5,7,6};
int n = sizeof(a)/sizeof (int);
int num1=morethanhalf(a,n-1);
if(num1!=0) 
    cout<<"number that repeats more than n/2 is " << num1 <<endl;
else    
    cout<<"no number repeats more than n/2 "<< endl
return 0;
}