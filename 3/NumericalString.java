public class NumericalString {
    public static boolean legalNumericString(String s, int b) {
        boolean ans = true;
        //Task 3.1
        if(s==null|b<2|b>10)
        	throw new IllegalArgumentException("input is not legal");
 
        for(int i=0;i<s.length();i++) {
        	int ch =toInt(s.charAt(i));
        	if(ch==-1)
        		throw new IllegalArgumentException("input is not legal");
        	if(ch>b-1|((i==s.length()-1&ch==0)&s.length()!=1)|ch==-1)
        		ans=false;
        	
        			
        }
        return ans;
    }

	public static String decimalIncrement(String s) {
        String ans = "";
        //Task 3.2
        if(!legalNumericString(s,10))
        	throw new IllegalArgumentException("number is not in base 10");
        if(s.charAt(0)=='9')
        	ans = decimalIncrement(s,1,"0",1);
        else
        	ans = (toInt(s.charAt(0))+1)+s.substring(1);
       return ans;
    }
	
	public static String decimalIncrement(String s, int i,String acc,int carry) {
		if(i<s.length()) {
			int ch = toInt(s.charAt(i))+carry;
			
			int lastCharInAcc=toInt(acc.charAt(acc.length()-1));
			
			if(ch+lastCharInAcc==10) {
				acc= acc+"0";
				acc=decimalIncrement(s,i+1,acc,1);//carry to the next lbl is 1
			}
			
			else { 
					acc=acc+ch;
					acc=decimalIncrement(s,i+1,acc,0);//carry is 0	
			}
		}
		else {//we are at the end of the string 
			if(carry==1)
				acc=acc+1;
		}	
		return acc;
	}

	public static String decimalDouble(String s) {
        String ans = "";
        //Task 3.3
        if(!legalNumericString(s, 10))
        	throw new IllegalArgumentException("number is not in base 10");
        ans = decimalDouble(s,0,"",0);
        return ans;
    }
	
	
	public static String decimalDouble(String s,int i,String acc,int carry) {
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

	public static String binary2Decimal(String s) {
        String ans = "";
        //Task 3.4
        if(!legalNumericString(s,2))
        	throw new IllegalArgumentException("number is not in base 2");
        ans =binary2Decimal(s,0,"");
        return ans;
	}
	
	
	public static String binary2Decimal(String s, int i, String acc) {
		if(i<s.length()) {
			if(s.charAt(i)=='1') {
				String ch ="1";
				for(int x=0;x<i;x++) {
					ch=decimalDouble(ch);
					
				}
				acc=addDecimalToDecimal(acc, ch);
				acc=binary2Decimal(s,i+1,acc);
			}
			else
				acc=binary2Decimal(s,i+1,acc);
		}
		return acc;
			
	}
	public static String addDecimalToDecimal(String s,String t) {
		if (s.length()>=t.length()) 
			return addDecimalToDecimal(t,s,0,"",0);
		
		else
			 return addDecimalToDecimal(s,t,0,"",0);
			
	}
	
	
	
	public static String addDecimalToDecimal(String smaller,String bigger,int i,String acc,int carry) {
		if (i<smaller.length()) {
			int s = toInt(smaller.charAt(i));
			int b = toInt(bigger.charAt(i))+carry;
			if (s+b>=10) {
				if(s+b==10)
					acc=acc+0;
				else
					acc=acc+((s+b)%10);
				acc=addDecimalToDecimal(smaller, bigger, i+1, acc, 1);
				
			}
			else {
				acc=acc+(s+b);
				acc=addDecimalToDecimal(smaller,bigger,i+1,acc,0);
			}	
		}
		else {
			if(i<bigger.length()) {
				int b = toInt(bigger.charAt(i))+carry;
				if (b==10) {
					acc= acc+"0";
					acc=acc=addDecimalToDecimal(smaller, bigger, i+1, acc, 1);
				}
				else
					acc=acc+b+bigger.substring(i+1);
			}
			else {
				if(carry==1)
					acc=acc+1;
			}
		}
		return acc;
	}

    public static void main(String[] args) {
        System.out.println("Good Luck! :)");
    }
    
    
    
    
    
    
    public static int toInt(char c) {
    	return "0123456789".indexOf(c);
    	}
}
