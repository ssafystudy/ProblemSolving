#include <string>
#include <vector>
#include <unordered_set>

using namespace std;

int answer = 0;

vector<int> child [17];

void solve(int node, int sheep, int wolf,
           const vector<int>& info, const unordered_set<int>& nextNodes){
    sheep += info[node] ^ 1;
    wolf += info[node];
    
    if(sheep <= wolf) return;
    
    answer = answer < sheep ? sheep : answer;
    
    for(int nNode: nextNodes){
        unordered_set<int> temp = nextNodes;
        
        temp.erase(nNode);
        for(int c : child[nNode]) temp.insert(c);
        solve(nNode, sheep, wolf, info, temp);
    }
}

int solution(vector<int> info, vector<vector<int>> edges) {
    
    for(auto& edge: edges)
        child[edge[0]].push_back(edge[1]);
    
    unordered_set<int> nNodes;
    
    for(int n: child[0]) nNodes.insert(n);
    
    solve(0,0,0,info, nNodes);
    
    return answer;
}