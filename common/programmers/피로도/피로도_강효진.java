public class Solution {
	
	public static int solution(int k, int[][] dungeons) {

		int n = dungeons.length;
		boolean[] isSelected = new boolean[n];
		
		return permutation(0, k, n, dungeons, isSelected);
	}
	
	public static int permutation(int cnt, int k, int n, int[][] dungeons, boolean[] isSelected) {
		int max = 0; //최대 던전 수
		
		if (cnt == n) {
			return 0;
		}
		
		for (int i = 0; i < n; i++) {
			if (isSelected[i]) {
				continue;
			}
			
			if (k < dungeons[i][0]) {
				continue;
			}
			
			isSelected[i] = true;
			
			int dungeonNum = permutation(cnt + 1, k - dungeons[i][1], n, dungeons, isSelected) + 1;
			
			if (max < dungeonNum) {
				max = dungeonNum;
			}
			
			isSelected[i] = false;
		}
		
		return max;
	}

 }
