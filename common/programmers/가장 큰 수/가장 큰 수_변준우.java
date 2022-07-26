import java.util.ArrayList;
import java.util.Collections;

class Solution {

	public static class Number implements Comparable<Number>
	{
		public int number;
		public String strNumber;

		public Number(int number)
		{
			this.number = number;
			this.strNumber = Integer.toString(number);
		}

		@Override
		public int compareTo(Solution.Number number) {
			if(number.strNumber.charAt(0) > this.strNumber.charAt(0))
			{
				return 1;
			}
			else if(number.strNumber.charAt(0) < this.strNumber.charAt(0))
			{
				return -1;
			}
			else
			{
				int num1 = Integer.parseInt(number.strNumber + this.strNumber);
				int num2 = Integer.parseInt(this.strNumber + number.strNumber);

				if(num1 > num2)
					return 1;
				else if(num1 == num2)
					return 0;
				else
					return -1;

			}
		}
	}

	public String solution(int[] numbers) {
		String answer = "";
		ArrayList<Number> numbers_ = new ArrayList<Number>();

		for(int i=0, len = numbers.length; i < len; i++)
		{
			numbers_.add(new Number(numbers[i]));
		}

		Collections.sort(numbers_);

		for(Number itr : numbers_)
		{
			answer += itr.strNumber;
		}

		int zeroCnt = 0;		
		for(int i=0, len = answer.length(); i<len;i++)
		{
			if(answer.charAt(i) == '0')
				zeroCnt++;				
		}
		if(zeroCnt == answer.length())
			answer = "0";

		return answer;
	}
}