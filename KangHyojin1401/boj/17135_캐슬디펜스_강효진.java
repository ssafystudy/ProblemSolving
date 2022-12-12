import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;

public class Main {
	
	static int N, M, D;
	static boolean[][] arr;
	static int cntEnemy; //고정 적의 수
	static int localCntEnemy; //갱신되는 적의 수

	static int localKill;
	static int maxKill = Integer.MIN_VALUE; //제거할 수 있는 적의 최대 수
	
	static int[] picked;
	static Set<String> enemyPos;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//초기화
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]); //격자판 행의 수
		M = Integer.parseInt(line[1]); //격자판 열의 수
		D = Integer.parseInt(line[2]); //궁수의 공격 거리 제한 

		arr = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			line = br.readLine().split(" ");
			
			for (int j = 0; j < M; j++) {
				if (Integer.parseInt(line[j]) == 1) {
					arr[i][j] = true;
					cntEnemy++;
				}
			}
		}
		
		
		//알고리즘
		/*
		 * 1. 조합으로 궁수를 배치할 3자리를 뽑는다.
		 * 2. 뽑았으면, 거리가 D이하인 적 중에서 가장 가까운 적 혹은 그러한 적이 여럿일 경우에는 가장 왼쪽에 있는 적 공격
		 * 3. 적 이동
		 */
		picked = new int[3];
		comb(3, 0, 0);
		
		
		//출력
		bw.write(maxKill + "\n");
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	static void comb(int r, int cnt, int start) {
		if (cnt == r) {
			boolean[][] array = copyArray(arr);
			
			localCntEnemy = cntEnemy;
			localKill = 0;
			
			while (localCntEnemy > 0) {
				enemyPos = new HashSet<String>();
				
				for (int i = 0; i < cnt; i++) {
					calcDistance(array, N, picked[i]);
				}
				
				killEnemy(array);
				moveEnemies(array);
			}
			
			maxKill = Math.max(maxKill, localKill);
			
			return;
		}
		
		for (int i = start; i < M; i++) {
			picked[cnt] = i;
			comb(r, cnt + 1, i + 1);
		}
	}
	
	static boolean[][] copyArray(boolean[][] origin) {
		boolean[][] copy = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copy[i][j] = origin[i][j];
			}
		}
		
		return copy;
	}
	
	/* 거리 계산 */
	static void calcDistance(boolean[][] arr, int myX, int myY) { //궁수의 x좌표, y좌표
		boolean isFound = false;
		
		for (int cnt = 0; cnt < D; cnt++) {
			for (int i = 0; i <= cnt; i++) {
				int enemyX = (myX - 1) - i; //가장 가까운 적의 x좌표
				int enemyY = myY - (cnt - i);
				
				if (enemyX >= 0 && enemyX < N && enemyY >= 0 && enemyY < M
						&& arr[enemyX][enemyY]) {
					memoEnemyPos(enemyX, enemyY);
					isFound = true;
					break;
				}
			}
			
			if (isFound) {
				break;
			}
			
			for (int i = 0; i < cnt; i++) {
				int enemyX = (myX - 1) - (cnt - 1 - i); //가장 가까운 적의 x좌표
				int enemyY = myY + (i + 1);
				
				if (enemyX >= 0 && enemyX < N && enemyY >= 0 && enemyY < M
						&& arr[enemyX][enemyY]) {
					memoEnemyPos(enemyX, enemyY);
					isFound = true;
					break;
				}
			}
			
			if (isFound) {
				break;
			}
		}
	}
	
	/* 적 위치 메모해두기 */
	static void memoEnemyPos(int x, int y) {
		enemyPos.add(new StringBuilder().append(x).append(",").append(y).toString());
	}
	
	/* 궁수가 적 공격 */
	static void killEnemy(boolean[][] arr) {
		for (String pos : enemyPos) {
			String[] position = pos.split(",");
			arr[Integer.parseInt(position[0])][Integer.parseInt(position[1])] = false;
			localCntEnemy--;
			localKill++;
		}
	}
	
	/* 적 이동 */
	static void moveEnemies(boolean[][] arr) {
		for (int j = 0; j < M; j++) {
			for (int i = N - 1; i >= 0; i--) {
				if (arr[i][j]) {
					if (i == N - 1) {
						localCntEnemy--;
					} else {
						arr[i + 1][j] = arr[i][j];
					}
					
					arr[i][j] ^= arr[i][j];
				}
			}
		}
	}

}
