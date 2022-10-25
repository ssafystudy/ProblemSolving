import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        List<Integer>[] tree = new List[N + 1];
        for (int i = 0; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }
        StringTokenizer st;
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            tree[a].add(b);
            tree[b].add(a);
        }

        int[] answer = new int[N + 1];
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{1, 0});

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            answer[curr[0]] = curr[1];

            List<Integer> childs = tree[curr[0]];
            for (int child : childs) {
                if (child != curr[1]) {
                    q.add(new int[]{child, curr[0]});
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= N; i++) {
            sb.append(answer[i]).append('\n');
        }
        System.out.println(sb);
    }
}