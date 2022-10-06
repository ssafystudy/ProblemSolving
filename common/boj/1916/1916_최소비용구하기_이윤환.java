import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static List<int[]>[] adjLists;

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		adjLists = new List[N];
		for (int i = 0; i < N; i++) {
			adjLists[i] = new ArrayList<>();
		}

		StringTokenizer st;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			adjLists[from].add(new int[] { to, Integer.parseInt(st.nextToken()) });
		}

		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken()) - 1;
		int dest = Integer.parseInt(st.nextToken()) - 1;

		System.out.println(dijkstra(start, dest));
	}

	static long dijkstra(int start, int dest) {
		Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
		long[] dist = new long[N];
		Arrays.fill(dist, Long.MAX_VALUE);
		dist[start] = 0;
		pq.offer(new int[] { start, 0 });

		while (!pq.isEmpty()) {
			int[] current = pq.poll();

			if (current[0] == dest) {
				break;
			}

			List<int[]> adjlList = adjLists[current[0]];
			for (int[] adj : adjlList) {
				if (dist[adj[0]] > current[1] + adj[1]) {
					dist[adj[0]] = current[1] + adj[1];
					pq.offer(new int[] { adj[0], (int) dist[adj[0]] });
				}
			}
		}

		return dist[dest];
	}
}