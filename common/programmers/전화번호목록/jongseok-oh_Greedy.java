import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book); // O(nlogn) log(1e6) -> 20
        
        for(int i =0; i<phone_book.length -1; i++){
            String f = phone_book[i];
            String s = phone_book[i+1];
            
            //if(f.equals(b.substring(0,f.length()-1))) return false;
            if(s.startsWith(f)) return false;
	// O(nm)
        }
        return true; // -> nlogn
    }
}