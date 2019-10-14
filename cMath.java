import java.util.ArrayList;

public class cMath{
    private static final int MAX = 10000000;
    private static boolean [] prime = new boolean[MAX/2];
    private static int [] phi = new int[MAX];
    private static int [] nprime = new int[664579];
    private static boolean primeCalc = false;
    private static boolean phiCalc = false;

    public static void siv()
    {
        primeCalc = true;
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
        if(!primeCalc) siv();
        return (n==2 || (n>2 && n%2==1 && prime[n/2]==false));
    }

    public static ArrayList<Integer> factorize(int n)
    {
        ArrayList<Integer> factorized = new ArrayList<Integer>();
        if(!primeCalc) siv();
        for(int i = 0; nprime[i]*nprime[i]<=n; i++){
            while(n%nprime[i]==0){
                factorized.add(nprime[i]);
                n /= nprime[i];
            }
        }
        if(n!=1) factorized.add(n);
        return factorized;

    }

    public static void phisiv()
    {
        phiCalc = true;
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
        if(!phiCalc) phisiv();
        return phi[n];
    }
}
