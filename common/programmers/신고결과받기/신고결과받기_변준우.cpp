#include <string>
#include <vector>
#include <unordered_map>
#include <unordered_set>
#include <algorithm>

using namespace std;

vector<int> solution(vector<string> id_list, vector<string> report, int k) {
	vector<int> answer;

	unordered_map<string, vector<string>> report_map;
	unordered_map<string, unordered_set<string>> reported_data;
	for (auto itr : id_list)
	{
		vector<string> t;
		report_map.emplace(itr, t);

		unordered_set<string> s;
		reported_data[itr] = s;
	}

	for (auto itr : report)
	{
		int npos = itr.find(" ");
		string name = itr.substr(0, npos);
		string report = itr.substr(npos + 1, itr.length() - 1);

		report_map[name].push_back(report);

		reported_data[report].emplace(name);
	}

	answer.resize(id_list.size(), 0);
	for (auto reported : reported_data)
	{
		if (reported.second.size() >= k)
		{
			for (auto reportee : reported.second)
			{
				auto find_result = std::find(id_list.begin(), id_list.end(), reportee) - id_list.begin();
				answer[find_result]++;
			}
		}
	}

	return answer;
}

void main()
{
	std::vector<string> id_list;
	std::vector<string> report;

	/*id_list.push_back("muzi");
	id_list.push_back("frodo");
	id_list.push_back("apeach");
	id_list.push_back("neo");

	report.push_back("muzi frodo");
	report.push_back("apeach frodo");
	report.push_back("frodo neo");
	report.push_back("muzi neo");
	report.push_back("apeach muzi");

	solution(id_list, report, 2);*/

	id_list.push_back("con");
	id_list.push_back("ryan");

	report.push_back("ryan con");
	report.push_back("ryan con");
	report.push_back("ryan con");
	report.push_back("ryan con");
	solution(id_list, report, 3);
}
