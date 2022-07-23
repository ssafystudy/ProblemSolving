import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        Queue<Integer> q = new LinkedList<>();
        
        for(int i =0; i<priorities.length; i++){
            pq.offer(priorities[i]);
            q.offer(i);
        }
        
        int cnt = 1;
        while(true){
            if(pq.peek() == priorities[q.peek()]){
                int index = q.poll();
                if(index == location) return cnt;
                cnt++; pq.poll();
            }else{
                q.offer(q.poll());
            }
        }
    }
}