#include <iostream>
#include <algorithm>
#include<string>
#define N 6
#define D 2
#define NEG_INFINITY -11000000
using namespace std;

int towers[N];				//stores sequences of tower placement
int ans[N];				    //Stores total income

typedef struct{
	int distance;
	int income;
}hs;

// Assume house location are sorted in non decreasing order
hs house[N]={{1,8},{12,9},{13,18},{14,19},{15,18},{16,9}};

int max( int a, int b){
	return ( a>b) ? a:b; 
}

/*This function will calculate placement of tower with maximum total income
 *with separation of D miles. If other tower is within D miles else part  
 *looks for maximum income house and replace old tower placement.
*/


int tplacement(){

	int j=0;
	int k=0;
	towers[j]=0;
	ans[k]=house[j].income;
	for(int n=1; n< N ; n++){
		if(house[n].distance-house[towers[j]].distance>=D){
			ans[n]= ans[n-1]+house[n].income;
			cout<< " tower is placed at " << house[n].distance << endl;
			towers[++j]=n;
		}
		else{
			for(k = n-1; house[k].distance < house[towers[j]].distance+D; --k){
				ans[n]=max(ans[n-1],house[n].income+ans[k]);
				if(ans[n]!= ans[n-1] ){
					cout<< " tower is changed to " << house[n].distance << endl;
					towers[j]=n;
				}
			}
		}
	}
	
return ans[N-1];
	
}

/*Initialize towers with NEG_INFINITY
*/
void init(){
	for(int n=0; n< N ; n++){
		ans[n]=NEG_INFINITY;
	}	
}

int main(){

	init();
	cout<<" maximum income is " << tplacement() << endl;
}