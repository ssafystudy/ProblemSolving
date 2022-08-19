#include <iostream>

using namespace std;

int n,k,l;

const int dy[] = { 0,1,0,-1 }, dx[] = { 1,0,-1,0 };

bool apple[100][100] = {false,};

struct loc { int y, x; };
struct cmd { int time; char ccmd;};

loc snake[101000];
cmd cmdArr[100];

int head = 0, tail = 0;
int dir = 0;
int cmdIdx = 0;

bool check(int y, int x) {
	if (y < 0 || y >= n || x < 0 || x >= n) return false;

	// 일단 머리를 늘려야 됨 개열받;;
	for (int i = tail; i <head; i++) {
		if (snake[i].y == y && snake[i].x == x) return false;
	}

	if (!apple[y][x]) ++tail;
	else apple[y][x] = false;
	snake[head] = { y,x };
	
	return true;
}

void Print(int time) {
	char map[100][100]; string dc[] = { "->","v","<-","^" };
	cout << time << " "<<dc[dir]<<"\n";
	for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) {
		if (apple[i][j]) map[i][j] = 'O';
		else map[i][j] = '.';
	}
	for (int i = tail; i <= head; i++) map[snake[i].y][snake[i].x] = 's';
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) cout << map[i][j] << " ";
		cout << "\n";
	}
}

int main() {
	ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	cin >> n >> k;
	while (k--) {
		int y, x; cin >> y >> x;
		apple[--y][--x] = true;
	}
	cin >> l;
	for (int i = 0; i < l; i++) cin >> cmdArr[i].time >> cmdArr[i].ccmd;

	snake[head] = { 0,0 };

	int time = 0;
	while (true) {
		time++;
		loc headLoc = snake[head++];
		int hY = headLoc.y, hX = headLoc.x;
		hY += dy[dir]; hX += dx[dir];
		if (!check(hY, hX)) { cout << time; return 0;}

		cmd tcmd = cmdArr[cmdIdx];
		if (tcmd.time == time) {
			char tc = tcmd.ccmd;
			tc == 'D' ? dir = (dir + 1) % 4 : dir = (dir + 3) % 4;
			cmdIdx++;
		}
		//Print(time);
		//cout << "\n";
	}
}