import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        
        int[] answer = new int[progresses.length];
        int answerIdx = 0;
        int current = 0;
        while(current != progresses.length){
           
            excute(progresses, speeds);

            int count = 0;
            while(current != progresses.length && progresses[current] >= 100){
                count++;    
                current++;
            }
            
            if(count != 0){
                answer[answerIdx++] = count;
            }
        }

        return Arrays.copyOf(answer, answerIdx);
    }
    
    private void excute(int[] progresses, int[] speeds){
        for(int i = 0; i < progresses.length ; i++){
            progresses[i] += speeds[i];
        }
    }
}