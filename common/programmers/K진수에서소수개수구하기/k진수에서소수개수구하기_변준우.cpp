#include <string>
#include <vector>
#include <algorithm>

using namespace std;

bool isPrime(long long number) {

	if (number < 2) return false;
	if (number == 2) return true;
	if (number % 2 == 0) return false;
	for (long long i = 3; (i * i) <= number; i += 2) {
		if (number % i == 0) return false;
	}
	return true;

}

string toKdigit(int n, int k)
{
	string result;

	while (n > 0)
	{
		result.append(to_string(n%k));
		n /= k;
	}
	reverse(result.begin(), result.end());
	return result;
}

int solution(int n, int k) {
	int answer = 0;

	string kdigit = toKdigit(n, k);

	auto itr = kdigit.begin();
	while (itr != kdigit.end())
	{
		// 11001111
		auto temp = itr;
		itr = find(itr, kdigit.end(), '0');

		string s = kdigit.substr(temp- kdigit.begin(), itr - temp);

		if(!s.empty() && isPrime(stoll(s)))
			answer++;

		if(itr != kdigit.end())
			itr++;
	}

	return answer;
}

int main()
{
	solution(1234110, 3);
}