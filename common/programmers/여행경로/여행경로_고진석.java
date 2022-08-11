import java.util.*;

class 여행경로_고진석 {
	static int N, M = 0;
	static boolean[] isVisited;
	static List<String> res = new ArrayList<>();
	
    public String[] solution(String[][] tickets) {
        N = tickets.length;
        isVisited = new boolean[N];
        dfs("ICN", tickets);
        
        Collections.sort(res);
        return res.get(0).split(" ");
    }
    
    public void dfs(String path, String[][] tickets) {
    	if(path.length() > 4 * N) {
    		res.add(path);
    	}
    	else {
    		for(int i = 0 ; i < N ; i++) {
    			if(!isVisited[i] && tickets[i][0].equals(path.substring(path.length() - 3))) {
    				isVisited[i] = true;
    				dfs(path + " " + tickets[i][1], tickets);
    				isVisited[i] = false;
    			}
    		}
    	}
    }
}