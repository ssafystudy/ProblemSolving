import java.io.*;
import java.util.*;

public class Main_20056_마법사상어와파이어볼_고진석 {
	static int N, M, K;
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1}, dy = {0, 1, 1, 1, 0, -1, -1 ,-1};
	static ArrayList<Fireball> FBList;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		FBList = new ArrayList<>();
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			FBList.add(new Fireball(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}

		for(int i = 0 ; i < K ; i++) {
			move();
		}

		int ans = 0;
		for(Fireball fb : FBList)
			ans += fb.m;
		bw.write(ans + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

	public static void move() {
		int[][][] map = new int[N][N][4];
		for(Fireball fb : FBList) {			
			int nr = fb.r + dx[fb.d] * fb.s;
			int nc = fb.c + dy[fb.d] * fb.s;
			while(nr < 0)
				nr += N;
			while(nc < 0)
				nc += N;
			nr %= N;
			nc %= N;

			if(map[nr][nc][0] == 0)
				map[nr][nc][3] = fb.d;
			else if(map[nr][nc][3] != -1 && (map[nr][nc][3] % 2 != fb.d % 2))
				map[nr][nc][3] = -1;
			map[nr][nc][0]++;
			map[nr][nc][1] += fb.m;
			map[nr][nc][2] += fb.s;
		}

		FBList.clear();
		for(int i = 0 ; i < N; i++)
			for(int j = 0 ; j < N ; j++) {
				if(map[i][j][0] == 0)
					continue;
				
				if(map[i][j][0] == 1) {
					FBList.add(new Fireball(i, j, map[i][j][1], map[i][j][2], map[i][j][3]));
					continue;
				}
				
				if(map[i][j][1] / 5 == 0)
					continue;
				
				if(map[i][j][3] == -1) {
					FBList.add(new Fireball(i, j, map[i][j][1] / 5, map[i][j][2] / map[i][j][0], 1));
					FBList.add(new Fireball(i, j, map[i][j][1] / 5, map[i][j][2] / map[i][j][0], 3));
					FBList.add(new Fireball(i, j, map[i][j][1] / 5, map[i][j][2] / map[i][j][0], 5));
					FBList.add(new Fireball(i, j, map[i][j][1] / 5, map[i][j][2] / map[i][j][0], 7));
				}
				else {
					FBList.add(new Fireball(i, j, map[i][j][1] / 5, map[i][j][2] / map[i][j][0], 0));
					FBList.add(new Fireball(i, j, map[i][j][1] / 5, map[i][j][2] / map[i][j][0], 2));
					FBList.add(new Fireball(i, j, map[i][j][1] / 5, map[i][j][2] / map[i][j][0], 4));
					FBList.add(new Fireball(i, j, map[i][j][1] / 5, map[i][j][2] / map[i][j][0], 6));
				}
			}
	}

	static class Fireball{
		int r;
		int c;
		int m;
		int s;
		int d;

		public Fireball(int r, int c, int m, int s, int d) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}
}
