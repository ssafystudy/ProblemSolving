#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int dp[151][151];

int solution(int alp, int cop, vector<vector<int>> problems) {   
    int targetAlp = alp, targetCop = cop;
    
    // 필요한 Alp와 Cop을 구하기
    for(const auto& problem: problems){
        targetAlp = max(problem[0], targetAlp);
        targetCop = max(problem[1], targetCop);
    }
    
    for(int i =alp; i<= targetAlp; i++)
        for(int j =cop; j<=targetCop; j++) dp[i][j] = 1e9;
    
    dp[alp][cop] = 0;
    
    for(int i = alp; i<=targetAlp; i++) for(int j =cop; j<=targetCop; j++){
        
        // 순수 알고 공부
        if(i + 1<= targetAlp)
            dp[i + 1][j] = min(dp[i][j] + 1, dp[i+1][j]);
        // 순수 코딩 공부
        if(j + 1<= targetCop)
            dp[i][j+1] = min(dp[i][j] + 1, dp[i][j+1]);
        
        // 모든 문제 순회하면서
        for(const auto& problem : problems){
            int alp_req = problem[0],
                cop_rep = problem[1];
            
            if(i >= alp_req && j >= cop_rep){
                
                // 얻는 알고력, 코딩력이 max 값보다 크면 max값으로 갱신
                int nAlp = min(targetAlp, i + problem[2]),
                    nCop = min(targetCop, j + problem[3]);
                
                dp[nAlp][nCop] = min(dp[i][j] + problem[4], dp[nAlp][nCop]);
            }
        }
    }
    return dp[targetAlp][targetCop];
}