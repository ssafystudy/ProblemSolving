#include <iostream>

using namespace std;

int n;

int map[499][499];

int dy[] = { 0,1,0,-1 }, dx[] = { -1,0,1,0 };

int ans = 0;

int wind[5][5] =
{{0,0,2,0,0},
{0,10,7,1,0},
{5,100,0,0,0},
{0,10,7,1,0},
{0,0,2,0,0}};

int pdir = 0;

int twind[5][5];

void rotate() {
	for (int i = 0; i < 5; i++) for (int j = 0; j < 5; j++) {
		twind[4 - j][i] = wind[i][j];
	}
	for (int i = 0; i < 5; i++) for (int j = 0; j < 5; j++) {
		wind[i][j] = twind[i][j];
	}
}

void tornado(int y, int x, int dir) {
	//cout << y << " " << x << "\n";
	if (pdir != dir) {
		pdir = dir;
		rotate();
	}
	
	int alphaY = -1, alphaX = -1;
	int tsand = map[y][x];
	map[y][x] = 0;
	int goodByeSand = 0;

	for (int i = -2; i < 3; i++) for (int j = -2; j < 3; j++) {
		if (wind[i + 2][j + 2]) {
			if (wind[i + 2][j + 2] == 100) {
				alphaY = y + i; alphaX = x + j;
			}
			else {
				int tgs = tsand * wind[i + 2][j + 2] / 100;
				goodByeSand += tgs;
				int ny = y + i, nx = x + j;
				if (ny < 0 || ny >= n || nx < 0 || nx >= n) {
					ans += tgs;
				}
				else {
					map[ny][nx] += tgs;
				}
			}
		}
	}

	int alphaSand = tsand - goodByeSand;

	if (alphaY < 0 || alphaY >= n || alphaX < 0 || alphaX >= n) {
		ans += alphaSand;
	}
	else {
		map[alphaY][alphaX] += alphaSand;
	}

	//cout << "\n";
	//for (int i = 0; i < n; i++) {
	//	for (int j = 0; j < n; j++) {
	//		cout << map[i][j] << " ";
	//	}
	//	cout << "\n";
	//}
}

int main() {
	ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	cin >> n;

	for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) cin >> map[i][j];

	int y = n / 2; int x = y;

	int dlen = 1, mcnt = 0, tdir = 0;

	while (true) {
		int tlen = 0;

		while (tlen < dlen) {
			y += dy[tdir];
			x += dx[tdir];
			
			tornado(y, x, tdir);
			if (y == 0 && x == 0) {
				cout << ans; return 0;
			}
			tlen++;
		}
		mcnt++;
		tdir = (tdir + 1) % 4;
		
		if (mcnt == 2) {
			mcnt = 0;
			dlen++;
		}	
	}
}
