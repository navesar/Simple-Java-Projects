/*
I, <Nave Sarfati> (<315212753>), assert that the work I submitted is entirely my own.
I have not received any part from any other student in the class,
nor did I give parts of it for use to others.
I realize that if my work is found to contain code that is not originally my own,
 a formal case will be opened against me with the BGU disciplinary committee.
*/


import java.util.LinkedList;
import java.util.Iterator;

public class BitList extends LinkedList<Bit> {
    private int numberOfOnes;

    // Do not change the constructor
    public BitList() {
        numberOfOnes = 0;
    }

    // Do not change the method
    public int getNumberOfOnes() {
        return numberOfOnes;
    }


//=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.1 ================================================
    public void addLast(Bit element) {
    	if (element==null)
    		throw new IllegalArgumentException("input is null");
    	super.addLast(element);
    	if(element.equals(Bit.ONE))
    		numberOfOnes = numberOfOnes+1;   
    }

    public void addFirst(Bit element) {
    	if (element==null)
    		throw new IllegalArgumentException("input is null");
    	super.addFirst(element);
    	if(element.equals(Bit.ONE))
    		numberOfOnes = numberOfOnes+1;
    }

    public Bit removeLast() {
    	if(size()==0)
    		throw new IllegalArgumentException("the BitList that you are trying to remove a bit from is empty");
    	Bit removed =super.removeLast();
    	if (removed.equals(Bit.ONE))
    		numberOfOnes=numberOfOnes-1;
    	return removed;
    	
       
    }

    public Bit removeFirst() {
    	if(size()==0)
    		throw new IllegalArgumentException("the BitList that you are trying to remove a bit from is empty");
    	Bit removed =super.removeFirst();
    	if (removed.equals(Bit.ONE))
    		numberOfOnes=numberOfOnes-1;
    	return removed;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.2 ================================================
    public String toString() {
    	String output= ">";
    	Iterator<Bit> it= this.listIterator();
    	while(it.hasNext()) {
    		output = it.next()+output;
    	}
    	output="<"+output;
    	return output;
    }
    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.3 ================================================
    public BitList(BitList other) {
    	if (other==null)
    		throw new IllegalArgumentException("the BitList that you try to copy is null");
    	Iterator<Bit> it =other.iterator();
    	while(it.hasNext()) {
    		addLast(it.next());
    	}
    	numberOfOnes =other.numberOfOnes;
       
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.4 ================================================

    
    public boolean isNumber() {
    	if(size()==0) 
    		return false;
    	
    	else {
    		Iterator<Bit> it = this.listIterator();
    		int cnt=0;
    		while(it.hasNext()&cnt<size()-1) {
    			it.next();
    			cnt++;
    		}
    		if(it.next().equals(Bit.ZERO)|numberOfOnes>1)
    			return true;
    		else 
    			return false;
    		
    	}
    	
    }
    
    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.5 ================================================
    public boolean isReduced() {
        String s= toString();
        if(!isNumber()) //condition 1
        	return false;
        else {
        	if((s.equals("<11>")|s.equals("<01>")|s.equals("<0>"))| //condition a
        		(s.length()>=5&(s.substring(1,3).equals("01")|s.substring(1,3).equals("10")))|//condition b
        		(s.length()>=5&numberOfOnes==2&s.charAt(1)=='1'&s.charAt(2)=='1'))//condition c
        		return true;
        	else
        		return false;
        }	
        		
    }

    public void reduce() {
        while(!isReduced())
        	removeLast(); //reduce the number
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.6 ================================================
    public BitList complement() {
        BitList output = new BitList();
        Iterator<Bit> it = this.listIterator();
        while(it.hasNext()) {
        	output.addLast(it.next().negate());
        }
        return output;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.7 ================================================
    public Bit shiftRight() {
    	if (size()==0)
    		return null;
    	else
    		return removeFirst();
    }

    public void shiftLeft() {
      addFirst(Bit.ZERO);
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.8 ==============================================
    public void padding(int newLength) {
    	if(size()==0)
    		throw new IllegalArgumentException("the BitList that you are trying to padd is empty");
    	Iterator<Bit> it =this.listIterator();
    	int i = size();
    	for(int j=0; j<i-1;j++) {
    		if(it.hasNext())
    			it.next();
    	}
    	Bit lastBit = it.next();
    	while (i<newLength) {
    		addLast(lastBit);
    		i++;
    		
    	}
    }
    
    //my function
    public BitList reverse() {
    	BitList output=new BitList();
    	Iterator<Bit> it = iterator();
    	while(it.hasNext()) {
    		output.addFirst(it.next());
    	}
    	return output;
    }

    

    //----------------------------------------------------------------------------------------------------------
    // The following overriding methods must not be changed.
    //----------------------------------------------------------------------------------------------------------
    public boolean add(Bit e) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public void add(int index, Bit element) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public Bit remove(int index) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public boolean offer(Bit e) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public boolean offerFirst(Bit e) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public boolean offerLast(Bit e) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public Bit set(int index, Bit element) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Do not use this method!");
    }
}