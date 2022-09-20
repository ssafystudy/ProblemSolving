import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Solution {

	public static int[] solution(int[] fees, String[] records) {
		
        HashMap<String, String> map = new HashMap<>(); //차량 번호, 입차 시각
        TreeMap<String, Integer> time = new TreeMap<String, Integer>(); //차량 번호, 누적 주차 시간
        
        for (int i = 0, size = records.length; i < size; i++) {
        	String[] s = records[i].split(" ");
        	
        	if (s[2].length() == 2) { //입차
        		map.put(s[1], s[0]);
        	} else { //출차
        		String[] out = s[0].split(":");
        		String[] in = map.get(s[1]).split(":");
        		
        		int outTime = Integer.parseInt(out[0]) * 60 + Integer.parseInt(out[1]);
        		int inTime = Integer.parseInt(in[0]) * 60 + Integer.parseInt(in[1]);
        		
        		time.put(s[1], time.getOrDefault(s[1], 0) + outTime - inTime);
        		map.remove(s[1]);
        	}
        }
        
        //마지막(23:59)까지 출차 안한 차 처리
        for (String key : map.keySet()) {
    		String[] in = map.get(key).split(":");
    		
    		int outTime = 23 * 60 + 59;
    		int inTime = Integer.parseInt(in[0]) * 60 + Integer.parseInt(in[1]);
    		
    		time.put(key, time.getOrDefault(key, 0) + outTime - inTime);
        }

        //주차 요금 계산
        int[] result = new int[time.size()];
        int idx = 0;
        
        for (Entry<String, Integer> entry : time.entrySet()) {
        	int totalTime = entry.getValue();
        	
        	if (totalTime > fees[0]) {
        		result[idx] = fees[1] + (int)(Math.ceil((totalTime - fees[0]) / (double)fees[2])) * fees[3];
        	} else {
        		result[idx] = fees[1];
        	}
        	idx++;
        }
        
        return result;
     
    }
	
}
