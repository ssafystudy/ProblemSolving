#include <iostream>
#include <queue>
#include <vector>

using namespace std;

int n, m;

int edges[1001][1001];
int dist[1001];
int parent[1001];

struct info {
	int nNode, cost;

	bool operator < (info b) const {
		return this->cost > b.cost;
	}
};

void trace(int tnode, vector<int>& vec) {
	vec.push_back(tnode);
	if (parent[tnode] == tnode) return;
	trace(parent[tnode], vec);
}

int main() {
	ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	cin >> n >> m;

	for (int i = 1; i <= n; i++) for (int j = 1; j <= n; j++) edges[i][j] = 1e9;
	for (int i = 1; i <= n; i++) dist[i] = 1e9;
	for (int i = 1; i <= n; i++) parent[i] = i;

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
		if (tnode == end) { cout << tcost<<"\n"; break; }

		for (int i = 1; i <= n; i++) {
			if (edges[tnode][i] == 1e9) continue;
			
			if (dist[i] > edges[tnode][i] + tcost) {
				dist[i] = edges[tnode][i] + tcost;
				parent[i] = tnode;
				q.push({ i, dist[i]});
			}
		}
	}

	vector<int> vec;
	trace(end, vec);

	cout << vec.size() << "\n";
	
	for (auto it = vec.rbegin(); it != vec.rend(); it++) cout << *it<<" ";
}