/*
 * 백준 5639 <이진 검색 트리>
 * https://www.acmicpc.net/problem/5639
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
	static int[] arr;
	static int idx;
	static BufferedWriter bw;

	public static void main(String[] args) throws Exception {

		// 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		arr = new int[10_000];
		idx = 0;
	
		String line;
		while ((line = br.readLine()) != null && !line.isEmpty()) {
			arr[idx++] = Integer.parseInt(line);
		}
		
		
		// 알고리즘
		postorder(0, idx);
		
		
		// 출력
		bw.flush();
		bw.close();
		br.close();

	}
	
	static void postorder(int mid, int finish) throws IOException {
		if (finish - mid <= 1) {
			bw.write(arr[mid] + "\n");
			return;
		}
		
		int left = mid;
		if (arr[mid] > arr[mid + 1]) {
			left++;
		}
		
		int right = left + 1;
		while (right < finish && arr[mid] > arr[right]) {
			right++;
		}
		
		if (mid != left) postorder(left, right);
		if (right != finish) postorder(right, finish);
		bw.write(arr[mid] + "\n");
	}

}
