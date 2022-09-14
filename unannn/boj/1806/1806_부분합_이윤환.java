import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        long answer = Integer.MAX_VALUE;

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        int sum = 0;
        int headIndex = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
            if (sum >= S) {
                while (sum - arr[headIndex] >= S) {
                    sum -= arr[headIndex++];
                }
                answer = Math.min(answer, i - headIndex + 1);
            }
        }
        System.out.println(answer != Integer.MAX_VALUE ? answer : 0);
    }
}