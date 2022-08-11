import java.util.*;

public class 가장큰수_고진석 {
	public String solution(int[] numbers) {
        String[] nums = new String[numbers.length];
        for(int i = 0 ; i < numbers.length ; i++) {
        	nums[i] = String.valueOf(numbers[i]);
        }

        Comparator<String> myComparator = new Comparator<String>() {
        	  @Override
        	  public int compare(String s1, String s2) {
        		  int startS1 = Integer.parseInt(s1 + s2);
        		  int startS2 = Integer.parseInt(s2 + s1);
        		  
        		  return startS2 - startS1;
        	  }
        };
        
        Arrays.sort(nums, myComparator);
        StringBuilder sb = new StringBuilder();
        for(String s : nums)
        	sb.append(s);
        while(sb.length() > 1 && sb.charAt(0) == '0')
        	sb.deleteCharAt(0);
        return sb.toString();
    }
}
