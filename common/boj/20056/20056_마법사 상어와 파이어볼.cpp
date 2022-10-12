#include <iostream>
#include <vector>

#define _MAX 50

using namespace std;

int n, m, k;

struct ball {
	int m, s, d;
};

vector<ball> map[_MAX][_MAX];

int dy[] = { -1,-1,0,1,1,1,0,-1 }, dx[] = { 0,1,1,1,0,-1,-1,-1 };

int main() {
	ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	cin >> n >> m >> k;

	while (m--) {
		int r, c, m, s, d;
		cin >> r >> c >> m >> s >> d;
		map[--r][--c].push_back({ m,s,d });
	}

	while (k--) {
		vector<ball> tempMap[_MAX][_MAX];

		// 1
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++) {
				for (const auto& fb : map[i][j]) {
					int ny = (i + dy[fb.d] * (fb.s % n) + n) % n,
						nx = (j + dx[fb.d] * (fb.s % n) + n) % n;
					tempMap[ny][nx].push_back(fb);
				}
			}

		// 2
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++) {
				if (tempMap[i][j].size() >= 2) {
					int sum = 0, sSum = 0;
					bool allEven = true, allOdd = true;
					for (const auto& fb : tempMap[i][j]) {
						sum += fb.m;
						sSum += fb.s;
						
						if (fb.d & 1) allEven = false;
						else allOdd = false;
					}
					int ballCnt = tempMap[i][j].size();
					tempMap[i][j].clear();
					int mass = sum / 5;
					if (mass != 0) {
						int startDir = allEven || allOdd ? 0 : 1;
						for (; startDir < 8; startDir += 2) {
							tempMap[i][j].push_back({ mass, sSum / ballCnt,startDir });
						}
					}
				}
			}

		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				map[i][j] = tempMap[i][j];
	}

	int ret = 0;
	for (int i = 0; i < n; i++)
		for (int j = 0; j < n; j++)
			if (map[i][j].size() != 0)
			{
				for (const auto& fb : map[i][j]) {
					ret += fb.m;
				}
			}
	cout << ret;
}