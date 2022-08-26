import java.io.*;
import java.util.*;

class 낚시터자리잡기_고진석 {
	static int N;
	static int[][] gateInfo = new int[3][2];
	static int ans;
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1 ; t <= T ; t++) {
			N = Integer.parseInt(br.readLine());
			for(int i = 0 ; i < 3 ; i++) {
				st = new StringTokenizer(br.readLine());
				gateInfo[i][0] = Integer.parseInt(st.nextToken());
				gateInfo[i][1] = Integer.parseInt(st.nextToken());
			}
			
			ans = Integer.MAX_VALUE;
			int[][] order = {{0,1,2},{0,2,1},{1,0,2},{1,2,0},{2,0,1},{2,1,0}};
			for(int i = 0 ; i < 6 ; i++)
				run(order[i]);
			
			bw.write("#" + t + " " + ans + "\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void run(int[] order) {
		boolean[] map = new boolean[N + 1];
		int cnt = in(map, order, 0);
		ans = Math.min(ans, cnt);
	}
	
	public static int in(boolean[] map, int[] order, int idx) {
		if(idx == 3)
			return 0;
		int cnt = 0;
		int gate = gateInfo[order[idx]][0];
		int n = gateInfo[order[idx]][1];
		
		int cnt2 = 0;
		boolean[] map2 = new boolean[N + 1];
		boolean chk = false;
		for(int i = 0 ; i < n ; i++) {
			int left = gate - 1;
			int right = gate;
			while(left > 0) {
				if(!map[left])
					break;
				left--;
			}
			while(right <= N) {
				if(!map[right])
					break;
				right++;
			}
			
			if(left == 0) {
				map[right] = true;
				cnt += right - gate + 1;
			}
			else if(right > N) {
				map[left] = true;
				cnt += gate - left + 1;
			}
			else {
				if(gate - left > right - gate) {
					map[right] = true;
					cnt += right - gate + 1;
				}
				else if(gate - left < right - gate) {
					map[left] = true;
					cnt += gate - left + 1;
				}
				else if(i < n - 1) {
					map[left] = true;
					cnt += gate - left + 1;
				}
				else {
					chk = true;
					System.arraycopy(map, 1, map2, 1, N);
					cnt2 = cnt;
					map[left] = true;
					cnt += gate - left + 1;
					map2[right] = true;
					cnt2 += right - gate + 1;
				}
			}
		}
		if(chk)
			return Math.min(cnt + in(map, order, idx + 1), cnt2 + in(map2, order, idx + 1));
		return cnt + in(map, order, idx + 1);
	}
}