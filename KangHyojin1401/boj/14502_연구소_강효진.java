import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
	static int N, M;
	static int[][] map;
	static int max = Integer.MIN_VALUE;
	
	static int cnt0;
	
	static int[] dx = {-1, 0, 1, 0}; //상우하좌
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//초기화
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]); // 지도의 세로 크기
		M = Integer.parseInt(line[1]); // 지도의 가로 크기
		
		map = new int[N][M]; //지도
		
		for (int i = 0; i < N; i++) {
			line = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(line[j]);
			}
		}
		
		
		//알고리즘
		/*
		 * 1. 조합 돌려서 벽 3개 뽑기
		 * 2. 벽 모두 세워졌으면 인접 영역으로 바이러스 퍼뜨리기(dfs)
		 * 3. 0 개수(안전 영역 크기) 세기
		 */
		
		//1. 조합 돌려서 벽 3개 뽑기
		comb(N * M, 3, 0, 0);
		
		
		//출력
		bw.write(max + "\n");
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	static void comb(int n, int r, int start, int cnt) {
		if (cnt == r) {
			//2. 벽 모두 세워졌으면 인접 영역으로 바이러스 퍼뜨리기(dfs)
			int[][] copy = new int[N][M];
			cnt0 = 0;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					copy[i][j] = map[i][j];
					
					if (map[i][j] == 0) {
						cnt0++;
					}
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (copy[i][j] == 2) {
						dfs(copy, i, j);
					}
				}
			}
			
			//3. 0 개수(안전 영역 크기) 세기
			max = Math.max(max, cnt0);
			
			return;
		}
		
		for (int i = start; i < n; i++) {
			int x = i / M;
			int y = i % M;
			
			if (map[x][y] == 0) {
				map[x][y] = 1;
				comb(n, r, i + 1, cnt + 1);
				map[x][y] = 0;
			}
		}
	}
	
	static void dfs(int[][] map, int i, int j) {
		for (int k = 0; k < 4; k++) {
			int newX = i + dx[k];
			int newY = j + dy[k];
			
			if (newX >= 0 && newX < N && newY >= 0 && newY < M
					&& map[newX][newY] == 0) {
				map[newX][newY] = 3;
				cnt0--;
				dfs(map, newX, newY);
			}
		}
	}
	
}
