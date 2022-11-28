import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] loads = new int[N - 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N - 1; i++) {
            loads[i] = Integer.parseInt(st.nextToken());
        }

        int[] minCosts = new int[N];
        st = new StringTokenizer(br.readLine());
        int temp = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            minCosts[i] = Math.min(temp, Integer.parseInt(st.nextToken()));
            temp = minCosts[i];
        }

        long answer = 0l;
        for (int i = 0; i < N - 1; i++) {
            answer += (long) loads[i] * minCosts[i];
        }
        System.out.println(answer);
    }
}