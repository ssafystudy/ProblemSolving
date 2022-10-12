#include <string>
#include <vector>
#include <sstream>
#include <map>
#include <cmath>

using namespace std;

vector<int> solution(vector<int> fees, vector<string> records) {
	vector<int> answer;

	map<int, vector<int>> inout_data;


	for (auto itr : records)
	{
		stringstream s;
		s.str(itr);

		vector<string> temp;
		string p;
		while (s >> p) temp.push_back(p);

		for (auto itr=0; itr < temp[0].size(); itr++)
		{
			if (temp[0][itr] == ':')
				temp[0][itr] = ' ';
		}

		int t;
		vector<int> time;
		s.clear();
		s.str(temp[0]);
		while (s >> t) time.push_back(t);

		int time_min = time[0] * 60 + time[1];

		inout_data[stoi(temp[1])].push_back(time_min);
	}

	for (auto car_num : inout_data)
	{
		int sum = 0;
		if (car_num.second.size() % 2 == 1)
		{
			car_num.second.push_back(23 * 60 + 59);
		}
		int inout_count = car_num.second.size() / 2;

		int duration = 0;

		for (auto times = 0; times < inout_count; times++)
		{
			int begin = car_num.second[times * 2];
			int end = car_num.second[times * 2+1];

			duration += end - begin;
		}

		if (duration <= fees[0])
		{
			sum += fees[1];
		}
		else
		{
			sum += fees[1] + (fees[3] * (int)ceil((duration - fees[0]) / (double)fees[2]));
		}

		answer.push_back(sum);
	}

	return answer;
}

int main()
{
	vector<int> fees;
	vector<string> records;

	fees.push_back(180);
	fees.push_back(5000);
	fees.push_back(10);
	fees.push_back(600);

	records.push_back("05:34 5961 IN");
	records.push_back("06:00 0000 IN");
	records.push_back("06:34 0000 OUT");
	records.push_back("07:59 5961 OUT");
	records.push_back("07:59 0148 IN");
	records.push_back("18:59 0000 IN");
	records.push_back("19:09 0148 OUT");
	records.push_back("22:59 5961 IN");
	records.push_back("23:00 5961 OUT");

	solution(fees, records);
}