#include <stdio.h>
#include <queue>
#include <vector>
#include <limits>
#include <algorithm>

#pragma warning(disable:4996)

typedef struct Vertex {
	int to, dist;
	bool checked = false;
	Vertex(int to, int dist) : to(to), dist(dist) {}

	bool operator<(const Vertex& v) const {
		return this->dist > v.dist;
	}
}Vertex;

int N, M;
std::vector<std::vector<Vertex>> adj_list;
std::vector<int> dest_vec;

constexpr int UNREACHABLE = std::numeric_limits<int>::max();

int dijkstra(int block_a, int block_b, bool create_path = false)
{
	std::vector<int> dist(N+1, UNREACHABLE);
	std::vector<bool> visited(N+1, false);
	std::priority_queue<Vertex> pq;

	for (auto itr : adj_list[1])
		pq.emplace(itr);

	dist[1] = 0;

	int cur_vetex = 1;
	int cur_dist = 0;

	while (true)
	{
		if (dist[cur_vetex] >= cur_dist)
		{
		/*if (!visited[cur_vetex]) <============ why??
		{
			visited[cur_vetex] = true;*/

			for (auto itr : adj_list[cur_vetex])
			{
				if ((cur_vetex == block_a && itr.to == block_b) || (cur_vetex == block_b && itr.to == block_a))
					continue;

				else if (dist[itr.to] > dist[cur_vetex] + itr.dist)
				{
					dist[itr.to] = dist[cur_vetex] + itr.dist;
					pq.emplace(Vertex(itr.to, dist[itr.to]));

					if (create_path)
						dest_vec[itr.to] = cur_vetex;
				}
			}
		}

		if (pq.empty() || cur_vetex == N)
			break;

		cur_vetex = pq.top().to;
		cur_dist = pq.top().dist;

		pq.pop();
	}

	return dist[N];
}

int main()
{
	scanf("%d %d", &N, &M);
	adj_list.resize(N+1);
	dest_vec.resize(N+1, UNREACHABLE);

	for (int i = 0; i < M; i++)
	{
		int from, to, dist;
		scanf("%d %d %d", &from, &to, &dist);
		
		adj_list[from].push_back(Vertex(to, dist));
		adj_list[to].push_back(Vertex(from, dist));
	}

	int min_cost = dijkstra(0,0, true);

	int prev_pos = N;
	std::vector<int> path;
	path.push_back(N);
	while (true)
	{
		path.push_back(dest_vec[prev_pos]);
		if (dest_vec[prev_pos] == 1)
			break;
		prev_pos = dest_vec[prev_pos];
	}

	int max_cost = std::numeric_limits<int>::min();
	for (auto i = 0; i < path.size() - 1; i++)
		max_cost = std::max(max_cost, dijkstra(path[i], path[i + 1]));

	if (max_cost == UNREACHABLE)
		printf("-1");
	else
		printf("%d", max_cost - min_cost);
}