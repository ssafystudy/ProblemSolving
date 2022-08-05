import java.util.*;

class Solution {
    public int solution(int bl, int w, int[] truck_weights) {
        int answer = bl;
        Queue<Truck> q = new LinkedList<>();
        int curw = 0;
        int lastIn = 0; // 맨 앞 차의 나가는 시간보다 맨 뒤 차의 들어온 시간이 클 수 있음
        
        for(int tw: truck_weights){
            if(q.size() < bl && curw + tw <= w){
                curw += tw;
                answer++;
                q.offer(new Truck(tw,answer));
                lastIn = answer - bl;
            }else{
                Truck tt = null;
                while(q.size() >= bl || curw + tw > w){
                    tt = q.poll();
                    curw -= tt.weight;
                }
                answer = Math.max(tt.outTime, lastIn + 1) + bl;
                q.offer(new Truck(tw, answer));
                lastIn = answer - bl;
                curw += tw;
            }
        }
        return answer;
    }
    
    class Truck{
        int weight, outTime;
        public Truck(int w, int t){
            this.weight = w; this.outTime = t;
        }
    }
}
