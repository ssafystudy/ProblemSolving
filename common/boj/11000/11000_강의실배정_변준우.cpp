#include <iostream>
#include <queue>
#include <algorithm>
#include <vector>

typedef struct ClassTime {
	int start, end;
	ClassTime(int s, int e) : start(s), end(e) {}
	ClassTime() : start(0), end(0) {}

	bool operator<(const ClassTime& ct) const
	{
		if (this->start == ct.start)
			return this->end < ct.end;
		else
			return this->start < ct.start;
	}
}CT;

int main()
{
	std::ios::sync_with_stdio(false); std::cin.tie(0); std::cout.tie(0);

	int N;
	std::cin >> N;

	std::vector<CT> times;
	for (auto i = 0; i < N; i++)
	{
		int s, e;
		std::cin >> s >> e;
		times.emplace_back(s, e);
	}

	std::sort(times.begin(), times.end());
	std::priority_queue<int, std::vector<int>, std::greater<int>> pq;
	int cnt = 0;

	for (auto time : times)
	{
		while (!pq.empty() && pq.top() <= time.start)
			pq.pop();
		
		pq.push(time.end);
		cnt = std::max(cnt, (int)pq.size());
	}

	std::cout << cnt;

	return 0;
}