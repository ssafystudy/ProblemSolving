/*
 * 백준 1991 <트리 순회>
 * https://www.acmicpc.net/problem/1991
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
	static BufferedWriter bw;
	static int N;
	static int[][] link;

	public static void main(String[] args) throws Exception {
		// 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		link = new int[N][2];
		
		for (int i = 0; i < N; i++) {
			String[] line = br.readLine().split(" ");
			
			char cleft = line[1].charAt(0);
			char cright = line[2].charAt(0);
			
			int left = cleft == '.' ? 0 : cleft - 'A';
			int right = cright == '.' ? 0 : cright - 'A';
			
			link[line[0].charAt(0) - 'A'] = new int[] {left, right};
		}
		
		
		// 출력
		preorder(0);
		bw.write("\n");
		
		inorder(0);
		bw.write("\n");
		
		postorder(0);
		bw.write("\n");
		
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	static void preorder(int n) throws IOException {
		bw.write((char) (n + 'A'));
		if (link[n][0] != 0) preorder(link[n][0]);
		if (link[n][1] != 0) preorder(link[n][1]);
	}
	
	static void inorder(int n) throws IOException {
		if (link[n][0] != 0) inorder(link[n][0]);
		bw.write((char) (n + 'A'));
		if (link[n][1] != 0) inorder(link[n][1]);
	}
	
	static void postorder(int n) throws IOException {
		if (link[n][0] != 0) postorder(link[n][0]);
		if (link[n][1] != 0) postorder(link[n][1]);
		bw.write((char) (n + 'A'));
	}

}
