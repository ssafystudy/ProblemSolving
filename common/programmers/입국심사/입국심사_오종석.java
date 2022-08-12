class Solution {
    
    public long solution(int n, int[] times) {
        
        int maxTime = -1;
        for(int t: times) if(t>maxTime) maxTime = t;
        
        long low = 0;
        long high = (long)maxTime * n; // (long) ㅅㅂ
        
        while(low+1 < high){
            long mid = (low + high) >>1;
            long cnt = 0;
            for(int t: times) cnt += mid/t;
            
            if(cnt < n) low = mid;
            else high = mid;
        }
        return high;
    }
}