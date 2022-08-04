import java.util.*;

class 모의고사 {
    public int[] solution(int[] answers) {
    	
    	int a=0, b=0, c=0;
    	
    	for(int i = 0, N = answers.length ; i < N ; i++) {
    		if(i%5 + 1 == answers[i])
    			a++;
    		
    		if(i % 2 == 0) {
    			if(answers[i] == 2)
    				b++;
    		}
    		else {
    			if(i % 8 == 1 && answers[i] == 1)
    				b++;
    			if(i % 8 == 3 && answers[i] == 3)
    				b++;
    			if(i % 8 == 5 && answers[i] == 4)
    				b++;
    			if(i % 8 == 7 && answers[i] == 5)
    				b++;
    		}
    		
    		if((i/2) % 5 == 0 && answers[i] == 3)
    			c++;
    		if((i/2) % 5 == 1 && answers[i] == 1)
    			c++;
    		if((i/2) % 5 == 2 && answers[i] == 2)
    			c++;
    		if((i/2) % 5 == 3 && answers[i] == 4)
    			c++;
    		if((i/2) % 5 == 4 && answers[i] == 5)
    			c++;
    	}
    	
    	int max = Math.max(Math.max(a, b), c);
    	int cnt = 0;
    	if(max == a) cnt++;
    	if(max == b) cnt++;
    	if(max == c) cnt++;
    	
        int[] answer = new int[cnt];
        int idx = 0;
        if(max == a) answer[idx++] = 1;
        if(max == b) answer[idx++] = 2;
        if(max == c) answer[idx++] = 3;
        
    	return answer;
        
        
    }
}