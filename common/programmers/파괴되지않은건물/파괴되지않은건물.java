import java.util.*;

class Solution {
    public int solution(int[][] board, int[][] skill) {
        int N = board.length;
        int M = board[0].length;

        int[][] logs = new int[N][M];
        for (int[] s : skill) {
            int r1 = s[1], c1 = s[2];
            int r2 = s[3], c2 = s[4];
            int degree = s[5];

            if (s[0] == 1) {
                logs[r2][c2] -= degree;
                if (isValid(N, M, r2, c1 - 1)) logs[r2][c1 - 1] += degree;
                if (isValid(N, M, r1 - 1, c2)) logs[r1 - 1][c2] += degree;
                if (isValid(N, M, r1 - 1, c1 - 1)) logs[r1 - 1][c1 - 1] -= degree;
            } else {
                logs[r2][c2] += degree;
                if (isValid(N, M, r2, c1 - 1)) logs[r2][c1 - 1] -= degree;
                if (isValid(N, M, r1 - 1, c2)) logs[r1 - 1][c2] -= degree;
                if (isValid(N, M, r1 - 1, c1 - 1)) logs[r1 - 1][c1 - 1] += degree;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = M - 1; j > 0; j--) {
                logs[i][j - 1] += logs[i][j];
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = N - 1; j > 0; j--) {
                logs[j - 1][i] += logs[j][i];
            }
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (logs[i][j] + board[i][j] > 0) {
                    answer++;
                }
            }
        }
        return answer;
    }

    private boolean isValid(int N, int M, int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}