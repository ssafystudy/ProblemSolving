#include <stdio.h>
#include <vector>
#include <algorithm>

#pragma warning(disable:4996)

typedef struct Pos {
	int x, y;
	Pos(int x, int y) : x(x), y(y) {}
	Pos() : x(0), y(0) {}
}Pos;

std::vector<std::vector<int>> grid;
int N, M, H; // W, M, H
std::vector<Pos> candidates;
int max_num = 4;

bool checkLadder()
{
	for (auto start = 1; start < N + 1; start++)
	{
		int x = start;
		int y = 0;

		while (y++ < H)
		{
			if (grid[y][x] == 0)
			{
				if (grid[y][x - 1] == 1) // check left
					x--;
			}

			else // goto right
				x++;
		}

		if (x != start)
			return false;
	}

	return true;
}
void createCandidatesList()
{
	for (auto x = 1; x < N; x++) // 1 ~ N-1 cuz i and i+1 is connected
		for (auto y = 1; y < H + 1; y++)
			if (grid[y][x-1] == 0 && grid[y][x] == 0 && grid[y][x + 1] == 0) // check left, current, right
				candidates.emplace_back(x, y);
}

int simulate(int start, int count)
{
	if (checkLadder()) // check fully constructed
	{
		max_num = std::min(max_num, count);
		return count;
	}

	if (count == 3) // max horizontal
		return -1;

	for (auto i = start; i < candidates.size(); i++) // combination
	{
		Pos current = candidates[i];

		if (grid[current.y][current.x - 1] == 1) // not continuous with left. right is considered when creating available candidates.
			continue;

		grid[current.y][current.x] = 1; // mark it
		int retval = simulate(i + 1, count + 1); // simulate it(DFS)
		grid[current.y][current.x] = 0; // unmark it
	}

	return -1;
}

int main()
{
	scanf("%d %d %d", &N, &M, &H); // input #1
	grid.resize(H+1);
	for (auto i = 0; i < H + 1; i++)
		grid[i].resize(N + 1, 0);

	for (auto i = 0; i < M; i++)
	{
		int x, y;
		scanf("%d %d", &y, &x); // input #2
		grid[y][x] = 1;
	}
	
	createCandidatesList(); // create candidates

	simulate(0, 0);

	if (max_num != 4)
		printf("%d", max_num);
	else
		printf("-1");
}