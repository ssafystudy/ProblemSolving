import java.io.*;
import java.util.*;

public class 뱀_고진석 {
	static int[][] map;
	static int N, K, L;
	static int dir;
	static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};
	static boolean finish = false;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		map = new int[N + 2][N + 2];
		
		Arrays.fill(map[0], -1);
		for(int i = 1 ; i <= N ; i++) {
			map[i][0] = -1;
			map[i][N + 1] = -1;
		}
		Arrays.fill(map[N + 1], -1);
		
		for(int i = 0 ; i < K ; i++) {
			st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 2;
		}
		
		
		L = Integer.parseInt(br.readLine());
		int[] cmd0 = new int[L];
		char[] cmd1 = new char[L];
		for(int i = 0 ; i < L ; i++) {
			st = new StringTokenizer(br.readLine());
			cmd0[i] = Integer.parseInt(st.nextToken());
			cmd1[i] = st.nextToken().toCharArray()[0];
		}

		Deque<Point> dq = new ArrayDeque<>();
		dq.add(new Point(1, 1));
		map[1][1] = 1;
		dir = 0;
		int sec = 0;
		for(int i = 0 ; i < L ; i++) {
			while(true) {
				sec++;
				int nx = dq.peekFirst().x + dx[dir];
				int ny = dq.peekFirst().y + dy[dir];
				if(map[nx][ny] == 1 || map[nx][ny] == -1) {
					finish = true;
					break;
				}
				else if(map[nx][ny] == 2) {
					map[nx][ny] = 1;
					dq.offerFirst(new Point(nx, ny));
				}
				else {
					map[nx][ny] = 1;
					dq.offerFirst(new Point(nx, ny));
					Point tail = dq.pollLast();
					map[tail.x][tail.y] = 0;
				}
				if(sec == cmd0[i]) {
					if(cmd1[i] == 'L')
						dir = (dir + 3) % 4;
					else
						dir = (dir + 1) % 4;
					break;
				}
			}
			if(finish)
				break;
		}
		
		while(!finish) {
			sec++;
			int nx = dq.peekFirst().x + dx[dir];
			int ny = dq.peekFirst().y + dy[dir];
			if(map[nx][ny] == 1 || map[nx][ny] == -1)
				break;
			else if(map[nx][ny] == 2) {
				map[nx][ny] = 1;
				dq.offerFirst(new Point(nx, ny));
			}
			else {
				map[nx][ny] = 1;
				dq.offerFirst(new Point(nx, ny));
				Point tail = dq.pollLast();
				map[tail.x][tail.y] = 0;
			}
		}
		
		bw.write(sec + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
	
	static class Point{
		int x;
		int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
