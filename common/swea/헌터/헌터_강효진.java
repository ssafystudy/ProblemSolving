import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class No2_Hunter {
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("src/common/swea/test/prob2/sample_input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
			
			//초기화
			int n = Integer.parseInt(br.readLine()); //맵의 한 변의 길이
			
			int[][] monsters = new int[4][2];
			int[][] customers = new int[4][2];
			
			int mCnt = 0;
			
			for (int i = 0; i < n; i++) {
				String[] s = br.readLine().split(" ");
				for (int j = 0; j < n; j++) {
					int num = Integer.parseInt(s[j]);
					
					if (num > 0) {
						monsters[num - 1][0] = i;
						monsters[num - 1][1] = j;
						mCnt++;
					} else if (num < 0) {
						customers[(-num) - 1][0] = i;
						customers[(-num) - 1][1] = j;
					}
				}
			}
			
			
			//알고리즘
			int[] pick = new int[mCnt * 2];
			int min = perm(mCnt * 2, 0, 0, pick, monsters, customers, 0, 0, 0);
			
			
			//출력
			bw.write("#" + test_case + " " + min + "\n");
			
		}
		
		bw.flush();
		bw.close();
	}
	
	static int perm (int n, int cnt, int flag, int[] pick, int[][] monsters, int[][] customers, int startX, int startY, int distance) {
		if (cnt == n) {
			System.out.println(Arrays.toString(pick));
			return distance;
		}
		
		int min = Integer.MAX_VALUE;
		
		for (int i = 0; i < n; i++) {
			if ((flag & 1<<i) != 0) {
				continue;
			}
	
			pick[cnt] = i;
			
			if (i >= n / 2 && !isIn(pick, cnt, i - n / 2)) { //현재 고객 번호와 일치하는 몬스터를 앞에서 뽑지 않았으면 넘어감
				continue;
			}
			
			//거리 구하기
			int curX, curY;
			if (i < n / 2) {
				curX = monsters[i][0];
				curY = monsters[i][1];
			} else {
				curX = customers[i - n / 2][0];
				curY = customers[i - n / 2][1];
			}
			
			int temp = perm(n, cnt + 1, flag | 1<<i, pick, monsters, customers, curX, curY, 
							distance + Math.abs(startX - curX) + Math.abs(startY - curY));
			
			if (temp < min) {
				min = temp;
			}
		}
		
		return min;
	}

	private static boolean isIn(int[] pick, int size, int idx) {
		for (int i = 0; i < size; i++) {
			if (pick[i] == idx) {
				return true;
			}
		}
		return false;
	}

}
