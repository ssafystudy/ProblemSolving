class 타겟넘버_고진석 {
	int answer = 0;
    public int solution(int[] numbers, int target) {
        
        dfs(0, 0, target, numbers);
        return answer;
    }
    
    public void dfs(int depth, int sum, int target, int[] numbers) {
    	if(depth == numbers.length) {
    		if(sum == target)
    			answer++;
    		return;
    	}
    	for(int i = -1 ; i <= 1 ; i += 2)
    		dfs(depth + 1, sum + (-1) * i * numbers[depth], target, numbers);
    }
}