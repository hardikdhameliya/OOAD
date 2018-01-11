#include <iostream>
#include <algorithm>
using namespace std;

template <class Item>
void merge(Item A[], int left, int mid, int right)
{
	int n = right - left + 1;
	Item temp[n];
	int i(left), j(mid+1), k;

	for (k = 0; k < n; ++k)
	{
		if (i > mid || (j <= right && A[j] < A[i]))
			temp[k] = A[j++];
		else
			temp[k] = A[i++];
	}
	copy(temp, temp+n, A+left);
}

template <class Item>
void merge_sort(Item A[], int left, int right)
{
	if (left < right)
	{
		int mid = (left + right) / 2;
		merge_sort(A, left, mid);
		merge_sort(A, mid+1, right);
		merge(A, left, mid, right);
	}
}

int main()
{
	int A[] = {60, 1, 50, 87, 58, 32, 12, 95};  
  	int n = sizeof(A)/sizeof (int);
  	merge_sort(A, 0, n-1);
	int temp=0;
	
	cout<< "Execution order " <<endl;
	for (int i=0;i<n;i++)
		cout<< A[i] << " " ;
	cout<<endl;
	return 0;
}