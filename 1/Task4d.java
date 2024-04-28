import java.util.Scanner;
public class Task4d{
	
		 
	public static void main(String[] args) {
		
	
		Scanner scanner = new Scanner(System.in);
		int ans1 = 0; //s
		int ans2 = 0; //d
	        
		//---------------write your code BELOW this line only!--------------
		int n= scanner.nextInt(); 
		int s= 0;		
		n=n-1;
		while (n%2==0) { // the loop that finds out how many times n%2==0
			s=s+1; 
			n=n/2;
	        		
		}
		ans1= s;
		ans2= n;
	        		
	        
	 
	        //---------------write your code ABOVE this line only!--------------
	
		System.out.println(ans1);
		System.out.println(ans2);
		scanner.close();
	}
}