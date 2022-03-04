import java.io.*;
import java.util.*;

public class Main {
	static char[][] seat = new char[5][5];
	static int result;
	static boolean[] v = new boolean[25];
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str;
		result = 0;
		for (int r = 0; r < 5; r++) {
			str = br.readLine();
			for (int c = 0; c < 5; c++) {
				seat[r][c] = str.charAt(c);
			}
		}

		com(0, 0, 0, 0);
		System.out.println(result);
	}

	private static void com(int start, int number, int scount, int sel) {
		// TODO Auto-generated method stub
		if (number == 7) {
			if (scount >= 4) {
				if (check(sel))
					result++;
				return;
			}
		}
		if (7 - number < 4 - scount) {
			return;
		}
		for (int i = start; i < 25; i++) {
			if (seat[i / 5][i % 5] == 'S')
				com(i + 1, number + 1, scount + 1, sel | 1 << i);
			else
				com(i + 1, number + 1, scount, sel | 1 << i);
		}

	}

	private static boolean check(int sel) {
		// TODO Auto-generated method stub
		Queue<Integer> IQ = new LinkedList<>();
		int count = 0;
		int itemp = 0;
		boolean[] v = new boolean[25];
		for (int i = 0; i < 25; i++) {
			if ((sel & 1 << i) != 0) {
				IQ.add(i);
				v[i] = true;
				itemp = i;
				break;
			}
		}
		
		while (!IQ.isEmpty()) {
			int temp = IQ.poll();
			
			count++;
			for (int d = 0; d < 4; d++) {
				if(temp%5 == 4 && d == 1)continue;
				if(temp%5 == 0 && d == 3)continue;
				int newloc = temp + dc[d] + 5 * dr[d];
				if (newloc >= 0 && newloc < 25 && !v[newloc] && (sel & 1 << newloc) != 0) {
					v[newloc] = true;
					IQ.add(newloc);
				}
			}
		}
		if (count == 7) {
			return true;
		}
			

		return false;
	}

}