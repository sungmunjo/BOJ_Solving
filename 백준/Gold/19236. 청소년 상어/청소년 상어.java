import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class fishInfo{
    int index;
    int direction;
}


public class Main {
    static int [] dr = {-1,-1,0,1,1,1,0,-1};
    static int [] dc = {0,-1,-1,-1,0,1,1,1};

    static fishInfo[][] map;
    static int maxValue;



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        maxValue = 0;
        map = new fishInfo[4][4];
        for(int r=0;r<4;r++){
            st = new StringTokenizer(br.readLine());
            for(int c=0;c<4;c++){
                int fishindex = Integer.parseInt(st.nextToken());
                int fishdirection = Integer.parseInt(st.nextToken()) - 1;
                map[r][c] = new fishInfo();
                map[r][c].index = fishindex;
                map[r][c].direction = fishdirection;
            }
        }
        int startScore = 0;
        int sharkr=0;
        int sharkc =0;
        int sharkdir =map[0][0].direction;
        startScore = map[0][0].index;

        map[0][0].index = 0;
        fishInfo[][] startMap = new fishInfo[4][4];
        for(int r=0;r<4;r++){
            for(int c=0;c<4;c++){
                startMap[r][c] = new fishInfo();
                startMap[r][c].index = map[r][c].index;
                startMap[r][c].direction = map[r][c].direction;
            }
        }


        moveFish(startMap, sharkr,sharkc);
        per(startMap,sharkr,sharkc,sharkdir, startScore);
        System.out.println(maxValue);
    }
    private  static  boolean checkmap(int nr,int nc){
        if(nr < 4 && nr >= 0 && nc < 4 && nc >= 0)return true;
        return false;
    }
    private static void moveFish(fishInfo[][] mapinfo,int sharkr, int sharkc) {
        for(int i=1;i<17;i++){
            boolean ischange = false;
            for(int r=0;r<4;r++){
                for(int c=0;c<4;c++){
                    // 해당하는 생선이 있는 경우
                    if(mapinfo[r][c].index == i){
                        fishInfo tempfish = new fishInfo();
                        tempfish.index = mapinfo[r][c].index;
                        tempfish.direction = mapinfo[r][c].direction;
                        int count = 0;
                        boolean flag = true;
                        while(flag){
                            int nr = r + dr[tempfish.direction];
                            int nc = c + dc[tempfish.direction];
                            // 상어나 벽이 없는 경우.
                            if(checkmap(nr,nc) && (nr!=sharkr || nc!=sharkc)){
                                // 이동하는 곳에 물고기 있나?
                                if(mapinfo[nr][nc].index != 0){

                                    mapinfo[r][c] = mapinfo[nr][nc];
                                    mapinfo[nr][nc] = tempfish;
                                }else{
                                    mapinfo[nr][nc] = tempfish;
                                    fishInfo newfish = new fishInfo();

                                    newfish.index = 0;
                                    newfish.direction = 0;
                                    mapinfo[r][c] = newfish;
                                }
                                ischange = true;
                                flag = false;

                            }
                            //상어나 벽이 있는 경우.
                            else{
                                count++;
                                tempfish.direction = (tempfish.direction+1)%8;
                                //mapinfo[r][c].direction = (mapinfo[r][c].direction +1)%8;
                                if(count == 8){
                                    ischange = true;
                                    flag = false;
                                }
                            }
                        }

                    }
                    if(ischange)break;
                }
                if(ischange)break;
            }
            /*for(int r=0;r<4;r++){
                for(int c=0;c<4;c++){
                    if(r == sharkr && c == sharkc){
                        System.out.print("s ");
                    }else{
                        System.out.print(mapinfo[r][c].index + " ");
                    }
                }
                System.out.println();
            }*/
        }
    }

    private static void per(fishInfo[][] mapinfo ,int sharkr,int sharkc, int sharkdir, int score) {
        boolean[] ableMove = new boolean[4];
        boolean flag = false;
        for(int i=1;i<4;i++){
            int nr = sharkr + (i) * dr[sharkdir];
            int nc = sharkc + (i) * dc[sharkdir];
            if(checkmap(nr,nc) && mapinfo[nr][nc].index !=0){
                flag = true;
                ableMove[i] = true;
            }
        }
        if(!flag){
            if(maxValue < score){
                maxValue = score;
            }
            return;
        }
        for(int i=1;i<4;i++){
            int nr = sharkr + (i) * dr[sharkdir];
            int nc = sharkc + (i) * dc[sharkdir];
            if(ableMove[i]){
                fishInfo[][] newmap = new fishInfo[4][4];
                for(int r=0;r<4;r++){
                    for(int c=0;c<4;c++){
                        newmap[r][c] = mapinfo[r][c];
                    }
                }
                fishInfo zerofish = new fishInfo();
                zerofish.index = 0;
                zerofish.direction = 0;
                newmap[nr][nc] = zerofish;
                moveFish(newmap,nr,nc);
                per(newmap, nr,nc,mapinfo[nr][nc].direction,score+ mapinfo[nr][nc].index);
            }
        }

        /*for(int r=0;r<4;r++){
            for(int c=0;c<4;c++){
                if(r == sharkr && c == sharkc){
                    System.out.print("s ");
                }else{
                    System.out.print(mapinfo[r][c].index + " ");
                }
            }
            System.out.println();
        }*/

        return;

    }

}