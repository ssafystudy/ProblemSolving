class 피로도_고진석 {
	int p, k, answer;
	int[][] dungeons;
	
    public int solution(int k, int[][] dungeons) {
        this.p = k;
        this.k = k;
        this.dungeons = dungeons;
        int N = dungeons.length;
        int[] arr = new int[N];
        for(int i = 0 ; i < N ; i++)
        	arr[i] = i;
        
        permutation(arr, 0, N, N);
        
        return answer;
    }

    public void permutation(int[] arr, int depth, int n, int r) {
        if (depth == r) {
        	int cnt = 0;
        	this.p = k;
        	for(int i : arr) {
        		if(p < dungeons[i][0]) {
        			answer = Math.max(cnt, answer);
        			return;
        		}
        		else {
        			cnt++;
        			p -= dungeons[i][1];
        		}
        	}
        	answer = Math.max(cnt, answer);
			return;
        }
     
        for (int i=depth; i<n; i++) {
            swap(arr, depth, i);
            permutation(arr, depth + 1, n, r);
            swap(arr, depth, i);
        }
    }
    
    public void swap(int[] arr, int a, int b) {
    	int tmp = arr[a];
    	arr[a] = arr[b];
    	arr[b] = tmp;
    	return;
    }
}