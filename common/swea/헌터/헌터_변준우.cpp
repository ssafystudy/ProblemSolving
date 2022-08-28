#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <memory.h>
#include <math.h>
#include <vector>
#include <map>
#include <queue>
#include <algorithm>
#include <limits>

#define MAX_GRID_SIZE 10
#define EMPTY 0

typedef struct Pos {
	int x, y;
	Pos() : x(0), y(0) {}
	Pos(int x, int y) : x(x), y(y) {}
}Pos;

int getDistance(Pos start, Pos end)
{
	return (abs(start.x - end.x) + abs(start.y - end.y));
}

int calculate_route(std::vector<int> route, std::map<int,Pos> map)
{
	Pos start(0,0);
	int result = 0;
	for (auto itr : route)
	{
		Pos end = map[itr];
		result += getDistance(start, end);

		start = end;
	}

	return result;
}

int simulate(std::vector<int> todo_list, std::map<int, Pos> map)
{
	int result = std::numeric_limits<int>::max();
	sort(todo_list.begin(), todo_list.end());

	while (next_permutation(todo_list.begin(), todo_list.end()))
	{
		int visited = 0;
		bool available = true;

		for (auto itr : todo_list)
		{
			// monster if positive, customer if negative
			if (itr > 0)
			{
				visited |= (1 << itr);
			}
			else
			{
				if ((visited & (1 << abs(itr))) == 0)
				{
					available = false;
					break;
				}
			}
		}

		if (available)
			result = std::min(result, calculate_route(todo_list, map));
	}

	return result;
}

int main()
{
	int T;
	scanf("%d", &T);
	std::vector<int> results;
	for (int test_case = 1; test_case <= T; test_case++)
	{
		int N;
		std::vector<int> todo_list;
		std::map<int, Pos> map;

		scanf("%d", &N);

		for (int j = 0; j < N; j++)
		{
			for (int i = 0; i < N; i++)
			{
				int val;
				scanf("%d", &val);
				if (val != 0)
				{
					todo_list.push_back(val);
					map.emplace(val, Pos(i, j));
				}
			}
		}
		printf("#%d %d\n", test_case, simulate(todo_list, map));

		todo_list.clear();
		map.clear();
	}
}