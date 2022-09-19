#include <string>
#include <vector>
#include <queue>
#include <cstring>

using namespace std;

int dy[] = {1,-1,0,0}, dx[] = {0,0,1,-1};

struct loc{int y = -1, x = -1;};

loc cardLoc[13];
bool visit[7];

int cardCnt = 0;

bool moveVisit[4][4];

bool isValid(int y, int x){
    return y>=0 && y<4 && x>=0 && x<4;
}

int getMinCost(int r, int c, int card, vector<vector<int>>& board){
    memset(moveVisit, 0, sizeof(moveVisit));

    int endR = cardLoc[card].y,
        endC = cardLoc[card].x;

    queue<loc> q; q.push({r,c});
    moveVisit[r][c] = true;

    int cost = 0;
    while(true){
        int qSize = q.size();
        while(qSize--){
            loc t = q.front(); q.pop();
            int ty = t.y, tx = t.x;

            if(ty == endR && tx == endC){
                board[endR][endC] = 0;
                return cost;
            }

            for(int dir = 0; dir<4; dir++){
                int ny = ty + dy[dir],
                    nx = tx + dx[dir];
                if(!isValid(ny,nx)) continue;
                // move
                if(!moveVisit[ny][nx]){
                    q.push({ny,nx});
                    moveVisit[ny][nx] = true;
                }

                ny = ty; nx = tx;
                // Ctrl move
                while (isValid(ny + dy[dir], nx + dx[dir])) {
                    ny += dy[dir]; nx += dx[dir];
                    if (board[ny][nx]) break;
                }
                if(!moveVisit[ny][nx]){
                    q.push({ny,nx});
                    moveVisit[ny][nx] = true;
                }
            }
        }
        cost++;
    }
}

int solve(vector<vector<int>>& board, int r, int c){
    if(!cardCnt) return 0;

    int ret = 1e9;

    for(int i = 1; i<7; i++){
        if(!visit[i]){
            visit[i] = true;
            int otherCard = (i + 5) % 12 + 1;

            cardCnt--;
            // r,c -> i.y, i.x -> other.y , other.x
            int dist = getMinCost(r,c,i,board) + getMinCost(cardLoc[i].y, cardLoc[i].x, otherCard, board);
            ret = min(ret, solve(board, cardLoc[otherCard].y, cardLoc[otherCard].x) + dist + 2);

            board[cardLoc[otherCard].y][cardLoc[otherCard].x] = i;
            board[cardLoc[i].y][cardLoc[i].x] = i;

             // r,c -> other.y , other.x -> i.y, i.x
            dist = getMinCost(r,c,otherCard,board)
                + getMinCost(cardLoc[otherCard].y, cardLoc[otherCard].x, i, board);

            ret = min(ret, solve(board, cardLoc[i].y, cardLoc[i].x) + dist + 2);

            board[cardLoc[otherCard].y][cardLoc[otherCard].x] = i;
            board[cardLoc[i].y][cardLoc[i].x] = i;

            visit[i] = false;
            cardCnt++;
        }
    }
    return ret;
}

int solution(vector<vector<int>> board, int r, int c) {
    for(int i = 1; i<7; i++) visit[i] = true;

    for(int i =0; i<4; i++) for(int j =0; j<4; j++){
        if(board[i][j]){
            if(cardLoc[board[i][j]].y == -1){
                cardLoc[board[i][j]] = {i,j};
                cardCnt++;
                visit[board[i][j]] = false;
            }else cardLoc[board[i][j] + 6] = {i,j};

        }
    }
    return solve(board,r,c);
}