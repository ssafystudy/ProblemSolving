#include <iostream>
#include <cstring>

using namespace std;

int n,m;

int dy[] = { 1,-1,0,0 }, dx[] = { 0,0,1,-1 };

int dp[500][500];
int map[500][500];

int solve(int y, int x) {
	if (y == n - 1 && x == m - 1) return 1;
	int& ret = dp[y][x];
	if (ret != -1) return ret;

	ret = 0;

	for (int dir = 0; dir < 4; dir++) {
		int ny = y + dy[dir],
			nx = x + dx[dir];
		if (ny < 0 || ny >= n || nx < 0 || nx >= m) continue;
		if (map[ny][nx] >= map[y][x]) continue;
		ret += solve(ny, nx);
	}
	return ret;
}

int main()
{
	ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	cin >> n>>m;

	for (int i = 0; i < n; i++) for (int j = 0; j < m; j++)
		cin >> map[i][j];

	memset(dp, -1, sizeof(dp));
	cout << solve(0, 0);
}