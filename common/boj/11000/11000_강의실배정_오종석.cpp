#include <iostream>
#include <algorithm>
#include <queue>

using namespace std;

int n;

struct _time
{
	int s, t;
};

_time tt[200000];

bool cmp(const _time& a, const _time& b) {
	if (a.s == b.s) return a.t < b.t;
	return a.s < b.s;
}

int main()
{
	ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	cin >> n;

	for (int i = 0; i < n; i++) cin >> tt[i].s >> tt[i].t;

	sort(tt, tt + n, cmp);

	// 기본 맥스힙
	priority_queue<int> q;

	q.push(-tt[0].t);

	int _max = 1;
	
	for (int i = 1; i < n; i++) {
		while (-q.top() <= tt[i].s) q.pop();
		q.push(-tt[i].t);
		_max = max(_max, (int)q.size());
	}
	cout << _max;
}