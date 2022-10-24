#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int n;

struct info {int node, cost;};

vector<info> tree[10001];

pair<int,int> dfs(int node, int parent) {
	
	int ret = 0;
	int deepNode = -1;

	for (const auto& n : tree[node]) {
		if (n.node == parent) continue;
		pair<int, int> tp = dfs(n.node, node);
		if (tp.first + n.cost > ret) {
			ret = tp.first + n.cost;
			deepNode = tp.second;
		}
	}
	if (ret == 0) return { ret, node };
	else return { ret, deepNode };
}

int main() {
	ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	cin >> n;
	for (int i = 0; i < n - 1; i++) {
		int a, b, c;
		cin >> a >> b >> c;
		tree[a].push_back({ b,c });
		tree[b].push_back({ a,c });
	}

	pair<int, int> deep = dfs(1, -1);
	cout << dfs(deep.second, -1).first;
}