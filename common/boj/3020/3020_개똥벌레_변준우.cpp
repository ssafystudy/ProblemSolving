#include <stdio.h>
#include <vector>
#include <algorithm>
#pragma warning(disable:4996)

std::vector<int> bottom, top;
int N, H;

int main()
{
	scanf("%d %d", &N, &H);

	for (auto i = 0; i < N; i++)
	{
		int in;
		scanf("%d", &in);

		if (i % 2 == 0)
			bottom.emplace_back(in);
		else
			top.emplace_back(in);
	}

	std::sort(bottom.begin(), bottom.end());
	std::sort(top.begin(), top.end());

	int dist = 0x3f3f3f3f;
	int cnt = 0;

	for (int i = 1; i <= H; i++)
	{
		auto bottom_dist = std::lower_bound(bottom.begin(), bottom.end(), i) - bottom.begin();
		auto top_dist = std::lower_bound(top.begin(), top.end(), H-i+1) - top.begin();
		auto dist_sum = N - (bottom_dist + top_dist);

		if (dist == dist_sum)
		{
			cnt++;
		}
		else if (dist > dist_sum)
		{
			dist = dist_sum;
			cnt = 1;
		}
	}

	printf("%d %d", dist, cnt);
}