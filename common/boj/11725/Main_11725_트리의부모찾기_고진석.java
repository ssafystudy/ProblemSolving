import java.io.*;
import java.util.*;

class Main_11725_트리의부모찾기_고진석{
	static int N;
	static List<Integer>[] adjList;
	static boolean[] visited;
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		adjList = new ArrayList[N + 1];
		for(int i = 1 ; i <= N ; i++)
			adjList[i] = new ArrayList<>();
		
		for(int i = 0 ; i < N - 1 ; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adjList[a].add(b);
			adjList[b].add(a);
		}
		
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] {0, 1});
		
		parent = new int[N + 1];
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			parent[cur[1]] = cur[0];
			
			for(int to : adjList[cur[1]]) {
				if(to == cur[0])
					continue;
				q.add(new int[] {cur[1], to});
			}
		}
		
		for(int i = 2 ; i <= N ; i++)
			bw.write(parent[i] + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
}
