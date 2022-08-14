import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        Queue<Integer> heap = new PriorityQueue<>();
        for (Integer s : scoville) {
            heap.add(s);
        }

        Integer first = null, second = null;
        //poll()은 힙이 비어있을 때 null 반환
        while ((first = heap.poll()) != null && (second = heap.poll()) != null) {
            heap.add(first + second * 2);
            answer++;
            if (heap.peek() >= K) {
                return answer;
            }
        }
        return -1;
    }
}