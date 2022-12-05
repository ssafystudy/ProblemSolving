import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	
	static int N;
	static char[][] map;
	static int[] dc = {-1, 0, 1, 0}; //상우하좌
	static int[] dr = {0, 1, 0, -1};
	
	static int totalCnt;
	static int[] totalHouses;
	static int idx;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//초기화
		N = Integer.parseInt(br.readLine()); //지도의 크기 N
		
		map = new char[N][N];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		totalHouses = new int[N * N];
		idx = 0; //명시
		
		
		//알고리즘
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == '1') {
					totalCnt++;
					totalHouses[idx++] = dfs(i, j);
				}
			}
		}
		
		
		//출력
		bw.write(totalCnt + "\n"); 
		
		Arrays.sort(totalHouses, 0, idx);
		for (int i = 0; i < idx; i++) {
			bw.write(totalHouses[i] + "\n"); 
		}
		
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	static int dfs(int c, int r) {
		int localCnt = 1;
		map[c][r] = '0'; //방문처리
		
		for (int i = 0; i < 4; i++) {
			int newC = c + dc[i];
			int newR = r + dr[i];
			
			if (newC >= 0 && newC < N && newR >= 0 && newR < N
					&& map[newC][newR] == '1') {
				localCnt += dfs(newC, newR);
			}
		}
		
		return localCnt;
	}

}
