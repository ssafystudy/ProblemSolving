import java.util.*;

class 섬연결하기 {
	List<Edge> edgeList = new ArrayList<>();
	
    public int solution(int n, int[][] costs) {
        int N = costs.length;
        Integer[][] arr = new Integer[N][3];
        for(int i = 0 ; i < N ; i++) {
        	arr[i][0] = costs[i][0];
        	arr[i][1] = costs[i][1];
        	arr[i][2] = costs[i][2];
        }
        Arrays.sort(arr, (a, b) -> a[2] - b[2]);
        
        int answer = 0;
        for(Integer[] info : arr) {
        	int start = info[0];
        	int end = info[1];
        	int cost = info[2];
        	
        	if(isLinked(start, end))
        		continue;
        	
        	edgeList.add(new Edge(start, end));
        	answer += cost;
        }
        return answer;
    }
    
    public boolean isLinked(int start, int end) {
    	
    	Set<Integer> set = new HashSet<>();
    	set.add(start);
    	
    	boolean chk = true;
    	while(chk) {
    		chk = false;
    		for(Edge e : edgeList) {
    			if(set.contains(e.start) && !set.contains(e.end)) {
    				set.add(e.end);
    				chk = true;
    				break;
    			}
    			if(!set.contains(e.start) && set.contains(e.end)) {
    				set.add(e.start);
    				chk = true;
    				break;
    			}
    		}
    	}
    	
    	if(set.contains(start) && set.contains(end))
    		return true;
    	else
    		return false;
    	
    }
    
    class Edge{
    	int start;
    	int end;
    	
    	public Edge(int start, int end) {
    		this.start = start;
    		this.end = end;
    	}
    }
}