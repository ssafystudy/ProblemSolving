import java.io.*;
import java.util.*;

public class Main {

    static List<int[]>[] adjLists;
    static int N;
    static int E;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        adjLists = new List[N];
        for (int i = 0; i < N; i++) {
            adjLists[i] = new ArrayList<>();
        }

        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());

            adjLists[from].add(new int[]{to, weight});
            adjLists[to].add(new int[]{from, weight});
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken()) - 1;
        int v2 = Integer.parseInt(st.nextToken()) - 1;

        long answer1 = dijkstra(0, v1) + dijkstra(v1, v2) + dijkstra(v2, N - 1);
        long answer2 = dijkstra(0, v2) + dijkstra(v2, v1) + dijkstra(v1, N - 1);
        long answer = Math.min(answer1, answer2);
        System.out.println(answer <  Integer.MAX_VALUE ? answer : -1);
    }

    private static long dijkstra(int start, int dest) {
        Queue<int[]> pq = new PriorityQueue<>(((o1, o2) -> o1[1] - o2[1]));
        int[] distances = new int[N];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[start] = 0;
        pq.add(new int[]{start, 0});
        while (!pq.isEmpty()) {
            int[] current = pq.poll();

            List<int[]> adjList = adjLists[current[0]];
            for(int[] adj : adjList){
                if (distances[adj[0]] > current[1] + adj[1]) {
                    distances[adj[0]] = current[1] + adj[1];
                    pq.offer(new int[]{adj[0], distances[adj[0]]});
                }
            }
        }
        return distances[dest];
    }
}