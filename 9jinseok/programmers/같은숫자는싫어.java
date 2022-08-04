import java.util.*;

public class 같은숫자는싫어 {
    public int[] solution(int[] arr) {
    	List<Integer> list = new ArrayList<>();
    	
    	for(int i = 0 ; i < arr.length ; i++)
    		if(i != arr.length - 1 && arr[i] != arr[i+1])
    			list.add(arr[i]);
    	list.add(arr[arr.length - 1]);
    	
        int[] answer = new int[list.size()];
        for(int i = 0 ; i < answer.length ; i++)
        	answer[i] = list.get(i);
        
        return answer;
    }
}

//public class Solution {
//	public int[] solution(int[] arr) {
//		Stack<Integer> st = new Stack<>();
//		st.push(arr[0]);
//		for(int i = 1 ; i < arr.length ; i++) {
//			if(st.peek() == arr[i])
//				continue;
//			st.push(arr[i]);
//		}
//		
//		int[] res = new int[st.size()];
//		for(int i = 0 ; i < res.length ; i++)
//			res[i] = st.get(i);
//		
//		return res;
//	}
//}