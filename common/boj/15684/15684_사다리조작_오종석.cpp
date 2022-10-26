#include <iostream>

using namespace std;

int n, m, h;

bool row_line[40][11];

bool solve(int cnt, int tarCnt, int tr, int tc) {
	if (cnt == tarCnt) {
		// simulation
		for (int i = 1; i <= n; i++) {
			int tcol = i, trow = 1;

			while (trow <= h) {
				while (trow <= h && !row_line[trow][tcol -1] && !row_line[trow][tcol])
					trow++;

				if (row_line[trow][tcol - 1]) tcol--;
				else if (row_line[trow][tcol]) tcol++;
				
				trow++;
			}
			if (tcol != i) return false;
		}
		return true;
	}

	int j = tc;
	for (int i = tr; i <=h; i++){
		for (; j <n; j++) {
			// 나랑 좌우에 가로선 없을 때
			if (!row_line[i][j] && !row_line[i][j-1] && !row_line[i][j+1]) {
				row_line[i][j] = true;
				if (solve(cnt + 1, tarCnt, i, j + 1)) return true;
				row_line[i][j] = false;
			}
		}
		j = 1;
	}
	return false;
}

int main() {
	ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	cin >> n >> m>>h;

	while(m--){
		int a, b;
		cin >> a >> b;
		row_line[a][b] = true;
	}

	for (int line = 0; line <= 3; line++) {
		if (solve(0, line, 1, 1)) { cout << line; return 0; }
	}

	cout << -1;
}