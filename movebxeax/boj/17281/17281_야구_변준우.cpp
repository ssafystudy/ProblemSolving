#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <vector>
#include <limits>
#include <algorithm>

#define HIT 1
#define DOUBLE 2
#define TRIPLE 3
#define HOMERUN 4
#define FIRST 0
#define SECOND 1
#define THIRD 2
#define HOME 3
#define OUT 0
#define HITTER_COUNT 9

std::vector<std::vector<int>> lineup_permutation;

int N; // innings
int inning_status[50][9];
int result = std::numeric_limits<int>::min();

int simulate(std::vector<int> lineup)
{
	int last_hitter = 0;
	int score = 0;

	for (int j = 0; j < N; j++) // innigs
	{
		int out_count = 0;
		bool base_status[8] = { false }; // first, second, third, home, buffer
		for (int i = last_hitter; ; i++) // hitter #
		{
			if (i > 8)
				i = i % 9;

			if (out_count > 2)
			{
				last_hitter = i;
				break;
			}

			int current = inning_status[j][lineup.at(i)]; // j-th inning's hitter #i's record

			switch (current)
			{
			case OUT:
				out_count++;
				break;
			case HIT:
				for (int i = 7; i > 0; i--)
				{
					base_status[i] = base_status[i - 1]; // each runner goes 1 base
				}
				base_status[FIRST] = true; // hit
				break;
			case DOUBLE:
				for (int i = 7; i > 1; i--)
				{
					base_status[i] = base_status[i - 2]; // each runner goes 2 bases
				}
				base_status[FIRST] = false;
				base_status[SECOND] = true;
				break;
			case TRIPLE:
				for (int i = 7; i > 2; i--)
				{
					base_status[i] = base_status[i - 3]; // each runner goes 2 bases
				}
				base_status[FIRST] = false;
				base_status[SECOND] = false;
				base_status[THIRD] = true;
				break;
			case HOMERUN:
				for (int i = 7; i > 3; i--)
				{
					base_status[i] = base_status[i - 4]; // each runner goes 2 bases
				}
				base_status[FIRST] = false;
				base_status[SECOND] = false;
				base_status[THIRD] = false;
				base_status[HOME] = true;
				break;
			}

			for (int i = 3; i < 8; i++)
			{
				if (base_status[i]) // home ~ home+4 include buffers
				{
					score++; // increase score
					base_status[i] = false; // reset bases
				}
			}
		}
	}

	return score;
}

void permutation(int start, int flag, std::vector<int> prev)
{
	if (prev.size() == 9)
	{
		result = std::max(result, simulate(prev));
		return;
	}
	else if (prev.size() == 3) // lineup #4 must be hitter #1
	{
		prev.push_back(0);
		permutation(start, flag, prev);
		prev.pop_back();
	}
	else
	{
		for (int i = 0; i < HITTER_COUNT; i++)
		{
			if ((flag & (1 << i)) != 0)
				continue;

			prev.push_back(i);
			permutation(start, flag | (1 << i), prev);
			prev.pop_back();
		}
	}
}

int main()
{
	scanf("%d", &N);

	for (int j = 0; j < N; j++)
	{
		for (int i = 0; i < 9; i++)
		{
			scanf("%d", &inning_status[j][i]);
		}
	}

	std::vector<int> prev;
	permutation(0, 1 << 0, prev);

	printf("%d", result);
}