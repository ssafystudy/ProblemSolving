import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {

        int N = truck_weights.length;

        Queue<Integer> waiting = new ArrayDeque<>();
        //{ 트럭 무게, 다리에 머무른 시간(초)}
        Queue<int[]> passing = new ArrayDeque<>();


        //대기큐에 트럭 담기
        for (int truck_weight : truck_weights) {
            waiting.add(truck_weight);
        }
    
        
        int answer = 0;         // 시간(초)
        int currentWeight = 0;  // 현재 다리를 건너고 있는 트럭의 무게
        int passed = 0;         // 다리를지난 트럭의 개수
        
        //운행 시작!
        while(passed != N){
            
            answer++;
            // 대기 트럭이 존재 && 현재 건너는 트럭이 다리의 길이보다 작음 && 다리를 건너는 트럭 무게 + 대기 트럭 중 맨 앞 트럭의 무게 <= 제한무게
            if(!waiting.isEmpty() && passing.size() < bridge_length && currentWeight + waiting.peek() <= weight){
                int w = waiting.remove();    // 대기 트럭 dequeue
                passing.add(new int[]{w,0}); // 다리를 건너는 트럭에 추가
                currentWeight += w;          // 다리를 건너는 트럭 무게에 무게 추가
            }
            
            //각각 트럭이 다리에 머무는 시간 1초씩 추가
            for(int[] t : passing){
                t[1]++;
            }
            
            
            // 다리를 건너는 조건(다리를 건너는 트럭이 존재 && 건너고 있는 맨앞의 트럭이 다리의 머무른 시간이 다리의 길이 이상일 때
            if(!passing.isEmpty() && passing.peek()[1] >= bridge_length){
                currentWeight -= passing.remove()[0];  //현재 다리 건너는 트럭 무게에서 
                passed++;
            }
        }
        
        return ++answer;
    }
}