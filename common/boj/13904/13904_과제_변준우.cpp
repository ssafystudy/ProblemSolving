#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

typedef struct Assignment
{
	int remain, point;
	Assignment(int r, int p) : remain(r), point(p) {}
	Assignment() : remain(0), point(0) {}

	bool operator<(const Assignment& a) const
	{
		return this->point < a.point;
	}
} Assignment;

int main()
{
	std::ios::sync_with_stdio(false); std::cin.tie(0); std::cout.tie(0);

	int N;
	std::cin >> N;

	std::priority_queue<Assignment> assgnments;
	std::vector<int> points(1001);
	for (auto i = 0; i < N; i++)
	{
		int r, p;
		std::cin >> r >> p;

		assgnments.emplace(r, p);
	}

	while (!assgnments.empty())
	{
		Assignment a = assgnments.top();
		assgnments.pop();

		while (a.remain > 0)
		{
			if (points[a.remain] == 0)
			{
				points[a.remain] = a.point;
				break;
			}

			--a.remain;
		}
	}

	int sum = 0;
	for_each(points.begin(), points.end(), [&](int& point) {sum += point;});

	std::cout << sum;
}