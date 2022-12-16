import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long[] arr = new long[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        Arrays.sort(arr);

        long answer = arr[0];
        int answerCount = 0;

        long tmp = arr[0];
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (tmp == arr[i]) {
                count++;
            } else {
                if (count > answerCount) {
                    answer = arr[i - 1];
                    answerCount = count;
                }
                count = 1;
                tmp = arr[i];
            }
        }
        if (count > answerCount) {
            answer = arr[N - 1];
            answerCount = count;
        }
        System.out.println(answer);
    }
}