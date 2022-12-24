import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
	static int n, m;
	static Node[] nodes;
	static boolean[] visited;
	
	static int cnt;
	
	static class Node {
		int n; 
		Node next;
		
		public Node(int n, Node next) {
			this.n = n;
			this.next = next;
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//초기화
		n = Integer.parseInt(br.readLine()); //전체 사람의 수
		
		String[] line = br.readLine().split(" ");
		int[] input = new int[2]; //촌수를 계산해야 하는 서로 다른 두 사람의 번호
		input[0] = Integer.parseInt(line[0]) - 1;
		input[1] = Integer.parseInt(line[1]) - 1;
		
		m = Integer.parseInt(br.readLine()); //부모 자식들 간의 관계의 개수
		
		nodes = new Node[n]; 
		for (int i = 0; i < m; i++) {
			line = br.readLine().split(" ");
			
			int from = Integer.parseInt(line[0]) - 1;
			int to = Integer.parseInt(line[1]) - 1;

			nodes[from] = new Node(to, nodes[from]);
			nodes[to] = new Node(from, nodes[to]);
		}
		
		visited = new boolean[n];
		
		
		//출력
		bw.write(dfs(input[0], input[1], 0) + "\n");
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	static int dfs(int cur, int target, int cnt) {
		if (cur == target) {
			return cnt;
		}
		
		visited[cur] = true;
		
		for (Node temp = nodes[cur]; temp != null; temp = temp.next) {
			if (!visited[temp.n]) {
				int result = dfs(temp.n, target, cnt + 1);
				
				if (result != -1) {  
					return result;
				}
			}
		}
		
		return -1;
	}

}
