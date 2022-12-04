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
	
	static int N, M;
	static Node[] nodes;
	static boolean[] visited;
	static int cnt = 0; //1번 컴퓨터를 통해 웜 바이러스에 걸리게 되는 컴퓨터의 수

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//초기화
		N = Integer.parseInt(br.readLine()); //컴퓨터의 수
		M = Integer.parseInt(br.readLine()); //네트워크 상에서 직접 연결되어 있는 컴퓨터 쌍의 수
		
		nodes = new Node[N]; //컴퓨터들의 연결리스트
		
		for (int i = 0; i < M; i++) {
			String[] line = br.readLine().split(" ");
			int from = Integer.parseInt(line[0]) - 1;
			int to = Integer.parseInt(line[1]) - 1;

			nodes[from] = new Node(to, nodes[from]);
			nodes[to] = new Node(from, nodes[to]);
		}
		
		visited = new boolean[N];
		
		
		//알고리즘
		dfs(0);
		
		
		//출력
		bw.write((cnt - 1) + "\n");
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	static void dfs(int i) {
		visited[i] = true;
		cnt++;
		
		for (Node temp = nodes[i]; temp != null; temp = temp.next) {
			if (!visited[temp.n]) {
				dfs(temp.n);
			}
		}
	}

}
