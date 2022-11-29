import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Shark{
    int r,c;
    int dir;
    int [][]orders;

    boolean isAlive;

}

public class Main {
    static int []dr = {-1,1,0,0};
    static int [] dc = {0,0,-1,1};
    static int N,M,K;

    static int [][][] map;

    static Shark[] sharks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int [N][N][2];
        sharks = new Shark[M+1];

        for(int r=0;r<N;r++){
            st = new StringTokenizer(br.readLine());
            for(int c=0;c<N;c++){
                int recValue = Integer.parseInt(st.nextToken());
                if(recValue != 0){
                    sharks[recValue] = new Shark();
                    sharks[recValue].r = r;
                    sharks[recValue].c = c;
                    map[r][c][0] = K;
                    map[r][c][1] = recValue;
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        for(int i=1;i<sharks.length;i++){
            sharks[i].dir = Integer.parseInt(st.nextToken());
        }
        for(int i=1;i<sharks.length;i++){
            sharks[i].isAlive = true;
            sharks[i].orders = new int [4][4];
            for(int j=0;j<4;j++){
                st = new StringTokenizer(br.readLine());
                for(int k=0;k<4;k++){

                    sharks[i].orders[j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }
        int answer = dosimul();
        System.out.println(answer);
    }

    private static boolean check(int nr, int nc){
        if(nr>=0 && nr<N && nc>=0 && nc<N)return true;
        return false;
    }
    private static int dosimul() {
        boolean flag = true;
        int count = 0;
        while(flag){
            //움직이자
            for(int i=1;i<sharks.length;i++){
                int r = sharks[i].r;
                int c = sharks[i].c;
                boolean nosit = true;
                for(int d=0;d<4;d++){
                    int nr = r + dr[sharks[i].orders[sharks[i].dir-1][d]-1];
                    int nc = c + dc[sharks[i].orders[sharks[i].dir-1][d]-1];
                    if(check(nr,nc) && map[nr][nc][0] == 0 && map[nr][nc][1] == 0){
                        sharks[i].r = nr;
                        sharks[i].c = nc;
                        sharks[i].dir = sharks[i].orders[sharks[i].dir-1][d];
                        nosit = false;
                        break;
                    }
                }
                if(nosit){
                    for(int d=0;d<4;d++){
                        int nr = r +dr[sharks[i].orders[sharks[i].dir-1][d]-1];
                        int nc = c+ dc[sharks[i].orders[sharks[i].dir-1][d]-1];
                        if(check(nr,nc) && map[nr][nc][1] == i){
                            sharks[i].r = nr;
                            sharks[i].c = nc;
                            sharks[i].dir = sharks[i].orders[sharks[i].dir-1][d];
                            break;
                        }
                    }
                }
            }

            //잡아먹자
            for(int i=1;i< sharks.length;i++){
                if(!sharks[i].isAlive)continue;
                int r = sharks[i].r;
                int c = sharks[i].c;
                for(int j = i + 1;j < sharks.length;j++){
                    if(sharks[j].r == r && sharks[j].c == c){
                        sharks[j].isAlive = false;
                    }
                }
            }


            //이전 숫자 줄이자
            for(int r=0;r<map.length;r++){
                for(int c=0;c<map[0].length;c++){
                    if(map[r][c][0]!= 0){
                        map[r][c][0]--;
                    }
                    if(map[r][c][0] == 0){
                        map[r][c][1] =0;
                    }
                }
            }

            // 새로운 가스 뿌리자
            for(int i=1;i<sharks.length;i++){
                if(sharks[i].isAlive){
                    int r = sharks[i].r;
                    int c = sharks[i].c;
                    map[r][c][1] = i;
                    map[r][c][0] = K;
                }
            }
            /*for(int r=0;r<map.length;r++){
                for(int c=0;c<map[0].length;c++){
                    System.out.print(map[r][c][0]);
                }
                System.out.println();
            }
            System.out.println();*/
            count++;
            boolean someShark = false;
            for(int i=2;i<sharks.length;i++){
                if(sharks[i].isAlive){
                    someShark = true;
                    break;
                }
            }
            if(!someShark){
                flag = false;
            }
            if(count>1000){
                break;
            }
        }
        if(count==1001){
            count = -1;
        }
        return count;
    }
}