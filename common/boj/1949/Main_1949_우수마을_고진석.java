import java.io.*;
import java.util.*;

public class Main_1949_우수마을_고진석 {
	static int N;
	static int[] population;
	static List<Integer>[] tree;
	static int[][] dp;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		population = new int[N + 1];
		tree = new ArrayList[N + 1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1 ; i <= N ; i++) {
			population[i] = Integer.parseInt(st.nextToken());
			tree[i] = new ArrayList<>();
		}
		
		for(int i = 0 ; i < N - 1 ;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			tree[a].add(b);
			tree[b].add(a);
		}
		
		dp = new int[N + 1][2];
		visited = new boolean[N + 1];
		post(1);
		
		bw.write(Math.max(dp[1][1], dp[1][0]) + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void post(int cur) {
		visited[cur] = true;
		
		dp[cur][1] = population[cur];
		dp[cur][0] = 0;
		for(int to : tree[cur]) {
			if(visited[to])
				continue;
			
			post(to);
			
			dp[cur][1] += dp[to][0];
			dp[cur][0] += Math.max(dp[to][0], dp[to][1]);
		}
	}
	
}