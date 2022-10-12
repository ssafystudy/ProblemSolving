import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Solution {
	
	public static int[] solution(String[] id_list, String[] report, int k) {
       
		//report를 돌면서 hashSet에 저장
		HashSet<String> set = new HashSet<String>();
		for (int i = 0, size = report.length; i < size; i++) {
			set.add(report[i]);
		}
		
		HashMap<String, Integer> reports = new HashMap<>(); //신고당한 아이디, 횟수
		HashMap<String, ArrayList<String>> user = new HashMap<>(); //유저, 유저가 신고한 ID 배열
		
		for (String tmp : set) {
			String[] s = tmp.split(" ");
			
			//신고한 ID 목록에 넣기
			if (!user.containsKey(s[0])) {
				user.put(s[0], new ArrayList<String>());
			}
			user.get(s[0]).add(s[1]);
			
			//신고당한 횟수 늘려주기
			reports.put(s[1], reports.getOrDefault(s[1], 0) + 1);
		}
		
		
		int idLen = id_list.length;
		int[] answer = new int[idLen];
		
		for (int i = 0; i < idLen; i++) {
			ArrayList<String> l = user.getOrDefault(id_list[i], null); //아예 신고 안했을 때 null
			
			if (l != null) {
				for (int j = 0, tmpLen = l.size(); j < tmpLen; j++) { //신고한 리스트만큼 돌면서
					if (reports.get(l.get(j)) >= k) { //그 신고한 아이디가 k번 이상 신고됐으면
						answer[i]++;
					}
				}
			}
		}
		
        return answer;
        
    }
	
}
