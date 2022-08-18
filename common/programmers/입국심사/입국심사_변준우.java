import java.util.Arrays;
class Solution {
	public long solution(int n, int[] times) {
		long answer = 0;

		long maxTime = Arrays.stream(times).max().getAsInt();
		long max = (long)maxTime * n;
		long min = 0;

		while(min <= max)
		{
			long mid = (max + min) / 2;

			long available = 0;
			for(long itr : times)
				available += mid/itr;

			if(available < n)
				min = mid+1;
			else
				max = mid-1;
		}
		answer = min;
		return answer;
	}
}