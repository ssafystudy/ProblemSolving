#include <iostream>
#include <algorithm>

using namespace std;

int n,k;

int train[50001];

int dp[50001][4];

int solve(int start, int cnt) {
	if (start > n) return 0;
	if (cnt == 0) return 0;
	int& ret = dp[start][cnt];
	if (ret != 0) return ret;
	
	int end = min(start - 1 + k, n);

	ret = max(solve(start + 1,cnt), train[end] - train[start - 1] + solve(start + k, cnt - 1));
	return ret;
}

int main() {
	ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	cin >> n;
	for (int i = 1; i <=n; i++) {
		cin >> train[i]; train[i] += train[i - 1];
	}
	cin >> k;
	cout << solve(1, 3);
}