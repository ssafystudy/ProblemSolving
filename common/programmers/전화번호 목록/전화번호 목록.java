import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
		        
        Map<String, Integer> phoneNumbersMap = new HashMap<String, Integer>(); // int is reserved value
        
        for(String itr : phone_book)
            phoneNumbersMap.put(itr, 0);
        
        for(String itr : phone_book)
        {
        	int len = itr.length();
        	
        	for(int i=1; i<len;i++)
        	{
        		if(phoneNumbersMap.containsKey(itr.substring(0,i)))
        		{
        			answer = false;
        		}
        	}
        }
        
        return answer;
    }
}