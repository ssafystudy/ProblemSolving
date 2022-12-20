#include <iostream>
#include <queue>

using namespace std;

typedef pair<int, int> pii;

int n,m;

int tomato[1000][1000];

int dy[] = { 1,-1,0,0 }, dx[] = { 0,0,1,-1 };

int main()
{
	ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	cin >> m>>n;

	int toCnt = 0;
	queue<pii> q;

	for (int i = 0; i < n; i++) for (int j = 0; j < m; j++)
	{
		cin >> tomato[i][j];
		if (tomato[i][j] == 1) q.push({ i,j });
		else if (tomato[i][j] == 0) toCnt++;
	}

	int day = 0;

	while (q.size()) {
		if (!toCnt) break;
		int qsize = q.size();

		while (qsize--) {
			pii t = q.front(); q.pop();
			int y = t.first, x = t.second;

			for (int dir = 0; dir < 4; dir++) {
				int ny = y + dy[dir],
					nx = x + dx[dir];

				if (ny < 0 || ny >= n || nx < 0 || nx >= m) continue;
				if (tomato[ny][nx]) continue;

				tomato[ny][nx] = 1;
				q.push({ ny,nx }); toCnt--;
			}
		}
		day++;
	}
	if (toCnt) cout << -1;
	else cout << day;
}