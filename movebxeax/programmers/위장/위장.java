import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 0;

        Map<String, Integer> clothesMap = new HashMap<String, Integer>();

        for(int i=0;i<clothes.length;i++)
        {
            if(clothesMap.containsKey(clothes[i][1]))
            {
                int tmp = clothesMap.get(clothes[i][1]).intValue();
                clothesMap.put(clothes[i][1], ++tmp);
            }
            else
            {
                clothesMap.put(clothes[i][1], 1);
            }
        }

        //// still can't understand why it is calculated like this;;;
        answer = 1;
        for(int itr : clothesMap.values())
            answer *= (itr+1);
	
        answer--;
        ////
        
        return answer;
    }
}