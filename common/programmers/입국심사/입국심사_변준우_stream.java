import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
	public static long solution(int n, int[] times) {
		long answer = 0;

		List<Integer> _times = Arrays.stream(times).boxed().collect(Collectors.toList());
		long maxTime = Arrays.stream(times).max().getAsInt();
		long max = (long)maxTime * n;
		long min = 0;

		while(min <= max)
		{
			long mid = (max + min) / 2;

			long[] available = new long[1];
			_times.forEach(o1->{available[0] += mid/o1;});

			if(available[0] < n)
				min = mid+1;
			else
				max = mid-1;
		}
		answer = min;
		return answer;
	}
}