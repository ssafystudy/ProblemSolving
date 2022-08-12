import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int answer = -1;
        int cnt = 0;
        
        for(int sc: scoville){
            pq.offer(sc);
        }
        
        while(!pq.isEmpty()){
            int a = pq.poll();
            if(a >= K){
                answer = cnt; break;
            }
            if(!pq.isEmpty()){
                int b = pq.poll();
                pq.offer(a + b*2);
                cnt++;
            }else break;
        }
        return answer;
    }
}