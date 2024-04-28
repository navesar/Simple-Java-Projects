
import java.math.BigInteger;
import java.util.Random;

class Assignment3BigInteger{

	public static BigInteger sumSmaller(BigInteger n){
        BigInteger sum =  new BigInteger("0");
        //Task 1.1
        for(BigInteger bi = BigInteger.ZERO; bi.compareTo(n)<0; bi=bi.add(BigInteger.ONE)) {
        	sum = sum.add(bi);
        	
        }
        
        return sum;
    }

	public static void printRandoms(int n){
        //Task 1.2
		//assumes n>=0
		 for(int i = 0; i<n; i=i+1) { //generate a new random number and print it
			 Random rand= new  Random();
			 int randNum = rand.nextInt();
			 System.out.println(randNum);
		 }
		 
		 
		
    }

	public static   boolean isPrime(BigInteger n){
		//assumes n != null & n>=0
        boolean ans= true;
        //Task 1.3
        for(BigInteger bi = BigInteger.TWO; (bi.pow(2)).compareTo(n)<=0 & ans; bi=bi.add(BigInteger.ONE)) {
        	if((n.mod(bi).equals(BigInteger.ZERO))) //bi divides n
        			ans = false;//n is not prime
        }
        
        return ans;
    }

	public static BigInteger randomPrime(int n){
    	//assumes n>1
        BigInteger randBig = new BigInteger("0");
        //Task 1.4
        Random rnd = new Random();
        BigInteger temp = new BigInteger(n+1,rnd);
        while(!isPrime(temp)|!(temp.compareTo(BigInteger.TWO)>=0)|
        !(temp.compareTo((BigInteger.TWO).pow(n))<0)) {
        	temp = new BigInteger(n,rnd);
        }
        randBig=temp;
        return randBig;
    }

}