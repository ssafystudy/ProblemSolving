class 최소직사각형 {
    public int solution(int[][] sizes) {
        int lmax = 0, smax = 0;
        for(int[] size : sizes) {
        	int l = Math.max(size[0], size[1]);
        	int s = Math.min(size[0], size[1]);
        	
        	lmax = Math.max(lmax, l);
        	smax = Math.max(smax, s);
        }
        
        return lmax * smax;
    }
}