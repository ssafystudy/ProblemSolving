import java.util.*;
import java.util.Map.Entry;

class 메뉴리뉴얼 {
	HashMap<String, Integer> map;

	public ArrayList<String> solution(String[] orders, int[] course) {
		ArrayList<String> answer = new ArrayList<>();

		for(int i = 0 ; i < orders.length ; i++) {
			char[] tmp = orders[i].toCharArray();
			Arrays.sort(tmp);
			orders[i] = String.valueOf(tmp);
		}

		for(int i = 0 ; i < course.length ; i++) {
			map = new HashMap<>();
			
			int max = Integer.MIN_VALUE;
			for(int j = 0 ; j < orders.length ; j++) {
				StringBuilder sb = new StringBuilder(); 
				if(course[i] <= orders[j].length())
					comb(orders[j], course[i], 0, 0, sb);                               
			}
			
			for(Entry<String, Integer> e : map.entrySet())
				max = Math.max(e.getValue(), max);
			
			if(max < 2)
				continue;
			
			for(Entry<String, Integer> e : map.entrySet())
				if(e.getValue() == max)
					answer.add(e.getKey());
		}

		Collections.sort(answer);
		return answer;
	}

	public void comb(String str, int r, int start, int depth, StringBuilder sb) {
		if(depth == r) {
			map.put(sb.toString(), map.getOrDefault(sb.toString(), 0) + 1);
			return;
		}

		for(int i = start ; i < str.length() ; i++) {
			sb.append(str.charAt(i));
			comb(str, r, i + 1, depth + 1, sb);
			sb.delete(depth, depth + 1);
		}
	}
}