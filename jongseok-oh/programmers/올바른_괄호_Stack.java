import java.util.*;

class Solution {
    boolean solution(String s) {
        Stack<Character> stack = new Stack<>();
        
        for(int i =0, len = s.length(); i<len; i++){
            char c = s.charAt(i);
            if(c == '('){
            	stack.push('(');
            }else{
                if(stack.isEmpty()) return false;
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}