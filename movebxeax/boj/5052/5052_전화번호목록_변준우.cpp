#include <stdio.h>
#include <vector>
#include <string>
#pragma warning(disable:4996)

typedef struct TrieNode {
	TrieNode* nextTrie[10] = { nullptr };
	bool is_end;
	int count = 0;

	~TrieNode() { for (auto itr : nextTrie) { if (itr != nullptr)  delete itr; } }
} TrieNode;

void create_trie(const char* num, int len, TrieNode* current)
{
		for (auto i = 0; i < len; i++)
		{
			if (current->nextTrie[num[i] - 0x30] == nullptr)
			{
				current->nextTrie[num[i] - 0x30] = new TrieNode();
				current->count++;
			}

			current = current->nextTrie[num[i] - 0x30];
		}

		current->is_end = true;
}

bool traverse_trie(const char* num, int len, TrieNode* current)
{
	for (auto i = 0; i < len; i++)
	{
		current = current->nextTrie[num[i] - 0x30];
	}

	if (current->count > 0 && current->is_end)
		return false;

	return true;
}

int main()
{
	int T;
	scanf("%d", &T);

	for (int test_case = 0; test_case < T; test_case++)
	{
		int N;
		scanf("%d", &N);

		TrieNode* root = new TrieNode;

		std::vector<std::string> numbers;

		for (int i = 0; i < N; i++)
		{
			char num[11] = { NULL };
			scanf("%s", &num);

			std::string num_str(num);
			create_trie(num_str.c_str(), num_str.length(), root);
			numbers.push_back(num);
		}

		bool is_consistent = false;

		for (auto itr : numbers)
		{
			is_consistent = traverse_trie(itr.c_str(), itr.length(), root);
			if (!is_consistent)
				break;
		}

		printf(is_consistent ? "YES\n" : "NO\n");
	}
}