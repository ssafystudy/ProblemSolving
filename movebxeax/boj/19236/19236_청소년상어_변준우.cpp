#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <memory.h>
#include <map>
#include <algorithm>

#define WRONG_DIR -1
#define SHARK -1
#define EMPTY 0
#define GRID_SIZE 4

typedef struct Pos {
	int x, y, direction;
	Pos() {}
	Pos(int x, int y, int d)
	{
		this->x = x;
		this->y = y;
		this->direction = d;
	}
};

typedef struct Shark {
	Pos pos;
	int size;
	Shark() {}
	Shark(int x, int y, int d)
	{
		this->pos.x = x;
		this->pos.y = y;
		this->pos.direction = d;
		this->size = 0;
	}
} Shark;

typedef struct Fish {
	Pos pos;
	bool isAlive;
	Fish() {}
	Fish(int x, int y, int d)
	{
		this->pos.x = x;
		this->pos.y = y;
		this->pos.direction = d;
		this->isAlive = true;
	}
} Fish;

const int directions[8][2] = { {-1,0}, {-1,-1}, {0,-1}, {1,-1}, {1,0}, {1,1}, {0,1}, {-1,1} };
int direction_grid[GRID_SIZE][GRID_SIZE];
int fish_grid[GRID_SIZE][GRID_SIZE];
std::map<int, Fish> fish_map;
int max_size = 0;

void swap(int* a, int* b)
{
	int temp = *a;
	*a = *b;
	*b = temp;
}

bool isAvailable(int x, int y)
{
	return (x >= 0 && x < GRID_SIZE&& y >= 0 && y < GRID_SIZE);
}

void dfs(Shark shark, Shark prev_shark, int arg_fish_grid[4][4], int arg_direction_grid[4][4], std::map<int, Fish> arg_fish_map)
{
	// copy datas
	std::map<int, Fish> t_fish_map;
	int t_direction_grid[GRID_SIZE][GRID_SIZE];
	int t_fish_grid[GRID_SIZE][GRID_SIZE];
	t_fish_map.insert(arg_fish_map.begin(), arg_fish_map.end());
	memcpy(t_fish_grid, arg_fish_grid, sizeof(int) * GRID_SIZE * GRID_SIZE);
	memcpy(t_direction_grid, arg_direction_grid, sizeof(int) * GRID_SIZE * GRID_SIZE);

	// shark eats fish
	int shark_x = shark.pos.x;
	int shark_y = shark.pos.y;
	shark.size += t_fish_grid[shark_y][shark_x];
	shark.pos.direction = t_direction_grid[shark_y][shark_x];
	t_fish_map[t_fish_grid[shark_y][shark_x]].isAlive = false;
	t_fish_grid[prev_shark.pos.y][prev_shark.pos.x] = EMPTY;
	t_direction_grid[prev_shark.pos.y][prev_shark.pos.x] = WRONG_DIR;
	t_fish_grid[shark_y][shark_x] = SHARK;

	// move fishes
	for (int i = 1; i < 17; i++)
	{
		Fish temp = t_fish_map[i];
		if (!temp.isAlive)
			continue;

		int tdir = t_direction_grid[temp.pos.y][temp.pos.x];
		for (int t = 0; t < 8; t++)
		{
			int ty = temp.pos.y + directions[(tdir + t) % 8][0];
			int tx = temp.pos.x + directions[(tdir + t) % 8][1];

			if (!isAvailable(tx, ty) || t_fish_grid[ty][tx] == SHARK)
				continue;
			else
			{
				t_fish_map[t_fish_grid[ty][tx]].pos.x = temp.pos.x;
				t_fish_map[t_fish_grid[ty][tx]].pos.y = temp.pos.y;
				t_fish_map[i].pos.x = tx;
				t_fish_map[i].pos.y = ty;
				t_direction_grid[temp.pos.y][temp.pos.x] = (tdir + t) % 8;

				swap(&t_fish_grid[temp.pos.y][temp.pos.x], &t_fish_grid[ty][tx]);
				swap(&t_direction_grid[temp.pos.y][temp.pos.x], &t_direction_grid[ty][tx]);
				break;
			}
		}
	}

	int tdir = shark.pos.direction;
	for(int pass = 1; pass < 5; pass++)
	{
		int ty = shark.pos.y + directions[tdir][0] * pass;
		int tx = shark.pos.x + directions[tdir][1] * pass;

		// empty
		if (t_fish_grid[ty][tx] == EMPTY)
		{
			// do nth.
		}
		// out of range
		else if (!isAvailable(ty, tx))
		{
			max_size = std::max(max_size, shark.size);
			break;
		}
		// fish
		else
		{
			Shark t_shark;
			t_shark.pos.x = tx;
			t_shark.pos.y = ty;
			t_shark.pos.direction = tdir;
			t_shark.size = shark.size;
			dfs(t_shark, shark, t_fish_grid, t_direction_grid, t_fish_map);
		}
	}
}

int main()
{
	for (int j = 0; j < 4; j++)
	{
		for (int i = 0; i < 4; i++)
		{
			int fish_number, direction;
			scanf("%d %d", &fish_number, &direction);
			--direction;
			
			direction_grid[j][i] = direction;
			fish_grid[j][i] = fish_number;
			fish_map.emplace(fish_number, Fish(i, j, direction));
		}
	}

	Shark shark(0, 0, direction_grid[0][0]);
	dfs(shark, Shark(0,0,0), fish_grid, direction_grid, fish_map);

	printf("%d", max_size);
}