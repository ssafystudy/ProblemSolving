#include <string>
#include <vector>

using namespace std;

int chk[1001][1001];
int n,m;

int solution(vector<vector<int>> board, vector<vector<int>> skill) {
    int answer = 0;
    
    n = board.size();
    m = board[0].size();
    
    for(const auto& sk: skill){
        int d = sk[5];
        d = sk[0] == 1? -d:d;
        chk[sk[1]][sk[2]] += d;
        chk[sk[1]][sk[4] + 1] -= d;
        chk[sk[3] + 1][sk[2]] -= d;
        chk[sk[3] + 1][sk[4] + 1] += d;
    }
    
    for(int i = 0; i<n; i++) for(int j =0; j<m; j++) chk[i][j + 1] += chk[i][j];
    for(int j = 0; j<m; j++) for(int i = 0; i<m; i++) chk[i +1][j] += chk[i][j];
    for(int i =0; i<n; i++) for(int j =0; j<m; j++) if(board[i][j] + chk[i][j]>0) answer++;
    
    return answer;
}