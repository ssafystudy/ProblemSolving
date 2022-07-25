import java.util.*;

class Solution {
	public static class Task
	{
		int priority;
		int uniqueTaskCode;
		
		/*public String toString()
		{
			return String.format("[%d %d]", this.priority, this.uniqueTaskCode);
		}*/
	}
	public int solution(int[] priorities, int location) {
		int answer = 0;
        
		int uniqueTaskCodeOfDesignatedTask = 0;
		
		Queue<Task> tasks = new LinkedList<Task>();
		
		for(int i=0;i<priorities.length;i++)
		{
			Task t = new Task();
			t.priority = priorities[i];
			t.uniqueTaskCode = i;
			tasks.add(t);
			
			if(i == location)
			{
				uniqueTaskCodeOfDesignatedTask = i; 
			}
		}
		
		int order = 0;
		
		while(!tasks.isEmpty())
		{
			Task t = tasks.poll();
			final boolean isHigherPriorityExists[] = {false};
			tasks.forEach(s -> {
				if(s.priority > t.priority)
				{
					isHigherPriorityExists[0] = true;
					return;
				}
			});
			
			if(isHigherPriorityExists[0])
			{
				tasks.add(t);
			}
			else
			{
				order++;
				if(t.uniqueTaskCode == uniqueTaskCodeOfDesignatedTask)
				{
					break;
				}
			}
		}
        
		answer = order;
		return answer;
	}
}