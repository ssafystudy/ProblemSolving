#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int dy[] = {1,-1,0,0}, dx[] = {0,0,1,-1};

int n,m;

// 짝수면 패배, 홀수면 승리
int solve(int cy, int cx, int ey, int ex, vector<vector<int>>& board){
    if(!board[cy][cx]) return 0;
    
    int ret = 0;
    
    for(int dir = 0; dir<4; dir++){
        int ny = cy + dy[dir], nx = cx + dx[dir];
        
        if(ny <0 || ny>= n || nx<0 || nx>=m || !board[ny][nx]) continue;
        
        board[cy][cx] = 0;
        int val = solve(ey,ex, ny,nx,board) + 1;
        board[cy][cx] = 1;
        
        if(!(ret & 1) && (val & 1)) ret = val;
        else if(!(ret & 1) && !(val & 1)) ret = max(ret, val);
        else if((ret & 1) && (val & 1)) ret = min(ret,val);
    }
    return ret;
}

int solution(vector<vector<int>> board, vector<int> aloc, vector<int> bloc) {
    n = board.size();
    m = board[0].size();
    
    return solve(aloc[0], aloc[1], bloc[0], bloc[1], board);
}