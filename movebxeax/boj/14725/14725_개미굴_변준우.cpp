#include <stdio.h>
#include <string>
#include <algorithm>
#include <vector>
#include <set>

#pragma warning(disable:4996)

typedef struct TrieNode {
	std::string str;
	std::set<TrieNode> next;

	TrieNode() {}
	TrieNode(std::string str) : str(str) {}

	bool operator<(const TrieNode& t) const {
		return (this->str.compare(t.str) < 0);
	}

	bool operator==(const TrieNode& t) const {
		return (this->str.compare(t.str) == 0);
	}
}TrieNode;
int N;

void traverse_trie(TrieNode trie, int depth)
{
	for (auto i = 0; i < depth; i++)
	{
		printf("--");
	}
	printf("%s\n", trie.str.c_str());
	for (auto itr : trie.next)
	{
		traverse_trie(itr, depth + 1);
	}
}

int main()
{
	scanf("%d", &N);

	std::set<TrieNode> trie;

	for (auto i = 0; i < N; i++)
	{
		int K;
		scanf("%d", &K);
		std::set<TrieNode>* current = &trie;
		for (auto j = 0; j < K; j++)
		{
			char food_name[16] = { 0 };
			scanf("%s", food_name);
			std::string foodstr;
			foodstr.assign(food_name);

			auto node = std::find(current->begin(), current->end(), TrieNode(foodstr));
			if (node != current->end())
			{
				current = (std::set<TrieNode>*) & (node->next);
			}
			else
			{
				current->emplace(TrieNode(foodstr));
				current = (std::set<TrieNode>*) & (current->find(TrieNode(foodstr))->next);
			}
		}
	}

	for (auto itr : trie)
	{
		traverse_trie(itr, 0);
	}

	return 0;
}
