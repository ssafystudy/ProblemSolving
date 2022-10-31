#include <stdio.h>
#include <memory.h>
#include <vector>
#include <algorithm>
#pragma warning(disable:4996)

typedef struct Node {
	int val, dist;
	Node(int val, int dist) : val(val), dist(dist) {}
	Node() : val(0), dist(0) {}
} Node;

std::vector<std::vector<Node>> adj_list;
bool visit[100001];
Node farthest(0, 0);

void dfs(int node_val, int current_dist)
{
	if (farthest.dist < current_dist)
	{
		farthest.val = node_val;
		farthest.dist = current_dist;
	}

	visit[node_val] = true;
	for (const auto& itr : adj_list[node_val])
	{
		if (visit[itr.val])
			continue;

		dfs(itr.val, current_dist + itr.dist);
	}
}


int main()
{
	int V;
	scanf("%d", &V);

	adj_list.resize(V + 1);

	int parent = 0;
	for (auto i = 0; i < V; i++)
	{
		scanf("%d", &parent);

		while (true)
		{
			int child, dist;
			scanf("%d", &child);

			if (child == -1)
				break;

			scanf("%d", &dist);

			adj_list[parent].emplace_back(child, dist);
		}
	}

	dfs(parent, 0);
	memset(visit, 0, sizeof(bool) * 100001);
	dfs(farthest.val, 0);

	printf("%d", farthest.dist);
	return 0;
}