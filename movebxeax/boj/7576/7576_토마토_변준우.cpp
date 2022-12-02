#include <iostream>
#include <queue>
#include <vector>
#include <algorithm>

typedef struct Pos {
	int x, y;
	Pos(int x, int y) : x(x), y(y) {};
	Pos() : x(0), y(0) {};
} Pos;

int grid[1000][1000];
int M, N;
const int directions[4][2] = { {-1, 0}, {1,0}, {0, -1}, {0, 1} };
std::vector<Pos> init_pos;

bool isAvailable(int x, int y)
{
	return x >= 0 && x < M&& y >= 0 && y < N;
}

int check_grid()
{
	int max = -1;
	for (auto i = 0; i < N; i++)
	{
		for (auto j = 0; j < M; j++)
		{
			if (grid[i][j] == 0)
				return -1;

			max = std::max(max, grid[i][j]);
		}
	}

	return max-1;
}

int bfs()
{
	std::queue<Pos> q;
	for (auto itr : init_pos)
		q.push(itr);

	int elapsed = 0;

	while (!q.empty())
	{
		elapsed++;

		int cnt = 0;
		int size = q.size();
		while (++cnt <= size)
		{
			Pos p = q.front();
			q.pop();
			for (auto itr : directions)
			{
				int ny = p.y + itr[0];
				int nx = p.x + itr[1];

				if (isAvailable(nx, ny) && grid[ny][nx] == 0)
				{
					grid[ny][nx] = grid[p.y][p.x] + 1;
					q.emplace(nx, ny);
				}
			}
		}
	}

	return elapsed;
}

int main()
{
	std::ios::sync_with_stdio(false); std::cin.tie(0); std::cout.tie(0);

	std::cin >> M >> N;

	for (auto i = 0; i < N; i++)
	{
		for (auto j = 0; j < M; j++)
		{
			std::cin >> grid[i][j];
			if (grid[i][j] == 1)
				init_pos.emplace_back(j, i);
		}
	}

	int elapsed = bfs();

	std::cout << check_grid();
}
