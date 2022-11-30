import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;



public class Main {
    static int N;
    static int[][] points;
    static double minValue;
    static boolean [] sel;

    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int test_case;

        test_case = Integer.parseInt(br.readLine());
        for(int tc=0;tc<test_case;tc++){
            N = Integer.parseInt(br.readLine());
            points = new int[N][2];

            sel = new boolean[N];
            minValue = 987654321;
            for(int i=0;i<N;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<2;j++){
                    points[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            per(0,0);

            System.out.println(minValue);
        }
    }

    private static void per(int count, int start) {
        if(count == N/2){
            double recResult;
            recResult = calculValue();
            if(recResult < minValue){
                minValue = recResult;
            }
            return;
        }

        for(int i=start;i<N;i++){
            sel[i] = true;
            per(count+1, i+1);
            sel[i] = false;
        }
    }

    private static double calculValue() {
        double returnValue = 0;
        int rVal = 0;
        int cVal = 0;

        for(int i=0;i<N;i++){
            if(sel[i]){
                rVal+=points[i][0];
                cVal+=points[i][1];
            }else{
                rVal-=points[i][0];
                cVal-=points[i][1];
            }
        }
        returnValue = Math.sqrt(Math.pow(Math.abs(rVal),2)+Math.pow(Math.abs(cVal),2));
        return returnValue;
    }
}