package common.programmers.기능개발;

import java.util.*;

public class 기능개발_이혜지 {
    
    public static void main(String[] args) {
        int[] progresses = {95, 90, 99, 99, 80, 99};
        int[] speeds = {1, 1, 1, 1, 1, 1};

        System.out.println(Arrays.toString(solution(progresses, speeds)));
    }

    public static int[] solution(int[] progresses, int[] speeds) {

        int [] answer = {};

        ArrayList<Integer> workDay = new ArrayList<>();
        
        ArrayDeque<Integer> spentDay = new ArrayDeque<>();


        int cnt = 0;

        for (int i = 0; i < progresses.length; i++) {
            int remainProgress = 100 - progresses[i];
            
            if (remainProgress % speeds[i] > 0) {
                spentDay.add(remainProgress / speeds[i] + 1);
            } else {
                spentDay.add(remainProgress / speeds[i]);
            }
        }

        int first = spentDay.pop();
        cnt++;

        while (!spentDay.isEmpty()) {
            int work = spentDay.pop();
            if (work > first) {
                workDay.add(cnt);
                first = work;
                cnt = 1;
                continue;
            }
            cnt++;
        }
        
        workDay.add(cnt);

        answer = new int[workDay.size()];

        for (int i = 0; i < workDay.size(); i++) {
            answer[i] = workDay.get(i);
        }

        return answer;
        
    }
    
}
