import java.util.Stack;

class Solution {
	static Stack<Boolean> operators = new Stack<>();
	static int result = 0;

	public void dumpOperators()
	{
		for(Boolean itr : operators)
		{
			System.out.print(itr.booleanValue() ? "+" : "-");
		}
		System.out.println();
	}

	public int calculateResult(int[] numbers)
	{
		int result = 0;

		for(int i=0;i<numbers.length;i++)
			result += (operators.get(i).booleanValue()) ? numbers[i] : -numbers[i];

		return result;
	}

	public void createOperators(int n, int[] numbers, int target)
	{
		if(n == 0)
		{
			if(calculateResult(numbers) == target)
				result++;
			
			//dumpOperators();
			return;
		}

		operators.push(true);
		createOperators(n-1, numbers, target);
		operators.pop();
		operators.push(false);
		createOperators(n-1, numbers, target);
		operators.pop();
	}

	public int solution(int[] numbers, int target) {
		int answer = 0;

		createOperators(numbers.length, numbers, target);

		//System.out.println(result);
		answer = result;

		return answer;
	}
}