import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


class Horse{
	int r,c;
	int dir;
	int order;
}


public class Main {

	static ArrayList<Horse> horsearray = new ArrayList<Horse>();
	static int [][] mapCount;
	static int[] dr = {0,0,-1,1};
	static int[] dc = {1,-1,0,0};
	static int N,K;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String temp = br.readLine();
		st = new StringTokenizer(temp);
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		mapCount = new int [N][N];
		for(int r = 0;r<N;r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0;c<N;c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				mapCount[r][c] = 0;
			}
		}
		for(int i=0;i<K;i++) {
			st = new StringTokenizer(br.readLine());
			Horse newhorse = new Horse();
			newhorse.r = Integer.parseInt(st.nextToken()) - 1;
			newhorse.c = Integer.parseInt(st.nextToken()) - 1;
			newhorse.dir = Integer.parseInt(st.nextToken()) - 1;
			newhorse.order = 0;
			mapCount[newhorse.r][newhorse.c]++;
			horsearray.add(newhorse);
		}
		
		int answer = 0;
		while(answer < 1000) {
			answer++;
			//System.out.println(answer);
			for(int index=0;index<horsearray.size();index++) {
				
				
				int r = horsearray.get(index).r;
				int c = horsearray.get(index).c;
				
				int nr = r + dr[horsearray.get(index).dir];
				int nc = c + dc[horsearray.get(index).dir];
				// 막히거나 파랑임
				if(!check(nr,nc) || map[nr][nc] == 2) {
					int tempr = r - dr[horsearray.get(index).dir];
					int tempc = c - dc[horsearray.get(index).dir];
					// 반대도 막힘?
					if(!check(tempr,tempc) || map[tempr][tempc] == 2) {
						if(horsearray.get(index).dir%2 == 0) {
							horsearray.get(index).dir++;
						}else {
							horsearray.get(index).dir--;
						}
					}
					// 반대 안막힘
					else if(map[tempr][tempc] == 0){
						int currCount = mapCount[tempr][tempc];
						int tempOrder = horsearray.get(index).order;
						int tempCount = mapCount[r][c];
						int diff = tempCount - tempOrder;
						if(horsearray.get(index).dir%2 == 0) {
							horsearray.get(index).dir++;
						}else {
							horsearray.get(index).dir--;
						}
						for(int k = 0;k<diff;k++) {
							for(int q= 0 ;q<horsearray.size();q++) {
								if(horsearray.get(q).r == r && horsearray.get(q).c == c && horsearray.get(q).order == tempOrder + k) {
									horsearray.get(q).r = tempr;
									horsearray.get(q).c = tempc;
									
									horsearray.get(q).order = currCount + k;
									break;
								}
							}
							
						}
						mapCount[tempr][tempc] = currCount + diff;
						mapCount[r][c] = tempCount - diff;
						if(mapCount[tempr][tempc] >= 4) {
							System.out.println(answer);
							return;
						}
					}else {
						int currCount = mapCount[tempr][tempc];
						int tempOrder = horsearray.get(index).order;
						int tempCount = mapCount[r][c];
						int diff = tempCount - tempOrder;
						if(horsearray.get(index).dir%2 == 0) {
							horsearray.get(index).dir++;
						}else {
							horsearray.get(index).dir--;
						}
						for(int k = 0;k<diff;k++) {
							for(int q= 0 ;q<horsearray.size();q++) {
								if(horsearray.get(q).r == r && horsearray.get( q).c == c && horsearray.get(q).order == tempOrder + k) {
									horsearray.get(q).r = tempr;
									horsearray.get( q).c = tempc;
									horsearray.get(q).order = currCount + diff - k - 1;
									break;
								}
							}
							
						}
						mapCount[tempr][tempc] = currCount + diff;
						mapCount[r][c] = tempCount - diff;
						if(mapCount[tempr][tempc] >= 4) {
							System.out.println(answer);
							return;
						}
						
					}
					
				}
				// 빨강
				else if(map[nr][nc] == 1) {
					int currCount = mapCount[nr][nc];
					int tempOrder = horsearray.get(index).order;
					int tempCount = mapCount[r][c];
					int diff = tempCount - tempOrder;
					mapCount[nr][nc] = currCount + diff;
					mapCount[r][c] = tempCount - diff;
					if(mapCount[nr][nc] >= 4) {
						System.out.println(answer);
						return;
					}
					//하나씩 바꿔줌.
					for(int k = 0;k<diff;k++) {
						for(int q= 0 ;q<horsearray.size();q++) {
							if(horsearray.get(q).r == r && horsearray.get( q).c == c && horsearray.get(q).order == tempOrder + k) {
								horsearray.get(q).r = nr;
								horsearray.get(q).c = nc;
								horsearray.get(q).order = currCount + diff - k - 1;
								break;
							}
						}
						
					}
				}
				// 흰색
				else {
					int currCount = mapCount[nr][nc];
					int tempOrder = horsearray.get(index).order;
					int tempCount = mapCount[r][c];
					//옮길 갯수
					int diff = tempCount - tempOrder;
					for(int k = 0;k<diff;k++) {
						for(int q= 0 ;q<horsearray.size();q++) {
							if(horsearray.get(q).r == r && horsearray.get(q).c == c && horsearray.get(q).order == tempOrder + k) {
								horsearray.get(q).r = nr;
								horsearray.get(q).c = nc;
								horsearray.get(q).order = currCount + k;
								break;
							}
						}
						
					}
					mapCount[nr][nc] = currCount + diff;
					mapCount[r][c] = tempCount - diff;
					if(mapCount[nr][nc] >= 4) {
						System.out.println(answer);
						return;
					}
				}
				/*for(int asd = 0;asd<horsearray.size();asd++) {
					System.out.println(horsearray.get(asd).r + " : " +horsearray.get(asd).c + " : " +horsearray.get(asd).order );
				}
				System.out.println();*/
			}
			//System.out.println();

		}
		System.out.println("-1");

		return;
	}
	static boolean check(int nr,int nc) {
		if(nr>=0 && nr < N && nc>=0 && nc<N)return true;
		return false;
	}

}