import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());


        StringTokenizer st;
        int[][] networks = new int[M][3];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            networks[i][0] = Integer.parseInt(st.nextToken()) - 1;
            networks[i][1] = Integer.parseInt(st.nextToken()) - 1;
            networks[i][2] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(networks, (o1, o2) -> o1[2] - o2[2]);

        int[] p = new int[N];
        for (int i = 0; i < N; i++) {
            p[i] = i;
        }

        int connect = 0;
        int cost = 0;
        for (int i = 0; i < M; i++) {
            if (unionSet(networks[i], p)) {
                connect++;
                cost += networks[i][2];
                if (connect == N - 1) {
                    break;
                }
            }
        }

        System.out.println(cost);
    }

    private static boolean unionSet(int[] network, int[] p) {
        int A = findSet(p, network[0]);
        int B = findSet(p, network[1]);
        if (A == B) return false;
        p[B] = A;
        return true;
    }

    private static int findSet(int[] p, int node) {
        if (node == p[node]) {
            return node;
        } else {
            return p[node] = findSet(p, p[node]);
        }
    }
}