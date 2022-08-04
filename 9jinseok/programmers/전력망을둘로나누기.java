import java.util.*;

class 전력망을둘로나누기 {
	int N = 0;
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        N = n;
        for(int i = 0 ; i < n - 1 ; i++) {
        	int[][] arr = new int[n - 2][2];
        	int idx = 0;
        	for(int j = 0 ; j < i ; j++)
        		arr[idx++] = wires[j];
        	for(int j = i + 1 ; j < n - 1 ; j++)
        		arr[idx++] = wires[j];
        	answer = Math.min(answer, calc(wires[i][0], wires[i][1], arr));
        }
        
        return answer;
    }
    
    public int calc(int a, int b, int[][] wires) {
    	Set<Integer> aSet = new HashSet<>();
    	Set<Integer> bSet = new HashSet<>();
    	
    	aSet.add(a);
    	bSet.add(b);
    	for(int i = 0 ; i < N ; i++) {
	    	for(int[] wire : wires)
	    		if(aSet.contains(wire[0]) || aSet.contains(wire[1])) {
	    			aSet.add(wire[0]);
	    			aSet.add(wire[1]);
	    		}
    	}
    	for(int i = 0 ; i < N ; i++) {
	    	for(int[] wire : wires)
	    		if(bSet.contains(wire[0]) || bSet.contains(wire[1])) {
	    			bSet.add(wire[0]);
	    			bSet.add(wire[1]);
	    		}
    	}
    	System.out.println(aSet.toString() + bSet.toString());
    	return Math.abs(aSet.size() - bSet.size());
    }
}