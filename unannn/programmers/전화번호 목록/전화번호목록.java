import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        //사전순 정렬
        Arrays.sort(phone_book);
        for (int i = 0; i < phone_book.length - 1; i++) {
            String current = phone_book[i];
            String next = phone_book[i+1];
            if(next.startsWith(current)) return false;
            if(current.startsWith(next)) return false;
        }
        return true;
    }
}