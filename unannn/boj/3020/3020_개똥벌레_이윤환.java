import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[][] crash = new int[2][H];
        for (int i = 0; i < N / 2; i++) {
            int bottom = Integer.parseInt(br.readLine());
            int top = Integer.parseInt(br.readLine());
            crash[0][bottom - 1] += 1;
            crash[1][top - 1] += 1;
        }

        int topSum = 0;
        int bottomSum = 0;
        for (int i = H - 1; i >= 0; i--) {
            topSum += crash[0][i];
            crash[0][i] = topSum;

            bottomSum += crash[1][i];
            crash[1][i] = bottomSum;
        }

        int[] results = new int[H];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < H; i++) {
            results[i] = crash[0][i] + crash[1][H - i - 1];
            if (results[i] < min) {
                min = results[i];
            }
        }

        int count = 0;
        for (int i = 0; i < H; i++) {
            if (results[i] == min) {
                count++;
            }
        }

        System.out.println(min + " " + count);
    }
}