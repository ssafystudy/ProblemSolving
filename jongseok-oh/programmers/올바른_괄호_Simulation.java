class Solution {
    boolean solution(String s) {
        int stack = 0;
        for(int i =0, len = s.length(); i<len; i++){
            char c = s.charAt(i);
            if(c == '(') stack++;
            else{
                if(stack == 0) return false;
                stack--;
            }
        }
        return stack == 0? true:false;
        // 더 빠름
    }
}