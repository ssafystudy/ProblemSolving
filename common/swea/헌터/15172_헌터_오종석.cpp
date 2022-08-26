#include <iostream>
#include <algorithm>
#include <cstring>

using namespace std;

int n,m;
int dp[9][1 << 9];

int map[10][10];

struct loc { int y, x; };

loc locArr[9];

int dist[9][9];

int solve(int cur, int visit) {
	visit |= (1 << cur);
	int& ret = dp[cur][visit];
	if (visit == (1 << (2 * m + 1)) - 1) return 0;
	if (ret != -1) return ret;
	ret = 1e9;

	for (int i = 0; i <= 2 * m; i++) {
		if (visit & (1 << i)) continue;
		if (i > m && !(visit & (1 << (i - m)))) continue;
		ret = min(ret, solve(i, visit | (1 << i)) + dist[cur][i]);
	}
	return ret;
}

int main(void) {
	ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	int T; cin >> T;
	
	for (int tc = 1; tc <= T; ++tc) {
		cin >> n;
		m = 0;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++) {
				cin >> map[i][j];
				if (map[i][j] > 0) m++;
			}

		for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) {
			if (map[i][j] > 0) locArr[map[i][j]] = { i,j };
			else if(map[i][j] <0) locArr[-map[i][j] + m] = { i,j };
		}

		locArr[0] = { 0,0 };

		for (int i = 0; i <= 2 * m; i++) for (int j = 0; j <= 2 * m; j++) {
			if (i == j) continue;
			dist[i][j] = abs(locArr[i].y - locArr[j].y) + abs(locArr[i].x - locArr[j].x);
		}
		
		memset(dp, -1, sizeof(dp));

		cout << "#" << tc << " " << solve(0, 0) << "\n";
	}
}