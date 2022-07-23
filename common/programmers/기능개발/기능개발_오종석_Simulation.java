import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> arr = new ArrayList();
        
        int tMaxDay = -1, cnt = 0;
        for(int i =0; i<speeds.length; i++){
            int tDay = (100 - progresses[i] + speeds[i] -1) / speeds[i];
            if(i == 0) {tMaxDay = tDay; cnt = 1; continue;}
            if(tMaxDay >= tDay){
                cnt++;
            }else{
                arr.add(cnt);
                cnt = 1;
                tMaxDay = tDay;
            }
        }
        arr.add(cnt);
        
        int[] answer = arr.stream()
                        .mapToInt(i -> i)
                        .toArray();
        return answer;
        // 쬐끔 더 빠름
    }
}