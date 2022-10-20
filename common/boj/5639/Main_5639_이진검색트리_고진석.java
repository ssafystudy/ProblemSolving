import java.io.*;
import java.util.*;

public class Main_5639_이진검색트리_고진석 {
	
	private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		BinaryTree tree = new BinaryTree(Integer.parseInt(br.readLine()));
		String s = "";
		while((s = br.readLine()) != null && s.length() != 0) {
			tree = tree.addTree(tree,Integer.parseInt(s));
		}
		post(tree);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void post(BinaryTree tree) {
		if(tree.left != null)
			post(tree.left);
		if(tree.right != null)
			post(tree.right);
		sb.append(tree.n+"\n");
	}
	
	private static class BinaryTree {
		int n;
		BinaryTree left;
		BinaryTree right;
		
		public BinaryTree(int n) {
			this.n = n;
			this.left = null;
			this.right = null;
		}
		
		public BinaryTree addTree(BinaryTree tree, int val) {
			BinaryTree curTree = null;
			if(tree == null)
				return new BinaryTree(val);
			if(tree.n > val) {
				curTree = addTree(tree.left, val);
				tree.left = curTree;
			}
			else {
				curTree = addTree(tree.right, val);
				tree.right = curTree;
			}
			return tree;
		}
	}
}
