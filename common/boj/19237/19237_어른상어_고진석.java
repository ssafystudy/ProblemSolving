import java.io.*;
import java.util.*;

class Main_19237_어른상어_고진석{
	static int N, M, k;
	static int[][] map;
	static Smell[][] smellMap;
	static Shark[] sharks;
	
	static int[] dx = {0,-1,1,0,0}, dy = {0,0,0,-1,1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		smellMap = new Smell[N][N];
		sharks = new Shark[M + 1];
		
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < M ; i++) {
			int dir = Integer.parseInt(st.nextToken());
			int[][] priority = new int[4][4];
			for(int j = 0 ; j < 4 ; j++) {
				StringTokenizer st2 = new StringTokenizer(br.readLine());
				for(int k = 0 ; k < 4 ; k++)
					priority[j][k] = Integer.parseInt(st2.nextToken());
			}
			sharks[i + 1] = new Shark(i + 1, dir, priority);
		}
			
		int sec = 0;
		while(M > 1) {
			sec++;
			if(sec > 1000) {
				sec = -1;
				break;
			}
			
			reduceSmell();
			
			putSmell();
			
			move();
		}
		
		bw.write(sec + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void reduceSmell() {
		for(int i = 0 ; i < N ; i++)
			for(int j = 0 ; j < N ; j++)
				if(smellMap[i][j] != null && --smellMap[i][j].k == 0)
					smellMap[i][j] = null;
	}
	
	public static void putSmell() {
		for(int i = 0 ; i < N ; i++)
			for(int j = 0 ; j < N ; j++)
				if(map[i][j] > 0)
					smellMap[i][j] = new Smell(map[i][j], k);
	}
	
	public static void move() {
		int[][] newMap = new int[N][N];
		int nx, ny;
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				int shark = map[i][j];
				if(shark > 0) {
					ArrayList<int[]> noSmell = new ArrayList<>();
					ArrayList<int[]> mySmell = new ArrayList<>();
					for(int d = 1 ; d <= 4 ; d++) {
						nx = i + dx[d];
						ny = j + dy[d];
						if(!isIn(nx, ny))
							continue;
						if(smellMap[nx][ny] == null)
							noSmell.add(new int[] {nx,ny,d});
						else if(smellMap[nx][ny].shark == shark)
							mySmell.add(new int[] {nx,ny,d});
					}
					
					nx = i;
					ny = j;
					int dir = sharks[shark].dir;
					if(noSmell.size() > 1) {
						int[] priority = sharks[shark].priority[dir - 1];
						for(int d = 0 ; d < 4 ; d++) {
							for(int[] xy : noSmell) {
								if(priority[d] == xy[2]) {
									nx = xy[0];
									ny = xy[1];
									dir = xy[2];
									break;
								}
							}
							if(nx != i || ny != j)
								break;
						}
					}
					else if(noSmell.size() == 1) {
						nx = noSmell.get(0)[0];
						ny = noSmell.get(0)[1];
						dir = noSmell.get(0)[2];
					}
					else {
						if(mySmell.size() > 1) {
							int[] priority = sharks[shark].priority[dir - 1];
							for(int d = 0 ; d < 4 ; d++) {
								for(int[] xy : mySmell) {
									if(priority[d] == xy[2]) {
										nx = xy[0];
										ny = xy[1];
										dir = xy[2];
										break;
									}
								}
								if(nx != i || ny != j)
									break;
							}
						}
						else if(mySmell.size() == 1) {
							nx = mySmell.get(0)[0];
							ny = mySmell.get(0)[1];
							dir = mySmell.get(0)[2];
						}
					}
					
					sharks[shark].dir = dir;
					if(newMap[nx][ny] != 0) {
						newMap[nx][ny] = Math.min(newMap[nx][ny], shark);
						M--;
					}
					else {
						newMap[nx][ny] = shark;
					}
				}
			}
		}
		
		map = newMap;
	}
	
	public static boolean isIn(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
	
	static class Shark{
		int id;
		int dir;
		int[][] priority = new int[4][4];
		
		public Shark(int id, int dir, int[][] priority) {
			this.id = id;
			this.dir = dir;
			this.priority = priority;
		}
	}
	
	static class Smell{
		int shark;
		int k;
		
		public Smell(int shark, int k) {
			this.shark = shark;
			this.k = k;
		}
	}
}