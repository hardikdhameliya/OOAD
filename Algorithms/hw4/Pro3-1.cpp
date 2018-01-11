#include <iostream>
#include <algorithm>
using namespace std;


void merge3(int A[], int lo, int mid1, int mid2, int hi)
{

	int n = hi - lo + 1;
	int temp[n];
	int i=lo;
	int j=mid1+1;
	int k=mid2+1;
	mid1=mid1+1;
	mid2=mid2+1;
	hi=hi+1;
	int l;
	
	for (l = 0; l < n; ++l)
	{
		
		if(i < mid1 && (j >= mid2 || A[i] < A[j]))
			if(k >= hi || A[i] < A[k])
				temp[l]=A[i++];
			else
				temp[l]=A[k++];	
		else if(j < mid2 && (k >= hi || A[j] < A[k]))
				temp[l]=A[j++];
			else
				temp[l]=A[k++];	
		
	}
	copy(temp, temp+n, A+lo);
}
