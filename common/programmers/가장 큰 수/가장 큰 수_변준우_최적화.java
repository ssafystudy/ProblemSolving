import java.util.ArrayList;
import java.util.Collections;

class Solution {
	public String solution(int[] numbers) {
		String answer = "";

		ArrayList<Integer> list = new ArrayList<>();
		for(int itr : numbers)
			list.add(itr);

		Collections.sort(list, (first, second) -> {
			String fs = String.valueOf(first)+String.valueOf(second);
			String sf = String.valueOf(second)+String.valueOf(first);

			return -(Integer.parseInt(fs) - Integer.parseInt(sf));
		});

		StringBuilder sb = new StringBuilder();

		for(Integer itr : list)
			sb.append(itr);

		if(sb.toString().charAt(0) == '0')
			answer = "0";
		else
			answer = sb.toString();

		return answer;
	}
}