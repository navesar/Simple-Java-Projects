import java.util.Comparator;

public class AccountComparatorByNumber implements Comparator<BankAccount>{

	@Override
	public int compare(BankAccount account1, BankAccount account2) {
		// task 2b
		return(account1.getAccountNumber()-account2.getAccountNumber()); //returns the difference
	}

}