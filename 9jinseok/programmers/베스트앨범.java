import java.util.*;
import java.util.Map.Entry;

public class 베스트앨범 {

	public static void main(String[] args) {
		String[] genres = {"classic", "pop", "classic", "classic", "pop"};
		int[] plays = {500, 600, 150, 800, 2500};
		
		System.out.println(Arrays.toString(solution(genres,plays)));
	}
	
	public static int[] solution(String[] genres, int[] plays) {
        int[] answer;
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        
        int N = genres.length;
        answer = new int[N];
        int ansL = 0;
        Song[] songList = new Song[N];
        
        for(int i = 0 ; i < N ; i++) {
        	if(map.containsKey(genres[i]))
        		map.replace(genres[i], map.get(genres[i]) + plays[i]);
        	else 
        		map.put(genres[i], plays[i]);
        	songList[i] = new Song(i, genres[i], plays[i]);
        }

    	Arrays.sort(songList);
    	List<Entry<String, Integer>> list_entries = new ArrayList<Entry<String, Integer>>(map.entrySet());

		Collections.sort(list_entries, new Comparator<Entry<String, Integer>>() {
			public int compare(Entry<String, Integer> obj1, Entry<String, Integer> obj2) {
				return obj2.getValue().compareTo(obj1.getValue());
			}
		});
        
		for(Entry<String, Integer> entry : list_entries) {
			String genre = entry.getKey();
			for(int i = 0 ; i < 2 ; i++) {
				for(int j = 0 ; j < N ; j++) {
					Song s = songList[j];
					if(s.genre.equals(genre) && s.id >= 0) {
						answer[ansL++] = s.id;
						songList[j].id = -1;
						break;
					} 
				}
			}
		}
		
        return Arrays.copyOf(answer, ansL);
    }
	
	static class Song implements Comparable<Song> {
		int id;
		String genre;
		int play;
		
		public Song(int id, String genre, int play) {
			this.id = id;
			this.genre = genre;
			this.play = play;
		}

		@Override
		public int compareTo(Song o) {
			return o.play - this.play;
		}
		
	}
}
