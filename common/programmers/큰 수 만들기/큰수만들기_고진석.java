
class 큰수만들기_고진석 {
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder(number);
        
        for(int cnt = 0 ; cnt < k ; cnt++ ) {
        	for(int i = 0, size = sb.length() - 1 ; i < size ; i++ ) {
        		if(sb.charAt(i) < sb.charAt(i+1)) {
        			sb.deleteCharAt(i);
        			break;
        		}
        		else if(i == size - 1) {
        			sb.delete(number.length() - k, sb.length());
        			cnt += k;
        			break;
        		}
        	}
        }
        
        return sb.toString();
    }
}

//처음엔 배열로 만들어서 빠질 수는 -1로 바꿔주고 처리. > 시간초과
//빼는 수가 많아지면 불필요한 반복이 많아지니까 그냥 arraylist로 만들어서 실제로 빼면서 반복 > 시간초과
//따로 int로 바꿔서 처리하지 말고 Stringbuilder에 넣어서 charAt으로 처리