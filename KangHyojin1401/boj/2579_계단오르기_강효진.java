import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 *                    0  1  2  3  4  5  6 
 * i-1(O) 		      | 0  10 30 35 50 65 65
 * i-1(X) & i-2(O)  | 0  10 20 25 55 45 75
 * max			        | 0  10 30 35 55 65 75
 */
public class Main {

	public static void main(String[] args) throws Exception {

		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		 
		 //초기화
		 int N = Integer.parseInt(br.readLine()); //계단의 개수
		 
		 int[] scores = new int[N];
		 
		 for (int i = 0; i < N; i++) {
			 scores[i] = Integer.parseInt(br.readLine());
		 }
		 
		 
		 //알고리즘
		 int[][] dp = new int[3][N + 2];
		 
		 for (int j = 2; j < N + 2; j++) {
			 int score = scores[j - 2];
			 
			 dp[0][j] = dp[1][j - 1] + score;
			 dp[1][j] = dp[2][j - 2] + score;
			 dp[2][j] = Math.max(dp[0][j], dp[1][j]);
		 }
		  
		 
		 //출력
		 bw.write(dp[2][N + 1] + "\n");
		 bw.flush();
		 bw.close();
		 br.close();
		
	}

}
