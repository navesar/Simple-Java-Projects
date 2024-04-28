
import java.util.Scanner;

public class Task4f {

    public static void main(String[] args) {
        
		Scanner scanner = new Scanner(System.in);
        boolean ans = true;

        //---------------write your code BELOW this line only!--------------
        int n = scanner.nextInt();
        int s = scanner.nextInt();
        int d = scanner.nextInt();
        int k = scanner.nextInt();
        
        for (int l =0; l<k; l=l+1) { // the loop that repeat k times
        	int b =2+(int)(Math.random()*(n-2)); // random number between 2 and n-1 included
        	
        	//check if b is a divider for n
        	// calculate (b^d)%n 
            int j=1;
            int bBdMODn= b; 
            while (j<d) { //the loop that calculate (b^d)%n
            	bBdMODn= (bBdMODn%n*b%n)%n;
            	j=j+1;
            }
            boolean condition1= true;
            if (bBdMODn == 1) //b^d = 1mod(n)
            	condition1 =false;
            
            
            boolean condition2 = true; 
            for (int i =0; i<=s-1 &condition2 ; i=i+1) { //the loop that checks condition 2 
            // calculate 2^i - like Task3a
            	int twoBi= 1;
            	int e= 1;
            	while (e<=i) { //the loop that calculate 2^n
            		twoBi= twoBi*2;
            		e=e+1;
            	}
            	
            	// caculate b^((2^i)*d)%n
            	
            	int twoBiD= twoBi*d;
            	int bBdtwoBiMODn = b;
            	for (int x=1; x<twoBiD; x=x+1) {
            		bBdtwoBiMODn=((bBdtwoBiMODn%n)*(b%n))%n;
            	}
            	
            	
            	
            	if (bBdtwoBiMODn==n-1) // b^((2^i)*d)%n = (n-1)mod(n)
            		condition2= false;
            	
            }
        
            
            if (condition1 & condition2)  // n is not prime
            	ans =false; 
        }
        
        //---------------write your code ABOVE this line only!--------------

        System.out.println(ans);
		scanner.close();
    
    }
}