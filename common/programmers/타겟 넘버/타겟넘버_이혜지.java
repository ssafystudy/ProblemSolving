import java.util.*;

public class Solution_타겟넘버 {


    public static void main(String[] args) {
      int[] numbers = {1, 1, 1, 1, 1};
      int target = 3;

      System.out.println(solution(numbers, target));
    }
    

    public static int solution(int[] numbers, int target) {
        int answer = 0;
        Stack<Integer> parents = new Stack<>();
        
        parents.add(0);
        //bfs
        for (int i = 0; i < numbers.length; i++) {
          Stack<Integer> child = new Stack<>();
          
          for (int j = 0; j < parents.size(); j++) {
            child.add(parents.get(j) + numbers[i]);
            child.add(parents.get(j) - numbers[i]);
          }


          parents = child;
        }

        for (Integer p : parents) {
          if (p == target) {
            answer++;
          }
        }

        return answer;
    }

}
// 먼저 super node로 [0]을 하나 만들어 두고 

// [0+1] [0-1] 로 sub node 두개를 생성한다

// 이렇게 생성된노드 super node로 저장되고, 

// 저장된 super node는 for문을 돌면서 [0+1+1] [0+1-1] , [0-1+1] [0-1-1] 를 거치면서 super node에 다시 저장된다.