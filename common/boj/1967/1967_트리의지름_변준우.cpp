#include <stdio.h>
#include <vector>
#include <algorithm>
#pragma warning(disable:4996)

typedef struct Node {
	int val, dist;
	Node(int val, int dist) : val(val), dist(dist) {}
	Node() : val(-1), dist(-1) {}
} Node;

std::vector<std::vector<Node>> nodes;
std::vector<int> leaves;
bool visit[10001] = { false };
int parent[10001] = { 0 };
Node farthest;

void find_farthest_node(int node_val, int current_dist)
{
	if (farthest.dist < current_dist)
	{
		farthest.val = node_val;
		farthest.dist = current_dist;
	}
	visit[node_val] = true;

	for (auto& itr : nodes[node_val])
	{
		if (visit[itr.val])
			continue;
		find_farthest_node(itr.val, current_dist + itr.dist);
	}
}

int main()
{
	int N;
	scanf("%d", &N);

	nodes.resize(N + 1);

	for (auto i = 0; i < N-1; i++)
	{
		int parent, child, dist;
		scanf("%d %d %d", &parent, &child, &dist);

		nodes[parent].emplace_back(child, dist);
		nodes[child].emplace_back(parent, dist);
	}
	
	find_farthest_node(1, 0);
	memset(visit, false, sizeof(bool) * 10001);
	find_farthest_node(farthest.val, 0);

	printf("%d", farthest.dist);

	return 0;
}