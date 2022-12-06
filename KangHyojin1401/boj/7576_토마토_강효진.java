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
		int M = Integer.parseInt(line[0]); //상자의 가로 칸의 수
		int N = Integer.parseInt(line[1]); //상자의 세로 칸의 수
		
		int[][] box = new int[N][M];
		int cnt0 = 0; //안 익은 토마토 개수
		
		Queue<int[]> queue = new ArrayDeque<>();
		
		for (int i = 0; i < N; i++) {
			line = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				int num = Integer.parseInt(line[j]);
				
				box[i][j] = num;
				
				if (num == 0) {
					cnt0++;
				} else if (num == 1) {
					queue.add(new int[] {i, j, 0});
				}
			}
		}
		
		int[] dc = {-1, 0, 1, 0}; //상우하좌
		int[] dr = {0, 1, 0, -1};
		
		
		//알고리즘
		/*
		 * 1. 이중 포문을 이용해 1인 데를 먼저 찾아서 큐에 넣는다. (초기화 할 때 해줌)
		 * 2. bfs 시작. 
		 * 3. 인접 탐색을 하면서 0이면 큐에 넣고 1로 바꿔준다(방문처리).
		 * 4. 만약 queue가 비었는데(루프가 끝났는데) 0의 개수가 양수이면 실패(-1)
		 * 		0의 개수가 0이면 최소 일수 출력
		 */
		
		int result = 0;
		
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int curC = cur[0];
			int curR = cur[1];
			int curDay = cur[2];
			
			for (int i = 0; i < 4; i++) {
				int newC = curC + dc[i];
				int newR = curR + dr[i];
				
				if (newC >= 0 && newC < N && newR >= 0 && newR < M
						&& box[newC][newR] == 0) {
					queue.add(new int[] {newC, newR, curDay + 1});
					cnt0--;
					box[newC][newR] = 1;
				}
			}
			
			result = curDay;
		}
		
		
		//출력
		if (cnt0 > 0) {
			bw.write(-1 + "\n");
		} else {
			bw.write(result + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
		
	}

}
