import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	
	static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {

		// 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		int[][] matrix = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			Arrays.fill(matrix[i], -1);
			matrix[i][i] = 0;
		}
		
		for (int i = 0; i < M; i++) {
			String[] line = br.readLine().split(" ");
			
			int from = Integer.parseInt(line[0]) - 1;
			int to = Integer.parseInt(line[1]) - 1;
			int w = Integer.parseInt(line[2]); 
			
			if (matrix[from][to] == -1 || matrix[from][to] > w) {
				matrix[from][to] = w;
			}
		}
		
		int[] minD = new int[N];
		boolean[] visited = new boolean[N]; 
		
		String[] line = br.readLine().split(" ");
		int start = Integer.parseInt(line[0]) - 1;
		int finish = Integer.parseInt(line[1]) - 1;
		
		
		// 알고리즘
		Arrays.fill(minD, INF);
		
		minD[start] = 0;
		
		int min;
		int minVertex;
		
		for (int i = 0; i < N; i++) {
			min = INF;
			minVertex = -1;
			for (int j = 0; j < N; j++) {
				if (!visited[j] && min > minD[j]) {
					min = minD[j];
					minVertex = j;
				}
			}
			
			visited[minVertex] = true;
			
			if (minVertex == finish) { //이 뒤로 finish를 경유지로 할 필요가 없음
				break;
			}
			
			for (int j = 0; j < N; j++) {
				if (!visited[j] && matrix[minVertex][j] != -1 &&
						minD[j] > minD[minVertex] + matrix[minVertex][j]) {
					minD[j] = minD[minVertex] + matrix[minVertex][j];
				}
			}
		}
		
		
		// 출력
    bw.write(minD[finish] + "\n");
		bw.flush();
		bw.close();

	}

}
