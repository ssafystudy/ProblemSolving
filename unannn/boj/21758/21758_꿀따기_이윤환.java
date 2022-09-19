import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] leftAcc = new int[N];
        int[] rightAcc = new int[N];

        leftAcc[0] = arr[0];
        rightAcc[0] = arr[N - 1];
        for (int i = 1; i < N; i++) {
            leftAcc[i] = arr[i] + leftAcc[i - 1];
            rightAcc[i] = arr[N - i - 1] + rightAcc[i - 1];
        }

        int max = 0;
        int first = leftAcc[N - 1] - leftAcc[0];
        for (int i = 1; i < N; i++) {
            max = Math.max(first - arr[i] + leftAcc[N - 1] - leftAcc[i], max);
        }

        first = rightAcc[N - 1] - rightAcc[0];
        for (int i = 1; i < N; i++) {
            max = Math.max(first - arr[N - i - 1] + rightAcc[N - 1] - rightAcc[i], max);
        }

        for (int i = 1; i < N - 1; i++) {
            max = Math.max(leftAcc[i] - leftAcc[0] + rightAcc[N - i - 1] - rightAcc[0], max);
        }

        System.out.println(max);
    }
}