class 양궁대회 {
	int[] minArr = new int[11];
	boolean[] winPos = new boolean[11];
	int[] answer;
	int max = -100;
	
	public int[] solution(int n, int[] info) {
		for(int i = 0 ; i < 11 ; i++)
			minArr[i] = info[i] + 1;
    	
		selectPos(0, 0, n, info);
		
		if(max <= 0)
			return new int[]{-1};
        return answer;
    }
	
	public void selectPos(int depth, int start, int n, int[] info) {
		if(depth > 0) {
			int[] info1 = new int[11];
			int cnt = 0;
			for(int i = 0 ; i < 10 ; i++)
				if(winPos[i]) {
					info1[i] = minArr[i];
					cnt += info1[i];
				}
			if(cnt > n)
				return;
			info1[10] = n - cnt;
			
			int score = calc(info1, info);
			if(score > max) {
				max = score;
				answer = info1;
			}
			else if(score == max) {
				for(int i = 10 ; i >= 0 ; i--) {
					if(info1[i] > answer[i]) {
						answer = info1;
						break;
					}
					else if(info1[i] < answer[i])
						break;
				}
			}
		}
		for(int i = start ; i < 10 ; i++) {
			winPos[i] = true;
			selectPos(depth + 1, i + 1, n, info);
			winPos[i] = false;
		}
	}
	
	public int calc(int[] info1, int[] info2) {
		int res = 0;
		
		for(int i = 0 ; i < 10 ; i++) {
			if(info1[i] > info2[i])
				res += 10 - i;
			else if(info2[i] != 0)
				res -= 10 - i;
		}
		
		return res;
	}
}