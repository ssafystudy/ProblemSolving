
import java.util.*;

public class Solution {
	static PriorityQueue<Integer> pq = new PriorityQueue<>();
	public static void main(String[] args) {
		int[] scoville = {1,2,3,9,10,12};
		int K = 7;
		System.out.println(solution(scoville, K));

	}
	
	public static int solution(int[] scoville, int K) {
        int answer = 0;
        
        for (int s : scoville) {
			pq.add(s);
		}
        
        while(pq.peek() < K && pq.size() > 0) {
        	int first = pq.poll();
            if (pq.size() == 0) {
                return -1;
            }
        	int second = pq.poll();
        	
        	int newScoville = first + second * 2;
        	pq.add(newScoville);
        	answer++;
        }
        
        if (pq.peek() > K) {
        	return answer;
        } else if (pq.size() == 0) {
        	return -1;
        }
        
        
        
        
        return answer;
    }

	
	
}
