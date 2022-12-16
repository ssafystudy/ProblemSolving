import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//초기화
		int N = Integer.parseInt(br.readLine()); //시험장의 개수
		
		int[] peoples = new int[N]; //응시자 수
		
		String[] line = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			peoples[i] = Integer.parseInt(line[i]);
		}
		
		line = br.readLine().split(" ");
		int B = Integer.parseInt(line[0]); //총감독관이 한 시험장에서 감시할 수 있는 응시자의 수
		int C = Integer.parseInt(line[1]); //부감독관이 한 시험장에서 감시할 수 있는 응시자의 수

		
		//알고리즘
		long result = N;//총감독관
		for (int i = 0; i < N; i++) {
			int people = peoples[i];
			
			people = people - B;
			
			if (people > 0) {
				if (people % C == 0) { //부감독관
					result += people / C;
				} else {
					result += people / C + 1;
				}
			}
		}
		
		
		//출력
		bw.write(result + "\n");
		bw.flush();
		bw.close();
		br.close();
		
	}

}
