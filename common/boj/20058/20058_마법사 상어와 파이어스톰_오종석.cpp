#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int n, q;

int map[1 << 6 + 2][1 << 6 + 2] = { 0, };
int _copy[1 << 6][1 << 6];

int l;

struct loc { int y, x;};

int dy[] = { 1,-1,0,0 }, dx[] = { 0,0,1,-1 };

void rotate(int y, int x, int len) {
	if (len == (1 << l)) {
		for (int i = y; i < y + len; i++) for (int j = x; j < x + len; j++) {
			_copy[j-x][y+len - i -1] = map[i][j];
		}

		for (int i = 0; i < len; i++) for (int j = 0; j < len; j++)
			map[i + y][j + x] = _copy[i][j];
		return;
	}
	int half = len / 2;
	rotate(y, x, half);
	rotate(y, x+half, half);
	rotate(y+half, x, half);
	rotate(y + half, x + half, half);
}

int main(void) {
	ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	cin >> n >> q;

	int sum = 0;
	int len = (1 << n);
	for (int i = 1; i <= len; i++) for (int j = 1; j <= len; j++)
	{
		cin >> map[i][j];
		sum += map[i][j];
	}

	while (q--) {
		cin >> l;
		rotate(1, 1, 1 << n);
		vector<loc> melt;
		for (int i = 1; i <= len; i++) for (int j = 1; j <= len; j++) {
			if (!map[i][j]) continue;
			int zeroCnt = 0;
			if (!map[i - 1][j])zeroCnt++;
			if (!map[i + 1][j])zeroCnt++;
			if (!map[i][j + 1])zeroCnt++;
			if (!map[i][j - 1])zeroCnt++;
			if (zeroCnt >= 2) melt.push_back({ i,j });
		}
		sum -= melt.size();
		for (loc t : melt) map[t.y][t.x]--;
	}
	cout << sum << "\n";
	int maxSize = 0;
	for (int i = 1; i <= len; i++) for (int j = 1; j <= len; j++) {
		if (map[i][j]) {
			int tSize = 1;
			queue<loc> q; q.push({ i,j });
			map[i][j] = 0;
			while (q.size()) {
				loc t = q.front(); q.pop();
				int y = t.y, x = t.x;

				for (int dir = 0; dir < 4; dir++) {
					int ny = y + dy[dir], nx = x + dx[dir];
					if (!map[ny][nx]) continue;
					map[ny][nx] = 0;
					q.push({ ny,nx }); tSize++;
				}
			}
			maxSize = maxSize < tSize ? tSize : maxSize;
		}
	}
	cout << maxSize;
}