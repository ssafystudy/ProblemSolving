import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class Solution {
	
	HashMap<String, Integer>[] combs;
	
	public String[] solution(String[] orders, int[] course) {
        
		ArrayList<String> answer = new ArrayList<String>(); 
		
		int cLen = course.length;
		int oLen = orders.length;
		
		combs = new HashMap[cLen];
		
		//course 루프 돌면서 횟수만큼 조합 짜기
		for (int i = 0; i < cLen; i++) {
			//combs[i]에 HashMap<String, Integer> 하나씩 할당
			combs[i] = new HashMap<String, Integer>();
			
			int courseNum = course[i]; //메뉴 구성 개수
			
			for (int j = 0; j < oLen; j++) {
				char[] orderArray = orders[j].toCharArray();
				Arrays.sort(orderArray);
				String sortedOrder = new String(orderArray);
		        
				
				int menuNum = sortedOrder.length();
				
				if (menuNum < courseNum) {
					continue;
				}
				
				comb(menuNum, courseNum, 0, 0, i, sortedOrder, "");
			}
		}
		
		//value기준으로 map 정렬
		for (int i = 0; i < cLen; i++) {
			List<Entry<String, Integer>> tmp = new ArrayList<>(combs[i].entrySet());
			
			int tmpSize = tmp.size();
			
			if (tmpSize != 0) {
				tmp.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
			
				Entry<String, Integer> prev = tmp.get(0);
				int prevVal = prev.getValue();
				
				if (prevVal != 1) { //최소 2명 이상의 손님에게서 주문된 구성만
					answer.add(prev.getKey());
					
					if (tmpSize > 1) {
						int j = 1;
						
						int val;
						while (j < tmpSize && prevVal == (val = tmp.get(j).getValue())) {
							answer.add(tmp.get(j).getKey());
							prevVal = val;
							j++;
						}
					}
				}
			}
		}
		
		//배열로 변환
		int ansSize = answer.size();
		String[] newAnswer = new String[ansSize];
		int idx = 0;
		
		for (int i = 0; i < ansSize; i++) {
			newAnswer[idx++] = answer.get(i);
		}
		
		//사전 순으로 정렬
		Arrays.sort(newAnswer);
		
        return newAnswer;
        
    }
	
	public void comb(int n, int r, int start, int cnt, int idx, String order, String pick) {
		if (cnt == r) {
			combs[idx].put(pick, combs[idx].getOrDefault(pick, 0) + 1);
			return;
		}
		
		for (int i = start; i < n; i++) {
			comb(n, r, i + 1, cnt + 1, idx, order, pick + order.charAt(i));
		}
	}
	
}
