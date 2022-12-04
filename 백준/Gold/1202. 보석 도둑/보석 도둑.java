import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Jualinfo implements Comparable<Jualinfo>{
        int M,V;
        @Override
        public int compareTo(Jualinfo o) {
            return this.M - o.M == 0?this.V - o.V : this.M - o.M;
        }
    }

    static int N,K;
    static Jualinfo [] jualInfos;
    static int [] bagInfos;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        double answer = 0;
        jualInfos = new Jualinfo[N];
        bagInfos = new int[K];
        for(int i =0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            jualInfos[i] = new Jualinfo();

            jualInfos[i].M = Integer.parseInt(st.nextToken());
            jualInfos[i].V = Integer.parseInt(st.nextToken());
            //System.out.println(jualInfos[i].M + " : " + jualInfos[i].V);

        }

        for(int i=0;i<K;i++){
            bagInfos[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(bagInfos);
        Arrays.sort(jualInfos);
        for(int i=0;i<N;i++){
            //System.out.println(jualInfos[i].M + " : " + jualInfos[i].V);
        }
        int bagIndex =0;
        int jualIndex = 0;
        PriorityQueue<Integer> jualValues = new PriorityQueue<>((o1, o2) -> o2-o1);
        while(bagIndex != bagInfos.length){
            int bagMax = bagInfos[bagIndex];
            while(jualIndex < jualInfos.length && jualInfos[jualIndex].M <= bagMax){
                int jualValue = jualInfos[jualIndex].V;
                jualValues.add(jualValue);
                jualIndex++;
            }
            if(jualValues.size() !=0){
                answer += jualValues.poll();
            }
            bagIndex++;
        }
        System.out.printf("%.0f",answer);

    }
}