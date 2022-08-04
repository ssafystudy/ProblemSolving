import java.util.*;

class H인덱스 {
    public int solution(int[] citations) {
        int answer = 0;
        Arrays.sort(citations);
        int N = citations.length;
        for(int i = 0 ; i < N ; i++	) {
        	if(citations[i] >= N - i) {
        		answer = N - i;
        		break;
        	}
        }
        
        return answer;
    }
}