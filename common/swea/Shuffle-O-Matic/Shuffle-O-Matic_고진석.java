import java.io.*;
import java.util.*;

class Shuffle {
	static int N;
	static int ans;
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1 ; t <= T ; t++) {
			N = Integer.parseInt(br.readLine());
			int[] arr = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < N ; i++)
				arr[i] = Integer.parseInt(st.nextToken());
			ans = 6;
			dfs(0, arr);
			if(ans > 5)
				ans = -1;
			bw.write("#" + t + " " + ans + "\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void dfs(int depth, int[] arr) {
		if(depth >= ans)
			return;
		if(sorted(arr)) {
			ans = Math.min(ans, depth);
			return;
		}
		for(int i = 0 ; i < N ; i++) {
			int[] copyArr = new int[N];
			System.arraycopy(arr, 0, copyArr, 0, N);
			shuffle(copyArr, i);
			dfs(depth + 1, copyArr);
		}
	}
	
	public static void shuffle(int[] arr, int cmd) {
		int[] res = new int[3 * N];
		int idx = 0;
		for(int i =  N ; i < 2 * N ; i += 2)
			res[i] = arr[idx++];
		for(int i = 2 * N - 1 - 2 * cmd ; i < 3 * N - 1 - 2 * cmd ; i += 2)
			res[i] = arr[idx++];
		idx = 0;
		for(int i = 0 ; i < 3 * N ; i++)
			if(res[i] != 0)
				arr[idx++] = res[i];
	}
	
	public static boolean sorted(int[] arr) {
		boolean chk = true;
		for(int i = 0 ; i < N ; i++)
			if(arr[i] != i + 1)
				chk = false;
		if(chk)
			return chk;
		chk = true;
		for(int i = 0 ; i < N ; i++)
			if(arr[i] != N - i)
				chk = false;
		return chk;
	}
}