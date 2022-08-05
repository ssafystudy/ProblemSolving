import java.util.*;

class Solution {
    
    void bfs(int[][] computers, int node){
        
        Queue<Integer> q = new LinkedList<>();
        
        q.offer(node); visit[node] = true;
        while(!q.isEmpty()){
            int tNode = q.poll();
            for(int i = 0; i<computers.length; i++){
                if(i == tNode) continue;
                if(computers[tNode][i] == 1 && !visit[i]){
                    visit[i] = true;
                    q.offer(i);
                }
            }
        }
    }
    
    boolean visit[];
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        visit = new boolean[n];
        
        for(int i =0; i<n; i++){
            if(!visit[i]){
                answer++;
                bfs(computers, i);
            }
        }
        return answer;
    }
}
