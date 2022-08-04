package com.study.programmers;

import java.util.*;

public class Solution_큰수만들기 {
	
	public static void main(String[] args) {
		
		String number = "4321";
		int k = 1;
		System.out.println(solution(number, k));
	}
	
	public static String solution(String number, int k) {
        int len = number.length();
        Stack<Integer> stack = new Stack<>();
        
        int[] num = new int[len];
        
        for (int i = 0; i < len; i++) {
        	num[i] = number.charAt(i) - '0';
        }
        
        for (int n = 0; n < len; n++) {
        	if (stack.empty()) {
        		stack.add(num[n]);
        		continue;
        	}
        	
        	if (k > 0) {
        		while (stack.peek() < num[n]) {
        			stack.pop();
        			k--;
        			if (stack.empty() || k <= 0) {
        				break;
        			}
        		}
        	}
        	stack.push(num[n]);
        }
        
        //tc 12번 틀려서 보니 4321 넣엇을 때 4321이 나와서 추가해줌
        if (stack.size() == len) {
        	stack.pop();
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < stack.size(); i++) {
        	sb.append(stack.get(i));
        }
       
        return sb.toString();
    }

}
