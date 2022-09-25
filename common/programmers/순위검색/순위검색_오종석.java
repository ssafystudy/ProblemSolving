import java.util.*;

class Solution {
    
    List<Integer> infoParse [][][][] = new List[4][3][3][3];
    
    // cpp ->2, java -> 1, python -> 0
    int getLanguageIndex(String language){
        return (language.charAt(0) - 'a') % 15 % 8;
    }
    
    // backend -> 1, frontend -> 0
    int getJobIndex(String job){
        return (job.charAt(0) - 'a') % 5;
    }
    
    // junior -> 0, senior -> 1
    int getCareerIndex(String Career){
        return (Career.charAt(0) - 'a') % 17 % 9;
    }
    // chicken -> 0, pizza -> 1
    int getFoodIndex(String food){
        return (food.charAt(0) - 'a') % 14 % 2;
    }
    
    int getPeopleCnt(List<Integer> nums, int num){
        
        int size = nums.size();
        int low = -1, high = size;
        while(low +1 < high){
            int mid = (low + high) >>1;
            
            if(nums.get(mid) >= num) high = mid;
            else low = mid;
            
        }
        return size - high;
    }
    
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        
        for(int i = 0; i<4; i++) for(int j = 0; j<3; j++)
        for(int k = 0; k<3; k++) for(int z = 0; z<3; z++)
            infoParse[i][j][k][z] = new ArrayList<>();
        
        for(String inf: info){
            String [] parse = inf.split(" ");
            
            
            int a = getLanguageIndex(parse[0]);
            int b = getJobIndex(parse[1]);
            int c = getCareerIndex(parse[2]);
            int d = getFoodIndex(parse[3]);
            //System.out.println(""+a+b+c+d);
            
            int e = Integer.parseInt(parse[4]);
            infoParse[a][b][c][d].add(e);
            
            for(int i = 1; i<16; i++){
                int arr[] = new int[4];
                arr[0] = a; arr[1] = b; arr[2] = c; arr[3] = d;
                for(int j = 0; j<4; j++){
                    if((i & (1<<j)) != 0){
                        if(j == 0) arr[j] = 3;
                        else arr[j] = 2;
                    }
                }
                infoParse[arr[0]][arr[1]][arr[2]][arr[3]].add(e);
            }
        }
        
        for(int i = 0; i<4; i++) for(int j = 0; j<3; j++)
        for(int k = 0; k<3; k++) for(int z = 0; z<3; z++)
            infoParse[i][j][k][z].sort((i1, i2) -> i1-i2);
        
        int idx = 0;
        for(String q: query){
            String []qq = q.split(" and ");
            
            int a,b,c,d,num;
            a = !qq[0].equals("-")?getLanguageIndex(qq[0]):3;
            b = !qq[1].equals("-")?getJobIndex(qq[1]):2;
            c = !qq[2].equals("-")?getCareerIndex(qq[2]):2;
            
            String[] fn = qq[3].split(" ");
            
            d = !fn[0].equals("-")?getFoodIndex(fn[0]):2;
            num = Integer.parseInt(fn[1]);
            
            //System.out.println(""+a+b+c+d+" "+num);
            
            int cnt = getPeopleCnt(infoParse[a][b][c][d], num);
            answer[idx++] = cnt;
        }
        return answer;
    }
}