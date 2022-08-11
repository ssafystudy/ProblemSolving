import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
	static class Element
	{
		String word;
		int depth;

		public Element(String word, int depth) {
			this.word = word;
			this.depth = depth;
		}
	}

	static int[] turns;
	static Queue<Element> queue = new LinkedList<>();

	public static boolean bfs(String original, int depth, String target, String[] words)
	{
		if(original.equals(target))
		{
			return true;
		}

		for(int idx = 0; idx < words.length; idx++)
		{
			int matchCount = 0;
			for(int i=0;i<original.length(); i++)
				if(words[idx].toCharArray()[i] == original.toCharArray()[i])
					matchCount++;

			if(matchCount + 1 == original.length())
			{
				if(turns[idx] > depth)
				{
					queue.offer(new Element(words[idx], depth+1));
					turns[idx] = depth;
				}
			}
		}

		while(!queue.isEmpty())
		{
			Element elem = queue.poll();
			if(bfs(elem.word, elem.depth, target, words))
				return true;
		}

		return false;
	}

	public static int solution(String begin, String target, String[] words) {
		int answer = 0;

		turns = new int[words.length];
		Arrays.fill(turns, Integer.MAX_VALUE);

		bfs(begin, 1, target, words);

		for(int i=0;i<words.length;i++)
			if(words[i].equals(target))
				answer = turns[i];

		return answer;
	}
}