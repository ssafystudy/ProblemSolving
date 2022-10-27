import java.io.*;
import java.util.*;

public class Main {

    static Map<String, String[]> tree;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        tree = new HashMap<>();

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String parent = st.nextToken();
            String left = st.nextToken();
            String right = st.nextToken();

            tree.put(parent, new String[]{left, right});
        }

        preorder("A");
        sb.append('\n');
        inorder("A");
        sb.append('\n');
        postorder("A");
        System.out.println(sb);
    }

    static void preorder(String curr) {
        if (curr.equals(".")) return;
        sb.append(curr);
        preorder(tree.get(curr)[0]);
        preorder(tree.get(curr)[1]);
    }

    static void inorder(String curr) {
        if (curr.equals(".")) return;
        inorder(tree.get(curr)[0]);
        sb.append(curr);
        inorder(tree.get(curr)[1]);
    }

    static void postorder(String curr) {
        if (curr.equals(".")) return;
        postorder(tree.get(curr)[0]);
        postorder(tree.get(curr)[1]);
        sb.append(curr);
    }
}