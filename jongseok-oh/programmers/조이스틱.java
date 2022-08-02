import java.util.*;
class Solution {
    int answer = Integer.MAX_VALUE;
    char[] _name;
    String gName;
    int len;
    
    boolean check(){
        for(int i =0; i<_name.length; i++)
            if(gName.charAt(i) != _name[i]) return true;
        return false;
    }
    
    void solve(int cursor, int tcnt){
        if(!check()){
            answer = Math.min(tcnt, answer);
            return;
        }
        int leftIndex = cursor, rightIndex = cursor;
        int leftCnt = 0, rightCnt = 0;
        while(_name[leftIndex]==gName.charAt(leftIndex) 
             ){
            leftIndex = (leftIndex - 1 + len) % len;
            
            leftCnt++;
        }
        
        while(_name[rightIndex]==gName.charAt(rightIndex)){
            rightIndex = (rightIndex + 1) % len;
            rightCnt++;
        }
        {
            char tc = gName.charAt(leftIndex);
            int ttcnt = tcnt + leftCnt;
            ttcnt += (tc>'N'?'Z' - tc + 1 : tc- 'A');
            _name[leftIndex] = tc;
            solve(leftIndex, ttcnt);
            _name[leftIndex] = 'A';
        }
        {
            char tc = gName.charAt(rightIndex);
            int ttcnt = tcnt + rightCnt;
            ttcnt += (tc>'N'?'Z' - tc + 1 : tc- 'A');
            _name[rightIndex] = tc;
            solve(rightIndex, ttcnt);
            _name[rightIndex] = 'A';
        }
    }
    
    public int solution(String name) {
        
        gName = name;
        len = gName.length();
        _name = new char[len];
        Arrays.fill(_name,'A');
        
        solve(0,0);
        
        return answer;
    }
}
