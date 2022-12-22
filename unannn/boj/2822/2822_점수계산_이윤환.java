import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] arr = new int[8][2];
        for (int i = 0; i < 8; i++) {
            arr[i][0] = i + 1;
            arr[i][1] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr, (o1, o2) -> o2[1] - o1[1]);
        arr = Arrays.copyOf(arr, 5);
        int sum = 0;
        for (int i = 0; i < 5; i++) {
            sum += arr[i][1];
        }

        Arrays.sort(arr, (o1, o2) -> o1[0] - o2[0]);
        StringBuilder sb = new StringBuilder();
        sb.append(sum).append('\n');
        for (int i = 0; i < 5; i++) {
            sb.append(arr[i][0]).append(' ');
        }

        System.out.println(sb);
    }
}