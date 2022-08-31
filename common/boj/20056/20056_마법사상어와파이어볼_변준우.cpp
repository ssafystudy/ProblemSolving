#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <vector>
#include <unordered_map>

#define MAX_GRID_SIZE 50

typedef struct Pos {
	int x, y;
	Pos() : x(0), y(0) {}
	Pos(int x, int y) : x(x), y(y) {}

	bool operator==(const Pos& p) const
	{
		return this->x == p.x && this->y == p.y;
	}
} Pos;

struct hash_fn
{
	size_t operator()(const Pos& p) const
	{
		return p.x * 1000000 + p.y * 10000;
	}
};

typedef struct FireBall {
	Pos p;
	int m, s, d;

	FireBall() : p(Pos(0, 0)), m(0), s(0), d(0) {}
	FireBall(Pos p, int m, int s, int d) : p(p), m(m), s(s), d(d) {}
}FireBall;

const int directions[8][2] = { {-1, 0}, {-1, 1}, {0,1}, {1,1}, {1,0}, {1,-1}, {0, -1}, {-1,-1} };
std::vector<FireBall> fireballs;
int N, M, K;

void simulate()
{
	// 1. move s in direction d
	std::unordered_map<Pos, std::vector<FireBall>, hash_fn> map;
	for (auto itr : fireballs)
	{
		FireBall f = itr;
		f.p.y = f.p.y + (directions[f.d][0]*itr.s)%N;
		f.p.x = f.p.x + (directions[f.d][1]*itr.s)%N;

		if (f.p.x < 0)
			f.p.x = N + f.p.x;
		else if (f.p.x >= N)
			f.p.x = f.p.x - N;

		if (f.p.y < 0)
			f.p.y = N + f.p.y ;
		else if (f.p.y >= N)
			f.p.y = f.p.y - N;

		if (map.find(Pos(f.p.x, f.p.y)) == map.end())
		{
			std::vector<FireBall> fireballs;
			fireballs.push_back(f);
			map.emplace(Pos(f.p.x, f.p.y), fireballs);
		}
		else
		{
			map[Pos(f.p.x, f.p.y)].push_back(f);
		}
	}

	std::vector<FireBall> temp_list;
	// check all fireballs
	for (auto itr : map)
	{
		// if only one fireball exists in each pos
		if (itr.second.size() == 1)
		{
			temp_list.push_back(itr.second[0]);
			continue;
		}

		// combine all fireballs
		FireBall temp;
		temp.p = itr.first;
		temp.s = 0;
		temp.m = 0;

		// is all even or odd?
		bool isEven = false, isOdd = false;
		for (auto fireball : itr.second)
		{
			temp.m += fireball.m;
			temp.s += fireball.s;

			if (fireball.d % 2 == 0)
				isEven = true;
			else
				isOdd = true;
		}

		// disappear if m==0
		if (temp.m / 5 == 0)
			continue;

		// split in 4
		FireBall divided;
		divided.m = temp.m / 5; // m = sum/5
		divided.s = temp.s / itr.second.size(); // s = sum(s)/fireball number
		divided.p = temp.p;

		if (isEven ^ isOdd) // all even or all odd
		{
			for (int i = 0; i < 4; i++)
			{
				divided.d = i * 2; // all odd/even, 0246
				temp_list.push_back(divided);
			}
		}
		else
		{
			for (int i = 0; i < 4; i++)
			{
				divided.d = i*2+1; // not all even or odd, , 1357
				temp_list.push_back(divided);
			}
		}
	}

	// assign new vector
	fireballs = temp_list;
}

int main()
{
	scanf("%d %d %d", &N, &M, &K);

	for (int i = 0; i < M; i++)
	{
		FireBall fb;
		scanf("%d %d %d %d %d", &fb.p.y, &fb.p.x, &fb.m, &fb.s, &fb.d);
		
		// use 0-index
		--fb.p.y;
		--fb.p.x;

		fireballs.push_back(fb);
	}

	for (int i = 0; i < K; i++)
		simulate();

	int sum = 0;
	for (auto itr : fireballs)
		sum += itr.m;

	printf("%d", sum);
}