
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Shark implements Comparable<Shark> {
		int speed, dir, size;
		boolean move;

		public Shark(int speed, int dir, int size) {
			super();
			this.speed = speed;
			this.dir = dir;
			this.size = size;
			this.move = false;
		}

		@Override
		public int compareTo(Shark o) {
			// TODO Auto-generated method stub
			return this.size - o.size;
		}

	}

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };
	static int R, C, M;
	static PriorityQueue<Shark>[][] smap;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int fisher = 1;
		int result = 0;
		st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		smap = new PriorityQueue[R + 1][C + 1];
		for (int r = 0; r < smap.length; r++) {
			for (int c = 0; c < smap[r].length; c++) {
				smap[r][c] = new PriorityQueue<>();
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int speed = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			int size = Integer.parseInt(st.nextToken());
			if(dir == 1 || dir == 2) {
				speed = speed % ((R-1)*2);
			}
			else {
				speed = speed % ((C-1)*2);
			}
			smap[r][c].add(new Shark(speed, dir - 1, size));
		}
		while (fisher < C + 1) {

			int sindex = 1;
			while (sindex < R + 1) {
				if (smap[sindex][fisher].size() == 1) {
					break;
				}
				sindex++;
			}
			if (sindex != R + 1) {
//				System.out.println(sindex + " : " + fisher);
				result += smap[sindex][fisher].poll().size;
//				System.out.println(result);
			}
			for (int r = 1; r < smap.length; r++) {
				for (int c = 1; c < smap[r].length; c++) {
					if (smap[r][c].size() == 0)
						continue;
					Queue<Shark> stempq = new LinkedList<>();
					while (!smap[r][c].isEmpty() && smap[r][c].peek().move == true) {
						stempq.add(smap[r][c].poll());
					}
					Shark stemp = smap[r][c].poll();
					while (!stempq.isEmpty()) {
						smap[r][c].add(stempq.poll());
					}
					int nr = r;
					int nc = c;

					if (stemp != null) {
//						System.out.print(r + ":" + c + ":" + stemp.size + ":" + stemp.dir + "\t -->");
//						if(stemp.dir == 1 && stemp.dir == 0) {
//							stemp.speed%=(R-1) * 2;
//						}else {
//							stemp.speed%=(C-1)*2;
//						}
						for (int i = 0; i < stemp.speed; i++) {
							nr += dr[stemp.dir];
							nc += dc[stemp.dir];
							if (nr == R + 1) {
								nr = R - 1;
								stemp.dir = 0;
							}
							if (nr == 0) {
								nr = 2;
								stemp.dir = 1;
							}
							if (nc == C + 1) {
								nc = C - 1;
								stemp.dir = 3;
							}
							if (nc == 0) {
								nc = 2;
								stemp.dir = 2;
							}
						}
//						System.out.println(nr + ":" + nc + ":" + stemp.size + ":" + stemp.dir);
						stemp.move = true;
						smap[nr][nc].add(stemp);
					}

				}
			}

			for (int i = 1; i < smap.length; i++) {
				for (int j = 1; j < smap[i].length; j++) {
					if (smap[i][j].size() == 0) {
						continue;
					}
					while (smap[i][j].size() > 1) {
						smap[i][j].poll();
					}

					smap[i][j].peek().move = false;

				}
			}
			fisher++;
		}
		System.out.println(result);
	}
}
