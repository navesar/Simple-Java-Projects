
public class Assignment2 {

	/*-----------------------
	 *| Part A - tasks 1-11 |
	 * ----------------------*/
	
	// task 1
	public static boolean isSquareMatrix(boolean[][] matrix) {
		if (matrix == null | matrix.length==0)
			return false;
		
		boolean ans= true; //the matrix is square
		
		int M= 0; // the number of the 1st D arrays
		for (int i = 0; i<matrix.length; i=i+1) { //Calculate how many 1st D arrays exists
			M=M+1;
		}
		
		for (int i = 0;i<matrix.length; i=i+1) { // calculate how many 2nd D arrays there are in each 1st D array
			int N=0;// the number of 2nd D arrays in each 1st D array
			for (int j = 0; j<matrix[i].length; j=j+1) {
				N=N+1;
			}
			
			if (N!=M) // the total number of 1st D arrays is not equal to the number of 2nd D arrays in each 1st D array
				ans = false; // the matrix is not square
		}
		
		return ans;
	}
	
	// task 2
	public static boolean isSymmetricMatrix(boolean[][] matrix) { //assumes the matrix is square
		boolean ans= true; // the matrix is symmetric
		
		for (int i=0; i<matrix.length & ans; i=i+1) { // goes over every 1st D array
			for (int j = 0; j<matrix[i].length & ans; j=j+1) { //goes over every 2nd D array
				if (matrix[i][j]!=matrix[j][i])
					ans= false; // the matrix is not symmetric
			}
		}
		return ans;
		
	}

	// task 3
	public static boolean isAntiReflexiveMatrix(boolean[][] matrix) { //assumes matrix is square
		boolean isReflexive = true; // the matrix is reflexive
		for (int i = 0 ; i<matrix.length & isReflexive; i=i+1) {
			if (matrix[i][i] == true) // matrix[i][i] has to be false according to the definition 
				isReflexive= false;
		}
		return isReflexive;
	}
	
	// task 4
	public static boolean isLegalInstance(boolean[][] matrix) {
		if (isSquareMatrix(matrix) & isSymmetricMatrix(matrix) & isAntiReflexiveMatrix(matrix) )
			return true;
		else
			return false;
					
	}
	
	// task 5
	public static boolean isPermutation(int[] array) { // assumes array != null
		int[] weSaw = new int[array.length];// a new array that will contain every number we see until this point;
		boolean isP = true; // the array is permutation
		for (int i = 0; i<array.length & isP; i=i+1) {// go over every number in the array
			if (array[i] >  array.length-1) // if the number is larger than the length of the array -1
				isP = false; // array is not permutation
			else // the number is in the accepted range
				weSaw[i]= array[i]; //add the number to the array of numbers we saw so far
			for (int j = 0; j<i; j=j+1) { // the loop that checks if the number has been seen before
				if (weSaw[j] == array[i])
					isP = false;
			}
						
		}
		return isP;
	}
	
	// task 6
	public static boolean hasLegalSteps(boolean[][] flights, int[] tour) {
		// assumes flights is legal on n>0 cities
		// assumes tour.length = n
		boolean hasLegalSteps= true; // every two following cities has a airline
		int firstCity= tour[0]; 
		int lastCity = tour[tour.length-1];
		if (flights[lastCity][firstCity]==false) // if the last city and the first city dont have a airline
			hasLegalSteps= false;
				
		for (int i = 0 ; i < tour.length-1 & hasLegalSteps; i=i+1) {
			int cityA= tour[i]; //the city that we are leaving at this stage
			int cityB= tour[i+1]; // the city that we are flying to at this stage
			if (flights[cityA][cityB]== false) // the city we are leaving and the city that we want to fly to dont have an airline
				hasLegalSteps= false;
		}
		return hasLegalSteps;
	}
	
