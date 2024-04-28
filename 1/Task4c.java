
import java.util.Scanner;

public class Task4c {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int ans = 0;

        //---------------write your code BELOW this line only!--------------
        int n = scanner.nextInt();
        int range= n-2; // the range between n-1 and 2 including n-1
        ans=2+ (int)(Math.random()*range); // 2+ to move the min value of the range to 2
        
        
        



        //---------------write your code ABOVE this line only!--------------

        System.out.println(ans);
		scanner.close();
    }
}