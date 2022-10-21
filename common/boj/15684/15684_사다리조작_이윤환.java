import java.io.*;
import java.util.*;

public class Main {

    static int N, M, H;
    static int[][] ladder;
    private static boolean isPossible;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        H = Integer.parseInt(st.nextToken());

        ladder = new int[H][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            ladder[a][b] = 1;
            ladder[a][b + 1] = -1;
        }

        for (int i = 0; i <= 3; i++) {
            isAllMatch(0, i);
            if (isPossible) {
                System.out.println(i);
                return;
            }
        }
        System.out.println(-1);
    }

    static void isAllMatch(int depth, int limit) {
        if (isPossible) {
            return;
        }

        if (depth == limit) {
            int i = 0, j = 0;
            for (i = 0; i < N; i++) {
                int start = i;
                for (j = 0; j < H; j++) {
                    start += ladder[j][start];
                }
                if (start != i) break;
            }

            if (i == N && j == H) {
                isPossible = true;
            }

            return;
        }

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N - 1; j++) {
                if (ladder[i][j] == 0 && ladder[i][j + 1] == 0) {
                    ladder[i][j] = 1;
                    ladder[i][j + 1] = -1;
                    isAllMatch(depth + 1, limit);
                    ladder[i][j] = 0;
                    ladder[i][j + 1] = 0;
                }
            }
        }
    }
}