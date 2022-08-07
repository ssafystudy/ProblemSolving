import java.util.*;

class Solution_다리를지나는트럭 {
	
	public static void main(String[] args) {
		//2	10	[7,4,5,6]	8
		int bridge_length = 100;
		int weight = 100;
		int[] truck_weight = {10,10,10,10,10,10,10,10,10,10};
		
		System.out.println(solution(bridge_length, weight, truck_weight));
	
	}
    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> queue = new LinkedList<>();
        
        int cnt = 0;
        int time = 0;
        
        //초기화
        for (int i = 0; i < bridge_length; i++) {
        	queue.add(0);
        }
        
        //트럭이 올라갈 수 없으면 0을 넣어주면서 진행

        int tmp;
        int idx = 0; 
        while (true) {
        	time++;
        	tmp = queue.poll();
        	if (tmp != 0) cnt++;
        	if (cnt == truck_weights.length) {
        		return time;
        	} else if (idx < truck_weights.length) {
        		if (sumWeight(queue) + truck_weights[idx] > weight) {
        			queue.add(0);
        		} else {
        			queue.add(truck_weights[idx]);
        			idx++;
        		}
        	}
        	
        }
        
        
        
 
    }
    
    
    
    public static int sumWeight(Queue<Integer> queue) {
    	int sum = 0;
    	for (Integer i : queue) {
			sum += i;
		}
    	return sum;
    }
}