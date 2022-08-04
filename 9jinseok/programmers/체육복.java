import java.util.Arrays;

class 체육복 {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        
        Arrays.sort(reserve);
        
        boolean[] arr = new boolean[n + 1];
        for(int i : lost)
        	arr[i] = true;

        for(int i = 0 ; i < reserve.length ; i++)
        	if(arr[reserve[i]]) {
        		arr[reserve[i]] = false;
        		reserve[i] = -1;
        	}
        
        for(int i : reserve) {
        	if(i == -1)
        		continue;
        	if(i - 1 >= 1 && arr[i - 1])
        		arr[i - 1] = false;
        	else if(i + 1 <= n && arr[i + 1])
        		arr[i + 1] = false;
        }
        
        for(int i = 1 ; i <= n ; i++)
        	if(!arr[i])
        		answer++;
        
        return answer;
    }
}