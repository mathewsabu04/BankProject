
/**
 * Write a description of class Checking here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Checking extends Account 
{
    private Money overdraftMaximum;
    
    
    
    
    public Checking( String passName, String passId, Money Passbalance, Money overdraftMax)
    {
        super(passName,passId,Passbalance);
        this.overdraftMaximum =overdraftMax;
        
        
    }
    
    @Override
    public void withDraw (Money theMoney)
    {   
        Money currentBalance = super.getBalance();
        Money withDrawAmount = currentBalance.add(overdraftMaximum);
        if(withDrawAmount.compareTo(theMoney)==1 ||withDrawAmount.compareTo(theMoney)==0 )
        {
            super.withDraw(theMoney);
        }
        else
        {
            throw new InsufficientFundsException("Withdrawing too much money!");
            
        }
        
    }
    public String getAccountType()
    {
        return("c");
    }
    // public long getBalanceAsLong()
    // {
        // return(this.balance.getTotalCents());
    // }
   
    public Money getOM()
    {
        return this.overdraftMaximum;
    }
}
