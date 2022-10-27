import java.io.*;
import java.util.*;

public class Main_10800_컬러볼_고진석 {
	static int N;
	static int[][] balls;
	static int[] sizePerColor;
	static int[] res;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		balls = new int[N][3];
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			int color = Integer.parseInt(st.nextToken());
			int size = Integer.parseInt(st.nextToken());
			balls[i][0] = color;
			balls[i][1] = size;
			balls[i][2] = i;
		}
		
		Arrays.sort(balls, (o1, o2) -> o1[1] - o2[1]);

		sizePerColor = new int[200001];
		res = new int[N];
		int sum = 0;
		int idx = 0;
		for(int i = 0 ; i < N ; i++) {
			while(balls[idx][1] < balls[i][1]) {
				sum += balls[idx][1];
				sizePerColor[balls[idx][0]] += balls[idx][1];
				idx++;
			}
			res[balls[i][2]] = sum - sizePerColor[balls[i][0]];
		}
		
		for(int i = 0 ; i < N ; i++)
			bw.write(res[i] + "\n");
		
		bw.flush();
		bw.close();
		br.close();
	}
	
}