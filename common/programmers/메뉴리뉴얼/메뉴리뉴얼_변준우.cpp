#include <string>
#include <vector>
#include <map>
#include <algorithm>

void subset(int start, std::string prev, std::vector<char>input, std::vector<std::string> &output)
{
	if (prev.length() > 0)
		output.push_back(prev);

	for (auto i = start; i < input.size(); i++)
		subset(i + 1, prev + input[i], input, output);
}

std::vector<std::string> solution(std::vector<std::string> orders, std::vector<int> course) {
	std::map<std::string, int> order_count;
	for (auto order : orders)
	{
		std::vector<char> menus;
		for (auto menu : order)
			menus.push_back(menu);

		std::sort(menus.begin(), menus.end());

		std::vector<std::string> candidates;
		std::string prev;
		subset(0, prev, menus, candidates);

		for (auto candidate : candidates)
			order_count[candidate]++;
	}

	std::map<int, std::vector<std::string>> result;

	for (auto order : order_count)
	{
		auto current_order_str = order.first;
		int current_order_length = order.first.length();
		if (result[current_order_length].empty()) // if empty, just add
		{
			if(order.second >= 2)
				result[current_order_length].push_back(current_order_str);
		}
		else if (result[current_order_length].front().length() == current_order_length) // length check
		{
			if(order_count[current_order_str] == order_count[result[current_order_length].front()]) // order count check
				result[current_order_length].push_back(current_order_str);
			else if (order_count[current_order_str] > order_count[result[current_order_length].front()])
			{
				result[current_order_length].clear();
				result[current_order_length].push_back(current_order_str);
			}
		}
	}

	std::vector<std::string> answer;
	for (auto course_cnt : course)
		answer.insert(answer.end(), result[course_cnt].begin(), result[course_cnt].end());

	std::sort(answer.begin(), answer.end());
	return answer;
}

int main()
{
	solution(std::vector<std::string>({ "ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH" }), std::vector<int>({ 2,3,4 }));
}