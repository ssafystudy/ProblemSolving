import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

class Solution {
	static class DestCity
	{
		String name;
		boolean isVisited;

		public DestCity(String name)
		{
			this.name = name;
			this.isVisited = false;
		}
	}

	static HashMap<String, ArrayList<DestCity>> ticketsMap = new HashMap<>();
	static ArrayDeque<String> finalPath = new ArrayDeque<>();
	static int totalCount; 

	public static boolean dfs(HashMap<String, ArrayList<DestCity>> ticketsMap,String prevCity)
	{
		if(!ticketsMap.containsKey(prevCity))
		{
			if(finalPath.size() == totalCount)
				return true;

			return false;
		}

		ArrayList<DestCity> tempList = ticketsMap.get(prevCity);

		for(int i=0; i<tempList.size(); i++)
		{
			if(tempList.get(i).isVisited)
				continue;

			tempList.get(i).isVisited = true;
			finalPath.addLast(tempList.get(i).name);
			if(!dfs(ticketsMap, tempList.get(i).name))
			{
				tempList.get(i).isVisited = false;
				finalPath.removeLast();
			}
		}

		if(finalPath.size() != totalCount)
			return false;

		return true;
	}

	public static String[] solution(String[][] tickets)
	{
		String[] answer = {};
		totalCount = tickets.length+1;

		for(String[] itr : tickets)
		{
			String src = itr[0];
			String dst = itr[1];

			if(ticketsMap.containsKey(src))
				ticketsMap.get(src).add(new DestCity(dst));
			else
			{
				ArrayList<DestCity> temp = new ArrayList<>();
				temp.add(new DestCity(dst));
				ticketsMap.put(src, temp);
			}
		}

		for(ArrayList<DestCity> itr : ticketsMap.values())
		{
			itr.sort((o1, o2) -> o1.name.compareTo(o2.name));
		}

		finalPath.add("ICN");

		dfs(ticketsMap, "ICN");

		answer = new String[totalCount];
		finalPath.toArray(answer);

		return answer;
	}
}