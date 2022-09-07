#include <string>
#include <vector>

using namespace std;

int ans[11];
int tempAns[11];
int max_diff = -1;

void solve(int n, const vector<int>& info, int idx){
    if(n == 0){
        int apeachSum = 0, lionSum = 0;
        
        for(int i =0; i<10; i++){
            if(tempAns[i] > info[i]) {lionSum += 10-i; continue;}
            if(tempAns[i] == 0 && info[i] == 0) continue;
            if(tempAns[i] <= info[i]) apeachSum += 10-i;
        }
        int diff = lionSum - apeachSum;
        if(diff != 0 && diff != -1 && diff >= max_diff){
            if(diff > max_diff){
                max_diff = diff;
                for(int i =0; i<11; i++) ans[i] = tempAns[i];
            }
            else{
                bool flag = false;
                
                for(int i = 10; i>-1; i--){
                    if(tempAns[i]>ans[i]){flag = true; break;}
                    if(tempAns[i]<ans[i])break; // 이거 없으면 왜 안되는거지?..
                    // 아!!
                }
                if(flag) for(int i =0; i<11; i++) ans[i] = tempAns[i];
            }
        }
        return;
    }
    
    for(int i = idx; i<11; i++){
        if(i == 10){
            tempAns[10] = n;
            solve(0,info, i+1);
            tempAns[10] = 0;
            return;
        }
        int toWin = info[i] + 1;
        if(toWin <= n){
            tempAns[i] = toWin;
            solve(n - toWin, info, i+1);
            tempAns[i] = 0;
        }
    }
}

vector<int> solution(int n, vector<int> info) {
    vector<int> answer;
    solve(n, info, 0);
    if(max_diff == -1) answer.push_back(-1);
    else{
        for(int i =0; i<11; i++) answer.push_back(ans[i]);
    }
    return answer;
}