
import java.util.Scanner;

public class Main {
	
	static int N;
	static int map[];
static void makeSet() {
	map = new int [N + 1];
	for (int i = 1; i <= N; i++) {
		map[i] = i;
	}
}
static boolean unionSet(int from, int go) {
	if(findSet(go) == findSet(from))return false;
	map[findSet(go)] = map[findSet(from)];
	return true;
}
static int findSet(int i) {
	if(map[i]==i)return i;
	return map[i] = findSet(map[i]);
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		makeSet();
		int M = sc.nextInt();
		for (int i = 0; i < M; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			
			unionSet(from, to);
		}
		
		int temp = findSet(1);
		int result = -1;
		for (int i = 1; i <=N	; i++) {
			if(findSet(i) == temp)result++;
		}
		System.out.println(result);
	}

}
