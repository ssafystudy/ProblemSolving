#include <stdio.h>
#include <vector>
#pragma warning(disable:4996)

typedef struct Node {
	int left = -1, right = -1;
} Node;

std::vector<Node> tree;

void preorder(int root)
{
	if (tree[root].left == -1 && tree[root].right == -1)
	{
		printf("%c", root + 0x41);
		return;
	}

	printf("%c", root + 0x41);

	if (tree[root].left != -1)
		preorder(tree[root].left);

	if (tree[root].right != -1)
		preorder(tree[root].right);

}

void inorder(int root)
{
	if (tree[root].left == -1 && tree[root].right == -1)
	{
		printf("%c", root + 0x41);
		return;
	}

	if (tree[root].left != -1)
		inorder(tree[root].left);

	printf("%c", root + 0x41);

	if (tree[root].right != -1)
		inorder(tree[root].right);

}

void postorder(int root)
{
	if (tree[root].left == -1 && tree[root].right == -1)
	{
		printf("%c", root + 0x41);
		return;
	}

	if (tree[root].left != -1)
		postorder(tree[root].left);

	if (tree[root].right != -1)
		postorder(tree[root].right);

	printf("%c", root + 0x41);
}

int main()
{
	int N;
	scanf("%d ", &N);
	tree.resize(26);

	for (auto i = 0; i < N; i++)
	{
		char p, l, r;
		scanf(" %c %c %c", &p, &l, &r);
		
		if (l != '.')
			tree[p - 0x41].left = l - 0x41;
		else
			tree[p - 0x41].left = -1;

		if (r != '.')
			tree[p - 0x41].right = r - 0x41;
		else
			tree[p - 0x41].right = -1;
	}

	preorder(0);
	printf("\n");
	inorder(0);
	printf("\n");
	postorder(0);
}