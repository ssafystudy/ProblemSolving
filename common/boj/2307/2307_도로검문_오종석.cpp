#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int n, m;

struct info {
	int node, cost;
	bool operator < (info b) const{
		return this->cost > b.cost;
	}
};

vector<info> edges[1001];
vector<pair<int, int>> edgeConnect;

int dist[1001];

int dijkstra(int edgeIdx) {
	for (int i = 1; i <= n; i++) dist[i] = 1e9;

	dist[1] = 0;
	priority_queue<info> q;
	q.push({ 1,0 });

	int blockNode1, blockNode2;
	
	if (edgeIdx == -1) {
		blockNode1 = -1;
		blockNode2 = -1;
	}
	else {
		blockNode1 = edgeConnect[edgeIdx].first;
		blockNode2 = edgeConnect[edgeIdx].second;
	}

	while (q.size()) {
		info t = q.top(); q.pop();

		int tnode = t.node,
			tcost = t.cost;

		if (tnode == n) return tcost;
		if (dist[tnode] > tcost) continue;

		for (const auto& next : edges[tnode]) {
			int nnode = next.node,
				ncost = next.cost;

			if ((tnode == blockNode1 && nnode == blockNode2)||
				(tnode == blockNode2 && nnode == blockNode1)) continue;

			if (dist[nnode] > ncost + tcost) {
				dist[nnode] = ncost + tcost;

				q.push({ nnode, dist[nnode] });
			}
		}
	}
	return 1e9;
}

int main() {
	ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	cin >> n >> m;


	
	for (int i = 0; i < m; i++) {
		int a, b, c;
		cin >> a >> b >> c;
		
		edges[a].push_back({ b,c });
		edges[b].push_back({ a,c });

		edgeConnect.push_back({ a,b });
	}

	int shortLen = dijkstra(-1);
	int ans = -1;

	for (int i = 0; i < edgeConnect.size(); i++) {
		int tt = dijkstra(i);

		if (tt == 1e9) { cout << -1; return 0; }

		if (tt - shortLen > ans) ans = tt - shortLen;
	}
	cout << ans;
}