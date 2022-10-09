#include <stdio.h>
#include <map>
#include <algorithm>

#pragma warning(disable:4996)

int main()
{
	std::map<int, int> tree;
	tree.emplace(0, 0);
	tree.emplace(300001, 0);
	int N;
	scanf("%d", &N);

	int first_val;
	scanf("%d", &first_val);
	tree.emplace(first_val, 0);

	printf("%d\n", tree[first_val]);
	long long counter = 0;
	for (int i = 1; i < N; i++)
	{
		int val;
		scanf("%d", &val);

		tree.emplace(val, 0);
		auto itr = tree.find(val);
		auto left = itr;
		auto right = itr;
		left--;
		right++;

		tree[val] = std::max(left->second + 1, right->second + 1);
		counter += tree[val];
		printf("%lld\n", counter);
	}
}