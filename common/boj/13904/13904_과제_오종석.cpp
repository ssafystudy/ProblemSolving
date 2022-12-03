#include <iostream>
#include <algorithm>

using namespace std;

int n;

struct problem { int d, w;};

problem p[1000];

bool cmp(const problem& a, const problem& b) {
	return a.w > b.w;
}

int ans[1001];

int main() {
	ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	cin >> n;

	for (int i = 0; i < n; i++) cin >> p[i].d >> p[i].w;
	
	sort(p, p + n, cmp);

	int max_day = -1;

	for (int i = 0; i < n; i++) {
		int end_day = p[i].d;
		max_day = max(max_day, end_day);
		while (end_day > 0 && ans[end_day]) end_day--;
		if (end_day) ans[end_day] = p[i].w;
	}
	
	int ret = 0;
	for (int i = 1; i <= max_day; i++) ret += ans[i];
	cout << ret;
}