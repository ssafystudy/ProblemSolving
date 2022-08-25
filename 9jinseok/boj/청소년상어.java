import java.io.*;
import java.util.*;

public class 청소년상어 {
	static int N = 4, ans = 0;
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1}, dy = {0, -1, -1, -1, 0, 1, 1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int[][][] map = new int[N][N][2];
		List<Fish> fishList = new ArrayList<>();
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N ; j++) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				map[i][j][0] = a;
				map[i][j][1] = b - 1;
				fishList.add(new Fish(i, j, a, b - 1));
			}
		}
		fishList.sort((a, b) -> a.idx - b.idx);
		
		dfs(map, fishList, 0, 0, 0);
		
		bw.write(ans + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void dfs(int[][][] map, List<Fish> fishList, int sum, int x, int y) {
		int idx = map[x][y][0];
		sum += idx;
		map[x][y][0] = 0;
		int dir = map[x][y][1];
		for(int i = 0 ; i < fishList.size() ; i++)
			if(fishList.get(i).idx == idx) {
				fishList.remove(i);
				break;
			}
		
		
		move(map, fishList, x, y);
		
		List<int[]> next = new ArrayList<>();
		int nx = x + dx[dir], ny = y + dy[dir];
		while(isIn(nx, ny)) {
			if(map[nx][ny][0] != 0)
				next.add(new int[] {nx, ny});
			nx += dx[dir];
			ny += dy[dir];
		}
		
		if(next.size() == 0) {
			ans = Math.max(ans, sum);
			return;
		}
		
		for(int[] shark : next) {
			int[][][] copyMap = new int[N][N][2];
			for(int i = 0 ; i < N ; i++)
				for(int j = 0 ; j < N ; j++) {
					copyMap[i][j][0] = map[i][j][0];
					copyMap[i][j][1] = map[i][j][1];
				}
			List<Fish> copyFishList = new ArrayList<>();
			for(Fish f : fishList)
				copyFishList.add(new Fish(f.x, f.y, f.idx, f.dir));
			dfs(copyMap, copyFishList, sum, shark[0], shark[1]);
		}
	}
	
	public static void move(int[][][] map, List<Fish> fishList, int x, int y) {
		for(Fish f : fishList) {
			f.dir--;
			for(int i = 0 ; i < 8 ; i++) {
				f.dir = (f.dir + 1) % 8;
				int nx = f.x + dx[f.dir];
				int ny = f.y + dy[f.dir];
				boolean change = false;
				if(isIn(nx, ny) && (nx != x || ny != y)) {
					if(map[nx][ny][0] == 0) {
						map[f.x][f.y][0] = 0;
						f.x = nx;
						f.y = ny;
						map[f.x][f.y][0] = f.idx;
						map[f.x][f.y][1] = f.dir;
					}
					else {
						for(Fish target : fishList)
							if(target.idx == map[nx][ny][0]) {
								target.x = f.x;
								target.y = f.y;
								f.x = nx;
								f.y = ny;
								map[f.x][f.y][0] = f.idx;
								map[f.x][f.y][1] = f.dir;
								map[target.x][target.y][0] = target.idx;
								map[target.x][target.y][1] = target.dir;
								break;
							}
					}
					change = true;
				}
				if(change)
					break;
			}
		}
	}
	
	static class Fish{
		int x;
		int y;
		int idx;
		int dir;
		
		public Fish(int x, int y, int idx, int dir) {
			this.x = x;
			this.y = y;
			this.idx = idx;
			this.dir = dir;
		}
	}
	
	public static boolean isIn(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
}
