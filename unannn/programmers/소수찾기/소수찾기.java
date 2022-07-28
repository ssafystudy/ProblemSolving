import java.util.*;

class Solution {
    
    private char[] numberChars;
    private boolean[] visited;

    public int solution(String numbers) {
        
        Set<Integer> numberSet = new HashSet<>();
        numberChars = numbers.toCharArray();
        visited = new boolean[numberChars.length];

        for(int i = 1, length = numbers.length(); i <= length; i++ ){
            
            Set<Integer> set = new HashSet<>();
            permutation(set, "",0, i);
            numberSet.addAll(set);
            
            Arrays.fill(visited, false);
        }
        
        return (int) numberSet.stream()
                .filter(this::isPrime)
                .count();
    }

    public void permutation(Set<Integer> set, String s,int level , int N){
        if(level == N){
            set.add(Integer.parseInt(s));
            return;
        }

        for(int i = 0; i < numberChars.length; i++){
            if(!visited[i]){
                visited[i] = true;
                permutation(set, s + numberChars[i], level + 1, N);
                visited[i] = false;
            }
        }
    }

    private boolean isPrime(int n){
        if(n == 0 || n == 1) return false;
        double N = Math.sqrt(n);
        for(int i = 2; i <= N; i++){
            if(n % i == 0) return false;
        }
        return true;
    }
}