#include <iostream>
#include <algorithm>
using namespace std;


int *suffixArray(string small)
{
int *temp= (int *)calloc(small.size(),sizeof(int));
int index=0;
	for(int h=1;h<small.size();)
	{
		if(small[index]==small[h])
		{
			temp[h]=index+1;
			index++;
			h++;
		}else{
			if(index!=0)
				index=temp[index-1];
	
			else{
				temp[h]=0;
				h++;
			}
		}	
	}
return temp;
}
int kmp_algo(string small, string large)
{
	int *temp=suffixArray(small);
	int maxlenght=0;
	int lenght=0;
	string substring;
	int index=0;	
	int i=0;
	for (int l = 0; l<large.size() && i<small.size(); )
	{	
			if(large[l]==small[i])
			{
				i++;			
				lenght++;
				l++;
			}else{
				if(i!=0)
					i=temp[i-1];
				else{
					l++;
				}
				lenght=0;
			}
	}
return lenght;	
}

int string_matching_prob2(string small, string large)
{
	int length=0;
	int maxlenght=0;
	string substring_l;
	string substring_s;
	for (int s = 0; s < small.size(); ++s)
	{
		for (int j = 1; j <= small.size()-s; ++j)
		{
			substring_s=small.substr(s, j);
			length=kmp_algo(substring_s,large);
			if(length>=maxlenght)
			 maxlenght=length;
			length=0;
		}
	}
return maxlenght;
}	