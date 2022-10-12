#include <string>
#include <vector>
#include <queue>
#include <algorithm>
#include <iostream>

using namespace std;

int _n;

bool isGate[50001] = {false,};
bool isSummit[50001] = {false,};

struct info{
    int nNode, cost;
    bool const operator < (info b) const{
        return this->cost > b.cost;
    }
};

vector<info> edges[50001];

int dist[50001];

int getMinIntensity(int startSummit){
    for(int i =1; i<=_n; i++) dist[i] = 1e9;
    
    dist[startSummit] = 0;
    
    priority_queue<info> q;
    q.push({startSummit, 0});
    
    // 다익스트라
    while(q.size()){
        info t = q.top(); q.pop();
        int tNode = t.nNode, tCost = t.cost;
        //cout<<tCost<<"\n";
        
        // 현재 방문노드가 문이면 코스트 리턴
        if(isGate[tNode]) return tCost;
        
        for(info& nInfo: edges[tNode]){
            int nNode = nInfo.nNode;
            if(isSummit[nNode]) continue;
            //cout<<nNode<<"\n";
            
            // intensity는 지금까지 왔던 경로에 있는 최대 등산 시간
            // 현재 코스트랑 간선의 코스트 중 큰 값으로 비교
            int nCost = max(nInfo.cost, tCost);
            
            if(dist[nNode] > nCost){
                dist[nNode] = nCost;
                q.push({nNode, nCost});
            }
        }
    }
    
    // 게이트에 도달 못했으면 무한 리턴
    return 1e9;
}


vector<int> solution(int n, vector<vector<int>> paths, vector<int> gates, vector<int> summits) {
    
    _n = n;
    
    // gate랑 summit 표시
    for(int i =0, len = gates.size(); i<len; i++) isGate[gates[i]] = true;
    for(int i =0, len = summits.size(); i<len; i++) isSummit[summits[i]] = true;
    
    // path 연결 벡터 생성
    for(auto& path: paths){
        edges[path[0]].push_back({path[1],path[2]});
        edges[path[1]].push_back({path[0],path[2]});
    }
    
    vector<int> answer(2,1e9);
    
    // 봉우리에서 시작함 -> 봉우리에서 시작해서 gate 만날 때 까지
    // 어차피 편도로 갔을 때 최적의 해였다면
    // 그 방향으로 다시 똑같이 돌아오면 여전히 최적의 해임
    for(int& summit : summits){
        int t = getMinIntensity(summit);
        if(t < answer[1]){
            answer[0] = summit;
            answer[1] = t;
        }else if(t == answer[1])
            answer[0] = answer[0] > summit? summit: answer[0];
    }
    return answer;
}