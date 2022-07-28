import java.util.Arrays;
import java.util.Comparator;

class Solution {
	public int solution(int[] citations) {
		int answer = 0;

		Integer[] citationsWrapper = Arrays.stream(citations).boxed().toArray(Integer[]::new);

		Arrays.sort(citationsWrapper, Comparator.reverseOrder());

		for(int i=0, len = citationsWrapper.length; i<len; i++)
		{
			if(i == 0 && citationsWrapper[i] == 0)
				break;
			if(citationsWrapper[i] >= i+1)
			{
				answer = i+1;
			}
		}

		return answer;
	}
}