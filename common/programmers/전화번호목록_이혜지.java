package common.programmers;

//프로그래머스 해시 lv2 전화번호목록
import java.util.HashMap;
class 전화번호목록_이혜지 {

    public static void main(String[] args) {
        전화번호목록_이혜지 sol = new 전화번호목록_이혜지();
        String[] phoneBook = {"119", "97674223", "1195524421"};
        System.out.println((sol.solution(phoneBook)));
        
    }

    public boolean solution(String[] phoneBook) {
        
        boolean answer = true;

        //해시맵으로 처리
        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();

        for (int i = 0; i < phoneBook.length; i++) {
            hashMap.put(phoneBook[i], i);
        }

        for (int i = 0; i <phoneBook.length; i++) {
            String phoneNum = "";
            for (int j = 0; j < phoneBook[i].length(); j++) { 
                //전화번호 슬라이싱
                phoneNum += Character.toString(phoneBook[i].charAt(j));
                //해시맵 번호 비교 
                if (hashMap.get(phoneNum) != null && !phoneNum.equals(phoneBook[i]))
                answer = false;
            }
        }
        
        return answer;
    }
}