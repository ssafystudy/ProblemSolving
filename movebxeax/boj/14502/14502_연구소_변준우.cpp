#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <memory.h>
#include <algorithm>
#include <vector>
#include <queue>
#include <limits>

#define MAX_GRID_SIZE 8
#define EMPTY 0
#define WALL 1
#define VIRUS 2

typedef struct Pos {
	int x, y;
	Pos() : x(0), y(0) {}
	Pos(int x, int y) : x(x), y(y) {}
};

const int directions[4][2] = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
int N, M;
int safe_zone_size = std::numeric_limits<int>::min();
std::vector<Pos> virus;
std::vector<Pos> empty;
std::vector<std::vector<Pos>> wall_candidate;

bool isAvailable(int x, int y)
{
	return (x >= 0 && x < M&& y >= 0 && y < N);
}

void spread(int x, int y, int grid[MAX_GRID_SIZE][MAX_GRID_SIZE])
{
	std::queue<Pos> q;
	bool visited[MAX_GRID_SIZE][MAX_GRID_SIZE] = { false };

	q.push(Pos(x, y));
	visited[y][x] = true;

	while (!q.empty())
	{
		Pos p = q.front();
		q.pop();

		for (int i = 0; i < 4; i++)
		{
			int ny = p.y + directions[i][0];
			int nx = p.x + directions[i][1];

			if (isAvailable(nx, ny) && !visited[ny][nx] && grid[ny][nx] == EMPTY)
			{
				grid[ny][nx] = VIRUS;

				q.push(Pos(nx, ny));
				visited[ny][nx] = true;
			}
		}
	}

	return;
}

int get_largest_safe_zone_size(int grid[][MAX_GRID_SIZE])
{
	int size = 0;
	for (int j = 0; j < N; j++)
	{
		for (int i = 0; i < M; i++)
		{
			if (grid[j][i] == EMPTY)
				size++;
		}
	}

	return size;
}

void draw_grid(int grid[MAX_GRID_SIZE][MAX_GRID_SIZE], std::vector<Pos> candidates)
{
	int temp_grid[MAX_GRID_SIZE][MAX_GRID_SIZE] = { 0 };
	memcpy(temp_grid, grid, sizeof(int) * MAX_GRID_SIZE * MAX_GRID_SIZE);

	for (auto itr : candidates)
	{
		int i = itr.x;
		int j = itr.y;
		temp_grid[j][i] = WALL;
	}

	for (auto itr : virus)
		spread(itr.x, itr.y, temp_grid);

	bool visited[MAX_GRID_SIZE][MAX_GRID_SIZE] = { false };
	safe_zone_size = std::max(safe_zone_size, get_largest_safe_zone_size(temp_grid));

	return;
}

void combination(int start, std::vector<Pos> prev, int grid[][8])
{
	if (prev.size() == 3)
	{
		//wall_candidate.push_back(prev);
		draw_grid(grid, prev);
		return;
	}

	for (int i = start; i < empty.size(); i++)
	{
		prev.push_back(empty[i]);
		combination(i + 1, prev, grid);
		prev.pop_back();
	}
}

int main()
{
	int grid[MAX_GRID_SIZE][MAX_GRID_SIZE] = { 0 };

	scanf("%d %d", &N, &M);

	for (int j = 0; j < N; j++)
		for (int i = 0; i < M; i++)
		{
			scanf("%d", &grid[j][i]);
			if (grid[j][i] == VIRUS)
				virus.push_back(Pos(i, j));
			else if (grid[j][i] == EMPTY)
				empty.push_back(Pos(i, j));
		}

	std::vector<Pos> prev;
	combination(0, prev, grid);

	for (auto itr : wall_candidate)
	{
		draw_grid(grid, itr);
	}

	printf("%d", safe_zone_size);
}