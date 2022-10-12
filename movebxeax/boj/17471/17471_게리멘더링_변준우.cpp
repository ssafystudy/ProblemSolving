#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <math.h>
#include <algorithm>
#include <limits>
#include <vector>
#include <queue>
#include <set>

#define MAX_CITY_COUNT 10

typedef struct City {
	int from, to;
	City() : from(0), to(0) {}
	City(int from, int to) : from(from), to(to) {}
};

std::vector<std::vector<int>> city_no_combination;
std::vector<std::vector<City>> city_adj_list;
int populations[MAX_CITY_COUNT] = { 0 };
int N;

void combination(int start, std::vector<int> prev)
{
	if (prev.size() > 0 && prev.size() < N)
	{
		city_no_combination.push_back(prev);
	}
	else if (prev.size() >= N)
		return;

	for (int i = start; i < N; i++)
	{
		prev.push_back(i);
		combination(i + 1, prev);
		prev.pop_back();
	}
}

int bfs(std::vector<int> list)
{
	std::set<int> city_set;
	for (auto itr : list)
		city_set.insert(itr);

	int visited_count = 0;
	int population_sum = 0;
	bool visited[MAX_CITY_COUNT] = { false };

	std::queue<int> q;
	q.push(list.front());
	visited[list.front()] = true;
	visited_count++;

	while (!q.empty())
	{
		int from = q.front();
		q.pop();
		population_sum += populations[from];

		for (auto itr : city_adj_list[from])
		{
			if (!visited[itr.to] && (city_set.find(itr.to) != city_set.end())) // next node has not visited and in city set
			{
				visited[itr.to] = true;
				q.push(itr.to);
				visited_count++;
			}
		}
	}

	if (visited_count == list.size()) // can traverse all nodes in list
		return population_sum;
	else
		return -1;


}

int simulate()
{
	int result = std::numeric_limits<int>::max();
	for (auto picked_cities : city_no_combination)
	{
		bool picked[MAX_CITY_COUNT] = { false };
		for (auto itr : picked_cities)
		{
			picked[itr] = true;
		}

		std::vector<int> comb_a, comb_b;
		comb_a = picked_cities;
		for (auto i = 0; i < N; i++)
		{
			if (!picked[i])
				comb_b.push_back(i);
		}

		bool visited[MAX_CITY_COUNT] = { false };

		int comb_a_population = bfs(comb_a);
		if (comb_a_population > 0)
		{
			int comb_b_population = bfs(comb_b);
			if (comb_b_population > 0)
				result = std::min(result, abs(comb_a_population - comb_b_population));
		}
	}

	if (result == std::numeric_limits<int>::max())
		return -1;

	return result;
}

int main()
{
	scanf("%d", &N);

	for (int i = 0; i < N; i++)
		scanf("%d", &populations[i]); // each city's population

	city_adj_list.resize(N);

	for (int j = 0; j < N; j++)
	{
		int close_count;
		scanf("%d", &close_count);

		for (int i = 0; i < close_count; i++)
		{
			int city_num;
			scanf("%d", &city_num);
			--city_num; // use 0-index

			city_adj_list[j].push_back(City(j, city_num));
			city_adj_list[city_num].push_back(City(city_num, j));
		}
	}

	std::vector<int> prev;
	combination(0, prev);

	printf("%d", simulate());
}