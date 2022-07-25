package leehyeji319.programmers.위장;

import java.util.HashMap;

public class 위장 {
    
    public static void main(String[] args) {
        String[][] clothes = {{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}};
        System.out.println(Arrays.toString(solution(clothes)));
    }


    static class Solution {
        public int solution(String[][] clothes) {
            int answer = 0;

            HashMap<String, Integer> kinds = new HashMap<>();

            for (int i = 0; i < clothes.length; i++) {
                if (kinds.containsKey(clothes[i][1])) {
                    kinds.put(clothes[i][1], kinds.get(clothes[i][1]) + 1);
                }
                else {
                    kinds.put(clothes[i][1], 1);
                }
            }
            
            answer = 1;

            for (int i : kinds.values()) {
                answer *= i + 1; //안입는경우 + 1
            }

            //옷 하나도 안입는 경우 빼
            return --answer;
        }
    }
}
