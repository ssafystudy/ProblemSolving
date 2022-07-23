import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> arr = new ArrayList();
        Queue<Integer> q = new LinkedList();
        
        for(int i =0; i<speeds.length; i++){
            int tDay = (100 - progresses[i] + speeds[i] - 1) / speeds[i];
            if(q.isEmpty() || q.peek() >= tDay){
                q.offer(tDay);
            }else{
                arr.add(q.size());
                q.clear(); q.offer(tDay);
            }
        }
        arr.add(q.size());
        int[] answer = arr.stream()
                        .mapToInt(i -> i)
                        .toArray();
        return answer;
    }
}