#include <iostream>
#include <algorithm>
using namespace std;


int max_value(int a[], int lo, int hi )
{

	if((hi-lo)<=1)
		if(a[hi]>a[lo])
			return a[hi];
		else
			return a[lo];
		
	int mid=(lo+hi)/2;
	int left=max_value(a,lo,mid-1);
	int right=max_value(a,mid,hi);
	if(left>right)
		return left;
	else
		return right;

}
