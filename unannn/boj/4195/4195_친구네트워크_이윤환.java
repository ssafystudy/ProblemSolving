import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int testCase = 1; testCase <= T; testCase++) {
            int F = Integer.parseInt(br.readLine());
            List<Integer> p = new ArrayList<>();
            Map<String, Integer> map = new HashMap<>();
            int index = 0;
            for (int i = 0; i < F; i++) {
                String[] relation = br.readLine().split(" ");
                String Aname = relation[0];
                String Bname = relation[1];

                if (!map.containsKey(Aname)) {
                    p.add(-1);
                    map.put(Aname, index++);
                }

                if (!map.containsKey(Bname)) {
                    p.add(-1);
                    map.put(Bname, index++);
                }

                int a = map.get(Aname);
                int b = map.get(Bname);


                sb.append(-unionSet(p, map, a, b)).append('\n');
            }
        }
        System.out.println(sb);
    }

    static int unionSet(List<Integer> p, Map<String, Integer> map, int a, int b) {
        int A = findSet(p, map, a);
        int B = findSet(p, map, b);
        if (A == B) {
            return p.get(A);
        }
        p.set(A, p.get(A) + p.get(B));
        p.set(B, A);
        return p.get(A);
    }

    static int findSet(List<Integer> p, Map<String, Integer> map, int nodeNumber) {
        int parent = p.get(nodeNumber);
        if (parent < 0) {
            return nodeNumber;
        }
        int temp = findSet(p, map, parent);
        p.set(nodeNumber, temp);
        return temp;
    }
}