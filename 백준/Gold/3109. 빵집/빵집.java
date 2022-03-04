
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static char [][] map;
	static int [] dr = {-1,0,1};
	static int [] dc = {1,1,1};
	static boolean [][] check;
	static int result;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str;
		StringTokenizer st;
		int rsize, csize;
		result = 0;
		str = br.readLine();
		st = new StringTokenizer(str);
		rsize = Integer.parseInt(st.nextToken());
		csize = Integer.parseInt(st.nextToken());
		map = new char[rsize][csize];
		check = new boolean[rsize][csize];
		for(int i=0;i<rsize;i++) {
			str = br.readLine();
			for(int j=0;j<csize;j++) {
				map[i][j] = str.charAt(j);
			}
		}
//		for(char [] a : map) {
//			System.out.println(Arrays.toString(a));
//		}
		
		for(int i=0;i<map.length;i++) {
			dfs(i,0);
		}
		System.out.println(result);
	}
	private static boolean dfs(int r,int c) {
		if(c == map[0].length - 1) {
			result++;
			return true;
		}
		boolean flag;
		for(int d=0;d<3;d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			if(nr>=0 && nr < map.length && nc >=0 && nc < map[0].length
					&& !check[nr][nc] && map[nr][nc] == '.') {
				check[nr][nc] = true;
				flag = dfs(nr, nc);
				if(flag == true) {
					return true;
				}
//				check[nr][nc] = false;
			}
		}
		return false;
		
	}
	
}