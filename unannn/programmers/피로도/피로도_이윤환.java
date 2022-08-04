import java.util.*;

class Solution {
    
    List<Integer> list = new ArrayList<>();
    boolean[] visited;

    public int solution(int k, int[][] dungeons) {

        visited = new boolean[dungeons.length];

        permutation(dungeons, k, 0, 0);

        return list.stream()
                .max(Integer::compareTo)
                .get()
                .intValue();
    }

    private void permutation(int[][] dungeons, int k, int n, int level){
        if(level == visited.length){
            list.add(n);
            return;
        }

        for(int i = 0;i < visited.length;i++){
            if(!visited[i]){
                visited[i] = true;
                if(k >= dungeons[i][0]){
                    permutation(dungeons, k - dungeons[i][1], n + 1, level + 1);
                } else{
                    permutation(dungeons, k, n, level + 1);
                }
                visited[i] = false;
            }
        }
    }
}