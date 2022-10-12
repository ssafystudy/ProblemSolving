import java.io.*;
import java.util.*;

class Main_15684_사다리조작_고진석{
	static int N, M, H;
	static boolean[][] sadari;
	static int res = -1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		sadari = new boolean[H][N - 1];
		
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			sadari[a - 1][b - 1] = true;
		}
		
		for(int i = 0 ; i < 4 ; i++) {
			
			dfs(0, i);
			
			if(res != -1)
				break;
		}
		
		bw.write(res + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void dfs(int depth, int n) {
		if(depth == n) {
			boolean chk = true;
			for(int i = 0 ; i < N ; i++)
				if(i != down(i)) {
					chk = false;
					break;
				}
			if(chk)
				res = n;
		}
		else {
			for(int i = 0 ; i < H ; i++)
				for(int j = 0 ; j < N - 1 ; j++) {
					if(!sadari[i][j]) {
						boolean chk = true;
						if(j > 0 && sadari[i][j - 1])
							chk = false;
						if(j < N - 2 && sadari[i][j + 1])
							chk = false;
						if(chk) {
							sadari[i][j] = true;
							dfs(depth + 1, n);
							sadari[i][j] = false;
						}
					}
				}
		}
	}
	
	public static int down(int start) {
		for(int i = 0 ; i < H ; i++) {
			if(start > 0 && sadari[i][start-1])
				start--;
			else if(start < N - 1 && sadari[i][start])
				start++;
		}
		
		return start;
	}
}