
import java.util.Scanner;

public class Task3a {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int ans = 0;

        //---------------write your code BELOW this line only!--------------
        int n = scanner.nextInt();
        int i =0;
        ans =1; //covering the option that n = 0
        while (i<n) { //the loop that calculate 2^n
        	ans= ans*2;
        	i=i+1;
        	
        }
        	



        //---------------write your code ABOVE this line only!--------------
        System.out.println(ans);
		scanner.close();
    }
}