#include <string>
#include <vector>
#include <algorithm>
#include <regex>

using namespace std;

string step2(string new_id)
{
	return std::regex_replace(new_id, std::regex("[^A-Za-z0-9-_.]"), "");
}

string step3(string new_id)
{
	while (std::regex_search(new_id, std::regex("[\.]{2,3}")))
		new_id = std::regex_replace(new_id, std::regex("[\.]{2,3}"), ".");

	return new_id;
}

string step4(string new_id)
{
	while (std::regex_search(new_id, std::regex("^[\.]+")))
		new_id = std::regex_replace(new_id, std::regex("^[\.]+"), "");

	while (std::regex_search(new_id, std::regex("[\.]+$")))
		new_id = std::regex_replace(new_id, std::regex("[\.]+$"), "");

	return new_id;
}

string solution(string new_id) {
	string answer = "";

	// 1. to lower
	std::transform(new_id.begin(), new_id.end(), new_id.begin(), ::tolower);

	// 2. remove ! @ # *
	new_id = step2(new_id);

	// 3. ... and .. -> .
	new_id = step3(new_id);

	// 4. remove . at start or end
	new_id = step4(new_id);

	// 5. input a if new_id is empty
	if (new_id.length() == 0)
		new_id = "a";

	// 6. remove over 16
	if(new_id.length() >= 16)
		new_id = new_id.substr(0, 15);

	new_id = std::regex_replace(new_id, std::regex("[\.]+$"), "");

	// 7. add last till over 3
	if (new_id.length() < 3)
	{
		char c = new_id[new_id.length() - 1];
		while (new_id.length() < 3)
			new_id += c;
	}
	
	answer = new_id;
	return answer;
}

int main()
{
	//solution("...!@BaT#*..y.a.............................................bcdefghijklm..................");
	solution("ba");
}