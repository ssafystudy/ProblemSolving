import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int T;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		T = Integer.parseInt(br.readLine());

		for(int t=0;t<T;t++)
		{
			char[] funcStr = br.readLine().toCharArray();
			int N = Integer.parseInt(br.readLine());

			int[] numbersList = new int[N];
			String numbers = br.readLine().replaceAll("\\[", "").replaceAll("\\]", "");
			StringTokenizer st = new StringTokenizer(numbers);
			for(int i=0;i<N;i++)
				numbersList[i] = Integer.parseInt(st.nextToken(","));

			boolean isError = false;
			boolean isSwapped = false;
			int start = 0, end=N-1;

			for(int i=0;i<funcStr.length;i++)
			{
				switch(funcStr[i])
				{
				case 'R':
					int temp = start;
					start = end;
					end = temp;
					if(isSwapped)
						isSwapped = false;
					else
						isSwapped = true;
					break;
				case 'D':
					if((isSwapped && start < end) || (!isSwapped && start > end))
					{
						bw.write("error\n");
						isError = true;
						break;
					}
					if(isSwapped)
						start--;
					else
						start++;
				}

				if(isError)
					break;
			}

			if(!isError)
			{
				bw.write("[");
				if(!isSwapped)
				{
					for(int i=start;i<=end;i++)
					{
						bw.write(String.valueOf(numbersList[i]));
						if(i != end)
							bw.write(",");
					}
				}
				else
				{
					for(int i=start;i>=end; i--)
					{
						bw.write(String.valueOf(numbersList[i]));
						if(i != end)
							bw.write(",");
					}
				}
				bw.write("]\n");
				bw.flush();
			}
		}

		bw.flush();
	}
}
