import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//초기화
		String[] line = br.readLine().split(" ");
		int N = Integer.parseInt(line[0]); //수빈이 위치 
		int K = Integer.parseInt(line[1]); //동생 위치
		
		boolean[] visited = new boolean[100_001];
		
		
		//알고리즘
		Queue<int[]> queue = new ArrayDeque<>(); //위치, depth
		
		queue.offer(new int[] {N, 1});
		visited[N] = true;
		
		int result = 0; //수빈이가 동생을 찾는 가장 빠른 시간
		
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int curPos = cur[0];
			int depth = cur[1];
			
			if (curPos == K) {
				result = depth - 1;
				break;
			}
			
			int newPos;
			
			//1. 뒤로 걷기
			newPos = curPos - 1;
			if (newPos >= 0 && !visited[newPos]) { 
				queue.offer(new int[] {newPos, depth + 1});
				visited[newPos] = true;
			}
			
			//2. 앞으로 걷기
			newPos = curPos + 1;
			if (newPos <= 100_000 && !visited[newPos]) {
				queue.offer(new int[] {newPos, depth + 1});
				visited[newPos] = true;
			}

			//3. 순간이동
			newPos = curPos * 2;
			if (newPos <= 100_000 && !visited[newPos]) { 
				queue.offer(new int[] {newPos, depth + 1});
				visited[newPos] = true;
			}
		}
		
		
		//출력
		bw.write(result + "\n");
		bw.flush();
		bw.close();
		br.close();
		
	}

}
