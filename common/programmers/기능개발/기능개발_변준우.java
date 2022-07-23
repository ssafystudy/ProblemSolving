import java.util.*;

class Solution {
	public static class Task
	{
		int progresses;
		int speed;

		public void work()
		{
			this.progresses += this.speed;
		}
	}

	public int[] solution(int[] progresses, int[] speeds) {
		int[] answer = {};

		Queue<Task> progresses_q = new LinkedList<Task>();
		for(int i=0;i<progresses.length;i++)
		{
			Task t = new Task();
			t.progresses = progresses[i];
			t.speed = speeds[i];

			progresses_q.add(t);
		}

		int answer_cnt = 0;
		int[] answers = new int[progresses.length];


		while(!progresses_q.isEmpty())
		{
			if(progresses_q.peek().progresses < 100)
			{
				progresses_q.forEach(s -> {s.work();});
			}
			else
			{
				int tmp = 0;
				while(progresses_q.peek()!= null && progresses_q.peek().progresses >= 100)
				{
					progresses_q.poll();
					tmp++;
				}
				answers[answer_cnt++] = tmp;
			}
		}

		answer = Arrays.copyOf(answers, answer_cnt);

		return answer;
	}
}