	// task 7
	public static boolean isSolution(boolean[][] flights, int[] tour) {
		//assumes flights is legal
		if (tour.length != flights.length) //condition 1 in definition 2
			throw new IllegalArgumentException("tour length does not match the number of cities");
		if (!isPermutation(tour) | !hasLegalSteps(flights,tour)) // condition 2 or condition 3 in definition 2 doesnt happen
			return false;
		else
			return true; // array tour fulfill definition 2 for flights
		
	}
	
	// task 8
	public static boolean evaluate(int[][] cnf, boolean[] assign) {
		// assumes cnf is legal
		// assumes assign is legal
		// assumes that assign[k] is the assign for the literal k  
		
		for (int i = 0; i<cnf.length; i=i+1) { //go over every clause in cnf
			boolean clauseIsTrue= false; //base assumption - clause if false
			for (int j = 0 ; j<cnf[i].length & !clauseIsTrue; j=j+1) {// goes over every literal
				int absValue;
				if (cnf[i][j]>0) { // the literal is positive
					if (assign[cnf[i][j]]) //the assign to the literal is true
						clauseIsTrue = true; // the literal is true and therefore the whole clause is true
				}
				else { // the literal is negative = not(xi)
					absValue= -cnf[i][j]; // see what the absolute value is so we can go to the index of the assign
					if(!assign[absValue]) // the assign is false
						clauseIsTrue = true; 
				}
					
			}
			if (!clauseIsTrue) // after we went over every literal in the clause and didnt find an assign that make one of them true
				return false; //the clause if false and therefor the assign doesnt make the cnf true
		}
		return true; // every clause is true
	}
	
	// task 9
	public static int[][] atLeastOne(int[] lits) { 
		//assumes lits != null & lits.length > 0 & lits contain ints > 0
		//the cnf will contain one clause with all the literals
		int[][] cnf= new int [1][];
		cnf[0]= lits; 

		return cnf;
	}

	// task 10
	public static int[][] atMostOne(int[] lits) {
		//assumes lits != null & lits.length > 0 & lits contain ints > 0
		int numOfLits= lits.length;
		int numOfClauses= (numOfLits*(numOfLits-1))/2; //calculate how many clauses with 2 literals we can make
		int[][] cnf = new int[numOfClauses][2];
		int cnfIndex = 0;
		for (int i = 0 ; i<lits.length-1; i=i+1) { //create all the possible couples of literals 
			for (int j = i+1; j<lits.length; j=j+1, cnfIndex = cnfIndex+1) {
				cnf[cnfIndex][0]=-lits[i]; //reverse literal value
				cnf[cnfIndex][1]= -lits[j];
				
			}
			
		}
		return cnf;
	}
	
	// task 11
	public static int[][] exactlyOne(int[] lits) {
		//assumes lits != null & lits.length > 0 & lits contain ints > 0
		//at least one+ at most one
		int numOfLits= lits.length;
		int numOfClauses= (numOfLits*(numOfLits-1))/2; //calculate how many clauses with 2 literals we can make
		numOfClauses= numOfClauses+1; //add a clause for at least one option
		int[][] cnf = new int[numOfClauses][];
		int cnfIndex = 0;
		for (int i = 0 ; i<lits.length-1; i=i+1) { //create all the possible couples of literals 
			for (int j = i+1; j<lits.length; j=j+1, cnfIndex = cnfIndex+1) {
				int[] clause= {-lits[i],-lits[j]};
				cnf[cnfIndex]= clause;
				
			}
			
		}
		cnf[cnfIndex]= lits; // add the at least one case
		return cnf;
	}
	/*------------------------
	 *| Part B - tasks 12-20 |
	 * -----------------------*/
	
	// task 12a
	public static int map(int i, int j, int n) {
		// assumes n>0
		//assumes 0<=i & 0<=j & i<n & j<n
		
		return (n*i+j)+1;
	}
	
	// task 12b
	public static int[] reverseMap(int k, int n) {
		//assumes n>0
		//assumes 1<=k & 1<=n*n
		int i=(k-1)/n; //the number of steps that we took
		int j=(k-1)%n; // the number of the city in the current step
		int[] a= {i,j};
		return a; 
		
	}
	
