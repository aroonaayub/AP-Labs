import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * Created by User on 11/13/2016.
 */
public class carAssembly {
    public int NUM_LINE = 3;
    public int NUM_STATION = 4;
    public int T1[] = new int[4];
    public int T2[] = new int[4];
    public int T3[] = new int[4];
    int num;

    public int carAssembly_DP(int[][] a, int[][] t, int[] e, int[] x) {

        T1[0] = e[0] + a[0][0]; // time taken to leave first station in line 1
        T2[0] = e[1] + a[1][0]; // time taken to leave first station in line 2
        T3[0] = e[2] + a[2][0]; // time taken to leave first station in line 3

        // Fill tables T1[] and T2[] using the above given recursive relations
        for (int i = 1; i < NUM_STATION; ++i) {
            T1[i] = min(T1[i - 1] + a[0][i], T2[i - 1] + t[1][i] + a[0][i]);
            T2[i] = min3((T2[i - 1] + a[1][i]), (T1[i - 1] + t[0][i] + a[1][i]), (T3[i - 1] + a[1][i] + t[3][i]));
            T3[i] = min(T3[i - 1] + a[2][i], T2[i - 1] + t[2][i] + a[2][i]);

           System.out.println("Assembly Lane1 -- " + T1[i]);
           System.out.println("Assembly Lane2 -- " + T2[i]);
           System.out.println("Assembly Lane3 -- " + T3[i]);
           System.out.print("\n");
            
        }

        // Consider exit times and return minimum
        num = min3(T1[NUM_STATION - 1] + x[0], T2[NUM_STATION - 1] + x[1],T3[NUM_STATION - 1] + x[2]);
        return num;
    }

    public static int min(int a, int b) {
        return a < b ? a : b;
    }

    public static int min3(int a, int b, int c) {
        int min = a;
        if (min > b) min = b;
        if (min > c) min = c;
        return min;
    }

    public int carAssembly_R_part(int[][] a, int[][] t, int[] e, int[] x, int n, int j){
        if(n == 0){
            return e[j] + a[j][0];
        }

        int T1 = Integer.MAX_VALUE;
        int T2 = Integer.MAX_VALUE;
        int T3 = Integer.MAX_VALUE;
        if(j == 0){
            T1 =Math.min(carAssembly_R_part(a, t, e, x, n-1, 0) + a[0][n],
                    carAssembly_R_part(a, t, e, x, n-1, 1) + t[1][n] + a[0][n]);

        }else if(j == 1){
            T2 = min3(carAssembly_R_part(a, t, e, x, n-1, 1) + a[1][n],
                    carAssembly_R_part(a, t, e, x, n-1, 0) + t[0][n] + a[1][n],
                    carAssembly_R_part(a, t, e, x, n-1, 2) + t[2][n] + a[1][n]);

        }else if(j == 2){
            T3 =Math.min(carAssembly_R_part(a, t, e, x, n-1, 2) + a[2][n],
                    carAssembly_R_part(a, t, e, x, n-1, 1) + t[1][n] + a[2][n]);


        }
        

        return min3(T1,T2,T3);
    }
    
    
    public int carAssembly_R(int[][] a, int[][] t, int[] e, int[] x){
        int n = a[0].length-1;
        return min3(carAssembly_R_part(a,t, e, x, n, 0) + x[0],
                carAssembly_R_part(a,t, e, x, n, 1) + x[1],carAssembly_R_part(a,t, e, x, n, 2) + x[2]);
    }


}
