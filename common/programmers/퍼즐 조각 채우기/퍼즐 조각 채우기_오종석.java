import java.util.*;

class Solution {
    
    int dy[] = {0,1,0,-1}, dx[] = {1,0,-1,0};
    int n;
    
    class loc implements Comparable<loc>{
        int y,x;
        
        public loc(int y, int x){this.y = y; this.x = x;}
        
        @Override
		public int compareTo(loc o) {
			int cmp = this.y - o.y;
            if(cmp == 0) return this.x - o.x;
            else return cmp;
		}
    }
    
    List<loc> getThings(int [][] board, int b, int y, int x){
        List<loc> l = new ArrayList<>();
        
        Queue<loc> q = new ArrayDeque();
        
        int d = b^1;
        
        q.offer(new loc(y,x)); board[y][x] = d;
        l.add(q.peek());
        
        while(!q.isEmpty()){
            loc t = q.poll();
            int ty = t.y, tx = t.x;
            
            for(int dir =0; dir<4; dir++){
                int ny = ty + dy[dir], nx = tx + dx[dir];
                
                if(ny<0 || ny>= n || nx<0 || nx>=n) continue;
                if(board[ny][nx] == d) continue;
                loc tt = new loc(ny,nx);
                q.offer(tt); board[ny][nx] = d;
                l.add(tt);
            }
        }
        
        Collections.sort(l);
        // l.forEach(ff -> System.out.println(ff.y + " " + ff.x));
        // System.out.println();
           
        loc t = l.get(0);
        int ty = t.y, tx = t.x;
        
        for(loc ll : l){
            ll.y -= ty;
            ll.x -= tx;
        }
        return l;
    }
    
    public int solution(int[][] game_board, int[][] table) {    
        n = game_board.length;
        
        List<List<loc>> wholes = new ArrayList<>();
        for(int i =0; i<n; i++){
            for(int j =0; j<n; j++){
                if(game_board[i][j] == 0){
                    List<loc> whole = getThings(game_board, 0, i,j);
                    // whole.forEach(l -> System.out.println(l.y + " " + l.x));
                    // System.out.println();
                    wholes.add(whole);
                }
            }
        }
        
        List<List<loc>[]> rotateBlocks = new ArrayList<>();
        
        for(int i =0; i<n; i++){
            for(int j =0; j<n; j++){
                if(table[i][j] == 1){
                    List<loc> Block = getThings(table, 1, i,j);
                    List<loc>[] Blocks = getRoatedBlocks(Block);
                    // for(int k =0; k<4; k++){
                    //     Blocks[k].forEach(l -> System.out.println(l.y + " " + l.x));
                    //     System.out.println();
                    // }
                    rotateBlocks.add(Blocks);
                }
            }
        }
        
        int BlockNum = rotateBlocks.size();
        boolean used[] = new boolean[BlockNum];
        
        int answer = 0;
            
        for(List<loc> whole: wholes){
            LOOP:
            for(int i = 0; i<BlockNum; i++){
                if(!used[i]){
                    List<loc>[] rotBlocks = rotateBlocks.get(i);
                    if(rotBlocks[0].size() != whole.size()) continue;
                    for(int j = 0; j<4; j++){
                        List<loc> Block = rotBlocks[j];
                        boolean same = true;
                        for(int k =0; k<Block.size(); k++){
                            if(whole.get(k).y != Block.get(k).y 
                               ||whole.get(k).x != Block.get(k).x){
                                same = false; break;
                            }
                        }
                        if(same){
                            used[i] = true;
                            answer += Block.size();
                            break LOOP;
                        }
                    }
                }
            }
        }
        return answer;
    }
    
    List<loc>[] getRoatedBlocks(List<loc> Block){
        List<loc>[] ret = new List[4];
        ret[0] = Block;
        
        for(int i = 1; i<4; i++){
            List<loc> pBlock= ret[i-1];
            List<loc> tBlock = new ArrayList<>();
            for(loc tl: pBlock){
                tBlock.add(new loc(tl.x, -tl.y));
            }
            Collections.sort(tBlock);
            loc tloc = tBlock.get(0);
            int ty = tloc.y, tx = tloc.x;
            for(loc tl : tBlock){
                tl.y -= ty;  tl.x -= tx;
            }
            ret[i] = tBlock;
        }
        return ret;
    }
}