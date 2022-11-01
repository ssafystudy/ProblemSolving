#include <iostream>

using namespace std;

int n, m, k;

struct smell {
	int no, k = 0;
};

struct shark { int y, x, dir;};

smell map[20][20];

shark sharks[401];

int dir_priority[401][5][4];

int dy[] = { -999, -1,1,0,0 }, dx[] = { -999,0,0,-1,1 };

bool visit[20][20];

int main() {
	ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	cin >> n >> m >> k;
	
	for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) {
		int no; cin >> no;
		if (no != 0) {
			map[i][j] = { no,k };
			sharks[no] = { i,j };
		}
	}

	for (int i = 1; i <= m; i++) cin >> sharks[i].dir;

	for (int i = 1; i <= m; i++) {
		for (int j = 1; j <= 4; j++) {
			for (int k = 0; k < 4; k++)
				cin >> dir_priority[i][j][k];
		}
	}

	int time = 0;

	while (time <1000) {
		time++;

		for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) visit[i][j] = false;

		// move
		for (int i = 1; i <= m; i++) {
			int tdir = sharks[i].dir;
			if (tdir == -1) continue;
			
			int ty = sharks[i].y,
				tx = sharks[i].x;

			int ny, nx;
			int ndir;
			bool find = false;
			
			for (int dir = 0; dir < 4; dir++) {
				ndir = dir_priority[i][tdir][dir];
				ny = ty + dy[ndir];
				nx = tx + dx[ndir];
				if (ny < 0 || ny >= n || nx < 0 || nx >= n) continue;
				if (map[ny][nx].k <= 0) {
					find = true; break;
				}
			}

			if (!find) {
				for (int dir = 0; dir < 4; dir++) {
					ndir = dir_priority[i][tdir][dir];
					ny = ty + dy[ndir];
					nx = tx + dx[ndir];

					// 복붙하다 이거 빼먹음 ㅋㅋ;;
					if (ny < 0 || ny >= n || nx < 0 || nx >= n) continue;
					
					if (map[ny][nx].no == i && map[ny][nx].k > 0) break;
				}
			}
			sharks[i].dir = ndir;
			

			if (visit[ny][nx]) sharks[i].dir = -1;
			else {
				sharks[i].y = ny;
				sharks[i].x = nx;
				visit[ny][nx] = true;
			}
		}

		for (int i = 1; i <= m; i++) {
			if (sharks[i].dir == -1) continue;
			map[sharks[i].y][sharks[i].x] = {i, k + 1 };
		}
		
		// 냄새 감소
		for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) map[i][j].k--;

		// int dy[] = { -999, -1,1,0,0 }, dx[] = { -999,0,0,-1,1 };
		bool allOut = true;
		for (int i = 2; i <= m; i++) {
			if (sharks[i].dir != -1) {
				allOut = false; break;
			}
		}
		
		if (allOut) { cout << time; return 0;}
	}
	cout << -1;
}
