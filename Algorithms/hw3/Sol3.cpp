#include <iostream>
#include <algorithm>
using namespace std;


typedef struct {
	int lenght;
	string suf;
}suffix;

suffix string_matching_prob3(string small, string large)
{
	suffix newSuffix;
	suffix nullSuffix;
	nullSuffix.lenght=0;
	nullSuffix.suf="";
	suffix oldsuffix1,oldsuffix2;
	
	if(small.size()==0 || large.size()==0)
		return nullSuffix;
	
	if(small[small.size()-1]==large[large.size()-1])
	{	
		oldsuffix1=string_matching_prob3(small.substr(0,small.size()-1), 
                                            large.substr(0,large.size()-1));
	
		if(oldsuffix1.lenght>oldsuffix1.suf.size()+1)	
		{
			newSuffix.lenght=oldsuffix1.lenght;
		}else{
			newSuffix.lenght=oldsuffix1.suf.size()+1;
		}
		newSuffix.suf=oldsuffix1.suf+small[small.size()-1];		
	}
	else{
		
        oldsuffix1= string_matching_prob3(small.substr(0,small.size()), 
                                            large.substr(0,large.size()-1));
		oldsuffix2=string_matching_prob3(small.substr(0,small.size()-1), 
                                            large.substr(0,large.size()));
		if(oldsuffix1.lenght>oldsuffix2.lenght)
			newSuffix=oldsuffix1;
		else
			newSuffix=oldsuffix2;
		newSuffix.suf="";
	}
return newSuffix;
}