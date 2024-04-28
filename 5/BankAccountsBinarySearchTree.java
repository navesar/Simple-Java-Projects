import java.util.Comparator;
import java.util.Iterator;

public class BankAccountsBinarySearchTree extends BinarySearchTree<BankAccount>{

	public BankAccountsBinarySearchTree(Comparator<BankAccount> myComparator) {
		super(myComparator);
	}
	
	// Complete the following methods
	public void balance(){
		// task 5b
		if(!isEmpty()) {
			List<BankAccount> lst = new DynamicArray<BankAccount>(size());
			Iterator<BankAccount> it = iterator();
			while(it.hasNext()) {
				BankAccount curr = it.next();
				lst.add(curr); 
				remove(curr);
			}
			buildBalancedTree(lst,0,lst.size()-1);
		}
		
		
	}
	
	private void buildBalancedTree(List<BankAccount> list, int low, int high){ //|left height - right.height|<=1
		// task 5b
		int middle = (high+low+1)/2;
		insert(list.get(middle));
		while(!contains(list.get(low))) //left sons
			buildBalancedTree(list,low,middle-1);
		while(!contains(list.get(high))) //right sons
			buildBalancedTree(list,middle+1,high);
			
		
		
	}
}