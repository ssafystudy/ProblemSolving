import java.io.*;
import java.util.*;

class Main_20057_마법사상어와토네이도_고진석{
	static int N, mid;
	static double k = 1.0;
	static int[][] map;
	static int res = 0;
	static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
					//좌 하 우 상
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for(int i = 0 ; i< N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N ; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		mid = N / 2;
		int tx = mid, ty = mid;
		int dir = 0;
		boolean isEnd = false;
		while(!isEnd) {
			int limit = (int) k;
			for(int i = 0 ; i < limit ; i++) {
				tx += dx[dir];
				ty += dy[dir];
				if(ty < 0) {
					isEnd = true;
					break;
				}
				move(tx, ty, dir);
			}
			dir = (dir + 1) % 4;
			k += 0.5;
		}
		
		bw.write(res + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void move(int x, int y, int dir) {
		if(map[x][y] == 0)
			return;
		int back = (dir + 2) % 4;
		int left = (dir + 1) % 4;
		int right = (dir + 3) % 4;
		int totalSand = map[x][y];
		int in = 0;
		int out = 0;
		
		int nx = x + dx[back] + dx[left];
		int ny = y + dy[back] + dy[left];
		int sand = totalSand * 1 / 100;
		if(isIn(nx, ny)) {
			in += sand;
			map[nx][ny] += sand;
		}
		else
			out += sand;
		
		nx = x + dx[back] + dx[right];
		ny = y + dy[back] + dy[right];
		if(isIn(nx, ny)) {
			in += sand;
			map[nx][ny] += sand;
		}
		else
			out += sand;
		
		nx = x + dx[left] + dx[left];
		ny = y + dy[left] + dy[left];
		sand = totalSand * 2 / 100;
		if(isIn(nx, ny)) {
			in += sand;
			map[nx][ny] += sand;
		}
		else
			out += sand;
		
		nx = x + dx[right] + dx[right];
		ny = y + dy[right] + dy[right];
		if(isIn(nx, ny)) {
			in += sand;
			map[nx][ny] += sand;
		}
		else
			out += sand;
		
		nx = x + dx[left];
		ny = y + dy[left];
		sand = totalSand * 7 / 100;
		if(isIn(nx, ny)) {
			in += sand;
			map[nx][ny] += sand;
		}
		else
			out += sand;
		
		nx = x + dx[right];
		ny = y + dy[right];
		if(isIn(nx, ny)) {
			in += sand;
			map[nx][ny] += sand;
		}
		else
			out += sand;
		
		nx = x + dx[dir] + dx[left];
		ny = y + dy[dir] + dy[left];
		sand = totalSand * 10 / 100;
		if(isIn(nx, ny)) {
			in += sand;
			map[nx][ny] += sand;
		}
		else
			out += sand;
		
		nx = x + dx[dir] + dx[right];
		ny = y + dy[dir] + dy[right];
		if(isIn(nx, ny)) {
			in += sand;
			map[nx][ny] += sand;
		}
		else
			out += sand;
		
		nx = x + dx[dir] + dx[dir];
		ny = y + dy[dir] + dy[dir];
		sand = totalSand * 5 / 100;
		if(isIn(nx, ny)) {
			in += sand;
			map[nx][ny] += sand;
		}
		else
			out += sand;
		
		nx = x + dx[dir];
		ny = y + dy[dir];
		sand = totalSand - (in + out);
		if(isIn(nx, ny))
			map[nx][ny] += sand;
		else
			out += sand;
		
		res += out;
	}
	
	public static boolean isIn(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
}