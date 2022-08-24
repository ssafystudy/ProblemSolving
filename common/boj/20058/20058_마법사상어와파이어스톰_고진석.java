import java.io.*;
import java.util.*;

public class Main2 {
	static int N, Q, size, sum = 0;
	static int[][] map;
	static int[] dx = {0, 0, -1, 1}, dy = {1, -1, 0, 0};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		size = 1 << N;
		map = new int[size][size];
		for(int i = 0 ; i < size ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < size ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				sum += map[i][j];
			}
		}

		st = new StringTokenizer(br.readLine());
		while(st.hasMoreTokens()) {
			int level = Integer.parseInt(st.nextToken());

			if(level > 0)
				for(int i = 0 ; i < 1 << (N - level) ; i++)
					for(int j = 0 ; j < 1 << (N - level) ; j++)
						rotate(i * (1 << level), j * (1 << level), level);

			reduce();
		}

		boolean[][] visited = new boolean[size][size];
		for(int i = 0 ; i < size ; i++)
			for(int j = 0 ; j < size ; j++)
				if(map[i][j] == 0)
					visited[i][j] = true;

		int max = 0;
		while(true) {
			int x = -1, y = -1;
			for(int i = 0 ; i < size ; i++)
				for(int j = 0 ; j < size ; j++)
					if(!visited[i][j]) {
						x = i;
						y = j;
					}
			if(x == -1)
				break;

			Queue<Point> q = new ArrayDeque<>();
			q.offer(new Point(x, y));
			visited[x][y] = true;
			int cnt = 0;
			while(!q.isEmpty()) {
				Point cur = q.poll();
				cnt++;
				for(int i = 0 ; i < 4 ; i++) {
					int nx = cur.x + dx[i];
					int ny = cur.y + dy[i];
					if(isIn(nx, ny) && !visited[nx][ny]) {
						q.offer(new Point(nx, ny));
						visited[nx][ny] = true;
					}
				}
			}
			max = Math.max(max, cnt);
		}

		bw.write(sum + "\n");
		bw.write(max + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static boolean isIn(int x, int y) {
		return x >= 0 && x < size && y >= 0 && y < size;
	}

	public static void rotate(int x, int y, int n) {
		int l = 1 << n;
		int[][] tmp = new int[l][l];
		for(int i = 0 ; i < l ; i++)
			for(int j = 0 ; j < l ; j++)
				tmp[i][j] = map[x + l - 1 - j][y + i];

		for(int i = 0 ; i < l ; i++)
			System.arraycopy(tmp[i], 0, map[x + i], y, l);
	}

	public static void reduce() {
		boolean[][] chk = new boolean[size][size];

		for(int i = 0 ; i < size ; i++)
			for(int j = 0 ; j < size ; j++) {
				if(map[i][j] == 0)
					continue;
				int cnt = 0;
				for(int d = 0 ; d < 4 ; d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];
					if(isIn(nx,ny) && map[nx][ny] > 0)
						cnt++;
				}
				if(cnt < 3)
					chk[i][j] = true;
			}
		for(int i = 0 ; i < size ; i++)
			for(int j = 0 ; j < size ; j++)
				if(chk[i][j]) {
					map[i][j]--;
					sum--;
				}
	}
}
