import java.util.*;

class 다지트_고진석 {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> q = new ArrayDeque<>();
        int res = 0;
        int curW = 0;
        int idx = 0;
        int targetW = 0;
        
        while(true) {
        	res++;
        	if(q.size() == bridge_length) {
        		targetW = q.poll();
        		curW -= targetW;
        	}
        	targetW = truck_weights[idx];
        	if(curW + targetW <= weight) {
        		q.offer(targetW);
        		curW += targetW;
        		idx++;
        		if(idx == truck_weights.length)
        			return res + bridge_length;
        	}
        	else {
        		q.offer(0);
        	}
        		
        }
    }
}