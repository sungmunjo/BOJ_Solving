import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N,M;
	static int dicer,dicec;
	static int K;
	static int [][]map;
	
	static int [][] dice;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		dicer = Integer.parseInt(st.nextToken());
		dicec = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int [N][M];
		dice = new int [4][3];
		for(int r=0;r<N;r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0;c<M;c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		for(int r=0;r<4;r++) {
			for(int c=0;c<3;c++) {
				dice[r][c] = 0;
			}
		}
		st = new StringTokenizer(br.readLine());
		for(int tc = 0;tc<K;tc++) {
			int order = Integer.parseInt(st.nextToken());
			if(order == 1) {
				goEast();
			}else if(order == 2) {
				goWest();
			}else if(order == 3) {
				goNorth();
			}else {
				goSouth();
			}
			//System.out.println("(" + dicer + ", " + dicec + ")");
		}
	}
	
	public static void goEast(){
		dicec++;
		if(dicec >= map[0].length) {
			dicec--;
			return;
		}
		int temp = dice[3][1];
		dice[3][1] = dice[1][0];
		
		for(int i=0;i<2;i++) {
			dice[1][i] = dice[1][i+1];
		}
		
		dice[1][2] = temp;
		if(map[dicer][dicec]!= 0) {
			dice[1][1] = map[dicer][dicec];
			map[dicer][dicec] = 0;
		}else {
			map[dicer][dicec] = dice[1][1];
		}
		
		System.out.println(dice[3][1]);
	}
	public static void goWest() {
		dicec--;
		if(dicec < 0) {
			dicec++;
			return;
		}
		int temp = dice[3][1];
		dice[3][1] = dice[1][2];
		
		for(int i=0;i<2;i++) {
			dice[1][2-i] = dice[1][1-i];
		}
		
		dice[1][0] = temp;
		if(map[dicer][dicec]!= 0) {
			dice[1][1] = map[dicer][dicec];
			map[dicer][dicec] = 0;
		}else {
			map[dicer][dicec] = dice[1][1];
		}
		System.out.println(dice[3][1]);
	}
	public static void goNorth() {
		dicer--;
		if(dicer < 0) {
			dicer++;
			return;
		}
		int temp = dice[3][1];
		
		
		for(int i=0;i<3;i++) {
			dice[3-i][1] = dice[2-i][1];
		}
		
		dice[0][1] = temp;
		if(map[dicer][dicec]!= 0) {
			dice[1][1] = map[dicer][dicec];
			map[dicer][dicec] = 0;
		}else {
			map[dicer][dicec] = dice[1][1];
		}
		System.out.println(dice[3][1]);
	}
	public static void goSouth() {
		dicer++;
		if(dicer >= map.length) {
			dicer--;
			return;
		}
		int temp = dice[0][1];
		
		
		for(int i=0;i<3;i++) {
			dice[i][1] = dice[i+1][1];
		}
		
		dice[3][1] = temp;
		if(map[dicer][dicec]!= 0) {
			dice[1][1] = map[dicer][dicec];
			map[dicer][dicec] = 0;
		}else {
			map[dicer][dicec] = dice[1][1];
		}
		System.out.println(dice[3][1]);
	}
}