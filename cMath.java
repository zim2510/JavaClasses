import java.util.ArrayList;
import java.util.Arrays;

public class cMath{
    private static final int MAX = 10000000;

    private static boolean sivInit = false;
    private static boolean [] prime = new boolean[MAX/2];
    private static int [] nprime = new int[664579];

    private static void siv()
    {
        sivInit = true;
        for(int i = 3; i*i<MAX; i+=2){
            if(!prime[i/2]) for(int x = i * i; x<MAX; x += 2 * i){
                prime[x/2] = true;
            }
        }
        int cntofprime = 0;
        nprime[cntofprime++] = 2;
        for(int i = 1; i<MAX/2; i++){
            if(!prime[i]) nprime[cntofprime++] = i * 2 + 1;
        }
    }

    public static boolean isPrime(int n)
    {
        if(!sivInit) siv();
        return (n==2 || (n>2 && n%2==1 && prime[n/2]==false));
    }

    public static ArrayList<Integer> factorize(int n)
    {
        ArrayList<Integer> factorized = new ArrayList();
        if(!sivInit) siv();
        for(int i = 0; nprime[i]*nprime[i]<=n; i++){
            while(n%nprime[i]==0){
                factorized.add(nprime[i]);
                n /= nprime[i];
            }
        }
        if(n!=1) factorized.add(n);
        return factorized;

    }

    private static boolean phiInit = false;
    private static int [] phi = new int[MAX];

    private static void phisiv()
    {
        phiInit = true;
        phi[0] = 1;
        for(int i = 1; i<MAX; i++) phi[i] = i;
        for(int i = 2; i<MAX; i++){
            if(phi[i]==i){
                for(int x = i; x<MAX; x += i){
                    phi[x] -= (phi[x]/i);
                }
            }
        }
    }

    public static int ephi(int n)
    {
        if(!phiInit) phisiv();
        return phi[n];
    }

    private static boolean ncrInit = false;
    private static long [][] ncr = new long [1005][1005];

    private static long nCrCalc(int n, int r, int MOD)
    {

        if(ncr[n][r]!=-1) return ncr[n][r];
        if(n==r || r==0) return 1;
        return ncr[n][r] = ((nCrCalc(n-1, r, MOD) + nCrCalc(n-1, r-1, MOD)))%MOD;
    }

    public static long nCr(int n, int r, int MOD){
        if(!ncrInit){
            for(long[] x: ncr){
                Arrays.fill(x, -1);
            }

            ncrInit = true;
        }
        return nCrCalc(n, r, MOD);
    }

    private static boolean nprInit = false;
    private static long [][] npr = new long [1005][1005];

    private static long nPrCalc(int n, int r, int MOD)
    {

        if(npr[n][r]!=-1) return npr[n][r];
        if(n==0 || r==0) return 1;
        return npr[n][r] = ((n*(nPrCalc(n-1, r-1, MOD))))%MOD;
    }

    public static long nPr(int n, int r, int MOD){
        if(!nprInit){
            for(long[] x: npr){
                Arrays.fill(x, -1);
            }

            nprInit = true;
        }
        return nPrCalc(n, r, MOD);
    }
}
