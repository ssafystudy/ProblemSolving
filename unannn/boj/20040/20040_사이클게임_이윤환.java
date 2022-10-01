import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] p = new int[N];
        for (int i = 0; i < N; i++) {
            p[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (!unionSet(p, a, b)) {
                System.out.println(i + 1);
                return;
            }
        }
        System.out.println(0);
    }

    static int finSet(int[] p, int x) {
        if (p[x] == x) return x;
        else return p[x] = finSet(p, p[x]);
    }

    static boolean unionSet(int[] p, int a, int b) {
        int A = finSet(p, a);
        int B = finSet(p, b);
        if (A == B) return false;
        p[B] = A;
        return true;
    }
}