import java.io.*;
import java.util.*;

public class Main {

	static final int[][] nd = { { 0, 0, 1 }, { 0, 0, -1 }, { 0, 1, 0 }, { 0, -1, 0 }, { 1, 0, 0 }, { -1, 0, 0 } };
	static int M, N, H;
	static int[][][] tomatoes;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		Queue<int[]> ripeQueue = new ArrayDeque<>();

		int tomatoNum = 0;
		int ripeNum = 0;
		tomatoes = new int[H][N][M];
		for (int h = 0; h < H; h++) {
			for (int n = 0; n < N; n++) {
				st = new StringTokenizer(br.readLine());
				for (int m = 0; m < M; m++) {
					int t = Integer.parseInt(st.nextToken());
					tomatoes[h][n][m] = t;
					if (t >= 0) {
						tomatoNum++;
					}

					if (t == 1) {
						ripeQueue.add(new int[] { h, n, m });
					}
				}
			}
		}
		ripeNum = ripeQueue.size();
		if (ripeNum == tomatoNum) {
			System.out.println(0);
			return;
		}

		int day = 0;
		while (!ripeQueue.isEmpty()) {
			int size = ripeQueue.size();
			day++;
			while (size-- > 0) {

				int[] curr = ripeQueue.poll();

				for (int i = 0; i < 6; i++) {
					int nh = curr[0] + nd[i][0];
					int nn = curr[1] + nd[i][1];
					int nm = curr[2] + nd[i][2];
					if (isValid(nh, nn, nm) && tomatoes[nh][nn][nm] == 0) {
						tomatoes[nh][nn][nm] = 1;
						ripeNum++;
						if (ripeNum == tomatoNum) {
							System.out.println(day);
							return;
						}
						ripeQueue.add(new int[] { nh, nn, nm });
					}
				}
			}
		}
		if (ripeNum == tomatoNum) {
			System.out.println(day);
		} else {
			System.out.println(-1);
		}
	}

	static boolean isValid(int h, int n, int m) {
		return h >= 0 && h < H && n >= 0 && n < N && m >= 0 && m < M;
	}
}
