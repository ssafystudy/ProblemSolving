class _6 {
	//d l r u
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
    	String res = "";
    	int dc = 0, lc = 0, rc = 0, uc = 0, cnt = 0;
    	
    	if(r > x)
    		for(int i = 0 ; i < r - x ; i++)
    			dc++;
    	if(y > c)
    		for(int i = 0 ; i < y - c ; i++)
    			lc++;
    	if(c > y)
    		for(int i = 0 ; i < c - y ; i++)
    			rc++;
    	if(x > r)
    		for(int i = 0 ; i < x - r ; i++)
    			uc++;
    	
    	cnt = dc + lc + rc + uc;
    	if((k - cnt) % 2 != 0 || cnt > k)
    		return "impossible";
    	
    	if(cnt == k) {
    		for(int i = 0 ; i < dc ; i++)
        		res += "d";
        	for(int i = 0 ; i < lc ; i++)
        		res += "l";
        	for(int i = 0 ; i < rc ; i++)
        		res += "r";
        	for(int i = 0 ; i < uc ; i++)
        		res += "u";
        	return res;
    	}
    	
    	while(x + dc < n) {
			dc++;
			uc++;
			cnt += 2;
			if(cnt == k) {
				for(int j = 0 ; j < dc ; j++)
	        		res += "d";
	        	for(int j = 0 ; j < lc ; j++)
	        		res += "l";
	        	for(int j = 0 ; j < rc ; j++)
	        		res += "r";
	        	for(int j = 0 ; j < uc ; j++)
	        		res += "u";
	        	return res;
			}
		}
    	
		while(y - lc > 1) {
			lc++;
			rc++;
			cnt += 2;
			if(cnt == k) {
				for(int j = 0 ; j < dc ; j++)
	        		res += "d";
	        	for(int j = 0 ; j < lc ; j++)
	        		res += "l";
	        	for(int j = 0 ; j < rc ; j++)
	        		res += "r";
	        	for(int j = 0 ; j < uc ; j++)
	        		res += "u";
	        	return res;
			}
		}
    	
    	for(int i = 0 ; i < dc ; i++)
    		res += "d";
    	for(int i = 0 ; i < lc ; i++)
    		res += "l";
    	
		while(true) {
			res += "rl";
			cnt += 2;
			if(cnt == k) {
	        	for(int j = 0 ; j < rc ; j++)
	        		res += "r";
	        	for(int j = 0 ; j < uc ; j++)
	        		res += "u";
	        	return res;
			}
		}
    }
}