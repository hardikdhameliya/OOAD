#include <iostream>
#include <algorithm>
using namespace std;


void merge_sort_3(int A[], int left, int right)
{
	int mid1 = (right - left) / 3;
	int mid2=(2*(right - left)) / 3;
	if((right-left)<3){
		merge3(A, left, left+mid1,left+mid2, right);
		return;
	}
	merge_sort_3(A, left,left+mid1);
	merge_sort_3(A, left+mid1+1, left+mid2);
	merge_sort_3(A, left+mid2+1, right);
	merge3(A, left, left+mid1,left+mid2, right);
	
}

void sort()
{
  int A[] = {1,5,9,2,4,5,69};
  int n = sizeof(A)/sizeof (int);
  merge_sort_3(A,0,n-1);
  
  for (int i;i<(n-1);i++)
    cout<<" " << A[i] ;
  cout<<endl;	

}

int main()
{
  sort();

}  
