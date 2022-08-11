package com.study.programmers;

import java.util.*;
import java.io.*;

public class Solution_단어변환 {
	public static boolean[] visited;
	public static int answer;
	
	public static void main(String[] args) {
		String begin = "hit";
		String target = "cog";
		String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
		System.out.println(solution(begin, target, words));
	}
	
	public static int solution(String begin, String target, String[] words) {
        visited = new boolean[words.length];
        
        dfs(begin, target, words, 0);
        return answer;
    }
	
	
	public static void dfs(String begin, String target, String[] words, int depth) {
		int size = words.length;
		
		if (begin.equals(target)) {
			answer = depth;
			return;
		}
		
		for (int i = 0; i < size; i++) {
			if (visited[i]) {
				continue;
			}
			
			int beginlen = begin.length();
			int cnt = 0; //같은 스펠링갯수세기
			for (int j = 0; j < beginlen; j++) {
				if (begin.charAt(j) == words[i].charAt(j)) {
					cnt++;
				}
			}
			
			if (cnt == 2) {
				visited[i] = true;
				dfs(words[i], target, words, depth + 1);
				visited[i] = false;
			}
		}
	
	}
	
}

/* 
dfs??
1. target이 words에 잇는지 확인
2. begin 단어와 words 안에있는 단어중 한개씩다른것만 찾아서 다시 탐색
 * */
