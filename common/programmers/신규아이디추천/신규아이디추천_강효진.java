class Solution {
    public String solution(String new_id) {
        StringBuilder sb = new StringBuilder();
		
//		1단계 new_id의 모든 대문자를 대응되는 소문자로 치환합니다.
		new_id = new_id.toLowerCase();
		
//		2단계 new_id에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거합니다.
		char[] cs = new_id.toCharArray();
		String special = "~!@#$%^&*()=+[{]}:?,<>/"; //빼기(-), 밑줄(_), 마침표(.) 제외한 특수문자
		
		for (int i = 0, size = cs.length; i < size; i++) {
			int j = 0;
			int len = special.length();
			
			for (; j < len; j++) {
				if (cs[i] == special.charAt(j)) {
					break;
				}
			}
			
			if (j == len) {
				sb.append(cs[i]); 
			}
		}
		
		new_id = sb.toString();
		sb.setLength(0);
		
//		3단계 new_id에서 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.
		cs = new_id.toCharArray();
		char prev = cs[0];
		sb.append(prev);
		
		for (int i = 1, size = cs.length; i < size; i++) {
			if (prev == '.' && cs[i] == '.') {
				prev = cs[i];
				continue;
			}
			
			sb.append(cs[i]);
			prev = cs[i];
		}
		
		new_id = sb.toString();
		sb.setLength(0);
		
//		4단계 new_id에서 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
		if (new_id.charAt(0) == '.') {
			new_id = new_id.substring(1);
		}
		
		if (!new_id.isBlank() && new_id.charAt(new_id.length() - 1) == '.') {
			new_id = new_id.substring(0, new_id.length() - 1);
		}
		
//		5단계 new_id가 빈 문자열이라면, new_id에 "a"를 대입합니다.
		if (new_id.length() == 0) {
			new_id = "a";
		}
		
//		6단계 new_id의 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다. 
//		만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거합니다.
		if (new_id.length() >= 16) {
			new_id = new_id.substring(0, 15);
			
			if (new_id.charAt(14) == '.') {
				new_id = new_id.substring(0, 14);
			}
		}
		
		
		
//		7단계 new_id의 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.
		if (new_id.length() <= 2) {
			char last = new_id.charAt(new_id.length() - 1);
			sb.append(new_id);
			
			while (sb.length() != 3) {
				sb.append(last);
			}
			
			new_id = sb.toString();
		}
		
        return new_id;
        
    }
}
