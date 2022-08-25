import java.util.*;
import java.io.*;

public class Main {

	static int[] dr = { 0, 0, -1, 1 };
	static int[] dc = { 1, -1, 0, 0 };
	static int[][] dice = { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };
	static int[][] map;
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		int[] loc = { x, y };
		for (int i = 0; i < K; i++) {
			int cmd = Integer.parseInt(st.nextToken());
			if (move(loc, cmd)) {
				sb.append(dice[1][1]).append('\n');
			}

		}
		System.out.println(sb);
	}

	private static boolean move(int[] loc, int direction) {
		int nr = loc[0] + dr[direction - 1];
		int nc = loc[1] + dc[direction - 1];

		if (nr < 0 || nr >= N || nc < 0 || nc >= M)
			return false;
		loc[0] = nr;
		loc[1] = nc;

		rotate(direction);
		if (map[loc[0]][loc[1]] == 0) {
			map[loc[0]][loc[1]] = dice[3][1];
		} else {
			dice[3][1] = map[loc[0]][loc[1]];
			map[loc[0]][loc[1]] = 0;
		}
		return true;
	}

	private static void rotate(int direction) {
		int temp;
		switch (direction) {
		case 1: // 동
			temp = dice[1][2];
			dice[1][2] = dice[1][1];
			dice[1][1] = dice[1][0];
			dice[1][0] = dice[3][1];
			dice[3][1] = temp;
			break;

		case 2: // 서
			temp = dice[1][0];
			dice[1][0] = dice[1][1];
			dice[1][1] = dice[1][2];
			dice[1][2] = dice[3][1];
			dice[3][1] = temp;
			break;

		case 3: // 남
			temp = dice[3][1];
			for (int i = 3; i > 0; i--) {
				dice[i][1] = dice[i - 1][1];
			}
			dice[0][1] = temp;
			break;

		case 4: // 북
			temp = dice[0][1];
			for (int i = 0; i < 3; i++) {
				dice[i][1] = dice[i + 1][1];
			}
			dice[3][1] = temp;
			break;

		}
	}
}