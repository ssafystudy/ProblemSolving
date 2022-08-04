import java.util.*;

class K번째수 {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        int idx = 0;
        for(int[] c : commands) {
        	int start = c[0];
        	int end = c[1];
        	int k = c[2];
        	
        	int N = end - start + 1;
        	int[] arr = new int[N];
        	for(int i = 0 ; i < N ; i++)
        		arr[i] = array[start - 1 + i];
        	Arrays.sort(arr);
        	answer[idx++] = arr[k - 1];
        }
        return answer;
    }
}