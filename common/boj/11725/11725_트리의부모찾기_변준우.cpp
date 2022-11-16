#include <vector>
#include <map>
#include <queue>
#pragma warning(disable:4996)

std::map<int, std::vector<int>> tree;
int parent[100001] = { 0 };

void bfs()
{
	bool visited[100001] = { false };
	std::queue<int> q;
	q.push(1);
	visited[1] = true;
	parent[1] = 1;

	while (!q.empty())
	{
		int cur = q.front();
		q.pop();

		for (auto itr : tree[cur])
		{
			if (visited[itr])
				continue;

			q.push(itr);
			parent[itr] = cur;
			visited[itr] = true;
		}
	}
}

int main()
{
	int N;
	scanf("%d", &N);

	tree.emplace(1, std::vector<int>());

	for (auto i = 0; i < N-1; i++)
	{
		int n1, n2;
		scanf("%d %d", &n1, &n2);

		tree[n1].emplace_back(n2);
		tree[n2].emplace_back(n1);
	}

	bfs();

	for (auto i = 2; i <= N; i++)
		printf("%d\n", parent[i]);
}