import java.util.ArrayList;
import java.util.HashMap;

class _1 {
    public int[] solution(String today, String[] terms, String[] privacies) {
        HashMap<String, Integer> map = new HashMap<>();
        ArrayList<Integer> answer = new ArrayList<Integer>();
        String tmp[];
        
        for(String str : terms) {
        	tmp = str.split(" ");
        	map.put(tmp[0], Integer.parseInt(tmp[1]) * 28);
        }

        tmp = today.split("\\.");
        int today_int = toDay(Integer.parseInt(tmp[0]),Integer.parseInt(tmp[1]),Integer.parseInt(tmp[2]));
        
        for(int i = 0 ; i < privacies.length ; i++) {
        	String str = privacies[i];
        	tmp = str.split(" ");
        	String[] tmp2 = tmp[0].split("\\.");
        	int day = toDay(Integer.parseInt(tmp2[0]), Integer.parseInt(tmp2[1]), Integer.parseInt(tmp2[2])); 
        	
        	int limit = map.get(tmp[1]);
        	
        	if(day + limit <= today_int)
        		answer.add(i+1);
        	
        }
        
        int[] res = new int[answer.size()];
        for(int i = 0 ; i < answer.size() ; i++)
        	res[i] = answer.get(i);
        return res;
    }
    
    public int toDay(int year, int month, int day) {
    	return year * 12 * 28 + month * 28 + day;
    }
}