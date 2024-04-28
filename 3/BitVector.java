public class BitVector extends NumericalString {
    private Bit[] bits;
    
    
    public BitVector(String s) {
        //Task 4.4
    	if(!legalNumericString(s,2))
    		throw new IllegalArgumentException("number is not in base 2");
    	bits = new Bit[s.length()];
    	for(int i=0;i<s.length();i++) {
    		if(s.charAt(i)=='1')
    			bits[i]=new Bit(true);
    		else
    			bits[i]=new Bit(false);
    			
    	}
    }

    public String toString() {
        String ans = "";
        //Task 4.5
        for(int i=0;i<bits.length;i++) {
        	ans= ans+(bits[i].toString()); //add the current value to the ans
        }
        ans = binary2Decimal(ans);
        String ans2="";
        for(int i=ans.length()-1;i>=0;i=i-1) { //reverse the answer to normal presentation
        	ans2 = ans2+ans.charAt(i);
        }
        ans=ans2;
        return ans;
    }

}