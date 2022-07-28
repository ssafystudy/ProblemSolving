import java.util.*;

class Solution {
    
    private List<String> strs = new ArrayList<>();
    private boolean[] visited;
        
    public String solution(int[] numbers) {        
        
        visited = new boolean[numbers.length];
        
        permutation(numbers, "", 0);

        return strs.stream()
            .max((o1, o2) -> o1.compareTo(o2))
            .get();
    }
    
    private void permutation(int[] numbers, String str, int depth){
        if(numbers.length == depth){
            strs.add(str);
            return;
        } 
        
        for(int i = 0; i < numbers.length;i++){
            if(visited[i] == false){
                visited[i] = true;
                permutation(numbers, str + numbers[i], depth + 1);
                visited[i] = false;
            } 
        }
    }
}