#include <stdio.h>
#include <string.h>
#include <vector>
#include <string>
#include <algorithm>

#pragma warning(disable:4996)

typedef struct TrieNode {
	TrieNode* nextTrie[26] = { NULL };
	bool is_end = false;
	int count = 0;

	TrieNode() {}
	~TrieNode() { for (auto itr : nextTrie) { if (itr != nullptr)  delete itr; } }
}TrieNode;

void create_trie(char* word, int len, TrieNode* current)
{
	for (auto i = 0; i < len; i++)
	{
		if (current->nextTrie[word[i] - 0x61] == nullptr)
		{
			current->nextTrie[word[i] - 0x61] = new TrieNode();
			current->count++;
		}

		current = current->nextTrie[word[i] - 0x61];
	}

	current->is_end = true;
}

int traverse_trie(const char* word, int len, TrieNode* current)
{
	int count = 1;
	int last_count = current->count;

	current = current->nextTrie[word[0] - 0x61];
	for (auto i = 1; i < len; i++)
	{
		if (current->count > 1 || current->is_end)
			count++;

		current = current->nextTrie[word[i] - 0x61];
	}

	return count;
}

int main()
{
	int N;
	while (scanf("%d", &N) > 0)
	{
		auto root = new TrieNode;
		std::vector<std::string> words;

		for (auto i = 0; i < N; i++)
		{
			char word[81] = { 0 };
			scanf("%s", word);

			create_trie(word, strlen(word), root);
			words.push_back(word);
		}

		int count = 0;
		for (auto itr : words)
		{
			count += traverse_trie(itr.c_str(), itr.length(), root);
		}

		printf("%.2lf\n", count / (double)N);

		delete root;
	}
}