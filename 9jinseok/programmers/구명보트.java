import java.util.*;

class 구명보트 {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        
        int answer = 0;
        int h = 0, t = people.length - 1;
        while(h <= t) {
        	if(people[t] + people[h] <= limit)
        		h++;
        	t--;
        	answer++;
        }
        
        return answer;
    }
}