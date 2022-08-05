import java.util.LinkedList;
import java.util.Queue;

class Solution {
	public int solution(int bridge_length, int weight, int[] truck_weights) {
		int answer = 0;
		Queue<Integer> remainTrucks = new LinkedList<>();
		Queue<int[]> movingTrucks = new LinkedList<>();
		
		for(int itr : truck_weights)
		{
			remainTrucks.add(itr);
		}
		
		int trucksOnBridgeWeightSum = 0;
		int elapsedTime = 0;
		while(!remainTrucks.isEmpty() || !movingTrucks.isEmpty())
		{
			if(!movingTrucks.isEmpty())
			{
				for(int[] itr : movingTrucks)
				{
					itr[1] -= 1;
				}
				
				if(movingTrucks.peek()[1] == 0)
				{
					trucksOnBridgeWeightSum -= movingTrucks.poll()[0];
				}
			}
			
			if(!remainTrucks.isEmpty())
			{
				if(weight >= trucksOnBridgeWeightSum + remainTrucks.peek())
				{
					int temp = remainTrucks.poll().intValue();
					movingTrucks.add(new int[] {temp, bridge_length});
					trucksOnBridgeWeightSum += temp;
				}
			}
			
			elapsedTime++;
		}
		
		//System.out.println(elapsedTime);
		answer = elapsedTime;
		
		return answer;
    }
}