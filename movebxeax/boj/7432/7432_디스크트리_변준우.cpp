#include <stdio.h>
#include <set>
#include <string>
#include <vector>
#include <sstream>
#include <algorithm>

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
} TrieNode;

std::vector<std::string> split(std::string str, char delim)
{
	std::istringstream iss(str);
	std::string buf;

	std::vector<std::string> res;

	while (getline(iss, buf, delim))
	{
		res.push_back(buf);
	}

	return res;
}

void traverse_trie(TrieNode trie, int depth)
{
	for (auto i = 0; i < depth; i++)
	{
		printf(" ");
	}
	printf("%s\n", trie.str.c_str());
	for (auto itr : trie.next)
	{
		traverse_trie(itr, depth + 1);
	}
}

int main()
{
	int N;
	scanf("%d", &N);

	std::set<TrieNode> trie;

	for (auto i = 0; i < N; i++)
	{
		char path[81] = { 0 };
		scanf("%s", path);

		std::vector<std::string> splited = split(std::string(path), '\\');

		auto current = &trie;
		for (auto splited_itr : splited)
		{
			auto node = current->find(TrieNode(splited_itr));

			if (node != current->end())
				current = (std::set<TrieNode>*)&(node->next);
			else
				current = (std::set<TrieNode>*)&(current->emplace(splited_itr).first->next);
		}
	}

	for (auto itr : trie)
	{
		traverse_trie(itr, 0);
	}
}