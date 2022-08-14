package com.ssafy.programmers;

public class Solution_입국심사 {

	public static void main(String[] args) {
		int n = 6;
		int[] times = {7, 10};
		System.out.println(solution(n, times));

	}
	
	 public static long solution(int n, int[] times) {
	        long answer = 0;
	        
	        long start = 1;
	        long end = Integer.MIN_VALUE;
	        for (long t : times) {
				end = (end > t) ? end : t;
			}
	        end *= n;
	        
	        while (start < end) {
	        	long total = 0; //심사한 사람의 수
	        	long mid = (start + end) / 2;
	        	
	        	for (long t : times) {
					total += mid / t; //모든 심사관이 mid분동안 심사한 사람수
				}
	        	
	        	if (total >= n) { //심사한 사람 수가 심사받아야할 사람수보다 많거나 같은 경우 
	        		end = mid; 
	        	} else {
	        		start = mid + 1; //심사한 사람 수가 심사받아야할 사람수보다 적은 경우
	        	}
	        }
	        
	        answer = start;
	        
	        return answer;
	        
	        
	    }
}
