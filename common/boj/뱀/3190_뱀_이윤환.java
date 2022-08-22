import java.io.*;
import java.util.*;

public class Main {

    private static final int[] dr = {0, -1, 0, 1};
    private static final int[] dc = {1, 0, -1, 0};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] board = new int[N][N];

        int K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            board[r][c] = 1;
        }

        int L = Integer.parseInt(br.readLine());
        int direction = 0; //오른쪽
        Deque<int[]> snake = new ArrayDeque<>();
        snake.addFirst(new int[]{0, 0});
        board[0][0] = 2;

        int answer = 0;
        for(int i = 0; i < L; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            char C = st.nextToken().charAt(0);

            while(answer < X){
                answer++;
                int[] current = snake.getFirst();
                int[] next = new int[2];
                next[0] = current[0] + dr[direction];
                next[1] = current[1] + dc[direction];

                if(isWall(board, next[0], next[1]) || isBody(board, next[0], next[1])){
                    System.out.println(answer);
                    return;
                }

                //다음 머리의 좌표를 추가
                snake.addFirst(next);
                
                if(!isApple(board,next[0], next[1])){
                    int[] tail = snake.getLast();
                    board[tail[0]][tail[1]] = 0;
                    snake.removeLast();
                }
                
                board[next[0]][next[1]] = 2;
            }
            direction = changeDirection(direction, C);
        }

        answer++;
        int[] current = snake.getFirst();
        int[] next = new int[2];
        next[0] = current[0] + dr[direction];
        next[1] = current[1] + dc[direction];

        while(!isWall(board, next[0], next[1]) && !isBody(board, next[0], next[1])){
            snake.addFirst(next);
            if(isApple(board,next[0], next[1])){
                board[next[0]][next[1]] = 2;
            } else{
                int[] tail = snake.getLast();
                board[tail[0]][tail[1]] = 0;
                snake.removeLast();
            }
            answer++;
            current = snake.getFirst();
            next = new int[2];
            next[0] = current[0] + dr[direction];
            next[1] = current[1] + dc[direction];
        }

        System.out.println(answer);
    }

    private static int changeDirection(int d, char C){
        if(C == 'L'){
         return (d + 1) % 4;
        }else{
            return (d - 1) < 0 ? 3 : d - 1;
        }
    }

    private static boolean isApple(int[][] board, int r, int c){
        return board[r][c] == 1;
    }
    private static boolean isWall(int[][] board, int r, int c){
        return (r < 0 || r >= board.length) || (c < 0 || c >= board.length);
    }

    private static boolean isBody(int[][] board, int r, int c){
        return board[r][c] == 2;
    }
}