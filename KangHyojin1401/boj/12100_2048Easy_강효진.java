
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	
	static int N;
	static int[][] board;
	static int maxBlock = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//초기화
		N = Integer.parseInt(br.readLine()); //보드의 크기
		
		board = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			String[] line = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(line[j]);
				
				board[i][j] = num;
				
				if (num > maxBlock) { //0번 했을 때 가장 큰 블록
					maxBlock = num;
				}
			}
		}
		
		
		//알고리즘
		perm(0, board);
		
		
		//출력
		bw.write(maxBlock + "\n");
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	static void perm(int cnt, int[][] map) {
		if (cnt == 5) {
			return;
		}
		
		//위. 0
		int[][] arr = copyArray(map);
		
		for (int j = 0; j < N; j++) {
			int idx = 0;
			int prev = -1;
			
			for (int i = 0; i < N; i++) {
				int cur = arr[i][j];
				
				if (cur != 0) {
					if (prev == cur) {
						arr[idx - 1][j] = cur * 2;
						arr[i][j] = 0;
						maxBlock = Math.max(maxBlock, cur * 2);
						prev = -1;
					} else {
						arr[idx++][j] = cur;
						prev = cur;
					}
				}
			}
			
			for (int i = idx; i < N; i++) {
				arr[i][j] = 0;
			}
		}
		
		perm(cnt + 1, arr);
		
		
		//아래. 1
		arr = copyArray(map);
		
		for (int j = 0; j < N; j++) {
			int idx = N - 1;
			int prev = -1;
			
			for (int i = N - 1; i >= 0; i--) {
				int cur = arr[i][j];
				
				if (cur != 0) {
					if (prev == cur) {
						arr[idx + 1][j] = cur * 2;
						arr[i][j] = 0;
						maxBlock = Math.max(maxBlock, cur * 2);
						prev = -1;
					} else {
						arr[idx--][j] = cur;
						prev = cur;
					}
				}
			}
			
			for (int i = 0; i <= idx; i++) {
				arr[i][j] = 0;
			}
		}
		
		perm(cnt + 1, arr);
		
		
		//왼쪽. 2
		arr = copyArray(map);
		
		for (int i = 0; i < N; i++) {
			int idx = 0;
			int prev = -1;
			
			for (int j = 0; j < N; j++) {
				int cur = arr[i][j];
				
				if (cur != 0) {
					if (prev == cur) {
						arr[i][idx - 1] = cur * 2;
						arr[i][j] = 0;
						maxBlock = Math.max(maxBlock, cur * 2);
						prev = -1;
					} else {
						arr[i][idx++] = cur;
						prev = cur;
					}
				}
			}
			
			for (int j = idx; j < N; j++) {
				arr[i][j] = 0;
			}
		}
		
		perm(cnt + 1, arr);
		
		
		//오른쪽. 3
		arr = copyArray(map);
		
		for (int i = 0; i < N; i++) {
			int idx = N - 1;
			int prev = -1;
			
			for (int j = N - 1; j >= 0; j--) {
				int cur = arr[i][j];
				
				if (cur != 0) {
					if (prev == cur) {
						arr[i][idx + 1] = cur * 2;
						arr[i][j] = 0;
						maxBlock = Math.max(maxBlock, cur * 2);
						prev = -1;
					} else {
						arr[i][idx--] = cur;
						prev = cur;
					}
				}
			}
			
			for (int j = 0; j <= idx; j++) {
				arr[i][j] = 0;
			}
		}
		
		perm(cnt + 1, arr);
		
	}
	
	static void print(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.println(Arrays.toString(arr[i]));
		}
		System.out.println();
	}

	static int[][] copyArray(int[][] origin) {
		int[][] copy = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				copy[i][j] = origin[i][j];
			}
		}
		
		return copy;
	}
}
