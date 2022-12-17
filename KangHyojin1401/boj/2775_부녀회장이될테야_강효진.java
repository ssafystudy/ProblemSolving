import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//초기화
		int T = Integer.parseInt(br.readLine()); //Test case의 수
		
		for (int tc = 0; tc < T; tc++) {
			int k = Integer.parseInt(br.readLine()); //층수
			int n = Integer.parseInt(br.readLine()); //호수
			
			
			//알고리즘
			/*
			 * 0층 - 1호: 1명, 2호: 2명, 3호: 3명, 4명: 4명, ...
			 * 1층 - 1호: 1명, 2호: 3명, 3호: 6명, 4명: 10명, ...
			 * 2층 - 1호: 1명, 2호: 4명, 3호: 10명, 4명: 20명, ...
			 * 
			 * => k층 n호 = dp[k][n - 1] + dp[k - 1][n]
			 */
			
			int[][] dp = new int[15][15];
			
			for (int j = 0; j < 15; j++) {
				dp[0][j] = j;
			}
			
			for (int i = 1; i < 15; i++) {
				for (int j = 1; j < 15; j++) {
					dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
				}
			}
			
			
			//출력
			bw.write(dp[k][n] + "\n");
		}
		
		
		//출력
		bw.flush();
		bw.close();
		br.close();
		
	}

}
