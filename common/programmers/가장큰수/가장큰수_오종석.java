import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        
        String[] strr = new String[numbers.length];
        for(int i =0; i<numbers.length; i++){
            strr[i] = Integer.toString(numbers[i]); // 이게 더 빠름
            //strr[i] = numbers[i]+"";
        }
        
        Arrays.sort(strr, new Comparator<String>(){
            @Override
            public int compare(String o1, String o2) {
                // if(o1 + o2 < o2 + o1) return 1;
                // return -1;
                return (o2+o1).compareTo(o1 + o2);
            }
        });
        
        
        if(strr[0].equals("0")) return "0";
        StringBuilder sb = new StringBuilder();
        for(String str: strr)
            sb.append(str);
        
        return sb.toString();
    }
}