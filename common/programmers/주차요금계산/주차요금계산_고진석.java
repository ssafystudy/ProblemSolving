import java.util.*;

class 주차요금계산 {
    public int[] solution(int[] fees, String[] records) {
    	HashMap<String, Integer> map = new HashMap<>();
    	HashMap<String, Integer> res = new HashMap<>();
    	
    	for(String record : records) {
    		String[] tmp = record.split(" ");
    		if(tmp[2].equals("IN"))
    			map.put(tmp[1], toTime(tmp[0]));
    		else {
    			res.put(tmp[1], res.getOrDefault(tmp[1], 0) + toTime(tmp[0]) - map.get(tmp[1]));
    			map.remove(tmp[1]);
    		}
    	}
    	
    	for(String key : map.keySet()) {
    		res.put(key, res.getOrDefault(key, 0) + toTime("23:59") - map.get(key));
    	}
    	
    	int N = res.size();
        int[] answer = new int[N];
        
        Object[] keys = res.keySet().toArray();
        Arrays.sort(keys);
        
        for(int i = 0 ; i < N ; i++) {
        	int time = res.get(keys[i]);
    		answer[i] = fees[1];
        	if(time > fees[0]) {
        		time -= fees[0];
        		answer[i] += time % fees[2] == 0 ? fees[3] * time / fees[2] :  fees[3] * Math.ceil(time / fees[2]);
        	}
        }
        
        return answer;
    }
    
    public int toTime(String time) {
    	String tmp[] = time.split(":"); 
    	return Integer.parseInt(tmp[0]) * 60 + Integer.parseInt(tmp[1]);
    }
}