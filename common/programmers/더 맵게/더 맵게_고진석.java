import java.util.*;

class 더맵게 {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i = 0 ; i < scoville.length ; i++)
        	pq.add(scoville[i]);
        
        while(true) {
        	if(pq.size() == 1) {
        		if(pq.poll() < K)
        			answer = -1;
        		break;
        	}
        		
        	int first = pq.poll();
        	int second = pq.poll();
        	
        	if(first == 0 && second == 0) {
        		answer = -1;
        		break;
        	}
        	
        	if(first >= K)
        		break;
        	
        	pq.add(first + second * 2);
        	answer++;
        }
        
        return answer;
    }
}