#include <iostream>
#include <queue>

using namespace std;

typedef pair<int, int> pii;

int n, m, g, r;

int map[50][50];
int tmap[50][50];

int landcnt = 0;
pii land[10];
int visit[10];

int dy[] = { 1,-1,0,0 }, dx[] = { 0,0,1,-1 };

int _max = -1;

void simulation() {
	for (int i = 0; i < n; i++) for (int j = 0; j < m; j++)
		tmap[i][j] = map[i][j];

	queue<pii> q;
	for (int i = 0; i < landcnt; i++) {
		if (visit[i])
		{
			tmap[land[i].first][land[i].second] = visit[i];
			q.push(land[i]);
		}
	}

	int flower = 0;

	while (q.size()) {
		int qsize = q.size();
		while (qsize--) {
			int y = q.front().first,
				x = q.front().second; q.pop();

			if (tmap[y][x] == 0x7fffffff) continue;

			int time = tmap[y][x] >> 3,
				value = tmap[y][x] & 7;

			for (int dir = 0; dir < 4; dir++) {
				int ny = y + dy[dir],
					nx = x + dx[dir];

				if (ny < 0 || ny >= n || nx < 0 || nx >= m) continue;
				if (tmap[ny][nx] == 0 || tmap[ny][nx] == 0x7fffffff) continue;
				if (tmap[ny][nx] == 1 || tmap[ny][nx] == 2) {
					tmap[ny][nx] = ((time + 1) << 3) + value;
					q.push({ ny,nx });
				}
				else {
					int ntime = tmap[ny][nx] >> 3,
						nvalue = tmap[ny][nx] & 7;
					if (nvalue == value) continue;
					if (ntime == time + 1) {
						tmap[ny][nx] = 0x7fffffff;
						flower++;
					}
				}
			}
		}
	}
	_max = _max > flower ? _max : flower;
}

void chooseGreen(int green, int idx) {
	if (!green) {
		//for (int i = 0; i < landcnt; i++) cout << visit[i];
		//cout << "\n";
		simulation();
		return;
	}
	for (int i = idx; i < landcnt; i++) {
		if (visit[i]) continue;
		visit[i] = 4;
		chooseGreen(green - 1, i + 1);
		visit[i] = 0;
	}
}

void chooseRed(int red, int idx) {
	if (!red) {
		chooseGreen(g, 0);
		return;
	}
	for (int i = idx; i < landcnt; i++) {
		visit[i] = 3;
		chooseRed(red - 1, i + 1);
		visit[i] = 0;
	}
}

int main()
{
	ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	cin >> n >> m >> g >> r;

	for (int i = 0; i < n; i++) for (int j = 0; j < m; j++) {
		cin >> map[i][j];
		if (map[i][j] == 2) {
			land[landcnt] = { i,j };
			landcnt++;
		}
	}
	chooseRed(r, 0);
	cout << _max;
}