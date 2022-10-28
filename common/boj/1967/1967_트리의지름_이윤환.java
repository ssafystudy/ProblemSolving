import java.io.*;
import java.util.*;

public class Main {

	private static List<int[]>[] tree;
	private static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		tree = new List[N + 1];

		StringTokenizer st;
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int parent = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			if (tree[parent] == null) {
				tree[parent] = new ArrayList<>();
			}
			tree[parent].add(new int[] { child, weight });
		}

		dfs(1);
		System.out.println(answer);
	}

	private static int dfs(int nodeNumber) {
		List<int[]> adjList = tree[nodeNumber];
		if (adjList == null) {
			return 0;
		}

		int a = 0, b = 0;
		for (int[] adj : adjList) {
			int w = dfs(adj[0]) + adj[1];
			if (w > a) {
				b = a;
				a = w;
			} else if (w > b) {
				b = w;
			}
		}

		answer = Math.max(answer, a + b);
		return a;
	}
}