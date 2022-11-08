import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		int x = Integer.parseInt(br.readLine());

		int answer = 0;
		for (int i = 0; i < N; i++) {
			int t = x - arr[i];
			if (t <= 0)
				break;
			if (Arrays.binarySearch(arr, t) >= 0)
				answer++;
		}

		System.out.println(answer / 2);
	}
}