import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        
        Map<String, Boolean> m = new HashMap<>();
        
        for(String s: phone_book) m.put(s,true);
        for(String s: phone_book){
            //for(int i =0; i<s.length()-1; i++){
            for(int i =0; i<s.length(); i++){
                if(m.containsKey(s.substring(0,i))) return false;
            }
        }
        return true;
    }
}