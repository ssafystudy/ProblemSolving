import java.util.*;
import java.util.Map.Entry;

class Solution {
	
	class Pair implements Comparable<Pair>{
		
		Pair(int play, int index){
			this.play = play;
			this.index = index;
		}
		int play, index;
		@Override
		public int compareTo(Pair p) {
			return this.play <= p.play? 1: -1;
		}
	}
	
    public int[] solution(String[] genres, int[] plays) {
        ArrayList<Integer> answer = new ArrayList();
        Map<String, Integer> genreCnt = new HashMap<>();
        Map<String, PriorityQueue<Pair>> genreArr = new HashMap<>();
        
        for(int i =0; i<genres.length; i++){
            if(!genreCnt.containsKey(genres[i])){
                genreCnt.put(genres[i], plays[i]);
                PriorityQueue<Pair> q = new PriorityQueue<>();
                q.add(new Pair(plays[i],i));
                genreArr.put(genres[i], q);
            }else{
                Integer tCnt = genreCnt.get(genres[i]) + plays[i];
                genreCnt.put(genres[i], tCnt);
                genreArr.get(genres[i]).add(new Pair(plays[i],i));
            }
        }
        List<Entry<String, Integer>> list = new ArrayList<>(genreCnt.entrySet());
        Collections.sort(list, (e1, e2) ->
        	(e1.getValue() > e2.getValue())? -1:
                         ((e1.getValue() == e2.getValue())? 0:1)
        );
        //return (x > y) ? -1 : ((x == y) ? 0 : 1);
        
        for(Entry e: list) {
        	String k = (String)e.getKey();
        	PriorityQueue<Pair> q = genreArr.get(k);
            int cnt = 0;
            while(!q.isEmpty() && ++cnt <= 2){
                answer.add(q.poll().index);
            }
        }
        int[] aanswer = new int[answer.size()];
        for(int i =0; i<aanswer.length; i++) aanswer[i] = answer.get(i);
        return aanswer;
    }
}