import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

class Solution {
	static List<DungeonsData> dungeonsList = new ArrayList<>();
	static HashSet<List<DungeonsData>> g_permutationResult = new HashSet<>();

	static class DungeonsData{
		int minimum;
		int comsumption;

		public DungeonsData(int minimum, int comsumption) {
			this.minimum = minimum;
			this.comsumption = comsumption;
		}
	}

	public static void permutation(List<DungeonsData> dungeonsList, List<DungeonsData> permutationResult)
	{
		int len = dungeonsList.size();
		for(int i=0; i<len;i++)
		{
			DungeonsData temp = dungeonsList.get(i);
			dungeonsList.remove(temp);

			List<DungeonsData> tempList = new ArrayList<>();
			tempList.addAll(permutationResult);
			tempList.add(temp);
			if(!permutationResult.isEmpty())
				g_permutationResult.add(permutationResult);

			permutation(dungeonsList, tempList);
			dungeonsList.add(i,temp);
		}

		if(!permutationResult.isEmpty())
			g_permutationResult.add(permutationResult);
	}

	public int solution(int k, int[][] dungeons) {
		int answer = -1;

		for(int i=0,len = dungeons.length; i<len; i++)
		{
			dungeonsList.add(new DungeonsData(dungeons[i][0], dungeons[i][1]));
		}

		permutation(dungeonsList, new ArrayList<>());

		int maxDungeonsCnt = -1;		
		for(List<DungeonsData> itr : g_permutationResult)
		{
			int tempK = k;
			int maxDungeons = 0;
			for(DungeonsData itr2 : itr)
			{
				if(itr2.minimum <= tempK)
				{
					tempK -= itr2.comsumption;
					maxDungeons++;
					continue;
				}

				break;
			}

			if(maxDungeons > maxDungeonsCnt)
				maxDungeonsCnt = maxDungeons;
		}

		answer = maxDungeonsCnt;
		return answer;
	}
}