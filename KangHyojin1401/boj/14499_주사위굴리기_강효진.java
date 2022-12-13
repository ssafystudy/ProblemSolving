import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
	static int[][] map;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//초기화
		String[] line = br.readLine().split(" ");
		int N = Integer.parseInt(line[0]); //지도의 세로 크기 (1 ≤ N ≤ 20)
		int M = Integer.parseInt(line[1]); //지도의 가로 크기 (1 ≤ M ≤ 20)
		int x = Integer.parseInt(line[2]); //주사위를 놓은 곳의 좌표 x (0 ≤ x ≤ N-1)
		int y = Integer.parseInt(line[3]); //주사위를 놓은 곳의 좌표 y (0 ≤ y ≤ M-1)
		int K = Integer.parseInt(line[4]); //명령의 개수 (1 ≤ K ≤ 1,000)
		
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			line = br.readLine().split(" ");
			
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(line[j]);
			}
		}
		
		String[] orders = br.readLine().split(" "); //이동하는 명령 (동쪽은 1, 서쪽은 2, 북쪽은 3, 남쪽은 4)
		
		int[] dice = {0, 0, 0, 0, 0, 0};
		
		int[] dx = {0, 0, -1, 1}; //동서북남
		int[] dy = {1, -1, 0, 0};
		
		
		//알고리즘
		for (int i = 0; i < K; i++) {
			int order = Integer.parseInt(orders[i]);
			
			int newX = x + dx[order - 1];
			int newY = y + dy[order - 1];
			
			if (newX >= 0 && newX < N && newY >= 0 && newY < M) {
				x = newX;
				y = newY;
				int[] newDice = null;
				
				switch(order) {
				case 1: //동
					newDice = goEast(dice);
					break;
				case 2: //서
					newDice = goWest(dice);
					break;
				case 3: //북
					newDice = goNorth(dice);
					break;
				case 4: //남
					newDice = goSouth(dice);
					break;
				}
				
				dice = newDice;
				copyNum(x, y, dice);
				bw.write(dice[0] + "\n");
			}
		}
		
		
		//출력
		bw.flush();
		bw.close();
		br.close();
			
	}
	
	static int[] goEast(int[] dice) {
		return new int[] {dice[3], dice[1], dice[0], dice[5], dice[4], dice[2]};
	}
	
	static int[] goWest(int[] dice) {
		return new int[] {dice[2], dice[1], dice[5], dice[0], dice[4], dice[3]};
	}
	
	static int[] goNorth(int[] dice) {
		return new int[] {dice[4], dice[0], dice[2], dice[3], dice[5], dice[1]};
	}
	
	static int[] goSouth(int[] dice) {
		return new int[] {dice[1], dice[5], dice[2], dice[3], dice[0], dice[4]};
	}
	
	static void copyNum(int x, int y, int[] dice) {
		if (map[x][y] == 0) {
			map[x][y] = dice[5];
		} else {
			dice[5] = map[x][y];
			map[x][y] = 0;
		}
	}
 
}
