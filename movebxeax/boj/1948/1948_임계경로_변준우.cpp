#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

typedef struct Node {
	int to, cost;
	Node(int to, int cost) : to(to), cost(cost) {}
	Node() : to(0), cost(0) {}
}Node;

int main()
{
	std::ios::sync_with_stdio(false); std::cin.tie(0); std::cout.tie(0);

	int N, M;
	std::cin >> N >> M;

	std::vector<std::vector<Node>> adj_list(N + 1), adj_list_inv(N + 1);
	std::vector<int> indegree(N + 1);
	std::vector<int> elapsed(N + 1, -1);

	for (auto i = 0; i < M; i++)
	{
		int from, to, cost;
		std::cin >> from >> to >> cost;

		adj_list[from].emplace_back(to, cost);
		adj_list_inv[to].emplace_back(from, cost);
		indegree[to]++;
	}

	int from, to;
	std::cin >> from >> to;

	std::queue<int> sort_q;
	sort_q.emplace(from);
	elapsed[from] = 0;

	while (!sort_q.empty())
	{
		int current = sort_q.front();
		sort_q.pop();

		for (auto v : adj_list[current])
		{
			if (--indegree[v.to] == 0)
				sort_q.push(v.to);

			elapsed[v.to] = std::max(elapsed[v.to], elapsed[current] + v.cost);
		}

		if (current == to)
			break;
	}

	int path_cnt = 0;
	std::queue<int> path_q;
	std::vector<bool> visit(N + 1);
	path_q.emplace(to);
	visit[to] = true;

	while (!path_q.empty())
	{
		auto current = path_q.front();
		path_q.pop();

		for (auto v : adj_list_inv[current])
		{
			if (elapsed[v.to] + v.cost == elapsed[current])
			{
				path_cnt++;

				if (!visit[v.to])
				{
					path_q.emplace(v.to);
					visit[v.to] = true;
				}
			}
		}
	}

	std::cout << elapsed[to] << "\n" << path_cnt;
}