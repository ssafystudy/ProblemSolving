import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static List<int[]>[] adjLists;
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    static Map<Integer, List<int[]>> islandMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adjLists = new List[N];
        for (int i = 0; i < N; i++) {
            adjLists[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            adjLists[start].add(new int[]{end, w});
            adjLists[end].add(new int[]{start, w});
        }

        int[] dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        Queue<int[]> pq = new PriorityQueue<>(((o1, o2) -> o1[1] - o2[1]));
        pq.add(new int[]{0, 0});
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();

            if (curr[0] == N - 1) {
                break;
            }

            List<int[]> adjList = adjLists[curr[0]];
            for (int[] adj : adjList) {
                if (dist[adj[0]] > dist[curr[0]] + adj[1]) {
                    dist[adj[0]] = dist[curr[0]] + adj[1];
                    pq.add(new int[]{adj[0], dist[adj[0]]});
                }
            }
        }

        System.out.println(dist[N - 1]);
    }
}