import java.io.*;

public class Main {

    static Node root;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Node root = new Node();
        root.data = Integer.parseInt(br.readLine());

        String input = "";
        Node curr = root;
        while ((input = br.readLine()) != null && !input.isEmpty()) {
            int data = Integer.parseInt(input);
            if (data < curr.data) {
                curr.left = new Node();
                curr.left.parent = curr;
                curr = curr.left;
            } else {
                while (curr.parent != null && curr.parent.data < data) curr = curr.parent;
                while (curr.right != null && curr.right.data < data) curr = curr.right;
                curr.right = new Node();
                curr.right.parent = curr;
                curr = curr.right;
            }
            curr.data = data;
        }

        postorder(root);
        System.out.println(sb);
    }

    static class Node {

        Node parent;
        int data;
        Node left;
        Node right;
    }

    private static void postorder(Node node) {
        if (node.left == null && node.right == null) {
            sb.append(node.data).append('\n');
            return;
        }
        if (node.left != null) postorder(node.left);
        if (node.right != null) postorder(node.right);
        sb.append(node.data).append('\n');
    }
}