#include <iostream>
#include <queue>

#define INF (1LL*1000000 * 50000 + 1)

using namespace std;

int n, m, k;

long long dist[21][10001];

struct info
{
	int nNode, k;
	long long cost;

	bool operator < (info b) const {
		return this->cost > b.cost;
	}
};

vector<info> edges[10001];

int main() {
	ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	cin >> n >> m >> k;
	while (m--) {
		int a, b, c;
		cin >> a >> b >> c;
		edges[a].push_back({ b,0,c});
		edges[b].push_back({a,0,c});
	}

	
	for (int i = 0; i <= k; i++) for (int j = 1; j <= n; j++) {
		dist[i][j] = INF;
	}

	priority_queue<info> q;
	q.push({ 1,k,0 });
	dist[k][1] = 0;
	
	while (q.size()) {
		info t = q.top(); q.pop();

		int tNode = t.nNode;
		int tk = t.k;
		long long tCost = t.cost;

		if (dist[tk][tNode] < tCost) continue;
		if (n == tNode) { cout << tCost; return 0;}

		for (const info& nInfo : edges[tNode]) {

			int nNode = nInfo.nNode;
			int nCost = nInfo.cost;

			// 포장 해버리기
			if (tk > 0) {
				if (dist[tk - 1][nNode] > tCost) {
					dist[tk - 1][nNode] = tCost;
					q.push({nNode,tk - 1,tCost});
				}
			}

			// 포장 안하기
			if (dist[tk][nNode] > tCost + nCost) {
				dist[tk][nNode] = tCost + nCost;
				q.push({ nNode,tk, dist[tk][nNode] });
			}
		}
	}
}