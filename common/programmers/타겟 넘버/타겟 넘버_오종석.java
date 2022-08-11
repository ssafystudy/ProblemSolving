class Solution {
    public int solution(int[] numbers, int target) {
        int answer = 0;
        
        int len = numbers.length;
        
        for(int v = 0, end = (1<<len); v<end; v++){
            
            int sum = 0;
            for(int i = 0; i<len; i++){
                int num = numbers[i];
                sum += ((v & (1<<i)) == (1<<i) ? num : -num);
            }
            if(sum == target) answer++;
        }
        return answer;
    }
}
