import java.util.*;
class Solution {
    int cnt = 0;
    boolean[] indexVisit;
    //boolean[] numVisit = new boolean[10000000];
    Map<Integer, Boolean> numVisit = new HashMap<>();
    String n;
    int nLen;
    
    public boolean isPrime(int num){
        if(num == 1) return false;
        int sqrt = (int)Math.sqrt(num);
        for(int i = 2; i<= sqrt; i++){
            if(num % i == 0) return false;
        }
        return true;
    }
    
    public void recursion(String t){
        for(int i = 0; i<nLen; i++){
            if(t == "" && n.charAt(i) == '0') continue;
            if(!indexVisit[i]){
                indexVisit[i] = true;
                String tt = t;
                tt += n.charAt(i);
                int ttInt = Integer.parseInt(tt);
                if(!numVisit.containsKey(ttInt) && isPrime(ttInt)){
                    numVisit.put(ttInt, true);
                    cnt++;
                }
                recursion(tt);
                indexVisit[i] =false;
            }
        }
        return;
    }
    
    public int solution(String numbers) {
        n = numbers;
        nLen = numbers.length();
        indexVisit = new boolean[nLen];
        recursion("");
        return cnt;
    }
}