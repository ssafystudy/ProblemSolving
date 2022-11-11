import java.util.*;
import java.io.*;

public class Main {

	private static int[] dr = { 1, 0, -1, 0 };
	private static int[] dc = { 0, 1, 0, -1 };
	private static int M;
	private static int N;
	private static int[][] map;
	private static int[][] visited;

	private static int count;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new int[M][N];
		visited = new int[M][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(dfs(0, 0));
		System.out.println();
	}

	private static int dfs(int r, int c) {
		if (r == M - 1 && c == N - 1) {
			return 1;
		}
		
		int cnt = 0;
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (isValid(nr, nc) && map[nr][nc] != 0 && map[r][c] > map[nr][nc]) {
				if (visited[nr][nc] == 0) {
					cnt += dfs(nr, nc);
				} else if(visited[nr][nc] > 0) {
					cnt += visited[nr][nc];
				}
			}
		}
		visited[r][c] = cnt > 0 ? cnt : -1;
		return cnt;
	}

	private static boolean isValid(int r, int c) {
		return r >= 0 && r < M && c >= 0 && c < N;
	}
}