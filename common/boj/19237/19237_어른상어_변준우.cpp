#include <stdio.h>
#include <vector>
#include <unordered_map>
#include <algorithm>
#pragma warning(disable:4996)

typedef struct Pos {
	int x, y;
	Pos(int x, int y) : x(x), y(y) {}
	Pos() : x(0), y(0) {}
} Pos;

typedef struct Info {
	Pos p;
	int priority[5][4] = { 0 };
	int cur_dir;
	bool expelled = false;
	Info(int x, int y) : p(Pos(x, y)), cur_dir(0) {}
	Info() : p(Pos()), cur_dir(0) {}
} Info;

const int directions[5][2] = { {0, 0}, {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
int grid[2][20][20] = { 0 };
int N, M, K;
std::unordered_map<int, Info> sharks;

bool isAvailable(int x, int y)
{
	return (x >= 0 && x < N&& y >= 0 && y < N);
}

int simulate()
{
	int t = 0;
	while (t++ < 1000)
	{
		std::unordered_map<int, Pos> moves;
		for (auto shark : sharks)
		{
			// 0. no operations if expelled
			if (shark.second.expelled)
				continue;

			// 1. find where to move
			int cur_dir = shark.second.cur_dir;
			Pos cur_pos = shark.second.p;
			Pos new_pos(-1, -1);
			Pos prev_smell(-1, -1);
			int next_direction = -1;
			int next_direction_if_prev = -1;
			for (auto itr : shark.second.priority[cur_dir])
			{
				int ny = cur_pos.y + directions[itr][0];
				int nx = cur_pos.x + directions[itr][1];

				if (!isAvailable(nx, ny))
					continue;

				if (prev_smell.x == -1 && grid[0][ny][nx] == shark.first) // bakcup prev smell pos
				{
					prev_smell = Pos(nx, ny);
					next_direction_if_prev = itr;
				}

				if (grid[0][ny][nx] == 0) // find next pos with no smell
				{
					new_pos = Pos(nx, ny);
					next_direction = itr;
					break;
				}
			}

			// 2. create next pos map
			if (new_pos.x == -1) // no empty pos
			{
				if (next_direction_if_prev == -1)
					moves.emplace(shark.first, cur_pos);
				else
				{
					moves.emplace(shark.first, prev_smell);
					sharks[shark.first].cur_dir = next_direction_if_prev;
				}
			}
			else
			{
				moves.emplace(shark.first, new_pos);
				sharks[shark.first].cur_dir = next_direction;
			}
		}

		// 3. decrease smell count
		for (auto y = 0; y < N; y++)
		{
			for (auto x = 0; x < N; x++)
			{
				if (grid[1][y][x] > 0)
					grid[1][y][x]--;

				if (grid[1][y][x] == 0)
					grid[0][y][x] = 0;
			}
		}

		// 4. move sharks and leave smell
		for (auto move : moves)
		{
			Pos p = move.second;
			if (grid[0][p.y][p.x] == 0 || grid[0][p.y][p.x] == move.first)
			{
				grid[0][p.y][p.x] = move.first;
				grid[1][p.y][p.x] = K;
				sharks[move.first].p = p;
			}
			else if(grid[0][p.y][p.x] > move.first)
			{
				int prev_shark = grid[0][p.y][p.x];
				sharks[prev_shark].expelled = true;

				grid[0][p.y][p.x] = move.first;
				grid[1][p.y][p.x] = K;
				sharks[move.first].p = p;
			}
			else
			{
				sharks[move.first].expelled = true;
			}
		}

		// 5. check all sharks are expelled
		int expelled_cnt = 0;
		for (auto shark : sharks)
		{
			if (shark.first != 1 && shark.second.expelled)
				expelled_cnt++;
		}

		if (expelled_cnt == M - 1)
			return t;
	}

	return -1;
}

int main()
{
	scanf("%d %d %d", &N, &M, &K);

	for (auto y = 0; y < N; y++)
	{
		for (auto x = 0; x < N; x++)
		{
			scanf("%d", &grid[0][y][x]);
			if (grid[0][y][x] != 0)
			{
				sharks.emplace(grid[0][y][x], Info(x,y));
				grid[1][y][x] = K;
			}
		}
	}

	for (auto i = 1; i <= M; i++)
		scanf("%d", &sharks[i].cur_dir);

	for (auto i = 1; i <= M; i++)
		for (auto j = 1; j <= 4; j++)
			for (auto k = 0; k < 4; k++)
				scanf("%d", &sharks[i].priority[j][k]);

	printf("%d", simulate());
}