import java.io.*;
import java.util.*;

public class Main {

	static int R, C, M;
	static Shark[][] map;
	static int kingC;

	static int[] dr = { 0, -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 0, 1, -1 };

	static List<Shark> sharks;

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new Shark[R][C];
		sharks = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			sharks.add(new Shark(r, c, s, d, z));
		}

		kingC = 0;
		int answer = 0;
		while (kingC < C) {
			Shark caught = sharks.stream().filter(o -> o.c == kingC).min((o1, o2) -> o1.r - o2.r).orElse(null);

			if (caught != null) {
				answer += caught.z;
				sharks.remove(caught);
			}

			for (Shark shark : sharks) {
				shark.r += dr[shark.d] * shark.s;
				shark.c += dc[shark.d] * shark.s;
				if (!isValid(shark.r, shark.c)) {
					warigari(shark);
				}
			}

			for (int i = 0; i < R; i++) {
				Arrays.fill(map[i], null);
			}

			List<Shark> removes = new ArrayList<>();
			for (Shark shark : sharks) {
				if (map[shark.r][shark.c] == null) {
					map[shark.r][shark.c] = shark;
				} else {
					if (map[shark.r][shark.c].z < shark.z) {
						removes.add(map[shark.r][shark.c]);
						map[shark.r][shark.c] = shark;
					} else {
						removes.add(shark);
					}
				}
			}
			sharks.removeAll(removes);

			kingC++;
		}
		System.out.println(answer);
	}

	static void warigari(Shark shark) {
		if (shark.d <= 2) {// 위 아래
			while (!isValid(shark.r, shark.c)) {
				if (shark.r >= R) {
					int sub = shark.r - (R - 1);
					shark.r = R - 1 - sub;
					shark.d = shark.d == 1 ? 2 : 1;
				} else {
					shark.r = -shark.r;
					shark.d = shark.d == 1 ? 2 : 1;
				}
			}
		} else { // 좌 우
			while (!isValid(shark.r, shark.c)) {
				if (shark.c >= C) {
					int sub = shark.c - (C - 1);
					shark.c = C - 1 - sub;
					shark.d = shark.d == 3 ? 4 : 3;
				} else {
					shark.c = -shark.c;
					shark.d = shark.d == 3 ? 4 : 3;
				}
			}
		}
	}

	static boolean isValid(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C;
	}

	static class Shark {
		int r, c, s, d, z;

		public Shark(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}
}