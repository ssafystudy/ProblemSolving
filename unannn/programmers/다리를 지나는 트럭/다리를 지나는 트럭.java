import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {

        int N = truck_weights.length;

        Queue<Integer> waiting = new ArrayDeque<>();
        Queue<int[]> passing = new ArrayDeque<>();
        int passed = 0;

        for (int truck_weight : truck_weights) {
            waiting.add(truck_weight);
        }

        int answer = 0;
        int currentWeight = 0;
        while(passed != N){
            answer++;

            if(!waiting.isEmpty() && passing.size() < bridge_length && currentWeight + waiting.peek() <= weight){
                int w = waiting.remove();
                passing.add(new int[]{w,0});
                currentWeight += w;
            }

            for(int[] t : passing){
                t[1]++;
            }

            if(!passing.isEmpty() && passing.peek()[1] >= bridge_length){
                currentWeight -= passing.remove()[0];
                passed++;
            }
        }
        return ++answer;
    }
}