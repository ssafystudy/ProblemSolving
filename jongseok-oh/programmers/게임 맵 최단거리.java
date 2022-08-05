import java.util.*;

class Solution {
    
    int dy[] = {1,-1,0,0};
    int dx[] = {0,0,1,-1};
    
    int bfs(int[][] maps){
        Queue<info> q = new LinkedList<>();
        int r = maps.length;
        int c = maps[0].length;
        boolean visit[][] = new boolean[r][c];
        visit[0][0] = true;
        q.offer(new info(0,0,1));
        while(!q.isEmpty()){
            info t = q.poll();
            if(t.y == (r-1) && t.x == (c-1)) return t.cnt;
            
            for(int dir =0; dir<4; dir++){
                int ny = t.y + dy[dir];
                int nx = t.x + dx[dir];
                if(ny<0 || ny>= r || nx <0 || nx>=c) continue;
                if(maps[ny][nx] == 0) continue;
                if(visit[ny][nx]) continue;
                visit[ny][nx] = true;
                q.offer(new info(ny,nx,t.cnt+1));
            }
        }
        return -1;
    }
    
    public int solution(int[][] maps) {
        return bfs(maps);
    }
    
    class info{
        int y,x,cnt;
        info(int y,int x, int cnt){
            this.y=y; this.x= x; this.cnt= cnt;
        }
    }
}
