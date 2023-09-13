import java.lang.Comparable;
public class Account implements Comparable {
    private String name;
    private String id;
    private Money balance;

    public Account ( String passName, String passId, Money Passbalance)
    {
        this.name = passName;
        this.id = passId;
        this.balance = Passbalance;

    }

    public void deposit (Money theMoney)
    {
        this.balance = this.balance.add(theMoney);
    }

    public void withDraw (Money theMoney)
    {
        this.balance = this.balance.subtract(theMoney);
    }

    public void transfer(Account theAccount, Money toTransfer)
    {
        theAccount.withDraw(toTransfer);
        this.deposit(toTransfer);

    }

    public String toString()
    {
        String result = "" + this.name+"\n"
        +"The id: "+this.id+"\n"+"The balance is: "+this.balance+"\n";
        return result;
    }


    public boolean equals (Account other)
    {
        if (this.name.equals(other.name)&& this.id.equals(other.id)&& this.balance.equals (other.balance))
        {
            return true;
        }
        else{
            return false;
        }
    }

    
    public String getId(){
        return(this.id);
    }
    
    public Money getBalance()
    {
        return(this.balance);
    }
    
    public long getBalanceAsLong()
    {
        return(this.balance.getTotalCents());
    }
    
    public String getName()
    {
        return(this.name);
    }
    
    
    @Override 
    public int compareTo(Object o)
    {
       if (o instanceof Account)
        {
            Account accountObject = (Account) o;
            return(this.id.compareTo(accountObject.getId()));
        }
        else
        {
            return(2);
        }
    }  
    
    public String getAccountType()
    {
        return("r");
        
    }
    
    public  Money getOM()
    {
         Money returnMoney = new Money(00,00);
         return(returnMoney);
    }
    
    
    
  }
