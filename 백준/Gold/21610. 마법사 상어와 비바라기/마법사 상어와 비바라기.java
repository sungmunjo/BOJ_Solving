import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map;
	static boolean[][] cloud;
	static int dr[] = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int dc[] = { -1, -1, 0, 1, 1, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		cloud = new boolean[N][N];

		for (int r = N - 2; r < N; r++) {
			for (int c = 0; c < 2; c++) {
				cloud[r][c] = true;
			}
		}
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		for (int tc = 0; tc < M; tc++) {
			st = new StringTokenizer(br.readLine());
			int direction = Integer.parseInt(st.nextToken()) - 1;
			int distance = Integer.parseInt(st.nextToken());

			moveCloud(direction, distance);
			rain();
			addWater();
			newCloud();
			
//			showmap();
		}
		StringBuilder sb = new StringBuilder();
		
		sb.append(countWater());
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	private static int countWater() {
		int count=0;
		for(int r=0;r<N;r++) {
			for(int c=0;c<N;c++) {
				count += map[r][c];
			}
		}
		return count;
	}

	private static void showmap() {
		for(int r=0;r<N;r++) {
			for(int c=0;c<N;c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
		System.out.println();
		
	}

	private static void newCloud() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (!cloud[r][c] && map[r][c] >= 2) {
					cloud[r][c] = true;
					map[r][c] -= 2;
				} else {
					cloud[r][c] = false;
				}
			}
		}

	}

	private static boolean check(int nr, int nc) {
		if (nr >= 0 && nr < N && nc >= 0 && nc < N)
			return true;
		return false;
	}

	private static void addWater() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (cloud[r][c]) {
					int count = 0;
					for (int d = 0; d < 4; d++) {
						int nr = r + dr[2 * d + 1];
						int nc = c + dc[2 * d + 1];

						if (check(nr, nc) && map[nr][nc] > 0) {
							count++;
						}
					}
					map[r][c] += count;
				}
			}
		}

	}

	private static void rain() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (cloud[r][c]) {
					map[r][c]++;
				}
			}
		}

	}

	private static void moveCloud(int direction, int distance) {
		boolean[][] tempCloud = new boolean[N][N];
		int disr = dr[direction] * distance;
		int disc = dc[direction] * distance;

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				int nr = r + disr;
				int nc = c + disc;

				while (nr < 0) {
					nr += N;
				}
				while (nc < 0) {
					nc += N;
				}
				nr %= N;
				nc %= N;

				tempCloud[nr][nc] = cloud[r][c];
			}
		}

		cloud = tempCloud;
	}

}