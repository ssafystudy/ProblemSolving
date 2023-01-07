import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//초기화
		String[] line = br.readLine().split(" ");
		int N = Integer.parseInt(line[0]); //물품의 수
		int K = Integer.parseInt(line[1]); //버틸 수 있는 무게 
		
		int[][] bag = new int[N][2];
		
		for (int i = 0; i < N; i++) {
			line = br.readLine().split(" ");
			
			bag[i][0] = Integer.parseInt(line[0]); //물건의 무게
			bag[i][1] = Integer.parseInt(line[1]); //물건의 가치
		}
		
		int[][] dp = new int[N + 1][K + 1];

		
		//알고리즘
		for (int j = 1; j < K + 1; j++) {
			for (int i = 1; i < N + 1; i++) {
				int[] curBag = bag[i - 1]; //현재 물건
				int curW = curBag[0]; //현재 물건의 무게
				int curV = curBag[1]; //현재 물건의 가치
				
				if (j < curW) { //고려해 볼 무게 < 현재 물건의 무게
					dp[i][j] = dp[i - 1][j]; //현재 물건 고려하지 않은 이전 상태의 가방 그대로
				} else { //고려해 볼 무게 >= 현재 물건의 무게
					dp[i][j] = Math.max(curV + dp[i - 1][j - curW], dp[i - 1][j]); //(현재 물건 고려한 상태(현재 물건의 가치+(고려해 볼 무게-현재 물건의 무게)일 때 가방 가치), 현재 물건 고려하지 않은 이전 상태의 가방) 중 가치가 큰 것
				}
			}
		}
		
		
		//출력
		bw.write(dp[N][K] + "\n");
		bw.flush();
		bw.close();
		br.close();
		
	}

}
