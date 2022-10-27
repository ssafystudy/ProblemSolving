import java.io.*;
import java.util.*;

public class Main_9527_1의개수세기_고진석 {
	static long A, B;
	static long res = 0;
	static long[] bit;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		A = Long.parseLong(st.nextToken());
		B = Long.parseLong(st.nextToken());
		
		bit = new long[55];
		bit[0] = 1;
		for(int i = 1 ; i < 55 ; i++) {
			bit[i] = bit[i - 1] * 2 + (1L << i);
		}
		
		bw.write((bitCnt(B) - bitCnt(A - 1)) + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static long bitCnt(long n) {
		long ans = n & 1;
		
		for(int i = 54 ; i > 0 ; i--) {
			if((n & (1L << i)) > 0L) {
				ans += bit[i - 1] + (n - (1L << i) + 1);
				n -= 1L << i;
			}
		}
		
		return ans;
	}
	
}