
public class Bit {

    private boolean value;

    public Bit(boolean value){
        //Task 4.1
    	if(value==false)
    		this.value=false;
    	else
    		this.value=true;
    }

    public int toInt(){ 
        int ans = 0;
        //Task 4.2
        if (value==false)
        	ans= 0;
        else
        	ans= 1;
        	
        return ans;

    }

    public String toString(){
        String ans = "";
        //Task 4.3
        if (value==false)
        	ans ="0";
        else
        	ans="1";
        return ans;
    }
    
}
