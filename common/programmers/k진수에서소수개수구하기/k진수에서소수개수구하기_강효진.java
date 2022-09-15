public class Solution {

	public static int solution(int n, int k) {

		int answer = 0;

		StringBuilder sb = new StringBuilder();

		// 우선 3으로 계속 나눠서 쌓아주기
		while (n > 0) {
			sb.insert(0, n % k);
			n /= k;
		}

		// 완성된 k진법 수에서 0을 delim으로 하도록 split해주기
		String[] s = sb.toString().split("0");
		
		// for문 돌면서 그게 소수인지 판별
		for (int i = 0, len = s.length; i < len; i++) {
			if (!s[i].equals("") && isPrime(Long.parseLong(s[i]))) {
				answer++;
			}
		}

		return answer;

	}

	public static boolean isPrime(long n) {
		if (n == 1) {
			return false;
		}

		double sqrt = Math.sqrt(n);
		for (int i = 2; i <= sqrt; i++) {
			if (n % i == 0) {
				return false;
			}
		}

		return true;
	}

}
