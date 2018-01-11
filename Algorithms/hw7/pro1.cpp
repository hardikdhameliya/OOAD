#include <iostream>
#include <algorithm>
#include<string>
#define N 4			//Number of movies	
#define NEG_INFINITY -11000000	
using namespace std;
int table[N];			//Table to store seq
int ans[N];			//To store total reward

typedef struct{
	int start;
	int finish;
	int reward;
}mv; 

// Assume movies are sorted in non decreasing order
mv mov[N]={{1,3,5},{2,3,8},{1,6,6},{3,7,28}};

int max( int a, int b){
	return ( a>b) ? a:b; 
}

/* This function will return non overlapping movies with maximum reward
 *and store the sequences in table. 
*/
int movie(){
	int past_reward=0;	//Store Previous  reward	
	int present_reward=0;	//Store New reward
	int k=0;
	int j=0;
	table[j]=0;
	
	ans[j]=mov[j].reward;
	cout<< " ans[" << j << "]=" << ans[j] <<endl;
	
/*Look for sequences of non-overlapping movies, if movies are overlapping
 * then else part will check for movie with largest reward and store in 
 * the table.
 *
*/	
	for(int n=1; n< N ; n++){
		if(mov[n].start>=mov[table[j]].finish){
			ans[n]=ans[n-1]+mov[n].reward;
			table[++j]=n;
					
		}		
		else{
			for (k = n-1; mov[k].finish > mov[n].start; --k){
				if(k-1<0)
					ans[k-1]=0;
				ans[n] = max(ans[n-1], mov[n].reward + ans[k-1]);
				if(ans[n]!=ans[n-1]){
					table[j]=n;
		      		}
			}
		}
	}
return ans[N-1];
}

/* Print table
*/
void print_table(int *qty){
	for(int n=0; n< N ; n++)
		cout<< " table[" << n << "]=" << qty[n] <<endl;
}

/* Initialize table with NEG_INFINITY
*/
void init(){
	for(int n=0; n< N ; n++){
		table[n]=NEG_INFINITY;
		ans[n]=0;
	}	
}

int main(){
	init();
	cout<<" maximum reward is " << movie() <<endl;
	print_table(table);
	
}