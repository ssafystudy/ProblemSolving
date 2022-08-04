import java.util.*;

class 모음사전 {
    String[] dict = new String[10000];
    char[] arr = {' ','A','E','I','O','U'};
    int idx = 0;
    
    public int solution(String word) {
        makeDict("");
        while(word.length() < 5)
        	word += ' ';
        
        for(int i = 0 ; i < idx ; i++)
        	if(dict[i].equals(word))
        		return i + 1;
        
        return -1;
    }
    
    public void makeDict(String str) {
    	if(str.length() == 5) {
    		dict[idx++] = str;
    		return;
    	}
    	if(str.length() == 0)
    		for(int i = 1 ; i < 6 ; i++)
    			makeDict(str + arr[i]);
    	else if(str.charAt(str.length() - 1) == ' ')
    		makeDict(str + ' ');
    	else
			for(int i = 0 ; i < 6 ; i++)
				makeDict(str + arr[i]);
    }
}