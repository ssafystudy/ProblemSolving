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
        
        /*Arrays.sort(phone_book);
        for(int i=0;i<phone_book.length;i++)
        {
            for(int j=i;j<phone_book.length;j++)
            {
                if(phone_book[i].equals(phone_book[j]))
                    continue;

                if(phone_book[j].length() < phone_book[i].length())
                    continue;

                if(phone_book[j].substring(0, phone_book[i].length()).equals(phone_book[i]))
                    answer = false;
                }
            }*/
        
        return answer;
    }
}