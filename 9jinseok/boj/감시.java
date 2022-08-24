import java.io.*;
import java.util.*;

public class 감시 {
	static int N, M, C;
	static int ans;
	static int[][] map;
	static List<CCTV> cctvList;
	static int[] dx = {0, -1, 0, 1}, dy = {1, 0, -1, 0};
					//우 상 좌 하
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ans = N * M;
		
		map = new int[N][M];
		cctvList = new ArrayList<>();
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < M ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] > 0 && map[i][j] < 6)
					cctvList.add(new CCTV(i, j, map[i][j]));
			}
		}
		
		C = cctvList.size();
		int[] dir = new int[C];
		makeDir(0, dir);
		
		bw.write(ans + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void makeDir(int depth, int[] dir) {
		if(depth == C) {
			ans = Math.min(ans, calc(dir));
		}
		else {
			for(int i = 0 ; i < 4 ; i++) {
				dir[depth] = i;
				makeDir(depth + 1, dir);
			}
		}
	}

	static class CCTV {
		int x;
		int y;
		int type;

		public CCTV(int x, int y, int type) {
			this.x = x;
			this.y = y;
			this.type = type;
		}
	}

	public static boolean isIn(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}
	
	public static int calc(int[] dir) {
		int[][] copyMap = new int[N][M];
		for(int i = 0 ; i < N ; i++)
			System.arraycopy(map[i], 0, copyMap[i], 0, M);
		
		for(int i = 0 ; i < C ; i++)
			shoot(copyMap, cctvList.get(i), dir[i]);
		
		int cnt = 0;
		for(int i = 0 ; i < N ; i++)
			for(int j = 0 ; j < M ; j++)
				if(copyMap[i][j] == 0)
					cnt++;
		return cnt;
	}

	private static void shoot(int[][] map, CCTV cctv, int dir) {
		switch(cctv.type) {
			case 1:
				int nx = cctv.x + dx[dir];
				int ny = cctv.y + dy[dir];
				while(isIn(nx,ny)) {
					if(map[nx][ny] == 6)
						break;
					if(map[nx][ny] == 0)
						map[nx][ny] = -1;
					nx += dx[dir];
					ny += dy[dir];
				}
				break;
			case 2:
				nx = cctv.x + dx[dir];
				ny = cctv.y + dy[dir];
				while(isIn(nx,ny)) {
					if(map[nx][ny] == 6)
						break;
					if(map[nx][ny] == 0)
						map[nx][ny] = -1;
					nx += dx[dir];
					ny += dy[dir];
				}
				dir = (dir + 2) % 4;
				nx = cctv.x + dx[dir];
				ny = cctv.y + dy[dir];
				while(isIn(nx,ny)) {
					if(map[nx][ny] == 6)
						break;
					if(map[nx][ny] == 0)
						map[nx][ny] = -1;
					nx += dx[dir];
					ny += dy[dir];
				}
				break;
			case 3:
				nx = cctv.x + dx[dir];
				ny = cctv.y + dy[dir];
				while(isIn(nx,ny)) {
					if(map[nx][ny] == 6)
						break;
					if(map[nx][ny] == 0)
						map[nx][ny] = -1;
					nx += dx[dir];
					ny += dy[dir];
				}
				dir = (dir + 1) % 4;
				nx = cctv.x + dx[dir];
				ny = cctv.y + dy[dir];
				while(isIn(nx,ny)) {
					if(map[nx][ny] == 6)
						break;
					if(map[nx][ny] == 0)
						map[nx][ny] = -1;
					nx += dx[dir];
					ny += dy[dir];
				}
				break;
			case 4:
				for(int i = 0 ; i < 4 ; i++) {
					if(i == dir)
						continue;
					nx = cctv.x + dx[i];
					ny = cctv.y + dy[i];
					while(isIn(nx,ny)) {
						if(map[nx][ny] == 6)
							break;
						if(map[nx][ny] == 0)
							map[nx][ny] = -1;
						nx += dx[i];
						ny += dy[i];
					}
				}
				break;
			case 5:
				for(int i = 0 ; i < 4 ; i++) {
					nx = cctv.x + dx[i];
					ny = cctv.y + dy[i];
					while(isIn(nx,ny)) {
						if(map[nx][ny] == 6)
							break;
						if(map[nx][ny] == 0)
							map[nx][ny] = -1;
						nx += dx[i];
						ny += dy[i];
					}
				}
				break;
		}
	}

}
