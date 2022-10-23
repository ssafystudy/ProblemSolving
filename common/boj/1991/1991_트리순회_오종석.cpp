#include <iostream>
#include <vector>

using namespace std;

int n;

struct node {int left = -1, right = -1;};

node tree[26];

void pre(int node) {
	cout << char(node + 'A');
	if (tree[node].left != -1) pre(tree[node].left);
	if (tree[node].right != -1) pre(tree[node].right);
}

void in(int node) {
	if (tree[node].left != -1) in(tree[node].left);
	cout << char(node + 'A');
	if (tree[node].right != -1) in(tree[node].right);
}

void post(int node) {
	if (tree[node].left != -1) post(tree[node].left);
	if (tree[node].right != -1) post(tree[node].right);
	cout << char(node + 'A');
}

int main() {
	ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	cin >> n;

	for (int i = 0; i < n; i++) {
		char root, left, right;
		cin >> root >> left >> right;
		if (left != '.') tree[root - 'A'].left = left - 'A';
		if (right != '.') tree[root - 'A'].right = right - 'A';
	}
	pre(0); cout << "\n";
	in(0); cout << "\n";
	post(0); cout << "\n";
}