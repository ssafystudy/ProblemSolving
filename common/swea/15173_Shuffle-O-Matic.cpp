#include <iostream>
#include <queue>
#include <cstring>
using namespace std;

int n;

int splitCards[36];

bool check(const vector<int>& tempCards) {
	bool inc = true, dec = true;
	int tNum = tempCards[0];
	for (int i = 1; i < n; i++) {
		if (tNum < tempCards[i]) dec = false;
		else if (tNum > tempCards[i]) inc = false;
		tNum = tempCards[i];
	}
	return inc || dec;
}

void replace(vector<int>& tempCards) {
	int idx = 0;
	for (int i = 0; i < n * 3; i++) {
		if (splitCards[i] != -1) tempCards[idx++] = splitCards[i];
		if (idx == n) break;
	}
}

vector<int> shuffle(int x ,vector<int>& tempCards) {
	int start = n;
	int rStart = start + n - 1 - 2 * x;
	int shuffleCnt = 0;

	int half = n / 2;

	for (int i = 0; i < n * 3; i++) splitCards[i] = -1;
	int idx = 0;
	for (int j = 0; j < half; j++) splitCards[start + j*2] = tempCards[idx++];
	for (int j = 0; j < half; j++) splitCards[rStart + j * 2] = tempCards[idx++];
	replace(tempCards);
	return tempCards;
}

struct dpTree {
	dpTree* next[13];

	dpTree() {
		memset(next, 0, sizeof(next));
	}
	~dpTree() {
		for (int i = 0; i < 13; i++) if (next[i]) delete next[i];
	}

	// 존재 안하면 false
	bool check(int depth, const vector<int>& cards) {
		if (depth == n) return true;
		if (!next[cards[depth]]) return false;
		return next[cards[depth]]->check(depth + 1, cards);
	}

	void insert(int depth, const vector<int>& cards) {
		if (depth == n) return;
		if (!next[cards[depth]]) next[cards[depth]] = new dpTree();
		next[cards[depth]]->insert(depth + 1, cards);
	}
};

int main(void) {
	ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	int T; cin >> T;
	for (int tc = 1; tc <= T; tc++) {
		cin >> n;
		vector<int> cards(n);
		for (int i = 0; i < n; i++) cin >> cards[i];
		queue<vector<int>> q; q.push(cards);
		dpTree dp;
		dp.insert(0, cards);
		int shuffleCnt = 0;
		bool find = false;
		while (true) {
			int qSize = q.size();
			while (qSize--) {
				vector<int> tCards = q.front(); q.pop();
				if (check(tCards)) {
					find = true;
					break;
				}
				if(shuffleCnt < 5){
					for (int x = 1; x < n; x++) {
						vector<int> tempCards = tCards;
						shuffle(x, tempCards);
						if (dp.check(0, tempCards)) continue;
						dp.insert(0, tempCards);
						q.push(tempCards);
					}
				}
			}
			if (find) break;
			shuffleCnt++;
			if (shuffleCnt > 5) break;
		}
		cout << "#" << tc << " " << (shuffleCnt > 5?-1: shuffleCnt) << "\n";
	}
}