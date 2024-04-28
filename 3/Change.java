
class Change{

	public static boolean change(int [] coins, int n){
        boolean ans = false;
        //Task 2.1
        ans=change(coins,n,0);
        	
        return ans;
    }

	public static boolean change(int[] coins,int n, int i) {
		if (n==0)
			return true;
		else {
			if (i>=coins.length|n<0)
				return false;
			else
				return (change(coins,n-coins[i],i)|| //use the current coin again
						change(coins,n-coins[i],i+1)|| //use the current coin and go to the next coin 
						change(coins,n,i+1));//dont use current coin and go to the next coin
		}
	}

	public static boolean changeLimited(int[] coins, int n, int numOfCoinsToUse){
        boolean ans = false;
        //Task 2.2
        ans =changeLimited(coins,n,numOfCoinsToUse,0);
        return ans;
    }
	
	
	public static boolean changeLimited(int[] coins,int n,int numOfCoinsToUse, int i) {
		if (n==0&numOfCoinsToUse>=0)
			return true;
		else {
			if (i>=coins.length|n<0)
				return false;
			else 
				return ((changeLimited(coins,n-coins[i],numOfCoinsToUse-1,i))|| //keep using the same coin
						changeLimited(coins,n-coins[i],numOfCoinsToUse-1,i+1)|| //use the current coin and go to the next one
						changeLimited(coins,n,numOfCoinsToUse,i+1)); // dont use the current coin and go to the next one
			
		}
	
	
	}

	public static void printChangeLimited(int[] coins, int n, int numOfCoinsToUse){
        //Task 2.3
	  printChangeLimited(coins,n,numOfCoinsToUse,0,"");
	  
    }
	public static boolean printChangeLimited(int[] coins,int n,int numOfCoinsToUse, int i,String acc) {
		boolean ans=false; //there is no solution
		if(n==0 &numOfCoinsToUse>=0){
			System.out.println(acc); //print the solution
			ans=true; 
		}
		else {
			if (acc.length()>0 && acc.charAt(acc.length()-1)!=',')//check if there is a "," already at the end of acc and add ","if there isnt
				acc=acc+",";
		}		  
		if(n>0&i<coins.length&!ans) {
			ans= (printChangeLimited(coins,n-coins[i],numOfCoinsToUse-1,i,acc+coins[i])|| //pick one of the solutions
					printChangeLimited(coins,n-coins[i],numOfCoinsToUse-1,i+1,acc+coins[i])||
					printChangeLimited(coins,n,numOfCoinsToUse,i+1,acc));
		}
		return ans;
	}

	public static int countChangeLimited(int[] coins, int n, int numOfCoinsToUse){
        int ans = 0;
        //Task 2.4
        ans = changeLimited(coins,n,numOfCoinsToUse,0,"diffrent signature requaired");
        return ans;
    }
	
	public static int changeLimited(int[] coins,int n,int numOfCoinsToUse, int i,String s) {
		if (n==0&numOfCoinsToUse>=0)
			return 1; //found an option
		else {
			if (i>=coins.length|n<0)
				return 0; //not an option
			else 
				return changeLimited(coins,n-coins[i],numOfCoinsToUse-1,i,s)+ //keep using the same coin
						changeLimited(coins,n,numOfCoinsToUse,i+1,s); //dont use the current coin and go to the next coin
		}
		
	}

	public static void printAllChangeLimited(int[] coins, int n, int numOfCoinsToUse){
        //Task 2.5
	 printChangeLimited(coins,n,numOfCoinsToUse,0,"","",numOfCoinsToUse);
    }
 
 
 public static String printChangeLimited(int[] coins,int n,int numOfCoinsToUse, int i,String acc,String lastSol,int remember) {
	   
	  if(n==0 &numOfCoinsToUse>=0&acc.length()<=remember&!acc.equals(lastSol)) {
		  System.out.println(acc); //print the solution
		  lastSol=acc;
		  return lastSol; 
	  }
	  
 	else {
 		if (acc.length()>0 && acc.charAt(acc.length()-1)!=','){//check if there is a "," already at the end of acc and add ","if there isnt
			  acc=acc+",";
			  remember=remember+1; //add 1 to compensate the fact that acc contains numbers AND commas
 		}
 		if(n>0&i<coins.length) {
 			lastSol =printChangeLimited(coins,n-coins[i],numOfCoinsToUse,i,acc+coins[i],lastSol,remember);
 			lastSol= printChangeLimited(coins,n-coins[i],numOfCoinsToUse,i+1,acc+coins[i],lastSol,remember);
 			lastSol= printChangeLimited(coins,n,numOfCoinsToUse,i+1,acc,lastSol,remember);
 		}
 		return lastSol;
 	}
 		
	 


 }

}

