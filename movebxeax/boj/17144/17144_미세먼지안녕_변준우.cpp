#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>

int grid[50][50];
int air_purifier[2];
int R, C, T;
const int directions[4][2] = { {-1, 0}, {1, 0}, {0, -1}, {0,1} };

int calculate_dust_sum()
{
	int sum = 0;
	for (int j = 0; j < R; j++)
		for (int i = 0; i < C; i++)
			if(grid[j][i] > 0)
				sum += grid[j][i];

	return sum;
}

bool isAvailable(int x, int y)
{
	return (x >= 0 && x < C && y >= 0 && y < R);
}

void run_purifier()
{
	// upper grid
	int start = air_purifier[0];
	for (int i = start-1; i > -1; i--) // going down
	{
		if (grid[i+1][0] != -1)
			grid[i + 1][0] = grid[i][0];
	}

	for (int i = 0; i < C - 1; i++) // going left
	{
		grid[0][i] = grid[0][i + 1];
	}

	for (int i = 0; i < air_purifier[0]; i++) // going up
	{
		grid[i][C - 1] = grid[i+1][C - 1];
	}

	for (int i = C - 1; i > 1; i--) // going right
	{
		grid[start][i] = grid[start][i - 1];
	}

	grid[start][1] = 0;

	// lower grid
	start = air_purifier[1];
	for (int i = start; i < R - 1; i++) // going up
	{
		if (grid[i][0] != -1)
			grid[i][0] = grid[i+1][0];
	}

	for (int i = 0; i < C - 1; i++) // going left
	{
		grid[R-1][i] = grid[R-1][i + 1];
	}

	for (int i = R-1; i > start; i--) // going down
	{
		grid[i][C - 1] = grid[i - 1][C - 1];
	}

	for (int i = C - 1; i > 1; i--) // going right
	{
		grid[start][i] = grid[start][i - 1];
	}
	grid[start][1] = 0;
}


void simulate()
{
	// calculate difusion value
	int diffusion_grid[50][50] = { 0 };
	for (int j = 0; j < R; j++)
	{
		for (int i = 0; i < C; i++)
		{
			if (grid[j][i] > 0)
			{
				int count = 0;
				int diffusion_value = grid[j][i] / 5;

				for (int k = 0; k < 4; k++)
				{
					int ny = j + directions[k][0];
					int nx = i + directions[k][1];

					if (isAvailable(nx, ny) && grid[ny][nx] != -1)
					{
						count++;
						diffusion_grid[ny][nx] += diffusion_value;
					}
				}

				diffusion_grid[j][i] += -(diffusion_value * count);
			}
		}
	}

	// merge diffusion into grid
	for (int j = 0; j < R; j++)
		for (int i = 0; i < C; i++)
			grid[j][i] += diffusion_grid[j][i];

	run_purifier();
}

int main()
{
	scanf("%d %d %d", &R, &C, &T);
	int is_purifier_found = false;

	for (int j = 0; j < R; j++)
	{
		for (int i = 0; i < C; i++)
		{
			scanf("%d", &grid[j][i]);

			if (grid[j][i] < 0 && !is_purifier_found)
			{
				is_purifier_found = true;
				air_purifier[0] = j;
				air_purifier[1] = j+1;
			}
		}
	}

	for (int i = 0; i < T; i++)
		simulate();

	printf("%d", calculate_dust_sum());
}