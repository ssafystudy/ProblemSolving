import java.util.ArrayList;

public class 소수찾기_이혜지 {
        static boolean[] visited = new boolean[7]; //7까지라
        static int count = 0;
        static ArrayList<Integer> arr = new ArrayList<>();
    public static void main(String[] args) {
        String numbers = "011";
        System.out.println(solution(numbers));
        
    }
    
    public static int solution(String numbers) {
        String temp = "";
        //몇개뽑아 몇자리수만들건지 반복문
        for (int i = 1; i <= numbers.length(); i++) {
            permutation(numbers, temp, i);
        }
        return count;
    }

    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i <= (int)Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    //조합ㅠㅠ
    public static void permutation(String n, String temp, int len) {
        if (temp.length() == len) {
            //ArrayList에 이미 존재하는값인지 확인해 중복 안되게
            if (!arr.contains(Integer.parseInt(temp))) {
                arr.add(Integer.parseInt(temp));
                if (isPrime(Integer.parseInt(temp))) count++;
            }
            return;
        }

        //n으로 전달된 numbers탐색시작
        for (int j = 0; j < n.length(); j++) {
            //방문처리 확인 
            if(visited[j]) continue;
            //임시변수에 temp붙여나가면서 갯수 조합
            temp += n.charAt(j);
            //temp에 붙인거는 방문처리
            visited[j] = true;
            //재귀, temp변수, 현재 몇자리수만드는지 len변수 전달
            permutation(n, temp, len);
            //조합끝나면 방문처리한거 false로 변경
            visited[j] = false;
            temp = temp.substring(0, temp.length() - 1);
        }

    }
}
