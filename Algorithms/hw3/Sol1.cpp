#include <iostream>
#include <algorithm>
using namespace std;


int string_comparison(string longest, string shortest)
{
	int length=0;

	for (int i = 0; i < shortest.size(); ++i)
	{
		if(shortest[i]!='\0' && longest[i]!='\0')
			if(shortest[i]==longest[i])
				length++;
		
		
			
	}
	return length;
}

int string_matching_prob1(string small, string large)
{
	int length=0;
	int maxlenght=0;
	string substring_l;
	string substring_s;
	for (int l = 0; l < large.size(); ++l)
	{
		for (int i = 1; i <= large.size()-l; ++i)
		{
			substring_l=large.substr(l, i);
			for (int s = 0; s < small.size(); ++s)
			{
				for (int j = 1; j <= small.size()-s; ++j)
				{
				substring_s=small.substr(s, j);
				length=string_comparison(substring_l, substring_s);
				if(length>=maxlenght)
                    maxlenght=length;
				length=0;
				}
			}			
		}
	}
return maxlenght;
}
