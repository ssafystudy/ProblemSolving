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
		int N = Integer.parseInt(line[0]); 
		int M = Integer.parseInt(line[1]);
		
		char[][] arr = new char[N][M];
		
		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		
		int[] dc = {-1, 0, 1, 0}; //상우하좌
		int[] dr = {0, 1, 0, -1};

		
		//알고리즘
		Queue<int[]> queue = new ArrayDeque<int[]>();
		queue.add(new int[]{0, 0, 1}); //c, r, 현재 depth
		
		int result = 0; //지나야 하는 최소의 칸 수
		
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int curC = cur[0];
			int curR = cur[1];
			int curCnt = cur[2];
			
			if (curC == N - 1 && curR == M - 1) {
				result = curCnt;
				break;
			}
			
			for (int i = 0; i < 4; i++) {
				int newC = curC + dc[i];
				int newR = curR + dr[i];
				
				if (newC >= 0 && newC < N && newR >= 0 && newR < M
					&& arr[newC][newR] == '1') { //'1'이면서 방문한 적 없으면(== 방문처리를 '0'으로 했기 때문에 여기서는 그냥 '1'인지만 체크) 
											  //큐에 넣는다.
					queue.add(new int[] {newC, newR, curCnt + 1});
					arr[newC][newR] = '0'; //방문처리 해줌
				}
			}
		}
		
		
		//출력
		bw.write(result + "\n");
		bw.flush();
		bw.close();
		br.close();
		
	}

}
