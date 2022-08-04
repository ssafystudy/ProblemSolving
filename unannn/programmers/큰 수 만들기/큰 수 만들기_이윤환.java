import java.util.*;

class Solution {
    
    public String solution(String number, int k) {

        char[] chars = number.toCharArray();
        int length = number.length();
        char[] answer = new char[length - k];

        int t = 0;
        for (int i = 0; i < length - k; i++) {
            int max = 0;
            for (int j = t; j < k + i + 1; j++) {
                if (max < chars[j]) {
                    max = chars[j];
                    t = j;
                }
            }
            answer[i] = chars[t++];
        }

        return String.valueOf(answer);
    }
}