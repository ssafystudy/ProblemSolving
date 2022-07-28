import java.util.*;

class 소수찾기_고진석 {
	Set<Integer> set = new HashSet<>();
	
    public int solution(String numbers) {
    	
    	int N = numbers.length();
    	int[] arr = new int[N];
    	for(int i = 0 ; i < N ; i++)
    		arr[i] = numbers.charAt(i) - '0';
    	
    	for(int i = 1 ; i <= N ; i++) {
    		permutation(arr, 0, N, i);
    	}
    	
        return set.size();
    }
    
    public void permutation(int[] arr, int depth, int n, int r) {
        if (depth == r) {
        	String s = "";
        	for(int i = 0 ; i < r ; i++)
        		s += String.valueOf(arr[i]);
        	if(isPrime(Integer.parseInt(s)))
        		set.add(Integer.parseInt(s));
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
    
    public boolean isPrime(int num) {
    	if(num < 2)
    		return false;
    	for(int i = 2 ; i <= Math.sqrt(num) ; i++) {
    		if(num % i == 0)
    			return false;
    	}
    	return true;
    }
}