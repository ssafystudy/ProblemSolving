class k진수에서소수개수구하기 {
    public int solution(int n, int k) {
    	int answer = 0;
    	char[] num = Integer.toString(n,k).toCharArray();
    	
    	String tmp = "";
    	for(int i = 0 ; i < num.length ; i++) {
    		if(num[i] != '0')
    			tmp += num[i];
    		else if(tmp.length() != 0) {
				if(isPrime(Long.parseLong(tmp)))
					answer++;
				tmp = "";
    		}
    	}
    	
    	if(tmp.length() != 0 && isPrime(Long.parseLong(tmp)))
    		answer++;
    	
        return answer;
    }
    
    public boolean isPrime(long num) {
    	if(num == 1)
    		return false;
    	for(int i = 2 ; i <= Math.sqrt(num) ; i++)
    		if(num % i == 0)
    			return false;
    	return true;
    }
}