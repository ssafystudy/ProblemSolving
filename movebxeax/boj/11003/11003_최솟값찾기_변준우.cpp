#include <iostream>
#include <queue>
#pragma warning(disable:4996)

typedef struct Value {
	int val, pos;
	Value(int val, int pos) : val(val), pos(pos) {}

	bool operator<(const Value& v) const {
		return this->val > v.val;
	}
}Value;

int values[5000000] = { 0 };
int main()
{
	std::ios::sync_with_stdio(false);
	std::cin.tie(NULL);
	std::cout.tie(NULL);

	int N, L;
	//scanf("%d %d", &N, &L);
	std::cin >> N >> L;

	std::priority_queue<Value> pq;

	for (int i = 0; i < N; i++)
	{
		//scanf("%d", &values[i]);
		std::cin >> values[i];
		pq.push(Value(values[i], i));

		while (pq.top().pos < i-L+1)
			pq.pop();

		//printf("%d ", pq.top().val);
		std::cout << pq.top().val << ' ';
	}
}