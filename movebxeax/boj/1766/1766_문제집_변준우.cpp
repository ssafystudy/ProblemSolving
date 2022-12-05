#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

int main()
{
	std::ios::sync_with_stdio(false); std::cin.tie(0); std::cout.tie(0);

	int N, M;
	std::cin >> N >> M;

	std::vector<std::vector<int>> adj_list(N + 1);
	std::vector<int> indegree(N + 1);

	for (auto i = 0; i < M; i++)
	{
		int from, to;
		std::cin >> from >> to;
		adj_list[from].emplace_back(to);
		indegree[to]++;
	}

	std::priority_queue<int, std::vector<int>, std::greater<int>> sort_q;
	std::queue<int> res_q;
	for (auto i = 1; i <= N; i++)
		if (indegree[i] == 0)
			sort_q.emplace(i);

	while (!sort_q.empty())
	{
		auto current = sort_q.top();
		sort_q.pop();

		res_q.push(current);

		for (auto v : adj_list[current])
			if (--indegree[v] == 0)
				sort_q.push(v);
	}

	while (!res_q.empty())
	{
		std::cout << res_q.front() << " ";
		res_q.pop();
	}
}