#include <iostream>
#include <algorithm>

using namespace std;

int n;

struct ball {int num, color, weight;};

int colorSum[200000];

int weightSum[2001];

int ans[200000];

ball balls[200000];

bool cmp(ball& a, ball& b) {
	// a.weight <= b.weight 이건 시초 뜸
	if (a.weight == b.weight) return a.color < b.color;
	return a.weight < b.weight;
}

int main() {
	ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	cin >> n;
	for (int i = 0; i < n; i++) {
		int color, weight; cin >> color >> weight;
		balls[i] = { i,--color,weight };
	}
	sort(balls, balls + n, cmp);

	int totalSum = 0;

	for (int i = 0; i < n; i++) {
		int num = balls[i].num,
			color = balls[i].color,
			weight = balls[i].weight;
		
		colorSum[color] += weight;
		weightSum[weight] += weight;
		totalSum += weight;
		ans[num] = totalSum - weightSum[weight] - colorSum[color] + weight;
		if (i != 0 && balls[i - 1].weight == weight && balls[i - 1].color == color)
			ans[num] = ans[balls[i - 1].num];
	}
	for (int i = 0; i < n; i++) cout << ans[i] << '\n';
}