class _3 {
	int maxCnt = Integer.MIN_VALUE;
	int maxSum = Integer.MIN_VALUE;
	
    public int[] solution(int[][] users, int[] emoticons) {
        int N = emoticons.length;
        int[] order = new int[N];
        
        perm(0, N, order, users, emoticons);
        
        return new int[] {maxCnt, maxSum};
    }
    
    public void perm(int depth, int N , int[] order, int[][] users, int[] emoticons) {
    	if(depth == N) {
    		int sum = 0;
    		int cnt = 0;
    		for(int[] user : users) {
    			int userSum = 0;
    			for(int i = 0 ; i < N ; i++)
    				if(order[i] * 10 >= user[0])
    					userSum += emoticons[i] * (100 - order[i] * 10) / 100;
    			
    			if(userSum >= user[1])
					cnt++;
    			else
    				sum += userSum;
    		}
    		if(cnt > maxCnt) {
    			maxCnt = cnt;
    			maxSum = sum;
    		}
    		else if(cnt == maxCnt && sum > maxSum) {
    			maxSum = sum;
    		}
    	}
    	else {
    		for(int i = 1 ; i <= 4 ; i++) {
    			order[depth] = i;
    			perm(depth + 1, N, order, users, emoticons);
    		}
    	}
    }
}	