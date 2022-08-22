import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	static class SnakeBody
	{
		int x, y;

		public SnakeBody(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
	}
	static class TurnInfo
	{
		int X, C;

		public TurnInfo(int time, int dir)
		{
			this.X = time;
			this.C = dir;
		}
	}

	final static int BODY = 2;
	final static int APPLE = 9;
	final static int EMPTY = 0;
	final static int LEFT = 1;
	final static int RIGHT = -1;
	static int[][] directions = {{0,1}, {-1, 0}, {0, -1}, {1, 0}};//{{-1, 0}, {0,-1}, {1, 0}, {0,1}};
	static ArrayDeque<SnakeBody> snake = new ArrayDeque<SnakeBody>();
	static ArrayDeque<TurnInfo> turns;
	static int grid[][];
	static int N, K, L;

	public static boolean isApple(int x, int y)
	{
		return grid[y][x] == APPLE;
	}

	public static boolean isAvailable(int x, int y)
	{
		return (x >= 0 && x < N && y >= 0 && y < N);
	}

	public static int snake()
	{
		int time = 0;		
		int lastDirection = 0;

		while(true)
		{
			time++;
			SnakeBody current = new SnakeBody(snake.peek().x, snake.peek().y);
			current.x += directions[lastDirection%4][1];
			current.y += directions[lastDirection%4][0];

			if(!isAvailable(current.x, current.y) || grid[current.y][current.x] == BODY)
				break;

			if(!isApple(current.x, current.y))
			{
				SnakeBody temp = snake.pollLast();
				if(temp != null)
					grid[temp.y][temp.x] = EMPTY;
			}

			grid[current.y][current.x] = BODY;
			snake.addFirst(new SnakeBody(current.x, current.y));

			if(!turns.isEmpty() && time == turns.peek().X)
			{
				TurnInfo temp = turns.poll();

				if(temp.C == RIGHT)
					lastDirection += RIGHT;
				else
					lastDirection += LEFT;

				if(lastDirection < 0)
					lastDirection = 3;
			}
		}

		return time;
	}

	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());

		grid = new int[N][N];

		for(int i=0;i<K;i++)
		{
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());

			grid[y-1][x-1] = APPLE;
		}

		L = Integer.parseInt(br.readLine());
		turns = new ArrayDeque<>();
		for(int i=0;i<L;i++)
		{
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());
			char C = st.nextToken().toCharArray()[0];

			if(C == 'D')
				turns.add(new TurnInfo(X, RIGHT));
			else
				turns.add(new TurnInfo(X, LEFT));
		}

		snake.addFirst(new SnakeBody(0,0));
		System.out.println(snake());
	}
}
