#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

int main()
{
	std::ios::sync_with_stdio(false); std::cin.tie(0); std::cout.tie(0);

	int N;
	std::cin >> N;

	std::vector<std::vector<int>> adj_list(N + 1);
	std::vector<int> indegree(N + 1);
	std::vector<int> cost(N + 1);
	std::vector<int> elapsed(N + 1, 0);

	for (auto i = 1; i <= N; i++)
	{
		std::cin >> cost[i];

		int T;
		std::cin >> T;
		indegree[i] = T;
		for (auto task = 0; task < T; task++)
		{
			int task_num;
			std::cin >> task_num;
			adj_list[task_num].emplace_back(i);
		}
	}

	std::queue<int> sort_q;
	for (auto i = 1; i <= N; i++)
	{
		if (indegree[i] == 0)
		{
			sort_q.emplace(i);
			elapsed[i] = cost[i];
		}
	}

	while (!sort_q.empty())
	{
		auto current = sort_q.front();
		sort_q.pop();

		for (auto v : adj_list[current])
		{
			elapsed[v] = std::max(elapsed[v], elapsed[current] + cost[v]);
			if (--indegree[v] == 0)
			{
				sort_q.push(v);
			}
		}
	}

	int res = 0;
	for (auto e : elapsed)
		res = std::max(res, e);

	std::cout << res;
}