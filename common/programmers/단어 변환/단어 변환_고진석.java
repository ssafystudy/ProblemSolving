import java.util.*;

class 단어변환_고진석 {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        
        Queue<Pair> q = new ArrayDeque<>();
        
        q.offer(new Pair(begin, 0));
        
        while(!q.isEmpty()) {
        	Pair cur = q.poll();
        	
        	if(cur.str.equals(target)) {
        		int cnt = 0;
        		int visit = cur.visit;
        		for(int i = 0 ; i < words.length ; i++) {
        			if((visit & 1) == 1)
        				cnt++;
        			visit >>= 1;
        		}
        		answer = cnt;
        		break;
        	}
        	
        	for(int i = 0 ; i < words.length ; i++) {
        		if((cur.visit & 1<<i) != 1<<i && isNext(cur.str, words[i])) {
        			q.offer(new Pair(words[i], cur.visit | 1<<i));
        		}
        	}
        }
        
        return answer;
    }
    
    public boolean isNext(String s1, String s2) {
    	int same = 0;
    	for(int i = 0 ; i < s1.length() ; i++)
    		if(s1.charAt(i) == s2.charAt(i))
    			same++;
    	if(s1.length() - same == 1)
    		return true;
    	return false;
    }
    
    class Pair{
    	String str;
    	int visit;
    	
    	public Pair(String str, int visit) {
    		this.str = str;
    		this.visit = visit;
    	}
    }
}