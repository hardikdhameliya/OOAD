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
	int A[] = {12,1,3,10};
	merge_sort(A, 0, 3);
	int n = sizeof(A)/sizeof (int);
	int tower=0;
	int miles_covered=0;
	for (int i=0;i<n;i++)
		cout<< A[i] << " " ;
	cout<<endl;
	
	for (int i=0;i<n;i++){
		if(A[i]>miles_covered){
			miles_covered=A[i]+2;
			tower+=1;
		}
	}
	cout<<"number of tower " << tower <<endl;
}