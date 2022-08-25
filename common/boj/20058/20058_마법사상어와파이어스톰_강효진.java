import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Dice {

	public static void main(String[] args) throws Exception {

		// 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] s = br.readLine().split(" ");
		int N = Integer.parseInt(s[0]); //세로
		int M = Integer.parseInt(s[1]); //가로
		
		int x = Integer.parseInt(s[2]); //주사위 x좌표
		int y = Integer.parseInt(s[3]); //주사위 y좌표
		
		int K = Integer.parseInt(s[4]); //명령 개수
		
		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			s = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(s[j]);
			}
		}
		
		
		//알고리즘
		int[][] diceIdx = {null, 
						{3, 4, 2, 5}, //동서북남
						{3, 4, 6, 1},
						{6, 1, 2, 5},
						{1, 6, 2, 5},
						{3, 4, 1, 6},
						{3, 4, 5, 2}};
		int[] dice = new int[7]; //123456 의 실제값
		
		int topIdx = 1;
		
		int[] dx = {0, 0, -1, 1}; //동서북남
		int[] dy = {1, -1, 0, 0};
		
		s = br.readLine().split(" ");
		for (int i = 0; i < K; i++) {
			int direction = Integer.parseInt(s[i]) - 1;
			
			int newX = x + dx[direction];
			int newY = y + dy[direction];

			if (newX >= 0 && newX < N && newY >= 0 && newY < M) { //지도의 바깥으로 이동시킬 수 없다.
				int bottomIdx = diceIdx[topIdx][direction];
				int bottom = dice[bottomIdx];
				
				if (map[newX][newY] == 0) { //이동한 칸에 쓰여 있는 수가 0이면, 주사위의 바닥면에 쓰여 있는 수가 칸에 복사된다. 
					map[newX][newY] = bottom;
				} else { //0이 아닌 경우에는 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사되며, 칸에 쓰여 있는 수는 0이 된다.
					dice[bottomIdx] = map[newX][newY];
					map[newX][newY] = 0;
				}
				
				x = newX;
				y = newY;
				
				topIdx = 7 - bottomIdx;
				
				bw.write(dice[topIdx] + "\n");
			}
		}


		// 출력
		bw.flush();
		bw.close();

	}

}
