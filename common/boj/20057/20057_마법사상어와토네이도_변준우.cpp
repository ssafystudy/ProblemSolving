#include <stdio.h>
#include <vector>
#include <algorithm>

typedef struct Pos {
	int x, y;
	Pos(int x, int y) : x(x), y(y) {}
	Pos() : x(0), y(0) {}
} Pos;

const int directions[4][2] = { {0, -1}, {1, 0}, {0, 1}, {-1 , 0} };
const float ratio[] = { 1,1,2,2,7,7,10,10,5,100 };
const int pos[4][10][2] =
{
	{	{-1, 1}, {1,1}, {-2, 0}, {2, 0}, {-1, 0}, {1, 0}, {-1, -1}, {1, -1}, {0, -2}, {0, -1}	}
	,	{ {-1, -1}, { -1, 1}, {0, -2}, {0, 2}, {0, -1}, {0, 1}, {1, -1}, {1, 1}, {2, 0}, {1 ,0} }
	, { {-1, -1}, {1,-1}, {-2, 0}, {2, 0}, {-1, 0}, {1, 0}, {-1, 1}, {1, 1}, {0, 2}, {0, 1} }
	, { {1, -1}, { 1, 1}, {0, -2}, {0, 2}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {-2, 0}, {-1 ,0} }
};

int N;
int grid[499][499] = { 0 };
Pos tornado;

bool isAvailable(int x, int y)
{
	return (x >= 0 && x < N && y >= 0 && y < N);
}

int blow(Pos tornado, int direction)
{
	int oob_sum = 0;
	int calculated = 0;

	for (auto i=0;i<10;i++)
	{
		int ny = tornado.y + pos[direction][i][0];
		int nx = tornado.x + pos[direction][i][1];

		int current_calculated = grid[tornado.y][tornado.x] * (ratio[i] / 100);

		if (isAvailable(nx, ny))
		{
			if (i == 9)
			{
				grid[ny][nx] += grid[tornado.y][tornado.x] - calculated;
			}
			else
				grid[ny][nx] += current_calculated;
		}
		else
		{
			oob_sum += current_calculated;
		}

		calculated += current_calculated;
	}

	grid[tornado.y][tornado.x] = 0;

	return oob_sum;
}

int simulate()
{
	tornado = Pos(N / 2, N / 2);
	int tornado_move_cnt = 0;
	int oob_sum = 0;

	for (tornado_move_cnt = 0; tornado_move_cnt < N * 2 - 1; tornado_move_cnt++)
	{
		for (auto amount = 0; amount < tornado_move_cnt / 2 + 1; amount++)
		{
			int direction = tornado_move_cnt % 4;
			tornado.y += directions[direction][0];
			tornado.x += directions[direction][1];

			if (tornado.y < 0 || tornado.x < 0)
				break;

			oob_sum += blow(tornado, direction);
		}
	}

	return oob_sum;
}


int main()
{
	scanf("%d", &N);
	tornado = Pos(N / 2, N / 2);

	int total_sum = 0;

	for (auto i = 0; i < N; i++)
	{
		for (auto j = 0; j < N; j++)
		{
			scanf("%d", &grid[i][j]);
			total_sum += grid[i][j];
		}
	}

	simulate();

	int remain = 0;
	for (auto i = 0; i < N; i++)
	{
		for (auto j = 0; j < N; j++)
		{
			if (grid[i][j] != 0)
				remain += grid[i][j];
		}
	}

	printf("%d", total_sum - remain);
}