import java.io.*;
import java.util.*;



public class Main {
	// 계획서
	/**
	 * 팀을 뽑습니다 부분집합(혹은 조합)을 이용해서
	 * 팁을 다 뽑으면 팀의 능력치를 계산하고 두 팀의 차이를 비교하여 min값과 비교합니다.
	 * 모든 조합을 다 계산하고 나면 능력치의 최솟값이 나옵니다.
	 */
	static int min = 987654321;
	static int [][]map;
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		String str;
		map = new int [N][N];
		for(int r=0;r<N;r++) {
			str = br.readLine();
			st = new StringTokenizer(str);
			for(int c=0;c<N;c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		per(0,0);
		System.out.println(min);
	}
	private static void per(int sel, int count) {
		if(count == N) {
			int cal = colcul(sel);
			if(cal < min) {
				min = cal;
			}
			return;
		}
		
		per(sel|1<<count, count+1);
		per(sel, count+1);
	}
	private static int colcul(int sel) {
		int team[] = new int [2];
		int teamnum;
		for(int i=0;i<N;i++) {
			if((sel&1<<i) != 0) {
				teamnum = 1;
			}else {
				teamnum = 0;
			}
			for(int j=i+1;j<N;j++) {
				if((sel&1<<j)!=0 && teamnum == 1) {
					team[teamnum] += map[i][j];
					team[teamnum] += map[j][i];
				}else if((sel&1<<j) == 0 &&  teamnum == 0) {
					team[teamnum] += map[i][j];
					team[teamnum] += map[j][i];
				}
			}
		}
		return Math.abs(team[0] - team[1]);
	}

}