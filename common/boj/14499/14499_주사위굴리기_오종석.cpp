#include <iostream>

using namespace std;

int n, m, x,y,k;

string dice[] = {"000",
				 "000",
				 "000",
				 "000" };

int map[20][20];

int dy[] = { 0,0,-1,1 }, dx[] = { 1,-1,0,0 };

bool move(int cmd) {
	int ny = y + dy[cmd], nx = x + dx[cmd];
	if (ny < 0 || ny >= n || nx < 0 || nx >= m) return false;

	char temp;
	switch (cmd) {
		case 0:
			temp = dice[3][1];
			dice[3][1] = dice[1][2];
			dice[1][2] = dice[1][1];
			dice[1][1] = dice[1][0];
			dice[1][0] = temp;
			break;
		case 1:
			temp = dice[3][1];
			dice[3][1] = dice[1][0];
			dice[1][0] = dice[1][1];
			dice[1][1] = dice[1][2];
			dice[1][2] = temp;
			break;
		case 2:
			temp = dice[0][1];
			for (int i = 0; i < 3; i++) dice[i][1] = dice[i + 1][1];
			dice[3][1] = temp;
			break;
		case 3:
			temp = dice[3][1];
			for (int i = 3; i > 0; i--) dice[i][1] = dice[i-1][1];
			dice[0][1] = temp;
			break;
	}

	y = ny; x = nx;
	if (!map[y][x]) map[y][x] = dice[3][1] - '0';
	else {
		dice[3][1] = map[y][x] + '0';
		map[y][x] = 0;
	}
	return true;
}

int main(void) {
	ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	cin >> n >> m>>y>>x>>k;

	for (int i = 0; i < n; i++) for (int j = 0; j < m; j++) cin >> map[i][j];

	while (k--) {
		int cmd; cin >> cmd; --cmd;
		if (move(cmd)) cout << dice[1][1] << "\n";
	}
}