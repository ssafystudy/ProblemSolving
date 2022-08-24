#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <vector>
#include <algorithm>
#include <map>
#include <memory.h>

struct Shark
{
	int r, c, s, d, z;
};

static int directions[4][2] = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}}; // u d r l
static int EMPTY = -1;
int grid[101][101];
std::map<int, Shark> sharks;
int R, C, M;

bool isAvailable(int r, int c)
{
	return (r >= 1 && r <= R && c >= 1 && c <= C);
}

void dump()
{
	for (int j = 1; j < R + 1; j++)
	{
		for (int i = 1; i < C + 1; i++)
			printf("%d ", grid[j][i] == EMPTY ? 0 : grid[j][i]);
		printf("\n");
	}
	printf("\n");
}

void move()
{
	std::map<int, Shark> temp;
	for (auto shark : sharks)
	{
		int moves_r = R - 2 < 1 ? shark.second.s : shark.second.s % (2 * R - 2);
		int moves_c = R - 2 < 1 ? shark.second.s : shark.second.s % (2 * C - 2);
		int d = shark.second.d;

		printf("%d %d %d %d %d\n", shark.first, shark.second.d, shark.second.s, moves_r, moves_c);

		if (d < 2)
		{
			for (int s = 0; s < moves_r; s++)
			{
				int nr = shark.second.r + directions[d][0];
				if (!isAvailable(nr, 1))
				{
					if (d == 0)
						d++;
					else
						d--;
					nr = shark.second.r + directions[d][0];
				}
				shark.second.r = nr;
			}
		}
		else
		{
			for (int s = 0; s < moves_c; s++)
			{
				int nc = shark.second.c + directions[d][1];
				if (!isAvailable(1, nc))
				{
					if (d == 2)
						d++;
					else
						d--;
					nc = shark.second.c + directions[d][1];
				}
				shark.second.c = nc;
			}
		}

		shark.second.d = d;

		temp.emplace(shark.first, shark.second);
	}

	sharks.clear();
	sharks.swap(temp);
}

void remove_duplicated_pos()
{
	std::vector<int> dup_unique_number;
	memset(grid, EMPTY, sizeof(int) * 101 * 101);

	for (auto shark : sharks)
	{
		int r = shark.second.r;
		int c = shark.second.c;
		if (grid[r][c] == EMPTY)
		{
			grid[r][c] = shark.first;
			continue;
		}
		else
		{
			if (sharks[grid[r][c]].z < shark.second.z)
			{
				dup_unique_number.push_back(grid[r][c]);
				grid[r][c] = shark.first;
			}
			else
			{
				dup_unique_number.push_back(shark.first);
			}
		}
	}

	for (auto itr : dup_unique_number)
		sharks.erase(itr);
}

int main()
{
	memset(grid, EMPTY, sizeof(int) * 101 * 101);
	scanf("%d %d %d", &R, &C, &M);

	for (int i = 1; i <= M; i++)
	{
		Shark shark;
		scanf("%d %d %d %d %d", &shark.r, &shark.c, &shark.s, &shark.d, &shark.z);
		--shark.d;
		sharks.emplace(i, shark); // match d with directions var
		grid[shark.r][shark.c] = i;
	}

	int sum = 0;
	dump();
	for (int human = 1; human <= C; human++)
	{
		if (sharks.size() == 0)
			break;

		for (int i = 1; i <= R; i++)
		{
			if (grid[i][human] != EMPTY)
			{
				sum += sharks[grid[i][human]].z;
				sharks.erase(grid[i][human]);
				break;
			}
		}

		move();
		remove_duplicated_pos();
		dump();
	}

	printf("%d", sum);

	return 0;
}