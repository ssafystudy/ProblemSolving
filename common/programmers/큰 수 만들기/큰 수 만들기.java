class Solution {
    public String solution(String number, int k) {
        
        StringBuilder sb = new StringBuilder();
        char[] cArr = number.toCharArray(); // indexing이 훨씬 빠름
        int start = 0, idx = 0;
        int len = cArr.length - k;
        int cnt = 0;
        while(true){
            char _max = cArr[start];
            idx = start;
            for(int i = start; i<=start+k; i++){
                char t = cArr[i];
                if(_max < t){
                    _max = t; idx = i;
                    if(t == '9')break;
                }
            }
            sb.append(cArr[idx]); cnt++;
            if(cnt == len) break;
            k -= idx - start;
            start = idx + 1;
        }
        //sb.append(number.substring(start)); 8천 -> 645
        return sb.toString();
    }
}
