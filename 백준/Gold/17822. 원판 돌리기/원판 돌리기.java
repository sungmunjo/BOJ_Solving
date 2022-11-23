import java.io.*;
import java.util.*;
import java.util.logging.Level;

public class Main {
    static int N,M,T;
    static int map[][];

    static int[] dr = {0,1,0,-1};
    static int[] dc = {1,0,-1,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int r=0;r<N;r++){
            st = new StringTokenizer(br.readLine());

            for(int c=0;c<M;c++){
                map[r][c] = Integer.parseInt(st.nextToken());
            }

        }
        int x,d,k;
        for(int tc=0;tc<T;tc++){
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            //돌리기 시작
            int devtemp = N/x;
            for(int row = 1;row<=devtemp;row++){
                int turnindex = x * row -1;
                for(int tcount =0;tcount < k;tcount++){
                    if(d==0){
                        turnRight(map[turnindex]);
                    }else{
                        turnLeft(map[turnindex]);
                    }
                }
            }
            // 지우기 시작
            boolean res = bfsmap();
            // 후처리
            if(!res){
                asFunction();
            }


        }
        int answer = 0;
        for(int r=0;r<N;r++){
            for(int c=0;c<M;c++){
                answer += map[r][c];
                //System.out.print(map[r][c]);
            }
            //System.out.println();
        }
        System.out.println(answer);
    }
    public static void asFunction(){
        double sum = 0;
        int count =0;
        for(int r=0;r<N;r++){
            for(int c=0;c<M;c++){
                if(map[r][c]!= 0){
                    count++;
                    sum+=map[r][c];
                }
            }
        }
        double average = sum/count;
        for(int r=0;r<N;r++){
            for(int c=0;c<M;c++){
                if(map[r][c]!=0 &&  map[r][c] > average){
                    map[r][c] --;
                }else if(map[r][c] != 0 && map[r][c] < average){
                    map[r][c] ++;
                }
            }
        }


    }
    public static boolean check(int nr,int nc){
        if(nr<N && nr>=0 && nc>=0 && nc<M)return true;
        return false;
    }



    public static boolean bfsmap(){
        boolean flag = false;
        boolean [][] v = new boolean[N][M];
        for(int r=0;r<N;r++){
            for(int c=0;c<M;c++){
                if(v[r][c] || map[r][c]==0 ){
                    continue;
                }
                Queue<int[]> saveQ = new LinkedList<>();
                Queue<int[]> Q = new LinkedList<>();
                v[r][c] = true;
                int [] temparr = new int[2];
                temparr[0] = r;
                temparr[1] = c;
                int value = map[r][c];
                Q.add(temparr);
                saveQ.add(temparr);
                while(!Q.isEmpty()){
                    int[] q = Q.poll();
                    for(int d=0;d<4;d++){
                        int nr = q[0] + dr[d];
                        int nc = (q[1] + dc[d] + M)%M;
                        //System.out.println(nr + " : " + nc);
                        //System.out.println(value);
                        if(check(nr,nc) && !v[nr][nc] && map[nr][nc] == value){
                            //System.out.println("들어오긴하나");
                            v[nr][nc]=true;
                            int[] temparr2 = new int[2];
                            temparr2[0] = nr;
                            temparr2[1] = nc;
                            Q.add(temparr2);
                            saveQ.add(temparr2);
                        }
                    }
                }
                if(saveQ.size()>=2){
                    while(!saveQ.isEmpty()){
                        int[] dq = saveQ.poll();
                        map[dq[0]][dq[1]] = 0;
                        flag = true;

                    }
                }

            }
        }
        return flag;
    }

    public static void turnRight(int [] recarr){
        int temp = recarr[recarr.length - 1];
        for(int i=recarr.length-1;i>0;i--){
            recarr[i] = recarr[i-1];
        }
        recarr[0] = temp;
        return;
    }
    public static void turnLeft(int[] recarr){
        int temp = recarr[0];
        for(int i=0;i<recarr.length-1;i++){
            recarr[i] = recarr[i+1];
        }
        recarr[recarr.length-1] = temp;
        return;
    }

}