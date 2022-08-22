#include <stdio.h>
#include <memory>
#include <algorithm>
#include <iostream>
#include <queue>
#include <deque>
#include <vector>

#pragma warning(disable:4996)

#define APPLE 1
#define EMPTY 0
#define BODY 2

struct POS
{
	int x;
	int y;
};
struct TURN
{
	int tick;
	char direction;
};

enum Direction
{
	kRight = 0,
	kDown = 1,
	kLeft = 2,
	kUp = 3
};

int turn[4][2] = { {1,0}, {0, 1}, {-1, 0}, {0, -1} };
unsigned int n, k, l;
int board[500][500] = { 0 };
std::deque<POS> snake;
std::deque<TURN> turn_queue;
unsigned int last_direction;

int check()
{
	int elapsed = 0;

	while (1)
	{
		++elapsed;

		POS p = snake.front();
		p.y += turn[last_direction % 4][0];
		p.x += turn[last_direction % 4][1];

		if (p.x < 0 || p.x > n-1 || p.y < 0 || p.y > n-1 || board[p.x][p.y] == BODY) // boundary and body check
			break;

		if (board[p.x][p.y] != APPLE)
		{
			POS tail = snake.back();
			board[tail.x][tail.y] = EMPTY;
			snake.pop_back();
		}

		board[p.x][p.y] = BODY;
		snake.push_front(p);

		if (!turn_queue.empty() && turn_queue.front().tick == elapsed)
		{
			if (turn_queue.front().direction == 'D')
				++last_direction;
			else
				--last_direction;

			turn_queue.pop_front();
		}
	}

	return elapsed;
}
int main()
{
	std::cin >> n;
	std::cin >> k;

	for (auto count = 0; count < k; ++count)
	{
		int i, j;
		std::cin >> i >> j;

		board[i-1][j-1] = APPLE;
	}

	std::cin >> l;

	for (auto count = 0; count < l; ++count)
	{
		int i;
		char d;

		std::cin >> i >> d;

		TURN t;
		t.tick = i;
		t.direction = d;

		turn_queue.push_back(t);
	}

	POS p;
	p.x = 0;
	p.y = 0;
	snake.push_back(p);

	board[0][0] = BODY;
	last_direction = kRight;

	printf("%d\n", check());

	return 0;
}