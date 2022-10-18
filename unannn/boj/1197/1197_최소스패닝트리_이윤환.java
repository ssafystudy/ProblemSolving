import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());


        int[] p = new int[V];
        //init
        for (int i = 0; i < V; i++) {
            p[i] = i;
        }

        int[][] edges = new int[E][3];
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            edges[i][0] = Integer.parseInt(st.nextToken()) - 1;
            edges[i][1] = Integer.parseInt(st.nextToken()) - 1;
            edges[i][2] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(edges, (o1, o2) -> o1[2] - o2[2]);

        int count = 0;
        int weight = 0;
        for (int i = 0; i < E; i++) {
            if (unionSet(edges[i], p)) {
                count++;
                weight += edges[i][2];
                if (count == V - 1) {
                    break;
                }
            }
        }
        System.out.println(weight);
    }

    static boolean unionSet(int[] edge, int[] p) {
        int A = findSet(edge[0], p);
        int B = findSet(edge[1], p);
        if (A == B) {
            return false;
        } else {
            p[B] = A;
            return true;
        }
    }

    static int findSet(int node, int[] p) {
        if (node == p[node]) {
            return node;
        } else {
            return p[node] = findSet(p[node], p);
        }
    }
}