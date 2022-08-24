#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <vector>
#include <algorithm>

#define WATCHING 9
#define EMPTY 0
#define WALL 6
#define MAX_GRID_SIZE 8

// [i][j][k] => i = Cam.direction(from vector), j = loop in fuction, k = ref in get_dxdy
static int direction_type1[4][2] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; 
static int direction_type2[4][2][2] = {{{0, 1}, {0, -1}}, {{1, 0}, {-1, 0}}}; // not 2 but 4 for convinience
static int direction_type3[4][2][2] = {{{0, 1}, {1, 0}}, {{1, 0}, {0, -1}}, {{0, -1}, {-1, 0}}, {{-1, 0}, {0, 1}}};
static int direction_type4[4][3][2] = {{{0, 1}, {1, 0}, {0, -1}}, {{1, 0}, {0, -1}, {-1, 0}}, {{0, -1}, {-1, 0}, {0, 1}}, {{-1, 0}, {0, 1}, {1, 0}}};

typedef struct Cam
{
	int x, y, type, direction;
	Cam(int x, int y, int type)
	{
		this->x = x;
		this->y = y;
		this->type = type;
		this->direction = 0;
	}
	Cam() {}
} Cam;

typedef struct dxdy
{
	int dx, dy;
} dxdy;

int grid[MAX_GRID_SIZE][MAX_GRID_SIZE] = {};
int N, M;
std::vector<Cam> cam_list;

bool isAvailable(int x, int y)
{
	return (x >= 0 && x < M && y >= 0 && y < N);
}

dxdy get_dxdy(Cam cam, int p)
{
	dxdy result;
	switch(cam.type)
	{
		case 1:
			// 1 way
			result.dy = direction_type1[cam.direction][1];
			result.dx = direction_type1[cam.direction][0];
			break;
		case 2:
			// 2 ways (->, <-)
			result.dy = direction_type2[cam.direction][p][1];
			result.dx = direction_type2[cam.direction][p][0];
			break;
		case 3:
			// 2 ways (->, ^)
			result.dy = direction_type3[cam.direction][p][1];
			result.dx = direction_type3[cam.direction][p][0];
			break;
		case 4:
			// 3 ways (->, ^, <-)
			result.dy = direction_type4[cam.direction][p][1];
			result.dx = direction_type4[cam.direction][p][0];
			break;
		case 5:
			// 4 ways
			result.dy = direction_type1[p][1];
			result.dx = direction_type1[p][0];
	}

	return result;
}

void combination(int current, std::vector<int> prev, std::vector<std::vector<int>> *result)
{
	if(prev.size() == current)
	{
		result->push_back(prev);
		return;
	}

	for(int i=0;i<4;i++)
	{
		prev.push_back(i);
		combination(current, prev, result);
		prev.pop_back();
	}
}

int draw_grid_and_calculate(std::vector<int> heading_list)
{
	int temp[MAX_GRID_SIZE][MAX_GRID_SIZE] = {};
	memcpy(temp, grid, sizeof(int)*MAX_GRID_SIZE*MAX_GRID_SIZE);

	for(int i=0;i<heading_list.size();i++)
	{
		Cam cam = cam_list.at(i);
		cam.direction = heading_list.at(i);
		if(cam.type == 2 && cam.direction > 1) // type2 has only 2 ways
			continue;

		int turn = 1;

		if(cam.type == 2 || cam.type == 3)
			turn = 2;
		else if(cam.type == 4)
			turn = 3;
		else if(cam.type == 5)
			turn = 4;

		for (int p = 0; p < turn; p++)
		{
			int x, y;
			dxdy d = get_dxdy(cam, p);
			x = cam.x + d.dx;
			y = cam.y + d.dy;

			while (isAvailable(x, y) && temp[y][x] != WALL)
			{
				if (temp[y][x] == EMPTY)
					temp[y][x] = WATCHING;
				
				x += d.dx;
				y += d.dy;
			}
		}
	}

	int empty_count = 0;
	for(int j=0;j<N;j++)
	{
		for(int i=0;i<M;i++)
		{
			if(temp[j][i] == EMPTY)
				empty_count++;
		}
	}

	return empty_count;
}

int main()
{
	scanf("%d %d", &N, &M);

	for (int j = 0; j < N; j++)
	{
		for (int i = 0; i < M; i++)
		{
			scanf("%d", &grid[j][i]);
			if(grid[j][i] != EMPTY && grid[j][i] != WALL)
				cam_list.push_back(Cam(i, j, grid[j][i]));
		}
	}

	std::vector<int> temp;
	std::vector<std::vector<int>> result;
	combination(cam_list.size(), temp, &result);

	int answer = MAX_GRID_SIZE * MAX_GRID_SIZE + 1; // max val
	for(auto itr : result)
		answer = std::min(answer, draw_grid_and_calculate(itr));

	printf("%d", answer);

	return 0;
}