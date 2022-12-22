import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//초기화
		int n = Integer.parseInt(br.readLine()); //삼각형의 크기
		
		int[][] tree = new int[n][];
		
		for (int i = 0; i < n; i++) {
			tree[i] = new int[i + 1];
			String[] line = br.readLine().split(" ");
			
			for (int j = 0; j < i + 1; j++) {
				tree[i][j] = Integer.parseInt(line[j]);
			}
		}
		
		
		//알고리즘
		int[][] dp = new int[n + 1][];
		
		dp[0] = new int[2];
		
		for (int i = 1; i <= n; i++) {
			dp[i] = new int[i + 2];
			
			for (int j = 1; j <= i; j++) {
				dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + tree[i - 1][j - 1];
			}
		}
		
		int max = Integer.MIN_VALUE;
		
		for (int j = 1; j <= n; j++) {
			max = Math.max(max, dp[n][j]);
		}
		
		
		//출력
		bw.write(max + "\n");
		bw.flush();
		bw.close();
		br.close();
		
	}

}
