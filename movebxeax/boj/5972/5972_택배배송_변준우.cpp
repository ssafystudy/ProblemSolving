#include <stdio.h>
#include <queue>
#include <vector>
#include <set>
#include <limits>
#include <algorithm>

#pragma warning(disable:4996)

std::vector<std::vector<std::pair<int,int>>> adj_list;
bool visited[50001] = { false };

int main()
{
	int N, M;
	scanf("%d %d", &N, &M);

	adj_list.resize(N);

	for (auto i = 0; i < M; i++)
	{
		int from, to, dist;
		scanf("%d %d %d", &from, &to, &dist); from--; to--;

		adj_list[from].emplace_back(dist, to);
		adj_list[to].emplace_back(dist, from);
	}

	std::priority_queue <std::pair<int, int>, std::vector<std::pair<int, int>>, std::greater<std::pair<int, int>>> pq;
	std::vector<int> distance(N, std::numeric_limits<int>::max());
	distance[0] = 0;

	pq.emplace(0, 0);

	while (!pq.empty())
	{
		int current_dist = pq.top().first;
		int current_vertex = pq.top().second;
		pq.pop();


		if (!visited[current_vertex])
		{
			visited[current_vertex] = true;

			for (auto itr : adj_list[current_vertex])
			{
				if (distance[itr.second] > itr.first + current_dist)
				{
					distance[itr.second] = itr.first + current_dist;
					pq.emplace(distance[itr.second], itr.second);
				}
			}
		}
	}

	printf("%d", distance[N - 1]);
}