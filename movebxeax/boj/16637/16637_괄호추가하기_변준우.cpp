#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <algorithm>
#include <vector>
#include <list>
#include <string>
#include <limits>

#define C_PLUS '+'
#define C_MINUS '-'
#define C_MULTIPLY '*'

#define PLUS 1
#define MINUS 2
#define MULTIPLY 4
#define OPEN_PARENTHESIS 444
#define CLOSE_PARENTHESIS 999

int N;
std::vector<std::vector<int>> parenthesis_combination;

void create_combination(int start, int max, std::vector<int> prev)
{
	parenthesis_combination.push_back(prev);

	for (int i = start; i < max - 1; i++)
	{
		prev.push_back(i);

		create_combination(i + 2, max, prev);

		prev.pop_back();
	}
}

int operate(int a, int b, int op)
{
	switch (op)
	{
	case PLUS:
		return a + b;
	case MINUS:
		return a - b;
	case MULTIPLY:
		return a * b;
	}
}

int calculate(std::vector<int> numbers, std::vector<int> operators, std::vector<int> parenthesis)
{
	int parenthesis_idx = 0;
	int operator_idx = 0;
	int result = 0;
	for (int i = 0; i < numbers.size();)
	{
		if (parenthesis.size() > parenthesis_idx && parenthesis[parenthesis_idx] == i)
		{
			if (operator_idx == 0)
			{
				if (i == 1)
				{
					int parenthesis_result = operate(numbers[i], numbers[i + 1], operators[operator_idx + 1]);
					result = operate(result, parenthesis_result, operators[operator_idx]);
					operator_idx += 2;
				}
				else
				{
					int parenthesis_result = operate(numbers[i], numbers[i + 1], operators[operator_idx++]);
					result += parenthesis_result;
				}
				i += 2;
			}
			else
			{
				int parenthesis_result = operate(numbers[i], numbers[i + 1], operators[operator_idx + 1]);
				result = operate(result, parenthesis_result, operators[operator_idx]);
				operator_idx += 2;
				i += 2;
			}

			++parenthesis_idx;
		}
		else
		{
			if (i == 0)
				result = numbers[0];
			else
				result = operate(result, numbers[i], operators[operator_idx++]);
			i++;
		}
	}

	return result;
}

int main()
{
	scanf("%d", &N);

	std::vector<int> numbers;
	std::vector<int> operators;

	int x;
	char op;
	for (int i = 0; i < N / 2; i++)
	{
		scanf("%d%c", &x, &op);

		numbers.push_back(x);
		switch (op)
		{
		case C_PLUS:
			operators.push_back(PLUS);
			break;
		case C_MINUS:
			operators.push_back(MINUS);
			break;
		case C_MULTIPLY:
			operators.push_back(MULTIPLY);
			break;
		}
	}
	scanf("%d", &x);
	numbers.push_back(x);

	std::vector<int> prev;
	create_combination(0, numbers.size(), prev);

	int max_result = std::numeric_limits<int>::min();
	for (auto itr : parenthesis_combination)
	{
		max_result = std::max(max_result, calculate(numbers, operators, itr));
	}

	printf("%d", max_result);
}