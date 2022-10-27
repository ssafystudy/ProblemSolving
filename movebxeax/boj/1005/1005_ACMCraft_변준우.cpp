#include <stdio.h>
#include <vector>
#include <algorithm>
#include <queue>
#pragma warning(disable:4996)

void solve()
{
	int N, K;
	int winning;
	scanf("%d %d", &N, &K);

	std::vector<std::vector<int>> adj_list(N + 1);
	std::vector<int> indegree(N + 1);
	std::vector<int> time(N + 1);
	std::vector<int> elapsed(N + 1, -1);

	for (auto i = 1; i <= N; i++)
	{
		int t;
		scanf("%d", &t);
		time[i] = t;
	}

	for (auto i = 0; i < K; i++)
	{
		int from, to;
		scanf("%d %d", &from, &to);
		adj_list[from].emplace_back(to);
		indegree[to]++;
	}

	scanf("%d", &winning);

	std::queue<int> sorting;
	std::queue<int> result;

	for (auto i = 1; i < indegree.size(); i++)
	{
		if (indegree[i] == 0)
		{
			sorting.push(i);
			elapsed[i] = time[i];
		}
	}

	while (!sorting.empty())
	{
		int size = sorting.size();
		std::priority_queue<int, std::vector<int>, std::less<int>> res_pq;
		for (auto i = 0; i < size; i++)
		{
			int current = sorting.front();
			sorting.pop();

			if (current == winning)
				break;

			for (auto itr : adj_list[current])
			{
				if (--indegree[itr] == 0)
				{
					sorting.push(itr);
					res_pq.push(time[itr]);
				}
			}

			for (auto itr : adj_list[current])
				elapsed[itr] = std::max(elapsed[itr], elapsed[current] + time[itr]);
		}
	}

	printf("%d\n", elapsed[winning]);
}

int main()
{
	int T;
	scanf("%d", &T);
	for (auto tc = 0; tc < T; tc++)
	{
		solve();
	}
}