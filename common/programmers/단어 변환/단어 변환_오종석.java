import java.util.*;

class Solution {
    Set<String> _words = new HashSet<>();
    
    Set<Character> []alpha;
    
    Map<String, Boolean> visit = new HashMap<>();
    
    char[] _target;
    char[] tString;
    
    int solve(){
        boolean flag = true;
        for(int i =0; i<tString.length; i++){
            if(_target[i] != tString[i]){
                flag= false; break;
            }
        }
        if(flag) return 0;
        int ret = 987654321;
        
        for(int i = 0; i<tString.length; i++){
            Iterator<Character> it = alpha[i].iterator();
            char tChar = tString[i];
            while(it.hasNext()){
                tString[i] = it.next();
                String tStr = String.valueOf(tString);
                if(_words.contains(tStr) && !visit.get(tStr)){
                    visit.put(tStr, true);
                    ret = Math.min(ret, solve() + 1);
                    visit.put(tStr, false);
                }
                tString[i] = tChar;
            }
        }
        return ret;
    }
    
    public int solution(String begin, String target, String[] words) {
        
        _target = target.toCharArray();
        tString = begin.toCharArray();
        
        alpha = new Set[begin.length()];
        for(int i =0; i<alpha.length; i++){
            alpha[i] = new HashSet<>();
        }
        for(int i =0; i<words.length; i++){
            String wrd = words[i];
            _words.add(wrd);
            visit.put(wrd,false);
            for(int j =0, len = wrd.length(); j<len; j++){
                alpha[j].add(wrd.charAt(j));
            }
        }
        visit.put(begin,true);
        int answer = solve();
        return answer >= 987654321? 0 : answer;
    }
}