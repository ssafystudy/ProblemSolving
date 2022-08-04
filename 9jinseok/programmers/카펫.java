class 카펫 {
    public int[] solution(int brown, int yellow) {
        for(int i = 1 ; i <= Math.sqrt(yellow) ; i++) {
        	if(yellow % i != 0)
        		continue;
        	int yH = i;
        	int yW = yellow / i;
        	if(brown == 2 * yH + 2 * yW + 4) {
        		int[] res = new int[2];
        		res[0] = yW + 2;
        		res[1] = yH + 2;
        		return res;
        	}
        }
        return null;
    }
}