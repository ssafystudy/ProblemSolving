import java.util.*;

class 신고결과받기 {
    public int[] solution(String[] id_list, String[] report, int k) {
    	HashMap<String, Integer> idx = new HashMap<>();
    	HashMap<String, HashSet<String>> map = new HashMap<>();
    	HashSet<String> set = new HashSet<>(Arrays.asList(report));
    	
    	int N = id_list.length;
    	for(int i = 0 ; i < N ; i++)
    		idx.put(id_list[i], i);
    	
    	for(String id : id_list)
    		map.put(id, new HashSet<>());
    	
    	int[] cnt = new int[N];
    	for(String rep : set) {
    		String[] tmp = rep.split(" ");
    		map.get(tmp[0]).add(tmp[1]);
    		cnt[idx.get(tmp[1])]++;
    	}
    	
    	int[] answer = new int[N];
    	
    	for(int i = 0 ; i < N ; i++) {
    		if(cnt[i] >= k) {
    			for(int j = 0 ; j < N ; j++) {
    				if(map.get(id_list[j]).contains(id_list[i]))
    					answer[j]++;
    			}
    		}
    	}
        return answer;
    }

}