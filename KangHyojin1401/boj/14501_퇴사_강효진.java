import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//초기화
		int N = Integer.parseInt(br.readLine());
		
		int[][] schedules = new int[N][2];
		
		for (int i = 0; i < N; i++) {
			String[] line = br.readLine().split(" ");
			schedules[i][0] = Integer.parseInt(line[0]); //Ti : 상담을 완료하는데 걸리는 기간
			schedules[i][1] = Integer.parseInt(line[1]); //Pi : 상담을 했을 때 받을 수 있는 금액
		}
		
		
		//알고리즘
		int[] dp = new int[N + 5];
		
		for (int i = N - 1; i >= 0; i--) {
			int[] schedule = schedules[i];
			int t = schedule[0];
			int p = schedule[1];
			
			if (i + t > N) { //t가 퇴사일보다 크면 선택 안 함
				dp[i] = dp[i + 1];
			} else {
				dp[i] = Math.max(p + dp[i + t], dp[i + 1]);
			}
		}
		
		
		//출력
		bw.write(dp[0] + "\n");
		bw.flush();
		bw.close();
		br.close();
		
	}

}
