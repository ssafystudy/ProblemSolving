import java.util.*;

class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        StringBuilder sb = new StringBuilder();
        
        while(n >0){
            sb.append(n%k);
            n /=k;
        }
        
        StringTokenizer st = new StringTokenizer(sb.reverse().toString(), "0");
        while(st.hasMoreTokens()){
            long t= Long.parseLong(st.nextToken());
            if(isPrime(t)) answer++;
        }
        return answer;
    }
    
    public boolean isPrime(long n){
        if(n == 1) return false;
        
        long len = (long)Math.sqrt(n);
        
        for(long i = 2; i<=len; i++) if(n % i == 0) return false;
        return true;
    }
}