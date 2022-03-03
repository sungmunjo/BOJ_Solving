import java.util.Scanner;

public class Main {
	static int N;
	static double sum;
	static int max;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		max = 0;
		sum = 0;
		int itemp;
		for (int i = 0; i < N; i++) {
			itemp = sc.nextInt();
			if (max < itemp) {
				sum += max;
				max = itemp;
			} else {
				sum += itemp;
			}
		}
		if(sum %2 == 1) {
			System.out.println(sum/2 + max);
			return;
		}
		System.out.printf("%.0f",(sum/2 + max));
	}
}