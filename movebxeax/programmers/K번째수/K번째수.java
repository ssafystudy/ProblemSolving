import java.util.Arrays;

class Solution {
	public int[] solution(int[] array, int[][] commands) {
		int[] answer = {};

		answer = new int[commands.length];

		for(int i=0, len = commands.length;i<len;i++)
		{
			int[] newArray = Arrays.copyOfRange(array, commands[i][0]-1, commands[i][1]);
			Arrays.sort(newArray);

			answer[i] = newArray[commands[i][2]-1];
		}

		return answer;
	}
}