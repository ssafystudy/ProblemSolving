import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int[] modCounts = new int[M];
        int current = 0;
        for (int i = 0; i < N; i++) {
            current += Integer.parseInt(st.nextToken());
            current %= M;
            modCounts[current]++;
        }

        long answer = modCounts[0];
        for (int i = 0; i < M; i++) {
            answer += (long) modCounts[i] * (modCounts[i] - 1) / 2;
        }
        System.out.println(answer);
    }
}