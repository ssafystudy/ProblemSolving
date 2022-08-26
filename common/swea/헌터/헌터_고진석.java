import java.io.*;
import java.util.*;

class 헌터_고진석 {
	static int N, M;
	static int[][] list;
	static int[] order;
	static boolean[] visited;
	static boolean[] toCheck;
	static int ans;
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1 ; t <= T ; t++) {
			N = Integer.parseInt(br.readLine());
			M = 0;
			int[][] tmp = new int[8][3];
			for(int i = 0 ; i < N ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < N ; j++) {
					int n = Integer.parseInt(st.nextToken());
					if(n != 0) {
						tmp[M][0] = i;
						tmp[M][1] = j;
						tmp[M++][2] = n;
					}
				}
			}
			list = new int[M][2];
			order = new int[M];
			visited = new boolean[M];
			for(int i = 0 ; i < M ; i++) {
				if(tmp[i][2] > 0) {
					list[tmp[i][2] - 1][0] = tmp[i][0];
					list[tmp[i][2] - 1][1] = tmp[i][1];
				}
				else {
					list[M/2 - tmp[i][2] - 1][0] = tmp[i][0];
					list[M/2 - tmp[i][2] - 1][1] = tmp[i][1];
				}
			}
			
			toCheck = new boolean[M * 2];
			ans = Integer.MAX_VALUE;
			makeOrder(0);
			
			bw.write("#" + t + " " + ans + "\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void makeOrder(int depth) {
		if(depth == M) {
			int cnt = move();
			ans = Math.min(ans, cnt);
		}
		else {
			for(int i = 0 ; i < M/2 ; i++) {
				if(visited[i])
					continue;
				visited[i] = true;
				order[depth] = i;
				toCheck[i] = true;
				makeOrder(depth + 1);
				visited[i] = false;
				toCheck[i] = false;
			}
			for(int i = M/2 ; i < M ; i++) {
				if(visited[i] || !toCheck[i - M/2])
					continue;
				visited[i] = true;
				order[depth] = i;
				toCheck[i - M/2] = false;
				makeOrder(depth + 1);
				visited[i] = false;
				toCheck[i - M/2] = true;
			}
		}
	}
	
	public static int move() {
		int cnt = list[order[0]][0] + list[order[0]][1];
		for(int i = 1 ; i < M ; i++) {
			cnt += Math.abs(list[order[i - 1]][0] - list[order[i]][0]) + Math.abs(list[order[i - 1]][1] - list[order[i]][1]);
		}
		return cnt;
	}
}