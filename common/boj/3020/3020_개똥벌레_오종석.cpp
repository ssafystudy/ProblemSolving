#include <iostream>
using namespace std;

int n, h;

int seoksun[500001];
int jongseok[500001];

int main() {
	ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	cin >> n >> h;

	for (int i = 0; i < n; i++) {
		int len; cin >> len;
		if (i & 1) jongseok[len]++;
		else seoksun[len]++;
	}

	for (int i = h; i >= 2; i--) {
		jongseok[i-1] += jongseok[i];
		seoksun[i-1] += seoksun[i];
	}

	int _min = 1e9, cnt = 0;
	for (int i = 1; i <= h; i++) {
		int jidx = h - (i - 1);
		
		int tdumpCnt = seoksun[i] + jongseok[jidx];
		if (tdumpCnt < _min) {
			_min = tdumpCnt;
			cnt = 1;
		}
		else if (tdumpCnt == _min) cnt++;
	}
	cout << _min << " " << cnt;
}