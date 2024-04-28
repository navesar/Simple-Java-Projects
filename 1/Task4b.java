
import java.util.Scanner;

public class Task4b {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int ans = 0;

        //---------------write your code BELOW this line only!--------------
        int n= scanner.nextInt();
        int i = 2;
        
   
        while(i<=n) { // the loop that goes over every number until n
        	int j =2;
        	boolean isPrime= true;
        	while (j<i & isPrime) { // the loop that checks if i is prime
        		if (i%j==0)
        			isPrime= false;
        		j=j+1;
        		
      
        	}
        	if (isPrime)
    			ans=ans+1;
        	i=i+1;	
        }
        	



        //---------------write your code ABOVE this line only!--------------

        System.out.println(ans);    
		scanner.close();    
    }
}