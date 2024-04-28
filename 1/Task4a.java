
import java.util.Scanner;

public class Task4a {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean ans = true;

        //---------------write your code BELOW this line only!--------------
        int n=   scanner.nextInt();
        int i = 2;
   
        while(i<n & ans) { 
        	if (n%i==0) // if i divides n
        		ans = false; // n is not prime
        	i=i+1;
        }
        		
        

        //---------------write your code ABOVE this line only!--------------

        System.out.println(ans);
		scanner.close();
    }
}