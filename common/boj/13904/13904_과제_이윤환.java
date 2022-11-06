import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		List<Integer>[] classes = new List[1001];

		int maxDay = 0;
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int day = Integer.parseInt(st.nextToken());
			maxDay = Math.max(maxDay, day);
			int score = Integer.parseInt(st.nextToken());
			if (classes[day] == null) {
				classes[day] = new ArrayList<>();
			}
			classes[day].add(score);
		}

		int sum = 0;
		Queue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
		for (int i = maxDay; i > 0; i--) {
			if (classes[i] != null) {
				pq.addAll(classes[i]);
			}
			if (!pq.isEmpty()) {
				sum += pq.poll();
			}
		}
		System.out.println(sum);
	}
}