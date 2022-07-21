import java.util.*;

public class 전화번호목록_고진석 {
	public static void main(String[] args) {
		String[] phone_book = {"119", "97674223", "1195524421"};
		System.out.println(solution(phone_book));
	
	}

	public static boolean solution(String[] phone_book){
		HashMap<String, Integer> map = new HashMap<String, Integer>();

		for(String phoneNumber : phone_book)
			map.put(phoneNumber, 0);
		
		for(String phoneNumber : phone_book) 
			for(int i = phoneNumber.length() - 1 ; i > 0 ; i--)
				if( map.containsKey(phoneNumber.substring(0, i)) ) {
					return false;
				}
		
		return true;
	}

}
