import java.util.Arrays;

public class Solution {
	public static int[] solution(int[] progresses, int[] speeds) {
        int[] answer = new int[progresses.length]; 
        int idx = 0;
 
        int max = (int) Math.ceil((100.0 - progresses[0]) / speeds[0]);
        int cnt = 1;
        
        for (int i = 1; i < progresses.length; i++) {
        	int now = (int) Math.ceil((100.0 - progresses[i]) / speeds[i]);
        	
        	if (now <= max) {
        		cnt++;
        	} else {
        		answer[idx++] = cnt; //ver.1
        		cnt = 1;
        		max = now;
        	}
        }
        
        answer[idx++] = cnt; //마지막 원소 
        
        return Arrays.copyOfRange(answer, 0, idx); 
        
    }
}
