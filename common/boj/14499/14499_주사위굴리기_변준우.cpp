#include <stdio.h>

void roll_dice(int direction, int dice[3][2])
{
	int top = dice[0][0];
	int bot = dice[0][1];
	switch (direction)
	{
	case 1:
		dice[0][0] = dice[2][0];
		dice[0][1] = dice[2][1];
		dice[2][0] = bot;
		dice[2][1] = top;
		break;
	case 2:
		dice[0][0] = dice[2][1];
		dice[0][1] = dice[2][0];
		dice[2][0] = top;
		dice[2][1] = bot;
		break;
	case 3:
		dice[0][0] = dice[1][0];
		dice[0][1] = dice[1][1];
		dice[1][0] = bot;
		dice[1][1] = top;
		break;
	case 4:
		dice[0][0] = dice[1][1];
		dice[0][1] = dice[1][0];
		dice[1][0] = top;
		dice[1][1] = bot;
		break;
	}
}

int isAvailable(int y, int x, int N, int M)
{
	return (x >= 0 && x < M&& y >= 0 && y < N);
}

int main()
{
	int grid[20][20] = { 0 };
	int command[1000] = { 0 };
	int N, M, x, y, K;

	int dice[3][2] = { {0,0}, {0,0}, {0,0} }; // top bot fnt bck lft rgt
	int directions[4][2] = { {0,1}, {0,-1}, {-1,0}, {1,0} };

	scanf("%d %d %d %d %d", &N, &M, &y, &x, &K);

	for (int j = 0; j < N; j++)
		for (int i = 0; i < M; i++)
			scanf("%d", &grid[j][i]);

	for (int i = 0; i < K; i++)
	{
		int command;
		scanf("%d", &command);

		int ny = y + directions[command - 1][0];
		int nx = x + directions[command - 1][1];
		if (isAvailable(ny, nx, N, M))
		{
			roll_dice(command, dice);

			if (grid[ny][nx] == 0)
				grid[ny][nx] = dice[0][1];
			else
			{
				dice[0][1] = grid[ny][nx];
				grid[ny][nx] = 0;
			}

			x = nx;
			y = ny;
			printf("%d\n", dice[0][0]);
		}
	}
}