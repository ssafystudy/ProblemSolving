import java.util.Stack;

class Solution {
	boolean solution(String s) {
		boolean answer = true;

		Stack<Boolean> stack = new Stack<Boolean>();
		for(char itr : s.toCharArray())
		{
			if(itr == '(')
				stack.push(true);
			else if(itr == ')')
			{
				if(stack.isEmpty())
				{
					answer = false;
					break;
				}
				else
					stack.pop();
			}
		}

		if(!stack.isEmpty())
			answer = false;

		return answer;
	}
}