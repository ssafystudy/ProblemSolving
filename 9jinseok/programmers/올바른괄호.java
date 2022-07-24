import java.util.*;

public class 올바른괄호 {
	public static void main(String[] args) {
		String s = "(())()";
		
		System.out.println(solution(s));
	}

    public static boolean solution(String s) {
        Stack<Integer> st = new Stack<>();
        for(char c : s.toCharArray()) {
        	if(c == '(') {
        		st.push(1);
        	}
        	else {
        		if(st.isEmpty())
        			return false;
        		else
        			st.pop();
        	}
        }
        if(st.isEmpty())
        	return true;
        else
        	return false;
    }
}
