#include <stdio.h>
#include <limits>
#include <vector>
#include <queue>

#pragma warning(disable:4996)

typedef struct Vertex
{
	int to, dist;

	Vertex() {}
	Vertex(int to, int dist) : to(to), dist(dist) {}

	bool operator<(const Vertex v) const
	{
		return this->dist > v.dist;
	}
}Vertex;

int N;
const int UNREACHABLE = std::numeric_limits<int>::max();
std::vector<std::vector<Vertex>> adj_list;

int main()
{
	scanf("%d", &N);

	adj_list.resize(N);

	int M = 0;
	scanf("%d", &M);

	for (int i = 0; i < M; i++)
	{
		int src, dst, weight;
		scanf("%d %d %d", &src, &dst, &weight);
		adj_list[src - 1].push_back(Vertex(dst - 1, weight));
	}

	int src, dst;
	scanf("%d %d", &src, &dst);

	src--; dst--;

	std::vector<int> distance;
	distance.resize(N, UNREACHABLE);
	distance[src] = 0;

	int traversed_vertex_count = 0;
	int current_vertex = src;
	int current_dist = 0;

	std::vector<bool> visited;
	visited.resize(N, false);

	std::priority_queue<Vertex> q;
	while (true)
	{
		if (traversed_vertex_count == M)
			break;

		if (!visited[current_vertex])
		{
			visited[current_vertex] = true;

			for (auto itr : adj_list[current_vertex])
			{
				if (distance[itr.to] > itr.dist + current_dist)
				{
					Vertex v;
					v.to = itr.to;
					v.dist = distance[current_vertex] + itr.dist;
					distance[v.to] = v.dist;
					q.push(v);
				}
			}

		}

		if (q.empty())
			break;

		current_vertex = q.top().to;
		current_dist = q.top().dist;
		q.pop();
	}

	printf("%d", distance[dst]);
}