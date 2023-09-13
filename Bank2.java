import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
public class Bank2 implements bankInterface
{
    private String nameOfBank;
    private int numOfAccounts;

    private ArrayList<Account> accounts;

    private final int MaxNumOfAccounts = 100;
    private String newBankName;

    public Bank2()
    {
        this.nameOfBank = " "; 
        this.numOfAccounts = 0;
        this.accounts = new ArrayList<Account>();

    }

    public  void addAccount (Account other)
    {
        
        this.accounts.add(other);
        this.numOfAccounts++;

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
    
    // put this because its in the interface
    public void sortAccounts()
    {
        Collections.sort(accounts);
    }


     

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
    
    
    // public Account search(String id)
    // {
        // Account returnAccount = new Account("", "", new Money(0, 00));
        
        // for (int i=0; i< this.numOfAccounts;i++)
        // {
            // if (accounts.get(i).getId().equals(id))
            // {
                // returnAccount = accounts.get(i);
            // }
        // }
    
        // return(returnAccount);
    // }
    
    // finding the id using a binary search 
    public Account search(String id)
    {       
          // starts at 0 and ends at the numOfAccounts-1 because if you don't do - 1 it will go over the numOfAccounts
          int index = binarySearch(this.accounts, 0, numOfAccounts-1,id); 
          //Account returnAccount = new Account("", "", new Money(0, 00));
          //return(this.accounts[binarySearch(this.accounts, 0, numOfAccounts-1,id)]);
          if(index == -1)
          {
              return null;
              
          }
          else
          {
              return (this.accounts.get(index));                                  
              
              
              
          }
          
        
          
    }
    public static int binarySearch(ArrayList<Account>anArray, int first, int last, String value) {

// Searches the array items anArray[first] through
// anArray[last] for value by using a binary search.
// Precondition: 0 <= first, last <= SIZE-1, where
// SIZE is the maximum size of the array, and
// anArray[first] <= anArray[first+1] <= ... <= anArray[last].
// Postcondition: If value is in the array, the method
// returns the index of the array item that equals value;
// otherwise the method returns -1.
  int index;

  if (first > last) {

    index = -1;      // value not in original array

  } 
  else {

    // Invariant: If value is in anArray, 
    // anArray[first] <= value <= anArray[last]

    int mid = (first + last)/2;

    if (value.equals(anArray.get(mid).getId())) {

      index = mid;  // value found at anArray[mid]

    } 
    // compare value and id as a string and what happens if its less than 0
    else if (value.compareTo(anArray.get(mid).getId()) < 0 ){

      index = binarySearch(anArray, first, mid-1, value);   // point X

    } 
        // goes to this condition when its greater than 1.
    else {

      index = binarySearch(anArray, mid+1, last, value);    // point Y

    }  // end if
  } // end if
  return(index);
}
// makes a new file 
    public void copyTextFile(String originalFileName) {
// ---------------------------------------------------------
// Makes a duplicate copy of a text file.
// Precondition: originalFileName is the name of an existing
// external text file, and copyFileName is the name of the
// text file to be created.
// Postcondition: The text file named copyFileName is a
// duplicate of the file named originalFileName.
// ---------------------------------------------------------
  
  PrintWriter ofStream = null; // declare an output file stream
  try {
    ofStream = new PrintWriter(new FileWriter(originalFileName)); 

    String line;

    // copy lines one at a time from given file to new file
     for (int i = 0; i < numOfAccounts;i++)
     {
         ofStream.print(accounts.get(i).getName() + ",");
         ofStream.print(accounts.get(i).getId() + ",");
         
         if(accounts.get(i) instanceof Checking)
         {
             ofStream.print("c" + ",");
         }
         else
         {
             ofStream.print("r" + ",");
         }
         
        ofStream.print(accounts.get(i).getBalanceAsLong() + ",");
         
        if(accounts.get(i) instanceof Checking)
         {
             ofStream.print(accounts.get(i).getOM().getTotalCents());
         }
         else
         {
             ofStream.print("0");
         }
         
         ofStream.print("\n");
     }
  
    ofStream.close();
  } // end try
  
   catch (IOException e) {
     System.out.println("Error copying file");
  } // end catch
  finally {
    if (ofStream != null) {
           ofStream.close();
    } // end if
  } // end finally
} // end copyTextFile
    
    public String toString()
    {
        
        
        String report = "";
        report+="This Bank: " +this.nameOfBank+"\n";
        report+= "Number of Accounts: " + this.numOfAccounts+"\n";
        for (int i= 0; i < numOfAccounts; i++)
        {    
            report+="The amount of money in the account is: " +accounts.get(i).toString()+"\n";
        }
        

        return report;
    }
}

