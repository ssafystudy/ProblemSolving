import java.util.ArrayList;
import java.util.HashSet;

class Solution {

	public static HashSet<Integer> gList = new HashSet<Integer>();

	public static int isPrime(int n) {
		if(n < 2)
			return 0;
		for (int i = 2; i<=(int)Math.sqrt(n); i++) {
			if (n % i == 0) {
				return 0;
			}
		}
		return 1;
	}

	public static void permutation(ArrayList<Character> list, String output)
	{
		int len = list.size();
		for(int i=0;i<len;i++)
		{
			Character cTemp = list.get(i);
			String temp = output+list.get(i).toString();
			gList.add(Integer.parseInt(temp));

			list.remove(i);
			permutation(list, temp);
			list.add(i, cTemp);
		}
	}

	public int solution(String numbers) {
		int answer = 0;

		ArrayList<Character> numberList = new ArrayList<Character>();
		for(Character itr : numbers.toCharArray())
			numberList.add(itr);

		permutation(numberList, "");
		for(Integer itr : gList)
		{
			if(isPrime(itr.intValue()) == 1)
				answer++;
		}

		return answer;
	}
}