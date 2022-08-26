#include <iostream>
#include <cstring>
#include <algorithm>
#include <vector>

using namespace std;

int loc[3], peopleNum[3];

int n;

int _min;

string order[] = { "012","021","102","120","201","210"};

void Print(vector<int>& f) {
	for (int a : f) cout << a << " ";
	cout << "\n";
}

void solve(int idx, int ord, vector<int> fishing) {
	if (idx == 3) {
		//Print(fishing);
		int sum = 0;
		for (int i = 0; i < n; i++) {
			if (fishing[i] != -1) {
				int gate = fishing[i];
				int dist = abs(loc[gate] - i) + 1;
				sum += dist;
			}
		}
		_min = min(_min, sum);
		return;
	}
	int gate = order[ord][idx] - '0';
	
	int gateLoc = loc[gate];
	int personCnt = peopleNum[gate];
	
	while (personCnt-- > 1) {
		int leftDist = 0, rightDist = 0;

		while (gateLoc - leftDist >= 0 && fishing[gateLoc - leftDist] != -1) leftDist++;
		if (gateLoc - leftDist < 0) leftDist = 1e9;
		
		while (gateLoc + rightDist < n && fishing[gateLoc + rightDist] != -1) rightDist++;
		if (gateLoc + rightDist >= n) rightDist = 1e9;

		if (rightDist < leftDist) fishing[gateLoc + rightDist] = gate;
		else fishing[gateLoc - leftDist] = gate;
	}

	int leftDist = 0, rightDist = 0;

	while (gateLoc - leftDist>= 0 && fishing[gateLoc - leftDist] != -1) leftDist++;
	if (gateLoc - leftDist < 0) leftDist = 1e9;

	while (gateLoc + rightDist < n && fishing[gateLoc + rightDist] != -1) rightDist++;
	if (gateLoc + rightDist >= n) rightDist = 1e9;

	if (leftDist == rightDist) {
		fishing[gateLoc - leftDist] = gate;
		solve(idx + 1, ord, fishing);
		fishing[gateLoc - leftDist] = -1;
		fishing[gateLoc + rightDist] = gate;
		solve(idx + 1, ord, fishing);
	}
	else {
		if (leftDist < rightDist) fishing[gateLoc - leftDist] = gate;
		else fishing[gateLoc + rightDist] = gate;
		solve(idx + 1, ord, fishing);
	}
}



int main(void) {
	ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	int T; cin >> T;
	for (int tc = 1; tc <= T; tc++) {
		_min = 1e9;
		cin >> n;
		for (int i = 0; i < 3; i++) {
			cin >> loc[i] >> peopleNum[i];
			loc[i]--;
		}

		for (int i = 0; i < 6; i++) {
			solve(0, i, vector<int>(n,-1));
		}
		cout << "#" << tc << " " << _min << "\n";
	}
}