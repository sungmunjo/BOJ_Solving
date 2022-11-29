import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] memo;
    static boolean link[][];
    static int [][] minValue;
    static int [] times;
    static int N,K;
    static int W;
    public static void main(String[] args) throws IOException {
        int test_case;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        test_case = Integer.parseInt(st.nextToken());
        for(int tc=0;tc<test_case;tc++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            memo = new int [N];
            link = new boolean[N][N];
            times = new int[N];
            minValue = new int [N][N];
            for(int r=0;r<N;r++){
                for(int c=0;c<N;c++){
                    minValue[r][c] = Integer.MAX_VALUE - 1;
                }
            }
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<N;i++){
                memo[i] = -1;
                times[i] = Integer.parseInt(st.nextToken());
            }
            for(int i=0;i<K;i++){
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken()) - 1;
                int end = Integer.parseInt(st.nextToken()) - 1;
                link[start][end] = true;
            }
            W = Integer.parseInt(br.readLine()) - 1;
            int answer = getAnswer(W);
            System.out.println(answer);

        }

    }

    private static int getAnswer(int W) {
        int maxValue = 0;
        if(memo[W]!=-1){
            return memo[W];
        }
        for(int i=0;i<N;i++){
            if(link[i][W]){
                int data = getAnswer(i);
                if(data > maxValue){
                    maxValue = data;
                }
            }
        }
        memo[W] = maxValue + times[W];
        return maxValue + times[W];
    }
}