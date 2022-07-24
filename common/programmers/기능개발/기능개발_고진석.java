import java.util.*;

public class 기능개발_고진석 {
	public static void main(String[] args) {
		int[] progresses = {95, 90, 99, 99, 80, 99};
		int[] speeds = {1, 1, 1, 1, 1, 1};
		
		System.out.println(Arrays.toString(solution(progresses, speeds)));
	}

    public static int[] solution(int[] progresses, int[] speeds) {
        int N = progresses.length;
        int[] answer = new int[N];
        int ansL = 0;
        int idx = 0;
        while( idx < N ){
            int day = (100 - progresses[idx]) / speeds[idx];
            if((100 - progresses[idx]) % speeds[idx] != 0)
                day++;
            int res = 1;
            for(int i = idx + 1 ; i < N ; i++) {
                if(progresses[i] + speeds[i] * day >= 100){
                    res++;
                    idx++;
                }
                else
                    break;
            }
            answer[ansL++] = res;
            idx++;
        }
        
        return Arrays.copyOf(answer,ansL);
    }
}
