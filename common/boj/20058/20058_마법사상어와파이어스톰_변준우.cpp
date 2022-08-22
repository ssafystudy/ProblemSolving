#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <math.h>
#include <memory.h>
#include <vector>
#include <queue>

#define MAX_SIZE 64

bool g_visited[MAX_SIZE][MAX_SIZE] = { false };
int grid[MAX_SIZE][MAX_SIZE] = { 0 };
int temp[MAX_SIZE][MAX_SIZE] = { 0 };
int grid_size;
int directions[4][2] = { {0,-1}, {0,1}, {-1, 0}, {1,0} };

typedef struct Pos
{
	int x, y;
	Pos(int y, int x)
	{
		this->x = x;
		this->y = y;
	}
}Pos;

bool isAvailable(int j, int i)
{
	return (j >= 0 && j < grid_size&& i >= 0 && i < grid_size);
}

void rotate(int j, int i, int current, int len, int N)
{
	if (current == len)
	{
		for (int y = 0; y < len; y++)
			for (int x = 0; x < len; x++)
				temp[j + x][i + len - y - 1] = grid[j + y][i + x];

		return;
	}

	rotate(j, i, current / 2, len, N);
	rotate(j, i + current / 2, current / 2, len, N);
	rotate(j + current / 2, i, current / 2, len, N);
	rotate(j + current / 2, i + current / 2, current / 2, len, N);
}

void bfs(int j, int i, std::vector<Pos>* reduce_ice_list)
{
	bool bfs_visited[MAX_SIZE][MAX_SIZE] = { false };

	std::queue<Pos> q;
	q.push(Pos(j, i));
	while (!q.empty())
	{
		Pos p = q.front();
		q.pop();

		bfs_visited[p.y][p.x] = true;
		int no_close_ice_count = 0;

		for (int t = 0; t < 4; t++)
		{
			int ny = p.y + directions[t][0];
			int nx = p.x + directions[t][1];

			if (isAvailable(ny, nx))
			{
				if (!bfs_visited[ny][nx])
				{
					bfs_visited[ny][nx] = true;
					q.push(Pos(ny, nx));
				}
			}

			if (!isAvailable(ny, nx) || grid[ny][nx] <= 0)
				no_close_ice_count++;
		}
		if (no_close_ice_count > 1)
			reduce_ice_list->push_back(p);
	}
}

int find_largest_chunk(int j, int i)
{
	int chunk_size = 0;

	if (g_visited[j][i])
		return 0;

	std::queue<Pos> q;
	q.push(Pos(j, i));

	if (grid[j][i] > 0)
		chunk_size++;

	g_visited[j][i] = true;

	while (!q.empty())
	{
		Pos p = q.front();
		q.pop();
		for (int t = 0; t < 4; t++)
		{
			int ny = p.y + directions[t][0];
			int nx = p.x + directions[t][1];

			if (isAvailable(ny, nx) && grid[ny][nx] > 0 && !g_visited[ny][nx])
			{
				q.push(Pos(ny, nx));
				g_visited[ny][nx] = true;
				chunk_size++;
			}
		}
	}

	return chunk_size;
}

void simulate(int size)
{
	rotate(0, 0, grid_size, size, grid_size);
	memcpy(grid, temp, sizeof(int) * 64 * 64);

	std::vector<Pos> reduce_ice_list;
	bfs(0, 0, &reduce_ice_list);

	for (auto itr : reduce_ice_list)
		if (grid[itr.y][itr.x] > 0)
			grid[itr.y][itr.x]--;
}

int main()
{
	int N, Q;
	scanf("%d %d", &N, &Q);
	grid_size = (int)pow(2, N);

	for (int j = 0; j < grid_size; j++)
		for (int i = 0; i < grid_size; i++)
			scanf("%d", &grid[j][i]);

	for (int i = 0; i < Q; i++)
	{
		int size = 0;
		scanf("%d", &size);
		size = (int)pow(2, size);

		simulate(size);
	}

	int ice_sum = 0;
	int largest_chunk = -1;

	for (int j = 0; j < grid_size; j++)
	{
		for (int i = 0; i < grid_size; i++)
		{
			ice_sum += grid[j][i];
			largest_chunk = std::max(largest_chunk, find_largest_chunk(j, i));
		}
	}

	printf("%d\n%d", ice_sum, largest_chunk);
}