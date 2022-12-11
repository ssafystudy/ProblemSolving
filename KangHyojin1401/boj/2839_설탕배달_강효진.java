/*
 * 백준 2839 <설탕 배달>
 * https://www.acmicpc.net/problem/2839
 * 
 * => 두 가지 방식 다 메모리: 14500kb, 시간: 132ms 정도로 같다.
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	
	static int N;
	static Queue<int[]> queue;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//초기화
		N = Integer.parseInt(br.readLine());
		
		
		//알고리즘
		/* ver.1 BFS 방식 */
		queue = new ArrayDeque<>();
		visited = new boolean[N];
		
		int result = -1;
		
		queueInitCheck(N - 3);
		queueInitCheck(N - 5);
		
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int curKilo = cur[0];
			int curDepth = cur[1];
			
			if (curKilo == 0) {
				result = curDepth;
				break;
			}
			
			queueAddCheck(curKilo - 3, curDepth);
			queueAddCheck(curKilo - 5, curDepth);
		}
		

		/* ver.2 그리디 방식 */
//		int result = -1;
//		
//		int depth = 1;
//		boolean breakFlag = false;
//		
//		while (!breakFlag) {
//			for (int i = 0; i <= depth; i++) {
//				int num = 3 * (depth - i) + 5 * i;
//				
//				if (i == 0 && N - num < 0) { //3이 제일 많은데도 음수 -> 불가능
//					breakFlag = true;
//					break;
//				}
//				
//				if (N - num == 0) {
//					result = depth;
//					breakFlag = true;
//					break;
//				}
//			}
//			
//			depth++;
//		}
		
		
		//출력
		bw.write(result + "\n");
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	static void queueInitCheck(int n) {
		if (n >= 0) {
			queue.offer(new int[] {n, 1});
			visited[n] = true;
		}
	}
	
	static void queueAddCheck(int n, int depth) {
		if (n >= 0 && !visited[n]) {
			queue.offer(new int[] {n, depth + 1});
			visited[n] = true;
		} 
	}

}
