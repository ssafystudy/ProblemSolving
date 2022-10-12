#include <string>
#include <vector>

using namespace std;

int dist[201][201];

int solution(int n, int s, int a, int b, vector<vector<int>> fares) {
    for(int i =1; i<=n; i++) for(int j =1; j<=n; j++){
        if(i == j) dist[i][j] = 0;
        else dist[i][j] = 1e8;
    }
    
    for(const auto& f : fares){
        dist[f[0]][f[1]] = f[2];
        dist[f[1]][f[0]] = f[2];
    }
    
    for(int mid = 1; mid<=n; mid++){
        for(int i = 1; i<=n; i++){
            if(mid == i) continue;
            for(int j =1; j<=n; j++){
                if(mid == j) continue;
                if(i == j) continue;
                
                if(dist[i][j] > dist[i][mid] + dist[mid][j])
                    dist[i][j] = dist[i][mid] + dist[mid][j];
            }
        }
    }
    
    int answer;
    answer = dist[s][a] + dist[s][b];
    
    for(int i = 1; i<=n; i++){
        if(i == s) continue;
        int tCost = dist[s][i] + dist[i][a] + dist[i][b];
        answer = answer > tCost? tCost : answer;
    }
    
    return answer;
}