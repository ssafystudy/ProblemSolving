/*
 * 백준 11725 <트리의 부모 찾기>
 * https://www.acmicpc.net/problem/11725
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
	static class Node {
		int n;
		Node next;
		
		public Node(int n, Node next) {
			this.n = n;
			this.next = next;
		}
	}
	
	static int N;
	static Node[] nodes;
	static int[] parent;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {

		// 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		nodes = new Node[N + 1];
		
		for (int i = 0; i < N - 1; i++) {
			String[] line = br.readLine().split(" ");
			
			int from = Integer.parseInt(line[0]);
			int to = Integer.parseInt(line[1]);
			
			nodes[from] = new Node(to, nodes[from]);
			nodes[to] = new Node(from, nodes[to]);
		}
		
		visited = new boolean[N + 1];
		parent = new int[N + 1];
		
		
		//알고리즘
		dfs(1);
		
		
		//출력
		for (int i = 2; i < N + 1; i++) {
			bw.write(parent[i] + "\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	static void dfs(int n) {
		visited[n] = true;
		
		for (Node temp = nodes[n]; temp != null; temp = temp.next) {
			if (!visited[temp.n]) {
				parent[temp.n] = n;
				dfs(temp.n);
			}
		}
	}

}
