import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] p = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            p[i] = i;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (cmd == 0) {
                unionSet(p, a, b);
            } else {
                sb.append(findSet(p, a) == findSet(p, b) ? "YES" : "NO").append('\n');
            }
        }
        System.out.println(sb);
    }

    static int findSet(int[] p, int x) {
        if (x == p[x]) return x;
        else return p[x] = findSet(p, p[x]);
    }

    static void unionSet(int[] p, int a, int b) {
        int A = findSet(p, a);
        int B = findSet(p, b);
        if (A == B) return;
        p[B] = A;
    }
}