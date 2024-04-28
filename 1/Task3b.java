
import java.util.Scanner;


public class Task3b {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int ans = 0;
        

        //---------------write your code BELOW this line only!--------------
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int i = 1;
        int l= 2%k;
	     while (i<n) {
	    	 l= (l*2%k)%k; // (a*b)%k = ((a%k)*(b%k)%k
	    	 i=i+1;
	     }
	     ans= l;
        //---------------write your code ABOVE this line only!--------------

        System.out.println(ans);
		scanner.close();
    }
}