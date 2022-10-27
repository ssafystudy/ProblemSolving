import java.io.*;
import java.util.*;

public class Main_2616_소형기관차_고진석 {
	static int N, M;
	static int[] passengers;
	static int[] sum;
	static int[][] dp;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		passengers = new int[N];
		sum = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i++)
			passengers[i] = Integer.parseInt(st.nextToken());
		
		for(int i = 1 ; i <= N ; i++)
			sum[i] = passengers[i - 1] + sum[i - 1];
		M = Integer.parseInt(br.readLine());
		
		dp = new int[N + 1][4];
		
		for(int i = M ; i <= N ; i++) {
			dp[i][1] = Math.max(dp[i - M][0] + amount(i), dp[i - 1][1]);
			dp[i][2] = Math.max(dp[i - M][1] + amount(i), dp[i - 1][2]);
			dp[i][3] = Math.max(dp[i - M][2] + amount(i), dp[i - 1][3]);
		}
		
		bw.write(dp[N][3] + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static int amount(int idx) {
		return sum[idx] - sum[idx - M];
	}
}