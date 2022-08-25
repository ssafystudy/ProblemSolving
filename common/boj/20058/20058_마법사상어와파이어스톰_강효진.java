import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class FireStorm {

	static int sum = 0;

	public static void main(String[] args) throws Exception {

		// 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] s = br.readLine().split(" ");
		int N = Integer.parseInt(s[0]);
		int Q = Integer.parseInt(s[1]);

		int powN = 1 << N; // 2^N

		int[][] arr = new int[powN + 2][powN + 2];

		for (int i = 0; i < powN; i++) {
			s = br.readLine().split(" ");
			for (int j = 0; j < powN; j++) {
				int num = Integer.parseInt(s[j]);
				arr[i + 1][j + 1] = num;
				sum += num;
			}
		}

		int[][] copy;

		s = br.readLine().split(" ");
		for (int i = 0; i < Q; i++) {
			int l = Integer.parseInt(s[i]);

			// 알고리즘
			if (l != 0) {
				copy = new int[powN + 2][powN + 2];

				// 90도 회전
				int tmpSize = 1 << l;
				for (int x = 1; x <= powN; x += tmpSize) {
					for (int y = 1; y <= powN; y += tmpSize) {
						turn90(arr, copy, tmpSize, x, y);
					}
				}

				arr = copy;
			}
			
			// 녹은 거 계산
			melt(arr, powN);
		}

		// 2. 남아있는 얼음 중 가장 큰 덩어리가 차지하는 칸의 개수 (어려움)
		boolean[][] visited = new boolean[powN + 2][powN + 2];
		int max = 0;

		for (int i = 1; i <= powN; i++) {
			for (int j = 1; j <= powN; j++) {
				if (!visited[i][j] && arr[i][j] != 0) {
					int cnt = countChunk(arr, visited, i, j);

					if (cnt > max) {
						max = cnt;
					}
				}
			}
		}

		// 출력
		bw.write(sum + "\n");
		bw.write(max + "\n");
		bw.flush();
		bw.close();

	}

	static void turn90(int[][] arr, int[][] copy, int size, int startX, int startY) {
		int cnt = 0;

		for (int j = startY; j < startY + size; j++) {
			for (int i = startX + size - 1; i >= startX; i--) {
				copy[startX + cnt / size][startY + cnt++ % size] = arr[i][j];
			}
		}
	}

	static void melt(int[][] arr, int size) {
		int[] dx = { -1, 0, 1, 0 };
		int[] dy = { 0, 1, 0, -1 };

		ArrayList<int[]> removeList = new ArrayList<>(); //배열 복사본을 만들까 list로 인덱스만 저장할까 하다가 그냥 list로 결정

		for (int i = 1; i <= size; i++) {
			for (int j = 1; j <= size; j++) {
				if (arr[i][j] != 0) { // 현재 칸에 얼음이 있으면
					int cnt = 0;

					for (int k = 0; k < 4; k++) {
						int newX = i + dx[k];
						int newY = j + dy[k];

						if (arr[newX][newY] == 0) {
							cnt++;
						}
					}

					if (cnt >= 2) {
						removeList.add(new int[] {i, j}); //여기서 바로 arr[i][j]--; 해주면 나머지에 영향
						sum--;
					}
				}
			}
		}
		
		for (int i = 0, lSize = removeList.size(); i < lSize; i++) {
			int[] a = removeList.get(i);
			arr[a[0]][a[1]]--;
		}
	}

	static int countChunk(int[][] arr, boolean[][] visited, int i, int j) {
		int sum = 0;

		int[] dx = { -1, 0, 1, 0 };
		int[] dy = { 0, 1, 0, -1 };

		visited[i][j] = true;

		int k;
		for (k = 0; k < 4; k++) {
			int newX = i + dx[k];
			int newY = j + dy[k];

			if (!visited[newX][newY] && arr[newX][newY] != 0) {
				sum += countChunk(arr, visited, newX, newY);
			}
		}

		return sum + 1;
	}

}
