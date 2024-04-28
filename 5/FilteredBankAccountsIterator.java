import java.util.Iterator;
import java.util.NoSuchElementException;


public class FilteredBankAccountsIterator implements Iterator<BankAccount> {

	private BinaryTreeInOrderIterator<BankAccount> iterator;
	private Filter<BankAccount> filter;
    private BankAccount current;
    
    public FilteredBankAccountsIterator(BankAccountsBinarySearchTree bankAccountsTree, Filter<BankAccount> filter) {
        // task 5c
        iterator = (BinaryTreeInOrderIterator<BankAccount>) bankAccountsTree.iterator(); //bankAccountsTree has inOrder iterator by definition that he is a binaryTree
        this.filter=filter;
        current=null; 
        while(current==null&iterator.hasNext()) {
        	BankAccount temp = iterator.next();
        	if(filter.accept(temp)) current=temp; //set current as the first BankAccount that the filter accepts
        }
    }

    //Complete the following method
    @Override
    public boolean hasNext() {
        // task 5c
    	return current!=null; 
        
    }

    //Complete the following method
    @Override
    public BankAccount next() {
        // task 5c
        if(!hasNext()) throw new NoSuchElementException();
        BankAccount output = current; //the element we will return
        while(current.equals(output)&iterator.hasNext()) { //same process as we did in the constructor
        	BankAccount temp = iterator.next();
        	if(filter.accept(temp))
        		current = temp;
        }
        if(current.equals(output)) current =null;
        return output;
        
    }
}