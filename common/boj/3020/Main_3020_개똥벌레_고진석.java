import java.io.*;
import java.util.*;

public class Main_3020_개똥벌레_고진석 {
	static int N, H;
	static int[] jong, seok;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		jong = new int[H + 1];
		seok = new int[H + 1];
		for(int i = 0 ; i < N ; i++) {
			int len = Integer.parseInt(br.readLine());
			if(i % 2 == 0)
				seok[len]++;
			else
				jong[len]++;
		}
		
		for(int i = H - 1 ; i > 0 ; i--) {
			seok[i] += seok[i + 1];
			jong[i] += jong[i + 1];
		}
		
		int min = Integer.MAX_VALUE;
		int cnt = 0;
		for(int i = 1 ; i <= H ; i++) {
			int tmp = seok[i] + jong[H + 1 - i];
			if(tmp < min) {
				min = tmp;
				cnt = 1;
			}
			else if(tmp == min)
				cnt++;
		}
		
		bw.write(min + " " + cnt + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
	
}