public class FilterByBalance implements Filter<BankAccount>{
	private int balanceThreshold;
	public FilterByBalance(int balanceThreshold) {
        // task 5c
        this.balanceThreshold = balanceThreshold;
	}
	@Override
	public boolean accept(BankAccount elem) {
		// task 5c
		return elem.getBalance()>=balanceThreshold;
	}
}
