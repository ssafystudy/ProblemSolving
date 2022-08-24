#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <memory.h>
#include <vector>
#include <queue>

#define EMPTY 0
#define SHARK 9
#define MAX_GRID_SIZE 20

typedef struct Pos
{
	int x, y;
	Pos(int x, int y)
	{
		this->x = x;
		this->y = y;
	}
	Pos()
	{
	}
} Pos;

typedef struct SharkPos
{
	Pos pos;
	int size;
	int eat_count;
} SharkPos;

typedef struct FishPos
{
	Pos pos;
	int size;
	bool eaten;
} FishPos;

int N;
int grid[MAX_GRID_SIZE][MAX_GRID_SIZE] = {0};
int directions[4][2] = {{-1, 0}, {1, 0}, {0, -1}, {0,1}};
SharkPos shark;
std::vector<FishPos> fishes[7]; // size 1~6, don't use [0]
int eatable_fishes_count = 0;

bool isAvailable(int x, int y)
{
	return (x>=0 && x < N && y >= 0 && y < N);
}

// find shortest path with bfs
int get_shortest_distance(FishPos fish)
{
	int distance[MAX_GRID_SIZE][MAX_GRID_SIZE] = {0};
	memset(distance, -1, sizeof(int)*MAX_GRID_SIZE*MAX_GRID_SIZE);

	bool visited[MAX_GRID_SIZE][MAX_GRID_SIZE] = { false };
	std::queue<Pos> q;
	
	Pos p(shark.pos.x, shark.pos.y);
	q.push(p);
	visited[p.y][p.x] = true;
	distance[p.y][p.x] = 0;

	while(!q.empty())
	{
		Pos p2 = q.front();
		if(p2.x == fish.pos.x && p2.y == fish.pos.y)
			break;

		q.pop();
		int current_distance = distance[p2.y][p2.x];

		for(int i=0;i<4;i++)
		{
			int ny = p2.y + directions[i][0];
			int nx = p2.x + directions[i][1];

			if(isAvailable(nx,ny) && !visited[ny][nx] && grid[ny][nx] <= shark.size)
			{
				visited[ny][nx] = true;
				distance[ny][nx] = current_distance+1;
				q.push(Pos(nx,ny));
			}
		}
	}
	return distance[fish.pos.y][fish.pos.x];
}

// check every fishes which can be eaten
int find_eatable_and_eat()
{
	Pos eatable;
	int eatable_pos_i = -1, eatable_pos_j = -1;
	int shortest_distance = 999999;
	for(int i=1;i<7;i++)
	{
		if(i >= shark.size) // not eatable
			break;

		for(int j=0;j<fishes[i].size(); j++)
		{
			auto itr = fishes[i][j];
			if(!itr.eaten)
			{
				int temp = get_shortest_distance(itr);
				if(shortest_distance > temp && temp != -1)
				{
					shortest_distance = temp;
					eatable.x = itr.pos.x;
					eatable.y = itr.pos.y;
					
					eatable_pos_i = i;
					eatable_pos_j = j;
				}
				else if(temp == shortest_distance)
				{
					if(eatable.y > itr.pos.y)
					{
						eatable.y = itr.pos.y;
						eatable.x = itr.pos.x;

						eatable_pos_i = i;
						eatable_pos_j = j;
					}
					else if(eatable.y == itr.pos.y)
					{
						if (eatable.x > itr.pos.x)
						{
							eatable.x = itr.pos.x;
							eatable_pos_i = i;
							eatable_pos_j = j;
						}
					}
				}
			}
		}
	}

	if(eatable_pos_i != -1)
	{
		grid[eatable.y][eatable.x] = SHARK;
		grid[shark.pos.y][shark.pos.x] = EMPTY;
		
		fishes[eatable_pos_i][eatable_pos_j].eaten = true;
		eatable_fishes_count--;

		shark.eat_count++;
		if(shark.eat_count == shark.size)
		{
			shark.size++;
			shark.eat_count = 0;
		}
		shark.pos.x = eatable.x;
		shark.pos.y = eatable.y;
		return shortest_distance;
	}

	return -1;
}

int main()
{
	scanf("%d", &N);

	for (int j = 0; j < N; j++)
	{
		for (int i = 0; i < N; i++)
		{
			scanf("%d", &grid[j][i]);
			if(grid[j][i] == SHARK)
			{
				shark.pos.y = j;
				shark.pos.x = i;
				shark.size = 2; // initial size is 2
				shark.eat_count = 0;
			}
			else if(grid[j][i] != EMPTY && grid[j][i] != SHARK)
			{
				FishPos p;
				p.pos.y = j;
				p.pos.x = i;
				p.size = grid[j][i];
				p.eaten = false;
				fishes[grid[j][i]].push_back(p);
				eatable_fishes_count++;
			}
		}
	}

	int sum = 0;
	while(eatable_fishes_count > 0)
	{
		int temp = find_eatable_and_eat();

		dump();

		if(temp < 0)
			break;
		
		sum += temp;
	}

	printf("%d", sum);
}