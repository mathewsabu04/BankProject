import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
public class Bank implements bankInterface
{
    private String nameOfBank;
    private int numOfAccounts;

    private Account [] accounts;

    private final int MaxNumOfAccounts = 100;
    private String newBankName;
    
    //constructur for bank
    public Bank ()
    {
        this.nameOfBank = " ";
        this.numOfAccounts = 0;
        this.accounts = new Account[MaxNumOfAccounts];

    }
    

    
    // add account method 
    public  void addAccount (Account other)
    {
        if (this.numOfAccounts==this.accounts.length)
        {
            increaseSize();

        }
        this.accounts[this.numOfAccounts] = other;
        this.numOfAccounts++;

    }
    
    public void sortAccounts()
    {
        SortsClass.quickSort(accounts,0,numOfAccounts);
    }


    
    
    public Account search(String id)
    {
        Account blankAccount = new Account("", "", new Money(0, 00));
        
        for (int i=0; i< this.numOfAccounts;i++)
        {
            if (this.accounts[i].getId().equals(id))
            {
                blankAccount = this.accounts[i];
            }
        }
        
        return(blankAccount);
    }

    // public static int binarySearch(Account[] anArray, int first, int last, String value) {

// // Searches the array items anArray[first] through
// // anArray[last] for value by using a binary search.
// // Precondition: 0 <= first, last <= SIZE-1, where
// // SIZE is the maximum size of the array, and
// // anArray[first] <= anArray[first+1] <= ... <= anArray[last].
// // Postcondition: If value is in the array, the method
// // returns the index of the array item that equals value;
// // otherwise the method returns -1.

  // int index;

  // if (first > last) {

    // index = -1;      // value not in original array

  // } 
  // else {

    // // Invariant: If value is in anArray, 
    // // anArray[first] <= value <= anArray[last]

    // int mid = (first + last)/2;

    // if (value.equals(anArray[mid].getId())) {

      // index = mid;  // value found at anArray[mid]

    // } 
    // // compare value and id as a string and what happens if its less than 0
    // else if (value.compareTo(anArray[mid].getId()) < 0 ){

      // index = binarySearch(anArray, first, mid-1, value);   // point X

    // } 
        // // goes to this condition when its greater than 1.
    // else {

      // index = binarySearch(anArray, mid+1, last, value);    // point Y

    // }  // end if
  // }  // end if

  // return index;
// }  // end binarySearch
    
    

    public void increaseSize()
    {
        Account[] temp= new Account [this.accounts.length*2];
        for (int i = 0;  i < accounts.length; i++)
        {
            temp[i]= this.accounts[i];
        }

        this.accounts =temp;
    }
    @Override
    public void withdraw(String accountID, Money toWithdraw)
    {
        Account found = new Account ("","",new Money(0, 00));
        found = search(accountID);
        if(found!= null)
        {
            found.withDraw(toWithdraw);
        }
        
    }
    
    
    public void deposit(String accountID, Money toDeposit)
    {
        Account found = new Account ("","",new Money(0, 00));
        found = search(accountID);
        if(found!= null)
        {
            found.deposit(toDeposit);
        }
        
    }
    
    


     
    // All the getters and setters 
    public String getName()
    {
        return (this.nameOfBank);
    }

    public void setName(String newBankName)
    {
        this.nameOfBank = newBankName;
    }

    public int getNumOfAccounts()
    {
        return (this.numOfAccounts);
    }

    public String toString()
    {
        String report = "";
        report+="This Bank: " +this.nameOfBank+"\n";
        report+= "Number of Accounts: " + this.numOfAccounts;
        report+= "\nSize of Account list : " +accounts.length +"\n";

        return report;
    }
     public void copyTextFile(String originalFileName) 
     {
    // ---------------------------------------------------------
    // Makes a duplicate copy of a text file.
    // Precondition: originalFileName is the name of an existing
    // external text file, and copyFileName is the name of the
    // text file to be created.
    // Postcondition: The text file named copyFileName is a
    // duplicate of the file named originalFileName.
    // ---------------------------------------------------------
  
      PrintWriter ofStream = null; // declare an output file stream
      try 
      {
            ofStream = new PrintWriter(new FileWriter(originalFileName)); 

            String line;

            // copy lines one at a time from given file to new file
            for (int i = 0; i < numOfAccounts;i++)
             {
                ofStream.print(this.accounts[i].getName() + ",");
                 ofStream.print(this.accounts[i].getId() + ",");
                 ofStream.print(this.accounts[i].getBalanceAsLong() + ",");
                 ofStream.print("\n");
             }
  
            ofStream.close();
      } // end try
  
       catch (IOException e) 
       {
             System.out.println("Error copying file");
        } // end catch
        finally 
        {
            if (ofStream != null) 
            {
               ofStream.close();
            } // end if
    } // end finally
} // end copyTextFile

}
