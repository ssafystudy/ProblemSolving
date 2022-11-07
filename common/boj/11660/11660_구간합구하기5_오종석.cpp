#include <iostream>

using namespace std;

int n,m;

int sum[1025][1025];

int main() {
	ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	cin >> n>>m;

	for (int i = 1; i <= n; i++) for (int j = 1; j <= n; j++) {
		cin >> sum[i][j];
		sum[i][j] += sum[i][j - 1];
	}

	for (int i = 1; i <= n; i++) for (int j = 1; j <= n; j++)
		sum[j][i] += sum[j-1][i];
	
	while (m--) {
		int x1, y1, x2, y2;
		cin >> y1 >> x1 >> y2 >> x2;
		int ans = sum[y2][x2] - sum[y2][x1 - 1] - sum[y1 - 1][x2] + sum[y1 - 1][x1 - 1];
		cout << ans << "\n";
	}
}