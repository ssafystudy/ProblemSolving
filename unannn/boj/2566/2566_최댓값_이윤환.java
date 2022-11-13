import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int a = 0, b = 0, max = -1;
		for (int i = 0; i < 9; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				if (tmp > max) {
					max = tmp;
					a = i;
					b = j;
				}
			}
		}

		System.out.println(max + "\n" + (a + 1) + " " + (b + 1));

	}
}