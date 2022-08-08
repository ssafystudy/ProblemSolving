public class Solution {
    
	public static int solution(int[] numbers, int target) {
        int size = numbers.length;
        return countTargetNum(size, numbers, -1, target);
    }
    
    public static int countTargetNum(int size, int[] numbers, int idx, int target) {    	
    	if (idx == size - 1) {
    		if (target == 0) {
    			return 1;
    		}
    		return 0;
    	}
    	
    	int cnt = 0;
    	
    	//+
    	cnt += countTargetNum(size, numbers, idx + 1, target - numbers[idx + 1]);
    	
    	//-
    	cnt += countTargetNum(size, numbers, idx + 1, target + numbers[idx + 1]);
    	
    	return cnt;
    }
    
}
