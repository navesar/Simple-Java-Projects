
/*
I, <Nave Sarfati> (<315212753>), assert that the work I submitted is entirely my own.
I have not received any part from any other student in the class,
nor did I give parts of it for use to others.
I realize that if my work is found to contain code that is not originally my own,
 a formal case will be opened against me with the BGU disciplinary committee.
*/

import java.util.Iterator;

public class BinaryNumber implements Comparable<BinaryNumber>{
    private static final BinaryNumber ZERO = new BinaryNumber(0);
    private static final BinaryNumber ONE  = new BinaryNumber(1);
    private BitList bits;

    // Copy constructor
    //Do not chainge this constructor
    public BinaryNumber(BinaryNumber number) {
        bits = new BitList(number.bits);
    }

    //Do not chainge this constructor
    private BinaryNumber(int i) {
        bits = new BitList();
        bits.addFirst(Bit.ZERO);
        if (i == 1)
            bits.addFirst(Bit.ONE);
        else if (i != 0)
            throw new IllegalArgumentException("This Constructor may only get either zero or one.");
    }

    //Do not chainge this method
    public int length() {
        return bits.size();
    }

    //Do not change this method
    public boolean isLegal() {
        return bits.isNumber() & bits.isReduced();
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.1 ================================================
    public BinaryNumber(char c) {
    	int n = c-48; //char - ASCII value of 0
    	if(n<0|n>9)
    		throw new IllegalArgumentException("char is not in 0-9 range");
    	bits = new BitList();
    	while(n!=0) {//generate the binary number
    		bits.addLast(new Bit(n%2));
    		n=n/2;
    	}
    	bits.addLast(Bit.ZERO); //symbolize that the number is positive
    	
    }

        	
    

  //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.2 ================================================
    public String toString() {
        // Do not remove or change the next two lines
        //if (!isLegal()) // Do not change this line
            //throw new RuntimeException("I am illegal.");// Do not change this line
        //
        String output="";
        Iterator<Bit> it = bits.iterator();
        while(it.hasNext()) {
        	output = it.next()+output;
        }
        return output;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.3 ================================================

    public boolean equals(Object other) {
    	if(!(other instanceof BinaryNumber))
    		return false;
    	else {
    		if(length()!= ((BinaryNumber)other).length())
    			return false;
    		else {
    			Iterator<Bit> it1 = bits.listIterator();
    			Iterator<Bit> it2 = ((BinaryNumber)other).bits.listIterator();
    			while(it1.hasNext()) { 
    				if(!(it1.next().equals(it2.next())))
    					return false;
    			}
    			return true;
    		}
    			
    	}
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.4 ================================================
    
    public BinaryNumber add(BinaryNumber addMe) {
    	if(!addMe.isLegal())
    		throw new IllegalArgumentException("the number that you want to add is not legal");
    
    	if(addMe.equals(ZERO)) 
    		return new BinaryNumber(this);
    	
    	else if(equals(ZERO)) 
    		return new BinaryNumber(addMe);
    	
    	
    	BinaryNumber sum = new BinaryNumber(0);
    	sum.bits.removeFirst(); //start with an empty BitList
    	boolean onePosOneNeg=false; //if true we will not care for the last carry
    	if(signum()+addMe.signum()==0) 
    		onePosOneNeg = true;
    	//padding the numbers
    	if(length()>addMe.length())
    		addMe.bits.padding(length());
    	else
    		bits.padding(addMe.length());
    	//start the calculation 
    	Iterator<Bit> it1 = bits.iterator();
    	Iterator<Bit> it2 = addMe.bits.iterator();
    	Bit carry = Bit.ZERO;
    	while(it1.hasNext()) { //has the same numbers of elements as it2 due to the padding
    		Bit bit1= it1.next();
    		Bit bit2= it2.next();
    		sum.bits.addLast(Bit.fullAdderSum(bit1, bit2, carry));
    		carry = Bit.fullAdderCarry(bit1, bit2, carry);
    	}
    	if(!onePosOneNeg) {
    		if(signum()==1) {
    			if(carry.equals(Bit.ONE))
    				sum.bits.addLast(carry);
    			sum.bits.addLast(Bit.ZERO);
    		}
    		else {
    			if(carry.equals(Bit.ONE))
    				sum.bits.addLast(carry);
    		}
    	}
    	
    	bits.reduce();addMe.bits.reduce();
    	sum.bits.reduce();
    	return sum;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.5 ================================================
    public BinaryNumber negate() {
    	BinaryNumber output = new BinaryNumber(this);
    	output.bits=output.bits.complement();
    	return output.add(ONE);
    	
  
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.6 ================================================
    public BinaryNumber subtract(BinaryNumber subtractMe) {
    	if(!subtractMe.isLegal())
    		throw new IllegalArgumentException("the number you want to subtract is not legal");
    	return add(subtractMe.negate());	
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.7 ================================================
    public int signum() {
    	if (length()==1)
    		return 0;
    	else {
    		Iterator<Bit> it =bits.listIterator();
    		int cnt=0;
    		while(it.hasNext()&cnt<length()-1) { //go to the last bit
    			it.next();
    			cnt++;
    		}
    		if(it.next().equals(Bit.ONE)) //the number is negative
    			return -1;
    		else
    			return 1; //the number is positive
    		
    	}
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.8 ================================================
    public int compareTo(BinaryNumber other) {
    	  if(equals(other))
    		  return 0;
    	  else {
    		  if(subtract(other).signum()==1) //this is bigger than other
    			  return 1;
    		  else //this is smaller than other
    			  return -1;
    	  }
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.9 ================================================
    public int toInt() {
        // Do not remove or change the next two lines
        if (!isLegal()) // Do not change this line
            throw new RuntimeException("I am illegal.");// Do not change this line
        //
        if(signum()==0)
        	return 0;
        int sum=0,sign;
        BinaryNumber temp;
        if(signum()==1) { //this is positive
        	temp=new BinaryNumber(this);
        	sign=1;
        }
        else { //this is negative
        	temp=new BinaryNumber(negate()); //set temp as the absolute value of this
        	sign =-1;
        }
        Iterator<Bit> it = temp.bits.iterator();
        int index = 0;
        while(it.hasNext()) {
        	int curr =it.next().toInt();
        	sum = sum+curr*(int)Math.pow(2, index);
        	index++;
        }
        return sum*sign;
        
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.10 ================================================
    // Do not change this method
    public BinaryNumber multiply(BinaryNumber multiplyMe   ) {
    	if(!multiplyMe.isLegal())
    		throw new IllegalArgumentException("the number you want to multiply by is not legal");
    	//special cases that don't require calculation 
    	if(multiplyMe.equals(ONE)|equals(ZERO))
    		return new BinaryNumber(this);
    	else if(multiplyMe.equals(ZERO)|equals(ONE))
    		return new BinaryNumber(multiplyMe);
    	//set the variables that we will copy the input to so we don't change the actual numbers
    	BinaryNumber bn1,bn2;
    	//check to see if this or multiplyMe is negative
    	boolean thisPos,multiplyMePos;
    	if(signum()==1) {
    		thisPos =true;
    		bn1 =new BinaryNumber(this);
    	}
    	else {
    		thisPos=false;
    		bn1 =new BinaryNumber(this.negate());
    	}
    	if(multiplyMe.signum()==1) {
    		multiplyMePos=true;
    		bn2 = new BinaryNumber(multiplyMe);
    	}
    	else {
    		multiplyMePos=false;
    		bn2 = new BinaryNumber(multiplyMe.negate());
    	}
    	BinaryNumber output = bn1.multiplyPositive(bn2);
    	if((thisPos&multiplyMePos)|(!thisPos&!multiplyMePos)) //positive*positive = positive & negative*negative = positive
    		return output;
    	else //positive*negative = negative & negative*positive = negative
    		return output.negate();
    	
    	
    }

    
    private BinaryNumber multiplyPositive(BinaryNumber multiplyMe) {
    	return russianPeasantMultiplication(this, multiplyMe);
   }

	private static BinaryNumber russianPeasantMultiplication(BinaryNumber x,BinaryNumber y) { //using the russian peasant multiplication algorithm- https://www.youtube.com/watch?v=2qCr8H6tU44
		if (x.equals(ONE))
			return y;
		if(x.bits.removeFirst().equals(Bit.ONE)) //x is odd
			return y.add(russianPeasantMultiplication(x, y.multiplyBy2()));
		else //x is even
			return russianPeasantMultiplication(x, y.multiplyBy2());
		
	}

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.11 ================================================
    // Do not change this method
    public BinaryNumber divide(BinaryNumber divisor) {
    	// Do not remove or change the next two lines
    	if (divisor.equals(ZERO)) // Do not change this line
            throw new RuntimeException("Cannot divide by zero."); // Do not change this line
    	//
    	if(!divisor.isLegal())
    		throw new IllegalArgumentException("the number you want to divide by is not legal");
    	//special cases that calculation is not required
    	if(divisor.equals(ONE)|equals(ONE))
    		return multiply(divisor);
    	else if(equals(divisor))
    		return ONE;
    	//set the variables that we will copy the input to so we don't change the actual numbers
    	BinaryNumber bn1,bn2;
    	//check to see if this or multiplyMe is negative
    	boolean thisPos,multiplyMePos;
    	if(signum()==1) {
    		thisPos =true;
    		bn1 =new BinaryNumber(this);
    	}
    	else {
    		thisPos=false;
    		bn1 =new BinaryNumber(negate());
    	}
    	if(divisor.signum()==1) {
    		multiplyMePos=true;
    		bn2 = new BinaryNumber(divisor);
    	}
    	else {
    		multiplyMePos=false;
    		bn2 = new BinaryNumber(divisor.negate());
    	}
    	BinaryNumber output = bn1.dividePositive(bn2);
    	if((thisPos&multiplyMePos)|(!thisPos&!multiplyMePos)) //positive/positive = positive & negative/negative = positive
    		return output;
    	else //positive/negative = negative & negative/positive = negative
    		return output.negate();
    	
    	
    }
    
    
    private BinaryNumber dividePositive(BinaryNumber divisor) {
    	
    	BinaryNumber div =new BinaryNumber(divisor);
    	BinaryNumber cnt = new BinaryNumber(ZERO);
    	BinaryNumber mone = new BinaryNumber(this);
    	
    	while(mone.compareTo(div)>=0) {
    		div=div.multiplyBy2();
    		if(cnt.equals(ZERO)) cnt =cnt.add(ONE);
    		else cnt = cnt.multiplyBy2();
    	}
    	mone = mone.subtract(div.divideBy2());
    	if((mone.compareTo(ZERO)>0))
    		cnt= cnt.add(mone.dividePositive(divisor));
    	return cnt;
    	

    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.12 ================================================
    public BinaryNumber(String s) {
      String legalChars ="-0123456789"; //the chars that can represent a number 
      int j;
      boolean negative;
      bits =new BitList();
      BinaryNumber mult= new BinaryNumber(ONE);
      BinaryNumber bn10 = new BinaryNumber('9').add(ONE);
      BinaryNumber bn= new BinaryNumber(ZERO);
      if(s.charAt(0)=='-') {
    	  negative=true;
    	  j=1; //stop before you reach the '-' sign
      }
      else {
    	  negative =false;
    	  j=0;
      }  
     for (int i=s.length()-1; i>=j;i--,mult=mult.multiply(bn10)) { //go over the chars from end to start
    	 if(!legalChars.contains(s.substring(i,i))|s.charAt(i)=='-')
    		 throw new IllegalArgumentException("the number is not legal");
  
    	 BinaryNumber curr = new BinaryNumber(s.charAt(i)).multiply(mult);
    	 bn = bn.add(curr);
     }
    	 
      if(negative) 
    	  bn= bn.negate();
      bits = bn.bits;
    	  
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.13 ================================================
    public String toIntString() {
    	if(!isLegal())
    		throw new IllegalArgumentException();
    	String output = "0";
    	Iterator<Bit> it;
    	boolean negative;
    	if(signum()==1|signum()==0) {
    		it = bits.reverse().iterator();
    		negative =false;
    	}
    	else {
    		it = negate().bits.reverse().iterator();
    		negative =true;
    		}
    	while(it.hasNext()) {
    		output= decimalDouble(output);
    		if(it.next().equals(Bit.ONE))
    			output = decimalIncrement(output);
    	}
    	String rev ="";
    	for(int i=0;i<output.length();i++) {
    		rev=output.charAt(i)+rev;
    	}
    	if(negative)
    		rev="-"+rev;
    	return rev;
    }


    // Returns this * 2
    public BinaryNumber multiplyBy2() {
        BinaryNumber output = new BinaryNumber(this);
        output.bits.shiftLeft();
        output.bits.reduce();
        return output;
    }

    // Returens this / 2;
    public BinaryNumber divideBy2() {
        BinaryNumber output = new BinaryNumber(this);
        if (!equals(ZERO)) {
            if (signum() == -1) {
                output.negate();
                output.bits.shiftRight();
                output.negate();
            } else output.bits.shiftRight();
        }
        return output;
    }
    
       
   
    
	
	
	
	
	
	//functions that are in use in toIntString() method
	private static int toInt(char c) {
		 return "0123456789".indexOf(c);
		 }
	private static String decimalDouble(String s) {
        return decimalDouble(s,0,"",0);  
    }
	private static String decimalDouble(String s,int i,String acc,int carry) {
		if(carry==1&i>=s.length()) //the carry from the last res was 1
			acc=acc+1;
		if (i<s.length()) {
			int ch = toInt(s.charAt(i));
			int res=ch*2+carry;
			if(res>=10) {
				acc=acc+(res-10); 
				acc= decimalDouble(s,i+1,acc,1); //carry to the next number is 1
			}
			else {
				acc=acc+res;
				acc=decimalDouble(s,i+1,acc,0); //carry to the next number is 0
			}
				
		}
		return acc;
			
	}
	private static String decimalIncrement(String s) {
        if(s.charAt(0)=='9')
        	return decimalIncrement(s,1,"0",1);
        else
        	return (toInt(s.charAt(0))+1)+s.substring(1);
       
    }
	private static String decimalIncrement(String s, int i,String acc,int carry) {
		if(i<s.length()) {
			int ch = toInt(s.charAt(i))+carry;
			
			int lastCharInAcc=toInt(acc.charAt(acc.length()-1));
			
			if(ch+lastCharInAcc==10) {
				acc= acc+"0";
				acc=decimalIncrement(s,i+1,acc,1);
			}
			
			else {
					acc=acc+ch;
					acc=decimalIncrement(s,i+1,acc,0);	
			}
		}
		else {
			if(carry==1)
				acc=acc+1;
		}	
		return acc;
	}

}
