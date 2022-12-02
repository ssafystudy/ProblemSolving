#include <stdio.h>
#include <vector>
#include <queue>
#include <algorithm>
#pragma warning(disable:4996)

int main()
{
	int N, M;
	scanf("%d %d", &N, &M);

	std::vector<std::vector<int>> adj_list(N + 1);
	std::vector<int> indegree(N + 1);

	for (auto i = 0; i < M; i++)
	{
		int from, to;
		scanf("%d %d", &from, &to);
		adj_list[from].emplace_back(to);
		indegree[to]++;
	}

	std::queue<int> sort_q, res_q;

	for (auto i = 1; i < indegree.size(); i++)
		if (indegree[i] == 0)
			sort_q.push(i);

	while (!sort_q.empty())
	{
		auto current = sort_q.front();
		sort_q.pop();

		res_q.push(current);

		for (auto itr : adj_list[current])
		{
			if (--indegree[itr] == 0)
				sort_q.push(itr);
		}
	}

	while (!res_q.empty())
	{
		printf("%d ", res_q.front());
		res_q.pop();
	}
}