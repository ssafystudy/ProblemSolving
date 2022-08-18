class Solution {

	public static long solution(int n, int[] times) {
		long answer = 0;
		long start = Long.MAX_VALUE;
		long end = 0;

		long min = Long.MAX_VALUE;
		for (int i = 0; i < times.length; i++) {
			start = Math.min(start, times[i]);
			end = Math.max(end, times[i]);
		}

		start = (start * n) / times.length;
		end = (end * n) / times.length;
		
		while (start <= end) {

			long mid = (start + end) / 2;
			long sum = 0;
			
			for (int i = 0; i < times.length; i++) {
				sum += mid / times[i];
			}

			if (sum >= n) {
				answer = mid;
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}

		return answer;
	}
}