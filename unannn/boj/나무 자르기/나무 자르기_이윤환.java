import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] trees = new int[N];
        for (int i = 0; i < trees.length; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
        }

        long start = 0;
        long end = 0;
        for (int i = 0; i < N; i++) {
            end = Math.max(end, trees[i]);
        }

        long answer = 0;
        while (start <= end) {
            long sum = 0;
            long H = (start + end) / 2;
            for (int i = 0; i < N; i++) {
                if (trees[i] > H) {
                    sum += trees[i] - H;
                }
            }
            if (sum < M) {
                end = H - 1;
            } else {
                answer = H;
                start = H + 1;
            }
        }
        System.out.println(answer);
    }
}