#include <iostream>
#include <algorithm>

using namespace std;

int n,k;


int plug[100], future[100], unuse;

int main() {
	ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	cin >> n>>k;

	for (int i = 0; i < k; i++) cin >> future[i];

	int ans = 0;
	for (int i = 0; i < k; i++) {
		
		bool pluged = false;
		for (int j = 0; j < unuse; j++)
			if (plug[j] == future[i]) {
				pluged = true; break;
			}
		
		if (pluged) continue;
		
		if (unuse < n) {
			plug[unuse] = future[i];
			unuse++;  continue;
		}

		int idx = -1, len = -1;
		for (int j = 0; j < unuse; j++) {
			int tidx = i + 1;
			while (tidx < k && plug[j] != future[tidx]) tidx++;
			if (len < tidx) {
				len = tidx; idx = j;
			}
		}

		plug[idx] = future[i]; ans++;
	}
	cout << ans;
}