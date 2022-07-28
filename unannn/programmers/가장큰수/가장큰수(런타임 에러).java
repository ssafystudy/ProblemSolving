import java.util.*;

class Solution {
    
    private List<String> strs = new ArrayList<>();
        
    public static String solution(int[] numbers) {        
              
        String[] strs = new String[numbers.length];
        for(int i = 0; i < numbers.length;i++){
            strs[i] = String.valueOf(numbers[i]);
        }
        Arrays.sort(strs, new C());
         
        if(strs[0].equals("0")) return "0";
        
        StringBuilder sb = new StringBuilder();
        for(String str : strs){
            sb.append(str);
        }

        return sb.toString();
    }
    
    static class C implements Comparator<String>{

		@Override
		public int compare(String o1, String o2) {
			if(o1.length() == o2.length()) {
                return o2.compareTo(o1);
            } 
			
            int length = Math.min(o1.length(), o2.length());

            for(int i = 0;i < length ;i++){
                int sub = o2.charAt(i) - o1.charAt(i);
                if(sub != 0) return sub;
            }
            
            if(o1.length() < o2.length()){
                String temp = o1;
                o1 = o2;
                o2 = temp;
            }
            
            o1 = o1.substring(o2.length());
           
            return compare(o1, o2); 
		}
    }
}