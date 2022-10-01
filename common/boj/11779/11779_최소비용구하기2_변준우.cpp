#include <stdio.h>
#include <queue>
#include <stack>
#include <vector>
#include <limits>
#include <algorithm>

#pragma warning(disable:4996)

typedef struct Vertex {
	int to, dist;
	Vertex(int to, int dist) : to(to), dist(dist) {}

	bool operator<(const Vertex v) const {
		return this->dist > v.dist;
	}
}Vertex;

int N, M;
std::vector<std::vector<Vertex>> adj_list;
std::vector<int> distance;
std::vector<int> path;
const int UNREACHABLE = std::numeric_limits<int>::max();

void dijkstra(int start, int end)
{
	std::priority_queue<Vertex> pq;
	for (auto itr : adj_list[start])
		pq.emplace(itr);

	int current_vertex = start;
	int current_dist = 0;

	distance.resize(N, UNREACHABLE);
	path.resize(N, 0);
	distance[current_vertex] = 0;

	std::vector<bool> visited(N, false);

	while (true)
	{
		if (!visited[current_vertex])
		{
			visited[current_vertex] = true;

			for (auto itr : adj_list[current_vertex])
			{
				if (distance[itr.to] > distance[current_vertex] + itr.dist)
				{
					distance[itr.to] = distance[current_vertex] + itr.dist;
					pq.emplace(Vertex(itr.to, distance[itr.to]));
					path[itr.to] = current_vertex;
				}
			}
		}

		if (pq.empty() || current_vertex == end)
			break;

		current_vertex = pq.top().to;
		current_dist = pq.top().dist;
		pq.pop();
	}
}

int main()
{
	scanf("%d %d", &N, &M);
	
	adj_list.resize(N);

	for (auto i = 0; i < M; i++)
	{
		int from, to, dist;
		scanf("%d %d %d", &from, &to, &dist);
		from--; to--;

		adj_list[from].push_back(Vertex(to, dist));
	}

	int from, to;
	scanf("%d %d", &from, &to);
	from--; to--;
	dijkstra(from, to);

	std::stack<int> path_stack;
	int prev_pos = to;
	path_stack.push(prev_pos);
	while (true)
	{
		path_stack.push(path[prev_pos]);
		if (path[prev_pos] == from)
			break;
		prev_pos = path[prev_pos];
	}

	printf("%d\n", distance[to]);
	printf("%d\n", path_stack.size());
	while (!path_stack.empty())
	{
		printf("%d ", path_stack.top()+1);
		path_stack.pop();
	}
}