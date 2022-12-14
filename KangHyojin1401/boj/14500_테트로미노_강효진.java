import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	static int N, M;
	static int[][] map;
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//초기화
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]); //종이의 세로 크기 (4 ≤ N ≤ 500)
		M = Integer.parseInt(line[1]); //종이의 가로 크기 (4 ≤ M ≤ 500)
		
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			line = br.readLine().split(" ");
			
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(line[j]);
			}
		}
		
		int[][] shapes = {
				{1, 4}, //N, M
				{4, 1}, 
				{2, 2},
				{3, 2},
				{2, 3}};
		
		boolean[][][] shape3 = {
				{
					{true, false},
					{true, false},
					{true, true}
				},
				{
					{true, true},
					{true, false},
					{true, false}
				},
				{
					{true, true},
					{false, true},
					{false, true}
				},
				{
					{false, true},
					{false, true},
					{true, true}
				},
				{
					{true, false},
					{true, true},
					{false, true}
				},
				{
					{false, true},
					{true, true},
					{true, false}
				},
				{
					{false, true},
					{true, true},
					{false, true}
				},
				{
					{true, false},
					{true, true},
					{true, false}
				}
		};
		
		boolean[][][] shape4 = {
				{
					{true, true, true},
					{true, false, false}
				},
				{
					{false, false, true},
					{true, true, true}
				},
				{
					{true, false, false},
					{true, true, true}
				},
				{
					{true, true, true},
					{false, false, true}
				},
				{
					{false, true, true},
					{true, true, false}
				},
				{
					{true, true, false},
					{false, true, true}
				},
				{
					{true, true, true},
					{false, true, false}
				},
				{
					{false, true, false},
					{true, true, true}
				}
		};
		
		
		//알고리즘
		for (int a = 0, len = shapes.length; a < len; a++) {
			int[] nm = shapes[a];
			int n = nm[0];
			int m = nm[1];
			
			for (int i = 0; i + n - 1 < N; i++) {
				for (int j = 0; j + m - 1 < M; j++) {
					int sum = 0;
					
					if (a == 3) {
						findMax(i, j, n, m, shape3);
					} else if (a == 4) { 
						findMax(i, j, n, m, shape4);
					} else {
						for (int x = i; x < i + n; x++) {
							for (int y = j; y < j + m; y++) {
								sum += map[x][y];
							}
						}
						
						max = Math.max(max, sum);
					}
				}
			}
		}
		
		
		/*
		 * 다른 사람 코드
		 */
// 		int sum = 0;
		
// 		for (int i = 0; i < N; i++) {
// 			for (int j = 0; j < M; j++) {
// 				if (i + 3 < N) {
// 					sum = Math.max(sum, map[i][j] + map[i + 1][j] + map[i + 2][j] + map[i + 3][j]);
// 				}
// 				if (j + 3 < M) {
// 					sum = Math.max(sum, map[i][j] + map[i][j + 1] + map[i][j + 2] + map[i][j + 3]);
// 				}
// 				if (i + 1 < N && j + 1 < M) {
// 					sum = Math.max(sum, map[i][j] + map[i + 1][j] + map[i][j + 1] + map[i + 1][j + 1]);
// 				}
// 				if (i + 2 < N) {
// 					if (j + 1 < M) {
// 						sum = Math.max(sum, map[i][j] + map[i + 1][j] + map[i + 2][j] + map[i + 2][j + 1]);
// 						sum = Math.max(sum, map[i][j] + map[i + 1][j] + map[i + 2][j] + map[i][j + 1]);
// 						sum = Math.max(sum, map[i][j + 1] + map[i + 1][j + 1] + map[i + 2][j + 1] + map[i][j]);
// 						sum = Math.max(sum, map[i][j + 1] + map[i + 1][j + 1] + map[i + 2][j + 1] + map[i + 2][j]);
						
// 						sum = Math.max(sum, map[i][j] + map[i + 1][j] + map[i + 1][j + 1] + map[i + 2][j + 1]);
// 						sum = Math.max(sum, map[i][j + 1] + map[i + 1][j] + map[i + 1][j + 1] + map[i + 2][j]);
// 						sum = Math.max(sum, map[i][j] + map[i + 1][j] + map[i + 2][j] + map[i + 1][j + 1]);
// 						sum = Math.max(sum, map[i][j + 1] + map[i + 1][j + 1] + map[i + 2][j + 1] + map[i + 1][j]);
// 					}
// 				}
// 				if (j + 2 < M) {
// 					if (i + 1 < N) {
// 						sum = Math.max(sum, map[i][j] + map[i][j + 1] + map[i][j + 2] + map[i + 1][j]);
// 						sum = Math.max(sum, map[i][j] + map[i][j + 1] + map[i][j + 2] + map[i + 1][j + 2]);
// 						sum = Math.max(sum, map[i + 1][j] + map[i + 1][j + 1] + map[i + 1][j + 2] + map[i][j]);
// 						sum = Math.max(sum, map[i + 1][j] + map[i + 1][j + 1] + map[i + 1][j + 2] + map[i][j + 2]);
						
// 						sum = Math.max(sum, map[i][j] + map[i][j + 1] + map[i + 1][j + 1] + map[i + 1][j + 2]);
// 						sum = Math.max(sum, map[i + 1][j] + map[i + 1][j + 1] + map[i][j + 1] + map[i][j + 2]);
// 						sum = Math.max(sum, map[i][j] + map[i][j + 1] + map[i][j + 2] + map[i + 1][j + 1]);
// 						sum = Math.max(sum, map[i + 1][j] + map[i + 1][j + 1] + map[i + 1][j + 2] + map[i][j + 1]);
// 					}
// 				}
// 			}
// 		}
		
		
		//출력
    bw.write(max + "\n");
//     bw.write(sum + "\n");
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	static void findMax(int i, int j, int n, int m, boolean[][][] shape) {
		for (int z = 0, size = shape.length; z < size; z++) {
			int sum = 0;
			boolean[][] mini = shape[z];
			
			for (int x = i; x < i + n; x++) {
				for (int y = j; y < j + m; y++) {
					if (mini[x - i][y - j]) {
						sum += map[x][y];
					}
				}
			}
			
			max = Math.max(max, sum);
		}
	}

}
