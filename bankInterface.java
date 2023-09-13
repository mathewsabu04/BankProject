


public interface bankInterface {
 

  // Add new account to bank, increase size of bank capacity
  public void addAccount(Account newAccount);
  
  //Set bank name
  public void setName(String newBankName);
  
  public void copyTextFile(String originalFileName);
  
  


  // Retrieves account number using account ID defined in the Account object
  public Account search(String accountID);
  //public void copyTextFile(String originalFileName);


  // Adds money to the account, taking the accountID and the amount of Money(as an object) to deposit
  public void deposit(String accountID, Money toDeposit);


  // Subtracts money from account, taking in the accountID and the amount of money to withdraw
  public void withdraw(String accountID, Money toWithdraw);


  // returns the number of accounts as a string
  public String toString();
  public void sortAccounts();


  // Returns the number of acconts as an integer
  public int getNumOfAccounts();

  public String getName();
}