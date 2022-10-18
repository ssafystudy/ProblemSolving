#include <iostream>
#include <vector>
#include <cstring>

using namespace std;

typedef pair<int, int> pii;

int v;

vector<pii> edges[100001];

int deep = -1, _maxlen = 0;

bool visit[100001];

void dfs(int edge, int tlen) {
	if (tlen > _maxlen) {
		deep = edge; _maxlen = tlen;
	}
	visit[edge] = true;
	for (auto& n : edges[edge]) {
		int nnode = n.first, cost = n.second;
		if (visit[nnode]) continue;
		dfs(nnode, tlen + cost);
	}
}

int main() {
	ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	cin >> v;
	for (int i = 0; i < v; i++) {
		int me; cin >> me;
		int a, b;
		while (true) {
			cin >> a; if (a == -1) break;
			cin >> b;
			edges[me].push_back({ a,b });
		}
	}
	dfs(1, 0);
	memset(visit, 0, v + 1);
	_maxlen = 0;
	dfs(deep, 0);
	cout << _maxlen;
}