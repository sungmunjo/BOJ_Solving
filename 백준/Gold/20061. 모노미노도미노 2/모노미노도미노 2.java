import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static boolean [][] Red;
    static boolean [][] Green;
    static boolean [][] Blue;
    static int score = 0;
    static int count = 0;
    static int [][][] blocks = {{{0,0}},{{0,0},{0,1}},{{0,0}, {1,0}}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        Red = new boolean [4][4];
        Green = new boolean [6][4];
        Blue = new boolean [4][6];


        for(int tc=0;tc<N;tc++){
            int t,x,y;
            st = new StringTokenizer(br.readLine());
            t = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            int [][] selblock = blocks[t-1];

            doBlue(selblock ,x);
            doGreen(selblock ,y);

            deleteBlue();
            deleteGreen();

            asBlue();
            asGreen();
        }
        for(int i=0;i<Blue.length;i++){
            for(int j=0;j<Blue[i].length;j++){
                //System.out.print(Blue[i][j] + " ");
                if(Blue[i][j]){
                    count++;
                }
            }
            //System.out.println();
        }
        for(int i=0;i<Green.length;i++){
            for(int j=0;j<Green[i].length;j++){
                //System.out.print(Green[i][j] + " ");
                if(Green[i][j]){
                    count++;
                }
            }
            //System.out.println();
        }
        System.out.println(score);
        System.out.println(count);

    }

    public static void asBlue(){
        int topull = 0;
        for(int i=0;i<2;i++){
            boolean flag = false;
            for(int j=0;j<Blue.length;j++){
                if(Blue[j][i]){
                    flag = true;
                    break;
                }
            }
            if(flag){
                topull++;
            }
        }
        for(int i=0;i<topull;i++){
            for(int j = Blue[0].length-1; j>0;j--){
                for(int k=0;k<Blue.length;k++){
                    Blue[k][j] = Blue[k][j-1];
                }
            }
        }
        for(int i=0;i<2;i++){
            for(int j=0;j<Blue.length;j++){
                Blue[j][i] = false;
            }
        }
    }

    public static void asGreen(){
        int topull = 0;
        for(int i=0;i<2;i++){
            boolean flag = false;
            for(int j=0;j<Green[0].length;j++){
                if(Green[i][j]){
                    flag = true;
                    break;
                }
            }
            if(flag){
                topull++;
            }
        }
        for(int i=0;i<topull;i++){
            for(int j = Green.length-1; j>0;j--){
                for(int k=0;k<Green[0].length;k++){
                    Green[j][k] = Green[j-1][k];
                }
            }
        }
        for(int i = 0;i<2;i++){
            for(int j=0;j<Green[0].length;j++){
                Green[i][j] = false;
            }
        }
    }

    public static  void deleteBlue(){
        for(int i = Blue[0].length-1;i>=0;i--){
            boolean flag = true;
            for(int j=0;j<Blue.length;j++){
                if(Blue[j][i] == false){
                    flag = false;
                    break;
                }
            }
            if(flag){
                score++;
                int temp = i;
                for(int k=temp;k>0;k--){
                    for(int j=0;j<Blue.length;j++){
                        Blue[j][k] = Blue[j][k-1];
                    }
                }
                i++;
            }
        }
    }
    public static void deleteGreen(){
        for(int i = Green.length-1;i>=0;i--){
            boolean flag = true;
            for(int j=0;j<Green[0].length;j++){
                if(Green[i][j] == false){
                    flag = false;
                    break;
                }
            }
            if(flag){
                score++;
                int temp = i;
                for(int k=temp;k>0;k--){
                    for(int j=0;j<Green[0].length;j++){
                        Green[k][j] = Green[k-1][j];
                    }
                }
                i++;
            }
        }
    }
    public static boolean checkBlue(int nr,int nc){
        if(nr<4 && nr>=0 && nc<6 && nc >=0){
            return true;
        }
        return false;
    }
    public static boolean checkGreen(int nr,int nc){
        if(nr<6 && nr>=0 && nc<4 && nc >=0){
            return true;
        }
        return false;
    }
    public static void doBlue(int[][] selBlock, int x){
        for(int i=0;i<Blue[0].length;i++){
            boolean cando = true;
            for(int j=0;j<selBlock.length;j++){
                int nr = x + selBlock[j][0];
                int nc = i + selBlock[j][1];
                if(!checkBlue(nr,nc) || Blue[nr][nc] == true){
                    cando = false;
                    break;
                }
            }
            if(!cando){
                for(int j=0;j<selBlock.length;j++){
                    int nr = x + selBlock[j][0];
                    int nc = i - 1 + selBlock[j][1];
                    Blue[nr][nc] = true;
                }
                return;
            }

        }
        for(int j=0;j<selBlock.length;j++){
            int nr = x + selBlock[j][0];
            int nc = Blue[0].length-1;
            Blue[nr][nc] = true;
        }
    }

    public static void doGreen(int[][] selBlock, int y){
        for(int i=0;i<Green.length;i++){
            boolean cando = true;
            for(int j=0;j<selBlock.length;j++){
                int nr = i + selBlock[j][0];
                int nc = y + selBlock[j][1];
                if(!checkGreen(nr,nc) || Green[nr][nc] == true){
                    cando = false;
                    break;
                }
            }
            if(!cando){
                for(int j=0;j<selBlock.length;j++){
                    int nr = i - 1 + selBlock[j][0];
                    int nc = y + selBlock[j][1];
                    Green[nr][nc] = true;
                }
                return;
            }

        }
        for(int j=0;j<selBlock.length;j++){
            int nr = Green.length-1;
            int nc = y + selBlock[j][1];
            Green[nr][nc] = true;
        }
    }
}