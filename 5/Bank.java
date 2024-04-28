public class Bank {

	private BankAccountsBinarySearchTree namesTree;
	private BankAccountsBinarySearchTree accountNumbersTree;
	
	public Bank() {
		namesTree = new BankAccountsBinarySearchTree(new AccountComparatorByName());
		accountNumbersTree = new BankAccountsBinarySearchTree(new AccountComparatorByNumber());
	}

	public BankAccount lookUp(String name){
		// create an Entry with the given name, a "dummy" accountNumber (1) and zero balance
		// This "dummy" accountNumber will be ignored when executing getData
		BankAccount lookFor = new BankAccount(name, 1, 0);
		return (BankAccount)namesTree.findData(lookFor);
	}
	
	public BankAccount lookUp(int accountNumber){
		// create an Entry with a "dummy" name, zero balance and the given accountNumber
		// This "dummy" name will be ignored when executing getData
		BankAccount lookFor = new BankAccount("dummy", accountNumber,0);
		return (BankAccount)accountNumbersTree.findData(lookFor);
	}
	
	// END OF Given code -----------------------------------
	
	// Complete the methods from here on

	public boolean add(BankAccount newAccount) {
		// task 6a
		if(lookUp(newAccount.getName())!=null | lookUp(newAccount.getAccountNumber())!=null) //the name or the account number is already in the system
			return false;
		namesTree.insert(newAccount);
		accountNumbersTree.insert(newAccount);
		return true;
	}

	public boolean delete(String name){
		// this first line is given in the assignment file
		BankAccount toRemove = lookUp(name);
		// complete this:

		// task 6b
		if(toRemove==null)//account doesn't exists 
			return false;
		namesTree.remove(toRemove);
		accountNumbersTree.remove(toRemove);
		return true;
	}
	
	public boolean delete(int accountNumber){
		// this first line is given in the assignment file
		BankAccount toRemove = lookUp(accountNumber);
		// complete this:

		// task 6c
		if(toRemove==null)//account doesn't exists 
			return false;
		namesTree.remove(toRemove);
		accountNumbersTree.remove(toRemove);
		return true;
	}

	public boolean depositMoney(int amount, int accountNumber){
		// task 6d
		BankAccount depositTo = lookUp(accountNumber);
		if (depositTo == null)//account doesn't exists 
			return false;
		if(depositTo.depositMoney(amount))
			return true;
		return false;
	}

	public boolean withdrawMoney(int amount, int accountNumber){
		// task 6e
		BankAccount withdrawFrom = lookUp(accountNumber);
		if (withdrawFrom == null) //account doesn't exists 
			return false;
		if(withdrawFrom.withdrawMoney(amount))
			return true;
		return false;
		
	}	
	
	public boolean transferMoney(int amount, int accountNumber1, int accountNumber2) {
		// task 6f
		BankAccount transferFrom = lookUp(accountNumber1);
		BankAccount transferTo = lookUp(accountNumber2);
		if(transferFrom==null|transferTo==null)//one of the accounts does not exists
			return false;
		if(transferFrom.getBalance()<amount)//account1 doesn't have enough money
			return false;
		//make the transfer
		transferFrom.withdrawMoney(amount);
		transferTo.depositMoney(amount);
		return true;
		
		
	}	
	public boolean transferMoney(int amount, int accountNumber, String name) {
		// task 6g
		BankAccount transferFrom = lookUp(accountNumber);
		BankAccount transferTo = lookUp(name);
		if(transferFrom==null|transferTo==null)//one of the accounts does not exists
			return false;
		if(transferFrom.getBalance()<amount)//account1 doesn't have enough money
			return false;
		//make the transfer
		transferFrom.withdrawMoney(amount);
		transferTo.depositMoney(amount);
		return true;
	}	
}