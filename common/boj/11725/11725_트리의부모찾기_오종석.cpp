#include <iostream>
#include <vector>

using namespace std;

int n;

int parent[100001];

vector<int> edge[100001];

void solve(int node) {
	for (const int& nNode : edge[node]) {
		if (!parent[nNode]) {
			parent[nNode] = node;
			solve(nNode);
		}
	}
}

int main() {
	ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	cin >> n;

	for (int i = 0; i < n-1; i++) {
		int a, b;
		cin >> a >> b;
		edge[a].push_back(b);
		edge[b].push_back(a);
	}

	parent[1] = 1;
	solve(1);

	for (int i = 2; i <= n; i++) cout << parent[i] << "\n";
}