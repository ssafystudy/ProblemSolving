import java.util.*;

class Solution {
    String[][] tks;
    
    boolean visit[];
    
    int order[];
    
    List<String[]> ans = new ArrayList<>();
    
    void solve(int cnt){
        if(cnt == tks.length){
            //System.out.println(Arrays.toString(order));
            String[] t= new String[cnt+1];
            for(int i =0; i<cnt; i++){
                if(i == cnt-1){
                    t[i] = tks[order[i]][0];
                    t[i+1] = tks[order[i]][1];
                }else t[i] = tks[order[i]][0];
            }
            ans.add(t);
            return;
        }
        
        for(int i =0; i<tks.length; i++){
            if(cnt == 0 && !tks[i][0].equals("ICN")) continue;
            if(!visit[i]){
                if(cnt == 0 || tks[order[cnt-1]][1].equals(tks[i][0])){
                    visit[i] = true;
                    order[cnt] = i;
                    solve(cnt+1);
                    visit[i] = false;
                }
            }
        }
    }
    
    public String[] solution(String[][] tickets) {
        // 항상 ICN 시작
        tks = tickets;
        visit = new boolean[tks.length];
        
        order = new int[tks.length];
        
        solve(0);
        
        Collections.sort(ans, (a1,a2) ->{
            for(int i =0; i<a1.length; i++){
                int cmp = a1[i].compareTo(a2[i]);
                if(cmp != 0) return cmp;
            }
            return 0;
        });
        return ans.get(0);
    }
}