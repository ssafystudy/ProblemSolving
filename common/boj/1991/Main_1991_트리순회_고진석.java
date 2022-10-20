import java.io.*;
import java.util.*;

public class Main_1991_트리순회_고진석 {
	
	private static StringBuilder sbPre = new StringBuilder();
	private static StringBuilder sbIn = new StringBuilder();
	private static StringBuilder sbPost = new StringBuilder();
	
	private static int[][] nodes = new int[2][26];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		String cur, left, right;
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			cur = st.nextToken();
			left = st.nextToken();
			right = st.nextToken();
			
			int root = cur.charAt(0) - 'A';
			if(".".equals(left))
				nodes[0][root] = -1;
			else
				nodes[0][root] = left.charAt(0) - 'A';
			if(".".equals(right))
				nodes[1][root] = -1;
			else
				nodes[1][root] = right.charAt(0) - 'A';
		}
		
		pre(0);
		in(0);
		post(0);
		
		bw.write(sbPre.toString()+"\n");
		bw.write(sbIn.toString()+"\n");
		bw.write(sbPost.toString()+"\n");
		
		br.close();
		bw.flush();
		bw.close();
	}
	
	private static void pre(int cur) {
		sbPre.append((char)(cur+'A'));
		if(nodes[0][cur] != -1)
			pre(nodes[0][cur]);
		if(nodes[1][cur] != -1)
			pre(nodes[1][cur]);
	}
	
	private static void in(int cur) {
		if(nodes[0][cur] != -1)
			in(nodes[0][cur]);
		sbIn.append((char)(cur+'A'));
		if(nodes[1][cur] != -1)
			in(nodes[1][cur]);
	}
	
	private static void post(int cur) {
		if(nodes[0][cur] != -1)
			post(nodes[0][cur]);
		if(nodes[1][cur] != -1)
			post(nodes[1][cur]);
		sbPost.append((char)(cur+'A'));
	}
}
