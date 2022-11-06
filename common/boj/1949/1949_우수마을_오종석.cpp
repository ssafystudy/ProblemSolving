#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int n;

int village[10001];

int dp[10001][2];

bool visit[10001];
vector<int> edges[10001];

void solve(int t) {
	dp[t][0] = 0;
	dp[t][1] = village[t];
	visit[t] = true;
	for (int& next : edges[t]) {
		if (visit[next]) continue;
		solve(next);
		dp[t][0] += max(dp[next][0], dp[next][1]);
		dp[t][1] += dp[next][0];
	}
}

int main() {
	ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	cin >> n;
	for (int i = 1; i <=n; i++) cin >> village[i];
	for (int i = 0; i < n - 1; i++) {
		int a, b; cin >> a >> b;
		edges[a].push_back(b);
		edges[b].push_back(a);
	}

	solve(1);
	cout << max(dp[1][0], dp[1][1]);
}