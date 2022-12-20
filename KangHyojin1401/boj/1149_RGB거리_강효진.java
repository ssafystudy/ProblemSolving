import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class _221215_1149_RGB거리 {
	
	static int N;
	static int[][] houses;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//초기화
		N = Integer.parseInt(br.readLine()); //집의 수 
		
		houses = new int[N + 1][3];
		
		for (int i = 1; i <= N; i++) {
			String[] line = br.readLine().split(" ");
			
			houses[i][0] = Integer.parseInt(line[0]); //빨강으로 칠하는 비용
			houses[i][1] = Integer.parseInt(line[1]); //초록으로 칠하는 비용
			houses[i][2] = Integer.parseInt(line[2]); //파랑으로 칠하는 비용
		}
		
		
		//알고리즘
		/* ver.1 재귀 방식 -> 시간 초과 */
//		paint(-1, 0, 0);
		
		/* ver.2 dp 방식 */
		/*
		 *    빨			초			파
		 * 1  26  		83 			40
		 * 2  40+49		26+57		26+60
		 * 3  26+57+13	26+60+99	26+57+89
		 */
		
		int[][] dp = new int[N + 1][3];
		
		for (int i = 1; i <= N; i++) {
			dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + houses[i][0];
			dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + houses[i][1];
			dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + houses[i][2];
		}
		
		
		//출력
		bw.write(Math.min(Math.min(dp[N][0], dp[N][1]), dp[N][2]) + "\n");
		bw.flush();
		bw.close();
		br.close();
		
	}
	
// 	static void paint(int prev, int cnt, int cost) {
// 		if (cnt == N) {
// 			min = Math.min(min, cost);
// 			return;
// 		}
		
// 		//빨간색
// 		if (prev != 0) {
// 			paint(0, cnt + 1, cost + houses[cnt][0]);
// 		}
		
// 		//초록색
// 		if (prev != 1) {
// 			paint(1, cnt + 1, cost + houses[cnt][1]);
// 		}
		
// 		//파란색
// 		if (prev != 2) {
// 			paint(2, cnt + 1, cost + houses[cnt][2]);
// 		}
// 	}

}
