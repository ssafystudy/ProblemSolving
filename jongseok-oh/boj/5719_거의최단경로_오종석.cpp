#include <iostream>
#include <queue>
#include <cstring>

using namespace std;

int n, m, s, d;

struct info {
	int node, cost;

	bool operator < (info b)const {
		return this->cost > b.cost;
	}
};

vector<info> edges[500];
vector<info> revEdges[500];

int dist[500];
bool deletedEdges[500][500];

int nodeTrace[500];
bool visit[500][500];

void trace(int node, int idx) {
	nodeTrace[idx] = node;
	if (node == s) {
		for (int i = idx; i >= 1; i--) {
			deletedEdges[nodeTrace[i]][nodeTrace[i - 1]] = true;
		}
		return;
	}

	for (const info& tinfo : revEdges[node]) {
		int prevNode = tinfo.node;
		int preveCost = tinfo.cost;

		if (visit[node][prevNode]) {
			for (int i = idx; i >= 1; i--) {
				deletedEdges[nodeTrace[i]][nodeTrace[i - 1]] = true;
			}
			continue;
		}
		if ((dist[node] - preveCost) == dist[prevNode]) {
			visit[node][prevNode] = true;
			trace(prevNode, idx + 1);
		}
	}
}

int main() {
	ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	
	while (1) {
		cin >> n >> m;
		if (n == 0 && m == 0) break;
		cin >> s >> d;

		for (int i = 0; i < n; i++) {
			edges[i].clear();
			revEdges[i].clear();
		}

		while (m--) {
			int u, v, p;
			cin >> u >> v >> p;

			edges[u].push_back({ v,p });
			revEdges[v].push_back({ u,p });
		}

		for (int i = 0; i < n; i++) dist[i] = 1e9;

		priority_queue<info> q;
		q.push({ s,0 });
		dist[s] = 0;
		
		while (q.size()) {
			info t = q.top(); q.pop();
			int tNode = t.node;
			int tCost = t.cost;

			if (dist[tNode] < tCost) continue;

			for (const info& tinfo : edges[tNode]) {
				int nNode = tinfo.node;
				int nCost = tinfo.cost;

				if (dist[nNode] > tCost + nCost) {
					dist[nNode] = tCost + nCost;
					q.push({ nNode, dist[nNode]});
				}
			}
		}

		if (dist[d] == 1e9) { cout << -1<<"\n"; continue;}

		memset(deletedEdges, 0, sizeof(deletedEdges));
		memset(visit, 0, sizeof(visit));
		trace(d, 0);

		for (int i = 0; i < n; i++) dist[i] = 1e9;
		q.push({ s,0 });
		dist[s] = 0;

		bool find = false;

		while (q.size()) {
			info t = q.top(); q.pop();
			int tNode = t.node;
			int tCost = t.cost;

			if (tNode == d) {
				find = true;
				cout << tCost << "\n";
				break;
			}

			if (dist[tNode] < tCost) continue;

			for (const info& tinfo : edges[tNode]) {
				int nNode = tinfo.node;
				int nCost = tinfo.cost;

				if (deletedEdges[tNode][nNode]) continue;

				if (dist[nNode] > tCost + nCost) {
					dist[nNode] = tCost + nCost;
					q.push({ nNode, dist[nNode] });
				}
			}
		}
		if (!find) cout << -1 << "\n";
	}
}