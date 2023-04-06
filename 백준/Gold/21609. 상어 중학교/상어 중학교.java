import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static int N, M;
	static int[] dr = { 1, 0, -1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int result = 0;
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());

			}
		}
		boolean flag = true;
		while (flag) {
//			System.out.println("collection");
			int Count = Collection();
			if (Count < 2) {
				break;
			}
//			showMap();
//			System.out.println("gravity");
			gravity();
//			showMap();
//			System.out.println("rotate");
			totate();
//			showMap();
//			System.out.println("gravity");
			gravity();
//			showMap();
			result += (int)Math.pow(Count, 2);
			
		}
		System.out.println(result);

	}
	
	private static void showMap() {
		for(int r=0;r<N;r++) {
			for(int c=0;c<N;c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	private static void totate() {
		int[][] newMap = new int[N][N];
		
		for(int r=0;r<N;r++) {
			for(int c=0;c<N;c++) {
				newMap[r][c] = map[c][N-r-1];
			}
		}
		map = newMap;
	}

	private static void gravity() {

		for (int c = 0; c < N; c++) {
			
			for(int r=N-1;r>=0;r--) {
				int diff = 0;
				if(map[r][c] == -2) {
					while(check(r-1-diff,c) && map[r-1-diff][c] == -2) {
						diff++;
					}
					if(!check(r-1-diff,c)) {
						continue;
					}
//					System.out.println((r-1-diff) + " : " + c + " : " +   map[r-1-diff][c]);
					if(map[r-1-diff][c] != -1) {
						map[r][c] = map[r-1-diff][c];
						map[r-1-diff][c] = -2;
					}
					
				}
				
				
			}
		}

	}

	private static int Collection() {
		boolean visited[][] = new boolean[N][N];
		boolean rainVisit[][] = new boolean[N][N];
		int maxTotal = -1;
		int maxRainbow = -1;
		int targetr = -1;
		int targetc = -1;

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				int totalC = 1;
				int rainbowC = 0;
				int color = map[r][c];
				if(color == 0 || color == -1 || color == -2) {
					continue;
				}
//				System.out.println(r + " : " + c);
				Queue<int[]> Q = new LinkedList<int[]>();
				Q.add(new int[] {r,c});
				visited[r][c] = true;
				for (int rt = 0; rt < N; rt++) {
					for (int ct = 0; ct < N; ct++) {
						rainVisit[rt][ct] = false;
					}
				}
				while (!Q.isEmpty()) {
//					System.out.println("Q start");
					int[] current = Q.poll();
					int currentr = current[0];
					int currentc = current[1];
					
					
					
					for (int d = 0; d < 4; d++) {
						int nr = currentr + dr[d];
						int nc = currentc + dc[d];
						if (check(nr, nc) && ((map[nr][nc] == color && !visited[nr][nc])
								|| (map[nr][nc] == 0 && !rainVisit[nr][nc]))) {
//							System.out.println("in?");
							int cColor = map[nr][nc];
							if (cColor == color) {
								visited[nr][nc] = true;
							} else {
								rainVisit[nr][nc] = true;
								rainbowC++;
							}
							totalC++;
							Q.add(new int[] { nr, nc });
						}
					}

				}
				if (totalC > maxTotal) {
					targetr = r;
					targetc = c;
					maxTotal = totalC;
					maxRainbow = rainbowC;
				} else if (totalC == maxTotal && rainbowC >= maxRainbow) {
					targetr = r;
					targetc = c;
					maxTotal = totalC;
					maxRainbow = rainbowC;
				}

			}
		}
		if(targetr == -1 && targetc == -1) {
			return 1;
		}
		Queue<int[]> nq = new LinkedList<>();
		nq.add(new int[] { targetr, targetc });
		boolean dvisited[][] = new boolean[N][N];
		int targetColor = map[targetr][targetc];
		map[targetr][targetc] = -2;
		while (!nq.isEmpty()) {
			int[] current = nq.poll();

			for (int d = 0; d < 4; d++) {
				int nr = current[0] + dr[d];
				int nc = current[1] + dc[d];

				if (check(nr, nc) && !dvisited[nr][nc] && (map[nr][nc] == targetColor || map[nr][nc] == 0)) {
					nq.add(new int[] {nr,nc});
					
					dvisited[nr][nc] = true;
					map[nr][nc] = -2;
				}
			}
		}
//		System.out.println(targetr + " : " + targetc + " : " + maxTotal + " : " + maxRainbow);
		return maxTotal;

	}

	private static boolean check(int nr, int nc) {
		if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
			return true;
		}
		return false;
	}

}