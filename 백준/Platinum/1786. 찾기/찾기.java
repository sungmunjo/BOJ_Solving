import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int[] jindex;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Queue<Integer> iq = new LinkedList<>();
		String T = br.readLine();
		String P = br.readLine();
		jindex = new int[P.length()];
		jindex[0] = 0;
		for(int i=1, j=0; i<P.length(); i++){// i:접미사 포인터(i=1부터 시작: 우리는 부분일치테이블를 만드는게 목적이므로 첫글자 틀리면 0위치로 가야하므로.), j:접두사 포인터
	        while(j > 0 && P.charAt(i) != P.charAt(j)) j = jindex[j-1]; 
	        
	        if(P.charAt(i) == P.charAt(j)) jindex[i] = ++j;
	        else jindex[i] = 0;
	    }
//		for (int i = 1; i < jindex.length; i++) {
//			if (P.charAt(i) == P.charAt(jindex[i - 1])) {
//				jindex[i] = jindex[i - 1] + 1;
//			} else {
//				jindex[i] = 0;
//			}
//		}
//		System.out.println(Arrays.toString(jindex));
		int j = 0;
		int count = 0;
		for (int i = 0; i < T.length(); i++) {
			
			while (j > 0 && T.charAt(i) != P.charAt(j))
				j = jindex[j - 1];

			if (T.charAt(i) == P.charAt(j)) { // 두 글자 일치
				if (j == P.length() - 1) { // j가 패턴의 마지막 인덱스라면
					count++; // 카운트 증가
					iq.add((i + 2) - P.length());
					j = jindex[j];
				} else {
					j++;
				}
			}
		}
		System.out.println(count);
		while (!iq.isEmpty())
			System.out.print(iq.poll() + " ");

	}

}