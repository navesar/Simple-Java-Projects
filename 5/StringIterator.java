import java.util.Iterator;

public class StringIterator implements Iterator<String> {
	private String curr; //current String that we will return in the method next()
	
   
	//Complete the following methods
    public StringIterator(String start){
    	// task 0
		curr = start;
    }

    public boolean hasNext(){
    	// task 0
        return true; //we can always return bigger string
    }

    public String next(){
    	// task 0
    	String temp = curr; //the String we will return
    	 if(curr.endsWith("Z"))  curr = endsWithZ(curr); //my function that deals with this situation
    		
    	 else { if(!curr.isEmpty()) {
    		 		String lastChar = lastChar(curr);
    		 		curr = curr.substring(0, curr.length()-1)+nextChar(lastChar);}
    		 else // curr is empty
    			 curr = "a";	 
    	 }
    	 return temp;
    	 }
    
    
    
    	
    private static char nextChar(String ch) {
    	String aZ ="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    	if(ch.equals("Z"))
    		return 'a';
    	else
    		return aZ.charAt(aZ.indexOf(ch)+1);
    }

        
    
    private static String lastChar(String s) {
    	return s.substring(s.length()-1);
    }
    
    		
    private static String endsWithZ(String curr){
    	String s = curr.substring(0,curr.length()-1); //remove the Z
    	if(s.isEmpty()) return "aa"; //by definition 
    	if(s.endsWith("Z"))
    		s = endsWithZ(s)+"a"; //recursive call
    	else {
    		String lastChar = lastChar(s); 
    		s=s.substring(0,s.length()-1)+nextChar(lastChar)+"a"; //remove the last char in s and add the next char according to aZ and add a in the end
    	}
    	return s;
    	
    }
}
