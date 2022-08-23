import java.io.*;
import java.util.*;

public class Main_14499_주사위굴리기_고진석 {
	static int N, M, K;
	static int x, y;
	static int[][] map;
	static int[] dice = {0, 0, 0, 0, 0, 0};
						//바닥,앞,뒤,좌,우,위
	static int[] dx = {0, 0, 0, -1, 1}, dy = {0, 1, -1, 0, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < M ; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		while(st.hasMoreTokens()) {
			int dir = Integer.parseInt(st.nextToken());
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			if(isIn(nx, ny)) {
				x = nx;
				y = ny;
				roll(dir);
				if(map[x][y] == 0)
					map[x][y] = dice[0];
				else {
					dice[0] = map[x][y];
					map[x][y] = 0;
				}
				bw.write(dice[5] + "\n");
			}
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static boolean isIn(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}
	
	public static void roll(int dir) {
		switch(dir) {
			case 1:
				int tmp = dice[0];
				dice[0] = dice[4];
				dice[4] = dice[5];
				dice[5] = dice[3];
				dice[3] = tmp;
				break;
			case 2:
				tmp = dice[0];
				dice[0] = dice[3];
				dice[3] = dice[5];
				dice[5] = dice[4];
				dice[4] = tmp;
				break;
			case 3:
				tmp = dice[0];
				dice[0] = dice[1];
				dice[1] = dice[5];
				dice[5] = dice[2];
				dice[2] = tmp;
				break;
			case 4:
				tmp = dice[0];
				dice[0] = dice[2];
				dice[2] = dice[5];
				dice[5] = dice[1];
				dice[1] = tmp;
				break;
		}
	}
}
