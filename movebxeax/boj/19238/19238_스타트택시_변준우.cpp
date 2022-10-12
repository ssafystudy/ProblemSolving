#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <queue>
#include <map>

#define MAX_GRID 20
#define WALL 9999
#define EMPTY 0

typedef struct Pos {
	int x, y;
	int distance;
	Pos() : x(0), y(0) {}
	Pos(int x, int y) : x(x), y(y) {}
	Pos(int x, int y, int d) : x(x), y(y), distance(d) {}

	bool operator<(const Pos p) const
	{
		if (this->distance == p.distance)
		{
			if (this->y == p.y)
				return this->x > p.x;
			else
				return this->y > p.y;
		}
		else
			return this->distance > p.distance;
	}
};

typedef struct CustomerGoalPair
{
	Pos customer, goal;
	CustomerGoalPair() : customer(Pos()), goal(Pos()) {}
	CustomerGoalPair(Pos c, Pos g) : customer(c), goal(g) {}
} CustomerGoalPair;

const int directions[4][2] = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
int N, M;
int grid[MAX_GRID][MAX_GRID] = { 0 };
std::map<int, CustomerGoalPair> customers;

bool isAvailable(int x, int y)
{
	return (x >= 0 && x < N&& y >= 0 && y < N);
}

int get_goal_distance(int x, int y)
{
	bool visited[MAX_GRID][MAX_GRID] = { false };
	int distance[MAX_GRID][MAX_GRID] = { 0 };
	int customer_number = grid[y][x];
	Pos goal = customers[customer_number].goal;
	std::queue<Pos> q;
	q.push(Pos(x, y));
	visited[y][x] = true;

	while (!q.empty())
	{
		Pos p = q.front();
		q.pop();

		for (int i = 0; i < 4; i++)
		{
			int ny = p.y + directions[i][0];
			int nx = p.x + directions[i][1];

			if (!isAvailable(nx, ny) || visited[ny][nx] || grid[ny][nx] == WALL)
				continue;

			distance[ny][nx] = distance[p.y][p.x] + 1;

			if (ny == goal.y && nx == goal.x)
				return distance[ny][nx];

			q.push(Pos(nx, ny));
			visited[ny][nx] = true;
		}
	}

	return -1;
}

int get_closest_customer_distance(int x, int y, Pos *customer)
{
	bool visited[MAX_GRID][MAX_GRID] = { false };
	int distance[MAX_GRID][MAX_GRID] = { 0 };

	if (grid[y][x] != 0 && grid[y][x] != WALL)
	{
		customer->y = y;
		customer->x = x;
		return 0;
	}

	std::priority_queue<Pos> customer_candidate;
	std::queue<Pos> q;
	q.push(Pos(x, y));
	visited[y][x] = true;

	while (!q.empty())
	{
		Pos p = q.front();
		q.pop();

		for (int i = 0; i < 4; i++)
		{
			int ny = p.y + directions[i][0];
			int nx = p.x + directions[i][1];

			if (!isAvailable(nx, ny) || visited[ny][nx] || grid[ny][nx] == WALL)
				continue;

			distance[ny][nx] = distance[p.y][p.x] + 1;

			if (grid[ny][nx] != EMPTY)
				customer_candidate.push(Pos(nx, ny, distance[ny][nx]));

			q.push(Pos(nx, ny));
			visited[ny][nx] = true;
		}
	}

	if (!customer_candidate.empty())
	{
		Pos result = customer_candidate.top();
		customer->y = result.y;
		customer->x = result.x;
		return distance[result.y][result.x];
	}

	return -1;
}

int drive(int x, int y, int fuel)
{
	Pos taxi(x, y);
	int remaining_fuel = fuel;
	for (int i = 0; i < M; i++)
	{
		Pos customer(0,0);
		int distance = get_closest_customer_distance(taxi.x, taxi.y, &customer);

		CustomerGoalPair pair = customers[grid[customer.y][customer.x]];

		if (distance == -1)
			return -1;
		else if (remaining_fuel - distance < 0)
			return -1;

		remaining_fuel -= distance;

		distance = get_goal_distance(customer.x, customer.y);
		if (distance == -1)
			return -1;
		else if (remaining_fuel - distance < 0)
			return -1;

		remaining_fuel += distance;

		grid[pair.customer.y][pair.customer.x] = EMPTY;
		taxi.x = pair.goal.x;
		taxi.y = pair.goal.y;
	}

	return remaining_fuel;
}


int main()
{
	int fuel;
	scanf("%d %d %d", &N, &M, &fuel);

	for (int j = 0; j < N; j++)
		for (int i = 0; i < N; i++)
		{
			scanf("%d", &grid[j][i]);
			if (grid[j][i] == 1)
				grid[j][i] = WALL;
		}

	Pos taxi(0,0);
	scanf("%d %d", &taxi.y, &taxi.x);
	taxi.y--; // use 0-index
	taxi.x--;

	for (int i = 1; i <= M; i++)
	{
		Pos customer, goal;
		scanf("%d %d %d %d", &customer.y, &customer.x, &goal.y, &goal.x);

		// use 0-index
		--customer.y;
		--customer.x;
		--goal.y;
		--goal.x;

		grid[customer.y][customer.x] = i;

		customers.emplace(i, CustomerGoalPair(customer, goal));
	}

	int result = drive(taxi.x, taxi.y, fuel);
	printf("%d", result);

	return 0;
}