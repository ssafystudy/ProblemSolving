#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

int main()
{
	std::ios::sync_with_stdio(false); std::cin.tie(0); std::cout.tie(0);

	int N;
	std::cin >> N;

	std::vector<std::vector<int>> adj_list(N + 1);
	std::vector<int> indegree(N + 1);
	std::vector<int> cost(N + 1);
	std::vector<int> elapsed(N + 1);

	for (int i = 1; i <= N; i++)
	{
		std::cin >> cost[i];

		while (true)
		{
			int bld_no;
			std::cin >> bld_no;
			if (bld_no == -1)
				break;

			adj_list[bld_no].emplace_back(i);
			indegree[i]++;
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
			if (--indegree[v] == 0)
				sort_q.emplace(v);

			elapsed[v] = std::max(elapsed[v], elapsed[current] + cost[v]);
		}
	}

	for (auto i = 1; i <= N; i++)
		std::cout << elapsed[i] << "\n";
}