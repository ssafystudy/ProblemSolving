#include <iostream>

typedef long long ll;

using namespace std;

ll a, b;

ll cache[60];

// 2진수 변환시 수의 길이
int getLen(ll num) {
	int ret = 60;
	while (!(num & (1LL << ret))) ret--;
	return ret + 1;
}

ll getOneCnt(ll num, int _size) {
	int tbit = _size - 1;
	int tOneCnt = 1;
	
	ll ret = 0;

	for (int i = tbit - 1; i >= 0; i--) {
		if (num & (1LL << i)) {
			ret += cache[i + 1] + (tOneCnt - 1) * (1LL << i);
			tOneCnt++;
		}
	}
	return ret;
}

int getOneBit(ll num) {
	int ret = 0;
	for (int i = 60; i >= 0; i--)
		if (num & (1LL << i)) ret++;
	return ret;
}

int main()
{
	ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	cin >> a >> b;
	cache[1] = 1;

	int alen = getLen(a), blen = getLen(b);

	for (int i = 2; i < blen; i++) cache[i] = 2 * cache[i - 1] + (1LL << (i - 2));
	ll ans = 0;

	for (int i = alen + 1; i < blen; i++) ans += cache[i];

	ans += getOneCnt(b, blen) - getOneCnt(a, alen) + getOneBit(b);
	if (alen != blen) ans += cache[alen];
	cout << ans;
}