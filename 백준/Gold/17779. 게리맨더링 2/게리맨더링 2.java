import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int [][] map;
	static int min;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int [N][N];
		min = 987654321;
		for(int r=0;r<N;r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0;c<N;c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		for(int r=0;r<N;r++) {
			for(int c=0;c<N;c++) {
				sol(r,c);
			}
		}
		
		System.out.println(min);

		
		
	}
	private static void sol(int r, int c) {
		int [] count = new int [5];
		int [][] cmap = new int [N][N];
		for(int d1=1;d1<N;d1++) {
			if(c-d1 < 0)break;
			for(int d2=1;d2<N;d2++) {
				if(r + d1+d2 >= N || c+d2 >= N) {
					break;
				}
				count = new int [5];
				for(int nr = 0 ;nr<r + d1;nr++) {
					for(int nc = 0; nc <= c;nc++) {
						cmap[nr][nc] = 0;
					}
				}
				for(int nr = 0;nr<=r + d2;nr++) {
					for(int nc = c+1;nc<N;nc++) {
						cmap[nr][nc] = 1;
					}
				}
				for(int nr=r + d1;nr< N;nr++) {
					for(int nc = 0;nc<c-d1+d2;nc++) {
						cmap[nr][nc] = 2;
					}
				}
				for(int nr=r +d2 + 1;nr<N;nr++) {
					for(int nc=c-d1+d2;nc<N;nc++) {
						cmap[nr][nc] = 3;
					}
				}
				for(int nr=0;nr<N;nr++) {
					for(int nc=0;nc<N;nc++) {
						if(nr+nc >= r + c && nr +nc <= r +c + 2* d2 && nr-nc >= r-c && nr-nc <= r-c+2*d1) {
							cmap[nr][nc] = 4;
						}
					}
				}
//				System.out.println(d1 + " : " + d2);
//				for(int r1=0;r1<N;r1++) {
//					for(int c1=0;c1<N;c1++) {
//						System.out.print(cmap[r1][c1] + " ");
//					}
//					System.out.println();
//				}
//				System.out.println();
				
				for(int nr = 0;nr<N;nr++) {
					for(int nc=0;nc<N;nc++) {
						count[cmap[nr][nc]] += map[nr][nc];
					}
				}
				
				int maxval = 0;
				int minval=987654321;
				for(int i=0;i<5;i++) {
					if(count[i] > maxval) {
						
						maxval = count[i];
						
					}
					if(count[i] < minval) {
						minval = count[i];
					}
				}
				
				if(maxval - minval < min) {
					min = maxval - minval;
				}
			}
		}
		
	}

}