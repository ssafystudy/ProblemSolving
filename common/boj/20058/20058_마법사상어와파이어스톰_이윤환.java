import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int Q;
    static int L;
    static int[][] board;
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        int size = (int) Math.pow(2, N);
        board = new int[size][size];
        for (int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < size; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            L = (int) Math.pow(2, Integer.parseInt(st.nextToken()));
            dac(L, 0, 0, size);
            melt(size);
        }

        boolean[][] visited = new boolean[size][size];

        int max = 0;
        int totalIce = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                totalIce += board[i][j];
                if (!visited[i][j] && board[i][j] != 0) {
                    Queue<int[]> q = new ArrayDeque<>();
                    q.add(new int[]{i, j});
                    visited[i][j] = true;
                    int sum = 0;
                    while (!q.isEmpty()) {
                        int[] current = q.poll();
                        sum++;
                        for (int d = 0; d < 4; d++) {
                            int nr = current[0] + dr[d];
                            int nc = current[1] + dc[d];
                            if (isValid(size, nr, nc) && !visited[nr][nc] && board[nr][nc] != 0) {
                                visited[nr][nc] = true;
                                q.add(new int[]{nr, nc});
                            }
                        }
                    }
                    max = Math.max(max, sum);
                }
            }
        }
        System.out.println(totalIce + "\n" + max);
    }

    private static void melt(int size) {
        int[][] temp = new int[size][size];
        for (int j = 0; j < size; j++) {
            for (int k = 0; k < size; k++) {
                int iceSum = 0;
                for (int d = 0; d < 4; d++) {
                    int nr = j + dr[d];
                    int nc = k + dc[d];
                    if (isValid(size, nr, nc) && board[nr][nc] != 0) iceSum++;
                }
                if (iceSum < 3) temp[j][k] = 1;
            }
        }

        for (int j = 0; j < size; j++) {
            for (int k = 0; k < size; k++) {
                if (board[j][k] > 0) {
                    board[j][k] -= temp[j][k];
                }
            }
        }
    }

    static boolean isValid(int size, int r, int c) {
        return r >= 0 && r < size && c >= 0 && c < size;
    }

    private static void dac(int H, int startR, int startC, int N) {
        if (H == N) {
            rotate(startR, startC, H);
            return;
        }
        int nextN = N / 2;
        dac(H, startR, startC, nextN);
        dac(H, startR + nextN, startC, nextN);
        dac(H, startR, startC + nextN, nextN);
        dac(H, startR + nextN, startC + nextN, nextN);
    }

    private static void rotate(int r, int c, int l) {

        int[][] temp = new int[l][l];

        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
                temp[i][j] = board[r + l - j - 1][c + i];
            }
        }

        for (int i = 0; i < l; i++) {
            System.arraycopy(temp[i], 0, board[r + i], c, l);
        }
    }
}
