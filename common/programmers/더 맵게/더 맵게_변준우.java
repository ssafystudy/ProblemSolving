import java.util.PriorityQueue;

class Solution {
    public static int solution(int[] scoville, int K) {
		int answer = 0;

		PriorityQueue<Integer> pq = new PriorityQueue<>();

		for(int itr : scoville)
			pq.offer(itr);

		while(pq.size() >= 2)
		{
			if(pq.peek() >= K)
				break;

			pq.offer(pq.poll() + (pq.poll() * 2));
			answer++;
		}

		if(pq.peek() < K)
			return -1;

		return answer;
	}
}