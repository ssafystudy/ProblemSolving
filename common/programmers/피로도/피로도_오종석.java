class Solution {
    
    int solve(int k, int visit, int[][] dungeons){
        int ret = 0;
        for(int i = 0; i<dungeons.length; i++){
            if((visit & (1<<i)) == (1<<i)) continue; // JAVA 개열받음!;;
            if(k >= dungeons[i][0]){
                ret = Math.max(ret, solve(k - dungeons[i][1],
                                          visit ^ (1<<i), dungeons) + 1);
            }
        }
        return ret;
    }
    
    public int solution(int k, int[][] dungeons) {
        return solve(k,0,dungeons);
    }
}