	// task 13
	public static int[][] oneCityInEachStep(int n) {
		//assumes n>0
		int numOfLitsPerStep= n; //the number of cities
		int numOfClauses= ((numOfLitsPerStep*(numOfLitsPerStep-1))/2+1)*n;
		
		int[][] cnf = new int[numOfClauses][];
		int cnfIndex= 0;
		
		for(int i = 0; i<n; i=i+1) { //go over evrery step
			int[] lits= new int [n];
			for (int j = 0; j<n; j=j+1) { //go over every city in each step
				
				lits[j] = map(i,j,n); //generate the value 
			
			}
			int[][] cnfForI= exactlyOne(lits);
			for (int x=0; x<cnfForI.length; x=x+1,cnfIndex=cnfIndex+1) { //put the cnf of this step into the global cnf
				cnf[cnfIndex]= cnfForI[x];
			}
			
			
		}
		return cnf;
	}

	// task 14
	public static int[][] eachCityIsVisitedOnce(int n) {
		//assumes n>0
		int numOfLitsPerCity= n; // rows in the square matrix
		int numOfClauses= ((numOfLitsPerCity*(numOfLitsPerCity-1))/2+1)*n;
		
		int[][] cnf = new int[numOfClauses][];
		int cnfIndex= 0;
		
		for(int i = 0; i<n; i=i+1) { 
			int[] lits= new int [n];
			for (int j = 0; j<n; j=j+1) {
				
				lits[j] = map(j,i,n);
			
			}
			int[][] cnfForI= exactlyOne(lits);
			for (int x=0; x<cnfForI.length; x=x+1,cnfIndex=cnfIndex+1) {
				cnf[cnfIndex]= cnfForI[x];
			}
			
			
		}
		return cnf;
	}
	
	// task 15
	public static int[][] fixSourceCity(int n) {
		//assumes n>0
		int[][]cnf= {{1}}; //force the cnf to make 1 true
		return cnf;
	}
	
	// task 16
	public static int[][] noIllegalSteps(boolean[][] flights) {
		//assumes n>0 
		//assumes flights is legal
		int n = flights.length;
		int cnt =0;
		for(int l=0; l<n-1; l=l+1) {
			for(int k=l+1; k<n; k=k+1) {
				if (!flights[l][k])
					cnt=cnt+2;
			}
		}
		int[][] cnf = new int [cnt*n][];
		int cnfIndex = 0;
		for(int j=0; j<n; j=j+1) {
			for(int k=0; k<n; k=k+1) {
				if (!flights[j][k] & k!=j ) {
					for(int i =0; i<n; i=i+1) {
						
						int dep= map(i,j,n);
						int arrive = map((i+1)%n,k,n);
							
							
						int[] lits= {dep,arrive};
						int[][] cnf2 = atMostOne(lits);
						cnf[cnfIndex] = cnf2[0]; //atMostOne with lits that contain 2 literals always have one 2nd D array
						cnfIndex=cnfIndex+1;
							
							
					}
				}
							
			}
		}
						
		return cnf;
							
				
	}
	// task 17
	public static int[][] encode(boolean[][] flights) {
		//assumes flights is legal
		// caculate how long is the cnf
		int n = flights.length;
		int a = ((n*(n-1))/2+1)*n; //task13
		int b = ((n*(n-1))/2+1)*n; //task14
		int c = 1; //task 15
		int d =0; // task 16
		for(int l=0; l<n-1; l=l+1) {
			for(int k=l+1; k<n; k=k+1) {
				if (!flights[l][k])
					d=d+2;
			}
		}
		d=d*n;
		int[][] cnf =new int [a+b+c+d][]; //initial cnf 
		int cnfIndex = 0 ;
		
		int[][] A = oneCityInEachStep(n); //create cnf for limitation a
		for (int i = 0; i<A.length; i=i+1, cnfIndex=cnfIndex+1) { //add cnf a to total cnf
			cnf[cnfIndex]= A[i];
		}
		
		int[][]B = eachCityIsVisitedOnce(n); //create cnf for limitation b
		for (int j = 0; j<B.length; j=j+1, cnfIndex=cnfIndex+1) { //add cnf a to total cnf
			cnf[cnfIndex]= B[j];
		}
		
		int[][] C = fixSourceCity(n); //create cnf for limitation c
		for (int k = 0; k<C.length; k=k+1, cnfIndex=cnfIndex+1) { //add cnf a to total cnf
			cnf[cnfIndex]= C[k];
		}
		
		int[][] D = noIllegalSteps(flights); //create cnf for limitation d
		for (int e = 0; e<D.length; e=e+1, cnfIndex=cnfIndex+1) { //add cnf a to total cnf
			cnf[cnfIndex]= D[e];
		}
		return cnf;
	}

