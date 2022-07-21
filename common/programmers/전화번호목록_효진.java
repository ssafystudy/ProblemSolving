import java.util.Arrays;

class Solution {
    public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book, 0, phone_book.length);
		
		boolean answer = true;
		
		for (int i = 0, len = phone_book.length; i < len - 1; i++) {			
			if (phone_book[i].length() < phone_book[i + 1].length() 
					&& phone_book[i].equals(phone_book[i + 1].substring(0, phone_book[i].length()))) {
				answer = false;
				break;
			}
		}
		
		return answer;
    }
}
