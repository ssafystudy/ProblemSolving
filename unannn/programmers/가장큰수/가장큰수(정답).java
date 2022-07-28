import java.util.*;

class Solution {
    
    private List<String> strs = new ArrayList<>();
        
    public static String solution(int[] numbers) {        
              
        String[] strs = new String[numbers.length];
        for(int i = 0; i < numbers.length;i++){
            strs[i] = String.valueOf(numbers[i]);
        }
        
        Arrays.sort(strs, (o1, o2)->{
            String a = o1 + o2;
            String b = o2 + o1;
            return b.compareTo(a);
        });
                
        if(strs[0].equals("0")) return "0";
        
        StringBuilder sb = new StringBuilder();
        for(String str : strs){
            sb.append(str);
        }
        
        return  sb.toString();
    }
}