
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
	static int dr[] = { 0, 1, 0, -1 };
	static int dc[] = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N, M;
		int orders;
		int[][] map;
		int order;
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str);
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		orders = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			str = br.readLine();
			st = new StringTokenizer(str);
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		str = br.readLine();
		st = new StringTokenizer(str);
		for(int i=0;i<orders;i++) {
			order = Integer.parseInt(st.nextToken());
			switch(order) {
			case 1:
				order1(map);
				break;
			case 2:
				order2(map);
				break;
			case 3:
				map = order3(map);
				break;
			case 4:
				map = order4(map);
				break;
			case 5:
				order5(map);
				break;
			case 6:
				order6(map);
				break;
			}
		}
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map[0].length;j++) {
				System.out.print(map[i][j] +" ");
			}
			System.out.println();
		}
	}

	public static void order1(int[][] map) {
		int length = map.length - 1;
		int temp1;
		for (int i = 0; i < map.length / 2; i++) {
			for (int j = 0; j < map[i].length; j++) {
				temp1 = map[length - i][j];
				map[length - i][j] = map[i][j];
				map[i][j] = temp1;
			}
		}

	}

	public static void order2(int[][] map) {
		int length = map[0].length - 1;
		int temp1;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length / 2; j++) {
				temp1 = map[i][length - j];
				map[i][length - j] = map[i][j];
				map[i][j] = temp1;
			}
		}
	}

	public static int[][] order3(int[][] map) {
		int[][] tempmap = new int[map[0].length][map.length];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				tempmap[j][(map.length - 1) - i] = map[i][j];
			}
		}
		return tempmap;
	}

	public static int[][] order4(int[][] map) {
		int[][] tempmap = new int[map[0].length][map.length];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				tempmap[(map[0].length - 1) - j][i] = map[i][j];
			}
		}
		return tempmap;
	}

	public static void order5(int[][] map) {
		dr = new int[]{ 0, 1, 0, -1 };
		dc = new int[]{ 1, 0, -1, 0 };
		int[][] tempmap = new int[map.length / 2][map[0].length / 2];
		for(int i=0;i<map.length/2;i++) {
			for(int j=0;j<map[0].length/2;j++) {
				tempmap[i][j] = map[i][j];
			}
		}
		int ccurr,rcurr;
		ccurr = 0;
		rcurr = 0;
		for (int d = 0; d < 4; d++) {
			for(int i=0;i<map.length/2;i++) {
				for(int j=0;j<map[0].length/2;j++) {
					map[i + ccurr][j + rcurr] = map[ i + ccurr +map.length/2 * dc[d]][j + rcurr + map[0].length/2 * dr[d]];
				}
			}
			ccurr += map.length/2 * dc[d];
			rcurr += map[0].length/2 * dr[d];
		}
		for(int i = 0;i<map.length/2;i++) {
			for(int j = map[0].length/2;j<map[0].length;j++) {
				map[i][j]= tempmap[i][j - map[0].length/2];
			}
		}
		
	}

	public static void order6(int[][] map) {
		dr = new int[]{ 1, 0, -1, 0 };
		dc = new int[]{ 0, 1, 0, -1 };
		int[][] tempmap = new int[map.length / 2][map[0].length / 2];
		for(int i=0;i<map.length/2;i++) {
			for(int j=0;j<map[0].length/2;j++) {
				tempmap[i][j] = map[i][j];
			}
		}
		int ccurr,rcurr;
		ccurr = 0;
		rcurr = 0;
		for (int d = 0; d < 4; d++) {
			for(int i=0;i<map.length/2;i++) {
				for(int j=0;j<map[0].length/2;j++) {
					map[i + ccurr][j + rcurr] = map[ i + ccurr +map.length/2 * dc[d]][j + rcurr + map[0].length/2 * dr[d]];
				}
			}
			ccurr += map.length/2 * dc[d];
			rcurr += map[0].length/2 * dr[d];
		}
		for(int i = map.length/2;i<map.length;i++) {
			for(int j = 0;j<map[0].length/2;j++) {
				map[i][j]= tempmap[i-map.length/2][j];
			}
		}
		
	}

}