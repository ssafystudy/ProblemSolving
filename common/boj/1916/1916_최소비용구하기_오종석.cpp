#include <iostream>
#include <queue>

using namespace std;

int n, m;

int edges[1001][1001];
int dist[1001];

struct info {
	int nNode, cost;

	bool operator < (info b) const {
		return this->cost > b.cost;
	}
};

int main() {
	ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	cin >> n >> m;

	for (int i = 1; i <= n; i++) for (int j = 1; j <= n; j++) edges[i][j] = 1e9;
	for (int i = 1; i <= n; i++) dist[i] = 1e9;

	while (m--) {
		int a, b, c;
		cin >> a>>b>>c;

		edges[a][b] = edges[a][b] > c ? c : edges[a][b];
	}

	int start, end;
	cin >> start >> end;
	
	priority_queue<info> q;
	q.push({ start, 0 });
	dist[start] = 0;

	while (q.size())
	{
		info t = q.top(); q.pop();
		
		int tnode = t.nNode;

		int tcost = t.cost;
		if (tnode == end) { cout << tcost; return 0;}

		for (int i = 1; i <= n; i++) {
			if (edges[tnode][i] == 1e9) continue;
			
			if (dist[i] > edges[tnode][i] + tcost) {
				dist[i] = edges[tnode][i] + tcost;
				q.push({ i, dist[i]});
			}
		}
	}
}