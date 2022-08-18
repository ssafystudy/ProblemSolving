import java.util.PriorityQueue;

class Solution {
    public static int solution(int[] scoville, int K) {
		int answer = 0;
        
		PriorityQueue<Integer> pq = new PriorityQueue<>();

		for(int itr : scoville)
			pq.offer(itr);
        
		while(!pq.isEmpty() && pq.size() >= 2)
		{
			if(pq.peek() >= K)
				break;
			
			int first = pq.poll();
			int second = pq.poll();
            
			pq.offer(first + (second * 2));
			answer++;
		}
		
		if(pq.peek() < K)
			return -1;

		return answer;
	}
}