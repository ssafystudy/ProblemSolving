import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int answer = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int temp = Integer.parseInt(st.nextToken());
            boolean isPrime = true;
            for (int j = 2; j <= Math.sqrt(temp); j++) {
                if (temp % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            answer += isPrime && temp >= 2 ? 1 : 0;
        }
        System.out.println(answer);
    }
}