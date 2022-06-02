import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map;
	static int dr[] = { -1 ,- 1, 0, 1, 1, 1, 0, -1 };
	static int dc[] = { 0, 1, 1, 1, 0, -1, -1, -1 };

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str;
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		int result = bfs();
		System.out.println(result);
	}

	private static int bfs() {
		int result = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c] == 0) {
					int level = 0;
					boolean[][] v = new boolean[N][M];
					Queue<int[]> Q = new LinkedList<>();
					int arr[] = { r, c };
					v[r][c] = true;
					Q.add(arr);
					breakpoint: while (!Q.isEmpty()) {
						int size = Q.size();
						for (int s = 0; s < size; s++) {
							int item[] = Q.poll();
							if (map[item[0]][item[1]] == 1) {
								result = Math.max(result, level);
//								System.out.println(r + " : " + c + " = " + level);
								break breakpoint;
							}
							for (int d = 0; d < 8; d++) {
								int nr = item[0] + dr[d];
								int nc = item[1] + dc[d];
								if (check(nr, nc) && !v[nr][nc]) {
									v[nr][nc] = true;
									Q.add(new int[] { nr, nc });
								}
							}
						}
						level++;
					}
				}
			}
		}
		return result;
	}

	public static boolean check(int nr, int nc) {
		if (nr >= 0 && nr < N && nc >= 0 && nc < M)
			return true;
		return false;
	}

}