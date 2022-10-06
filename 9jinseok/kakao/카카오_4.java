class _4 {
	public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        
        for(int i = 0 ; i < numbers.length ; i++) {
        	String b = Long.toBinaryString(numbers[i]);
        	long l = 2;
        	while(true) {
        		if(b.length() == l - 1)
        			break;
        		else if(b.length() < l - 1) {
        			b = String.format("%1$" + (l-1) + "s", b).replace(' ', '0');
        			break;
        		}
        		l *= 2;
        	}
        	System.out.println(b);
        	if(isTree(b))
        		answer[i] = 1;
        	else
        		answer[i] = 0;
        }
        
        return answer;
    }
	
	public boolean isEmpty(String b) {
		for(char c : b.toCharArray())
			if(c == '1')
				return false;
		
		return true;
	}
	
	public boolean isTree(String b) {
		if(b.length() == 1)
			return true;
		if(b.length() == 3) {
			if(b.charAt(1) == '1')
				return true;
			else if(isEmpty(b))
				return true;
			else
				return false;
		}
        int mid = b.length()/2;
        if(!isTree(b.substring(0, mid)) || !isTree(b.substring(mid + 1)))
        	return false;
        
        if((!isEmpty(b.substring(0, mid)) || !isEmpty(b.substring(mid + 1))) && b.charAt(mid) == '0')
        	return false;
        
        return true;
	}
}