	// task 18
	public static int[] decode(boolean[] assignment, int n) {
		//assumes n>0
		if (assignment == null | assignment.length != n*n+1)	
			throw new IllegalArgumentException();
		
		
		int[] tour = new int[n];
		for(int i =0; i<n; i=i+1) {
			for(int j =0; j<n; j=j+1) {
				if (assignment[map(i,j,n)])
					tour[i]=j;
					
			}
			
		}
		
		return tour;
	}
	
	// task19
	public static int[] solve(boolean[][] flights) {
		if (!isLegalInstance(flights))
			throw new IllegalArgumentException("flights is not legal");
		
		int n = flights.length;
		int litsNum=n*n;
		SATSolver.init(litsNum);
		int[][] cnf= encode(flights);
		SATSolver.addClauses(cnf);
		boolean[] sol= SATSolver.getSolution();
		if (sol.length>0) {
			int[] tour = decode(sol,n);
			if(isSolution(flights,tour))
				return tour;
			else
				throw new IllegalArgumentException("solution is not legal");
		}
		else {
			if(sol == null)
				throw new IllegalArgumentException("TIMEOUT");
			else
				return null;
		}
	}
	
	// task20
	public static boolean solve2(boolean[][] flights) {
		if (!isLegalInstance(flights))
			throw new IllegalArgumentException("flights is not legal");
			
		//find if there is a 1st solution
		int n = flights.length;
		int litsNum=n*n;
		SATSolver.init(litsNum);
		int[][] cnf= encode(flights);
		SATSolver.addClauses(cnf);
		boolean[] sol1= SATSolver.getSolution();
		if (sol1.length>0) {
			int[] tour1 = decode(sol1,n);
				
			if(isSolution(flights,tour1)) { //there is a solution
				int[] clause = new int[tour1.length];
				for(int i =0; i<tour1.length; i=i+1) { //make a caluse that forces one of the literals to be false
					int j = tour1[i];
					clause[i] = -1*map(i,j,n);
				}
					
				//find if there is a 2nd solution
				SATSolver.init(litsNum);
				SATSolver.addClauses(cnf);
				SATSolver.addClause(clause);
				boolean[] sol2 =SATSolver.getSolution();
					
				if (sol2.length>0) {
					int[] tour2 = decode(sol2,n);
					if(isSolution(flights,tour2)) { //find out if the 2nd solution contains the opposite flights
						int cnt = 0;
						for(int i=0; i<tour2.length-1;i=i+1) { //go over the 1st solution from the index 1 to the end and go over the 2nd solution from to end to index 1
							if(tour1[i+1]==tour2[tour2.length-i-1])
								cnt=cnt+1;
						}
						if (cnt == tour2.length-1)
							return false;
					}
						return true;
						
				}
				else {//2nd solution is not found
					if (sol2 == null)
						throw new IllegalArgumentException("TIMEOUT");
					else //there is no 2nd solution
						return false;
							
				}
						
						
					
			}
					
			else //the 1st solution is not legal
				return false;
		}
		else { //1st solution is not found
			if(sol1 == null)
				throw new IllegalArgumentException("TIMEOUT");
			else //there isnt a 1st solution
				return false;
		}
			
		
			
					
	}
		
}