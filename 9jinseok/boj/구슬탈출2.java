import java.io.*;
import java.util.*;

public class 구슬탈출2 {
	static int N, M, ans;
	static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
	static int[][] copyMap;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		copyMap = new int[N][M];
		Point red = null, blue = null;
		for(int i = 0 ; i < N ; i++) {
			char[] input = br.readLine().toCharArray();
			for(int j = 0 ; j < M ; j++) {
				if(input[j] == '#')
					map[i][j] = -1;
				else if(input[j] == 'R') {
					map[i][j] = 1;
					red = new Point(i, j);
				}
				else if(input[j] == 'B') {
					map[i][j] = 2;
					blue = new Point(i, j);
				}
				else if(input[j] == 'O')
					map[i][j] = 3;
			}
		}
		
		ans = 11;
		
		dfs(map, 0, red, blue);
		
		if(ans > 10)
			ans = -1;
		bw.write(ans + "\n");
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
	
	public static void dfs(int[][] map, int depth, Point red, Point blue) {
		if(depth >= ans - 1)
			return;
		for(int i = 0 ; i < 4 ; i++) {
			for(int j = 0 ; j < N ; j++)
				System.arraycopy(map[j], 0, copyMap[j], 0, M);
			Point copyR = new Point(red.x, red.y);
			Point copyB = new Point(blue.x, blue.y);
			int res = move(copyMap, copyR, copyB, i);
			if(res == -1)
				continue;
			else if(res == 1) {
				ans = depth + 1;
				return;
			}
			else
				dfs(copyMap, depth + 1, copyR, copyB);
		}
	}

	private static int move(int[][] map, Point red, Point blue, int dir) {
		int nbx = blue.x + dx[dir];
		int nby = blue.y + dy[dir];
		while(true) {
			if(map[nbx][nby] == 3)
				return -1;
			if(map[nbx][nby] == -1) {
				nbx -= dx[dir];
				nby -= dy[dir];
				break;
			}
			nbx += dx[dir];
			nby += dy[dir];
		}
		
		
		int nrx = red.x + dx[dir];
		int nry = red.y + dy[dir];
		while(true) {
			if(map[nrx][nry] == 3)
				return 1;
			if(map[nrx][nry] == -1) {
				nrx -= dx[dir];
				nry -= dy[dir];
				break;
			}
			nrx += dx[dir];
			nry += dy[dir];
		}
		
		if(nrx == nbx && nry == nby) {
			boolean redMove = false;
			switch(dir) {
				case 0:
					if(red.x < blue.x)
						redMove = true;
					break;
				case 1:
					if(red.x > blue.x)
						redMove = true;
					break;
				case 2:
					if(red.y < blue.y)
						redMove = true;
					break;
				case 3:
					if(red.y > blue.y)
						redMove = true;
					break;
			}
			if(redMove) {
				nrx -= dx[dir];
				nry -= dy[dir];
			}
			else {
				nbx -= dx[dir];
				nby -= dy[dir];
			}
		}
		
		map[red.x][red.y] = map[blue.x][blue.y] = 0;
		map[nrx][nry] = 1;
		map[nbx][nby] = 2;
		red.x = nrx;
		red.y = nry;
		blue.x = nbx;
		blue.y = nby;
		
		return 0;
	}
	
}
