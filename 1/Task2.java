
import java.util.Scanner;

public class Task2 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int ans = 0;

        //---------------write your code BELOW this line only!--------------
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        double n = Math.random();
        int range = b-a+1; // the range between a and b including b
        
        ans =a+(int)(n*(range)); // (a+) == move the min value of the range to a
        
        

        //---------------write your code ABOVE this line only!--------------
        System.out.println(ans);
		scanner.close();
    }
}