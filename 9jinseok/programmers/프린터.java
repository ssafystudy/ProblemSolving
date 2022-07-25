import java.util.*;

public class 프린터 {
	
	public static void main(String[] args) {
		int[] priorities = {1, 1, 9, 1, 1, 1};
		int location = 0;
		
		System.out.println(solution(priorities, location));
	}
	
    static int[] nums = new int[9];
    
	public static int solution(int[] priorities, int location) {
        int answer = 0;
        
        Queue<Pair> q = new LinkedList<>();
        for(int i = 0 ; i < priorities.length ; i++){
            nums[priorities[i]-1]++;
            if( i == location )
                q.offer(new Pair(priorities[i],true));
            else
                q.offer(new Pair(priorities[i],false));
        }
        
        while( !q.isEmpty() ){
            Pair p = q.poll();
            if(isMax(p.pr)){
                answer++;
                nums[p.pr - 1]--;
                if(p.chk)
                    return answer;
            }
            else
                q.offer(p);
        }
        
        return answer;
    }
    
    static boolean isMax(int pr){
        for(int i = 8 ; i > pr - 1 ; i--)
            if(nums[i] > 0)
                return false;
        return true;
    }
	
	static class Pair{
	    int pr;
	    boolean chk;
	    
	    public Pair(int pr, boolean chk){
	        this.pr = pr;
	        this.chk = chk;
	    }
	}
}
