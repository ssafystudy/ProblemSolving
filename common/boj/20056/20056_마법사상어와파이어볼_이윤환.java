import java.io.*;
import java.util.*;

public class Main {

    static class FireBall {
        int r, c, m, s, d;

        public FireBall(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }

        public FireBall(int[] temp) {
            this(temp[0] - 1, temp[1] - 1, temp[2], temp[3], temp[4]);
        }
    }

    static final int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static final int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};

    static final int[] direction1 = {1, 3, 5, 7};
    static final int[] direction2 = {0, 2, 4, 6};


    static int M;
    static int N;
    static int K;

    static List<FireBall>[][] map;
    static List<FireBall> info;
    static List<int[]> fireBallCoordList;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        info = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int[] temp = new int[5];
            for (int j = 0; j < 5; j++) {
                temp[j] = Integer.parseInt(st.nextToken());
            }
            info.add(new FireBall(temp));
        }

        map = new List[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = new ArrayList<>();
            }
        }

        fireBallCoordList = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            move();
            processDuplicate();
            fireBallCoordList.clear();
        }

        System.out.println(getMassSum());
    }

    static void move() {
        for (FireBall ball : info) {
            int nr = ball.r + dr[ball.d] * ball.s;
            int nc = ball.c + dc[ball.d] * ball.s;

            while (nr < 0) nr += N;
            while (nc < 0) nc += N;
            if (nr >= N) nr %= N;
            if (nc >= N) nc %= N;

            ball.r = nr;
            ball.c = nc;

            map[ball.r][ball.c].add(ball);
            fireBallCoordList.add(new int[]{ball.r, ball.c});
        }
    }

    static void processDuplicate() {
        for (int[] coord : fireBallCoordList) {
            int r = coord[0];
            int c = coord[1];

            int ballNumber = map[r][c].size();
            if (ballNumber <= 1) {
                map[r][c].clear();
                continue;
            }

            int massSum = 0;
            int speedSum = 0;
            int count = 0;

            for (FireBall ball : map[r][c]) {
                massSum += ball.m;
                speedSum += ball.s;
                count += ball.d % 2;
                info.remove(ball);
            }
            map[r][c].clear();

            if (massSum < 5) {
                continue;
            }

            int[] directions = direction1;

            if (count == ballNumber || count == 0) {
                directions = direction2;
            }

            for (int i = 0; i < 4; i++) {
                info.add(new FireBall(r, c, massSum / 5, speedSum / ballNumber, directions[i]));
            }
        }
    }

    static int getMassSum() {
        int sum = 0;
        for (FireBall ball : info) {
            sum += ball.m;
        }
        return sum;
    }
}