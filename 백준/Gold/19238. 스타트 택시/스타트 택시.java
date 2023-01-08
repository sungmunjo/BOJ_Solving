import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;



public class Main {
	static int N,M,F;
	static int [][] startMap;
	static ArrayList<Integer> [][] endMap;
	static int taxiR,taxiC; 
	static int startCustomer;
	static int dr[] = {1,0,-1,0};
	static int dc[] = {0,1,0,-1};
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		F = Integer.parseInt(st.nextToken());
		startMap = new int [N][N];
		endMap = new ArrayList[N][N];
		for(int r=0;r<N;r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0;c<N;c++) {
				endMap[r][c] = new ArrayList<>();
				int mapValue = Integer.parseInt(st.nextToken());
				startMap[r][c] = mapValue;
				endMap[r][c].add(mapValue);
				
			}
		}
		st = new StringTokenizer(br.readLine());
		taxiR = Integer.parseInt(st.nextToken()) - 1;
		taxiC = Integer.parseInt(st.nextToken()) - 1;
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			
			int sTempR = Integer.parseInt(st.nextToken()) - 1;
			int sTempC = Integer.parseInt(st.nextToken()) - 1;
			
			startMap[sTempR][sTempC] = i + 2;
			
			int eTempR = Integer.parseInt(st.nextToken()) - 1;
			int eTempC = Integer.parseInt(st.nextToken()) - 1;
			endMap[eTempR][eTempC].add(i+2);
			
		}
		int count  = 0;
		boolean isPossible = true;
		while(isPossible && count != M) {
			isPossible = findNearBy();
			
			if(!isPossible) {
				System.out.println(-1);
				break;
			}
			//showMap();
			
			isPossible = customerEnd();
			
			if(!isPossible) {
				System.out.println(-1);
				break;
			}
			//showMap();
			count++;
		}
		if(isPossible) {
			System.out.println(F);
		}
		
	}
	public static void showMap() {
		for(int r=0;r<N;r++) {
			for(int c=0;c<N;c++) {
				if(r == taxiR && c == taxiC) {
					System.out.print("t");
				}else {
					System.out.print(startMap[r][c]);
				}
			}
			System.out.println();
		}
		System.out.println();
		for(int r=0;r<N;r++) {
			for(int c=0;c<N;c++) {
				if(r == taxiR && c == taxiC) {
					System.out.print("t");
				}else {
					System.out.print(endMap[r][c]);
				}
			}
			System.out.println();
		}
		System.out.println(F);
		System.out.println();
	}
	
	public static boolean customerEnd() {
		Queue<int []>Q = new LinkedList<>();
		Q.add(new int[] {taxiR, taxiC});
		boolean [][] v = new boolean[N][N];
		v[taxiR][taxiC] = true;
		
		
		int distance= 0;
		
		while(!Q.isEmpty()) {
			int Qsize = Q.size();
			for(int s=0;s<Qsize;s++) {
				int [] taxiLoaction = Q.poll();
				if(endMap[taxiLoaction[0]][taxiLoaction[1]].contains(startCustomer) && distance <= F) {
					F+= distance;
					
					taxiR = taxiLoaction[0];
					taxiC = taxiLoaction[1];
					return true;
				}else if(distance > F) {
					return false;
				}
				for(int d=0;d<4;d++) {
					int nr = taxiLoaction[0] + dr[d];
					int nc = taxiLoaction[1] + dc[d];
					if(check(nr, nc) && !endMap[nr][nc].contains(1) && !v[nr][nc]) {
						v[nr][nc] = true;
						Q.add(new int [] {nr,nc});
					}
				}
			}
			distance++;
		}
		return false;
	}
	
	public static boolean findNearBy() {
		Queue<int[]> Q = new LinkedList<>();
		Q.add(new int[] {taxiR, taxiC});
		boolean [][] v = new boolean[N][N];
		v[taxiR][taxiC] = true;
		
		int distance = 0;
		while(!Q.isEmpty()) {
			int Qsize = Q.size();
			boolean findCustomer = false;
			if(F < distance) {
				return false;
			}
			for(int s=0;s<Qsize;s++) {
				int [] taxiLoaction = Q.poll();
				if(startMap[taxiLoaction[0]][taxiLoaction[1]] >=2 ) {
					
					if(findCustomer && (taxiLoaction[0] < taxiR || (taxiLoaction[0] == taxiR && taxiLoaction[1] < taxiC))) {
						startCustomer = startMap[taxiLoaction[0]][taxiLoaction[1]];
						taxiR = taxiLoaction[0];
						taxiC = taxiLoaction[1];
					}
					if(!findCustomer) {
						startCustomer = startMap[taxiLoaction[0]][taxiLoaction[1]];
						taxiR = taxiLoaction[0];
						taxiC = taxiLoaction[1];
					}
					
					findCustomer = true;
				}
				
				for(int d=0;d<4;d++) {
					int nr = taxiLoaction[0] + dr[d];
					int nc = taxiLoaction[1] + dc[d];
					if(check(nr, nc) && !v[nr][nc] && startMap[nr][nc]!=1) {
						v[nr][nc] = true;
						Q.add(new int [] {nr,nc});
						
					}
				}
				
			}
			if(findCustomer) {
				startMap[taxiR][taxiC] = 0;
				F-=distance;
				return true;
			}
			distance++;
			
		}
		return false;
	}
	public static boolean check(int nr,int nc) {
		if(nr>=0 && nr < N && nc>=0 && nc<N)return true;
		return false;
	}
}