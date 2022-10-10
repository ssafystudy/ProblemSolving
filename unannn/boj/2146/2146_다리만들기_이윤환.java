import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] map;
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    static Map<Integer, List<int[]>> islandMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = -Integer.parseInt(st.nextToken());
            }
        }

        int islandCount = 1;
        islandMap = new HashMap<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == -1) {
                    islandMap.put(islandCount, new ArrayList<>());
                    dfs(i, j, islandCount++);
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 1; i < islandCount; i++) {
            List<int[]> A = islandMap.get(i);
            for (int j = 1; j < islandCount; j++) {
                if (i != j) {
                    List<int[]> B = islandMap.get(j);
                    for (int[] a : A) {
                        for (int[] b : B) {
                            int dist = Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]) + 1;
                            min = Math.min(dist, min);
                        }
                    }
                }
            }
        }
        System.out.println(min);
    }

    static void dfs(int r, int c, int islandNumber) {
        map[r][c] = islandNumber;

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (isValid(nr, nc)) {
                if (map[nr][nc] == -1) {
                    dfs(nr, nc, islandNumber);
                } else if (map[nr][nc] != -(islandNumber + 1)) {
                    map[nr][nc] = -(islandNumber + 1);
                    islandMap.get(islandNumber).add(new int[]{nr, nc});
                }
            }
        }
    }

    static boolean isValid(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}