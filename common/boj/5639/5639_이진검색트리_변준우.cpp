#include <stdio.h>
#include <vector>
#pragma warning(disable:4996)

typedef struct Node {
	int val;
	Node* left, * right;
	Node(int val) : val(val), left(nullptr), right(nullptr) {}
	Node() : val(-1), left(nullptr), right(nullptr) {}
} Node;

Node root;

void insertNode(Node* root, int val)
{
	if (val < root->val)
	{
		if (root->left == nullptr)
		{
			root->left = new Node(val);
		}
		else
		{
			insertNode(root->left, val);
		}
	}
	else
	{
		if (root->right == nullptr)
		{
			root->right = new Node(val);
		}
		else
		{
			insertNode(root->right, val);
		}
	}
}

void postorder(Node* root)
{
	if (root->left != nullptr)
		postorder(root->left);

	if (root->right != nullptr)
		postorder(root->right);

	printf("%d\n", root->val);
}

int main()
{
	scanf("%d", &root.val);

	int val;
	while (scanf("%d", &val) > 0)
	{
		insertNode(&root, val);
	}

	postorder(&root);

	return 0;
}