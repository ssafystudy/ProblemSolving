import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int[] p = new int[N];
        for (int i = 0; i < N; i++) {
            p[i] = i;
        }

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                if (Integer.parseInt(st.nextToken()) == 1) {
                    unionSet(p, i, j);
                }
            }
        }

        st = new StringTokenizer(br.readLine());

        int root = findSet(p, Integer.parseInt(st.nextToken()) - 1);

        for (int i = 1; i < M; i++) {
            if (root != findSet(p, Integer.parseInt(st.nextToken()) - 1)) {
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");
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