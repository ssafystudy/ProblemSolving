import java.util.*;

class 단속카메라 {
    public int solution(int[][] routes) {
    	int N = routes.length;
    	Integer[][] arr = new Integer[N][2];
    	for(int i = 0 ; i < N ; i++) {
    		arr[i][0] = routes[i][0];
    		arr[i][1] = routes[i][1];
    	}
    	Arrays.sort(arr, (a, b) -> a[0] - b[0]);
    	
        int answer = 0;
    	for(int i = 0 ; i < N ; i++) {
    		answer++;
    		int end = arr[i][1];
    		
    		for(int j = i + 1 ; j < N ; j++) {
    			int nStart = arr[j][0];
    			int nEnd = arr[j][1];
    			if(nStart > end) {
    				i = j - 1;
    				break;
    			}
    			if(nEnd < end)
    				end = nEnd;
				i = j;
    		}
    	}
    	
        return answer;
    }
}