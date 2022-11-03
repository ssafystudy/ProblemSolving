#include <iostream>

using namespace std;

int n = 0;
int tree[10000];

void pre(int idx, int end) {
	int t = idx + 1;
	while (t < end && tree[idx] > tree[t]) t++;

	if (t != idx + 1) pre(idx + 1, t);
	if (t != end) pre(t, end);
	cout << tree[idx] << "\n";
}

int main() {
	ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	while (cin >> tree[n]) n++;
	pre(0,n);
}