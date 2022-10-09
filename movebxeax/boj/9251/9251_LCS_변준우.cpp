#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

int main()
{
	std::string a, b;
	std::cin >> a >> b;
	int cnt = 0;

	std::vector<std::vector<int>> grid;

	grid.resize(b.length()+1);

	grid[0].resize(a.length() + 1, 0);

	for (int j = 1; j < b.length()+1; j++)
	{
		grid[j].resize(a.length() + 1, 0);
		for (int i = 0; i < a.length(); i++)
		{
			if (b[j - 1] == a[i])
				grid[j][i+1] = grid[j-1][i] + 1;
			else
				grid[j][i+1] = std::max(grid[j-1][i+1], grid[j][i]);
		}
	}

	for (int i = 0; i < a.length(); i++)
		cnt = std::max(cnt, grid[b.length()][i + 1]);

	std::cout << cnt;
}