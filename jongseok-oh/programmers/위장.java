import java.util.*;
class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        
        Map<String, Integer> m = new HashMap<>();
        
        for(String[] strs: clothes){
            if(!m.containsKey(strs[1])){
                m.put(strs[1], 1);
            }else{
                Integer t = m.get(strs[1]);
                m.put(strs[1], t+1);
            }
        }
        
        Set<String> kset = m.keySet();
        
        for(String k : kset){
            answer *= m.get(k) + 1;
        }
        return answer - 1;
    }
}