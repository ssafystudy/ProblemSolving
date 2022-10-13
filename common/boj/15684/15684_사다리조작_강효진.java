/*
 * 백준 15684 <사다리 조작>
 * https://www.acmicpc.net/problem/15684
 */

package common.bj.prob15684;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class LadderOperation {

	static int N, M, H;
	static boolean[][] arr;
	
	static int[] dr = {0, 0}; //우, 좌
	static int[] dc = {1, -1};

	public static void main(String[] args) throws Exception {

		// 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]); // 세로선의 수
		M = Integer.parseInt(line[1]); // 가로선의 수
		H = Integer.parseInt(line[2]); // 가로선을 놓을 수 있는 위치의 수

		arr = new boolean[H][N];

		for (int i = 0; i < M; i++) {
			line = br.readLine().split(" ");

			int sr = Integer.parseInt(line[0]) - 1;
			int sc = Integer.parseInt(line[1]) - 1;

			//ver.1
			
			/*
			 * 이렇게 하면 
			 * 11000
			 * 00110
			 * 01100
			 * 11110 여기서 문제 발생. 어디에 그어진 선인지를 모름
			 * 11011
			 * 00000
			 */
//			arr[sr][sc] = true;
//			arr[sr][sc + 1] = true;
			
			//ver.2
			arr[sr][sc] = true;
		}

		
		// 알고리즘
		int available = (N - 1) * H; // 가능한 짝대기 좌표 수
		int i;
		for (i = 0; i < 4; i++) {
			if (comb(available, i, 0, 0)) {
				break;
			}
		}
		
		
		// 출력
		if (i == 4) {
			i = -1;
		}
		
		bw.write(i + "\n");
		bw.flush();
		bw.close();
		
	}

	static boolean comb(int n, int r, int cnt, int start) {
		if (cnt == r) { // 판이 다 만들어졌으면
			// 탐색으로 i번 세로선의 결과가 i번이 나오는지 검사
			return search();
		}

		for (int i = start; i < n; i++) {
			int nr = i / (N - 1);
			int nc = i % (N - 1);

			if (arr[nr][nc] || (nc > 0 && arr[nr][nc - 1])) { // 그 위치에 이미 사다리가 있으면
				continue;
			}

			arr[nr][nc] = true; // 사다기 놓기
//			arr[nr][nc + 1] = true;

			if (comb(n, r, cnt + 1, i + 1)) {
				return true;
			}

			arr[nr][nc] = false; // 돌려놓기
//			arr[nr][nc + 1] = false;
		}

		return false;
	}
	
	static boolean search() {		
		int j;
		for (j = 0; j < N; j++) {
//			boolean[][] visited = new boolean[H][N];
			//처음엔 방문 배열을 둘까 생각했지만 prev를 두면 굳이 필요 없겠다 싶었음
			
			int curI = 0;
			int curJ = j;
			
			char prev = '0'; //l: 왼쪽, a: 위쪽, r: 오른쪽
			
			while (curI != H) {
				
				if (!arr[curI][curJ]) {
					if (prev != 'l' && (curJ > 0 && arr[curI][curJ - 1])) {
						prev = 'r';	//왼쪽으로
						curJ--;
					} else { //밑으로
						prev = 'a';
						curI++;
					}
				} else {
					if (prev != 'r') {
						prev = 'l'; //오른쪽으로
						curJ++;
					} else {
						prev = 'a';
						curI++;
					}
				}
				
			}
			
			if (curJ != j) {
				break;
			}
		}
		
		if (j == N) {
			return true;
		} else {
			return false;
		}
	}
	

}
