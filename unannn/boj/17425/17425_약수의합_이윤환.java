import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        int[] cases = new int[T];
        int max = 0;
        for (int testCase = 0; testCase < T; testCase++) {
            int temp = Integer.parseInt(br.readLine());
            max = Math.max(temp, max);
            cases[testCase] = temp;
        }

        int[] fArr = new int[max + 1];
        for (int i = 1; i <= max; i++) {
            for (int j = i; j <= max; j += i) {
                fArr[j] += i;
            }
        }

        long[] acc = new long[max + 1];
        for (int i = 1; i <= max; i++) {
            acc[i] = acc[i - 1] + fArr[i];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            sb.append(acc[cases[i]]).append('\n');
        }

        System.out.println(sb);
    }
}