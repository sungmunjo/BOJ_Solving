
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static int arr[];
	static LinkedList<Integer> li = new LinkedList<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str;
		StringTokenizer st;
		int N;
		int X;
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		str = br.readLine();
		st = new StringTokenizer(str);
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		X = Integer.parseInt(br.readLine());
		for (int i = 2; i <= X; i++) {
			if (X % i == 0) {
				li.add(i);
			}
		}
		
		System.out.println(change(sol(arr, X)));

	}

	public static String change(double d) {
		int count = 0;
		int pointidx = 0;
		String str = Double.toString(d);
		for(int i=0;i<str.length();i++) {
			if(str.charAt(i) == '.') {
				pointidx = i + 1;
				break;
			}
		}
		while(pointidx + count < str.length() && str.charAt(pointidx + count) != '0') {
			count++;
		}
		switch(count) {
		case 0: 
			return String.format("%.0f", d);
		case 1: 
			return String.format("%.1f", d);
		case 2: 
			return String.format("%.2f", d);
		case 3: 
			return String.format("%.3f", d);
		case 4: 
			return String.format("%.4f", d);
		case 5: 
			return String.format("%.5f", d);
		default: 
			return String.format("%.6f", d);
		}
		
	}

	public static double sol(int[] arr, int X) {
		double result = 0;
		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			if(gcd(arr[i],X) == 1) {
				result += arr[i];
				count++;
			}
		}
		return result / count;
	}
	 public static int gcd(int p, int q) 
	 {
		if (q == 0) return p;
		return gcd(q, p%q);
	 }
}