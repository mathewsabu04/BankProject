
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;

// Driver class for Bank project

public class ATM 
{
    public static void main (String[] args)
    { 
        try
        {
    
            // Read data from a file into a Bank.
            // Each line of the file has info for one account.  
            bankInterface myBank = readFromFile("BankData.txt"); // reads the info from the text file.
            myBank.sortAccounts();
            System.out.println(myBank);
            IOHandlerInterface ioh = new IOHandlerDialog ();
            // read user id
            String userId= readUserId(ioh);
            //checks if the id is valid or not
            boolean valid = isValid(myBank,userId,ioh);
            // ask if they want checkblanace, deposit, or withdraw
            String user2= "";
            // while not valid
            // checks to see if the id is invalid. If so, it asks again
        while(!valid)
        {   
            // show invalid id 
            ioh.put("This id does not exist");
                    
            // read user id again
            userId = readUserId(ioh);
                    
                   
        }
        do
        {
            if (valid)
            { 
                String type = "";
                Scanner scan = new Scanner (System.in);
                user2 = ioh.get("Do you want to check the balance, depoist, or withdraw.\n Type balance to get balance\n Type deposit to deposit\n Type withdraw to withdraw.\n If your transaction is done please type done");

                 //gets the balance
            if(user2.equals("balance"))
            {
                // show regular if its a type account 
                if(myBank.search(userId) instanceof Account)
                {
                    type = "regular";
                    
                }
                // Show checking if its type checking 
                if (myBank.search(userId) instanceof  Checking)
                {
                    type = "checking";
                }
                
                // shows the output if its a checking 
                String returnString = ("\n" + myBank.search(userId) + "\n" + "Account Type: " + type);
                if(type.equals("checking"))
                {
                    returnString += "\n" + "OverDraft Maximum: " + myBank.search(userId).getOM();
                }
                ioh.put(returnString);
                myBank.copyTextFile("out_accounts");
                
                
                
                //ioh.put(myBank.search(userId).toString());
            }
            //gets you deposit 
            else if (user2.equals("deposit"))
            {
                 user2 = ioh.get("How much do you want to deposit?");
                
                 String [] split = user2.split("[.]", 0);
                 int dollars = Integer.parseInt(split[0]);
                 int cents = Integer.parseInt(split[1]);
                 Money m = new Money(dollars,cents);
                
                //deposit the money into the bank
                myBank.deposit(userId,m);
                //shows its successful.
                ioh.put("Your deposit was successful. Your new balance is: "+ myBank.search(userId).getBalance());
            }
            
            //last conditon where you withdraw
            else if(user2.equals("withdraw")) 
            {   
                try
                {
                    user2=ioh.get("How much do you want to withdraw?");
                
                    String [] split = user2.split("[.]", 0);
                    int dollars = Integer.parseInt(split[0]);
                    int cents = Integer.parseInt(split[1]);
                    Money m = new Money(dollars,cents); 
                    
                    
                
                    //ioh.put("Your withdraw was successful. Your new balance is: "+ myBank.search(userId).getBalance());
                    //ioh.put(myBank.search(userId).toString());
                    if(myBank.search(userId).getBalance().compareTo(m)== -1 && myBank.search(userId).getAccountType() == "r")
                    {
                        ioh.put(myBank.search(userId).toString());
                        throw new InsufficientFundsException("Invalid transaction");
                        

                    }
                    
                    myBank.withdraw(userId,m);
                    
                    ioh.put("Your account with your new balance is : " + "\n" +myBank.search(userId)); 
                    myBank.copyTextFile("out_accounts");
                }
                // if they withdraw too much with or without the overdraft, it will print this 
                catch(InsufficientFundsException ife)
                {
                    ioh.put("Insufficient Funds.");
                }
                
            }

            
                
            
        }
    }while(!user2.equals("done"));
    myBank.copyTextFile("BankData.txt");
    myBank.copyTextFile("out_accounts");

    } // end try    
      catch (IOException ioe) {
         System.out.println("IOException in main: " + ioe.getMessage()); 
      ioe.printStackTrace();
      } // end catch
      catch (Exception e) {
         System.out.println("Exception in main: " + e.getMessage());
     e.printStackTrace();
      } // end catch
      
      
      
} // end main


   //reads the file 
   public static bankInterface readFromFile (String fileName) throws IOException
   {
     // Creata a bank.
         bankInterface myBank = new Bank2();

          // Open a file for reading.
         Scanner inputSource = new Scanner (new File("BankData.txt"));
       
         // while there are more tokens to read from the input source...
     while (inputSource.hasNext()) 
     {

        // Read one line of input from the file into an Account object
        Account acct = InputManager.readOneAccountFrom (inputSource);
        
        // Store the account info in the bank.
        myBank.addAccount(acct);
     } // end while

         

     return myBank;

   } // end readFromFile
    
   // reads the user value
    public static String readUserId(IOHandlerInterface ioh)
    {
        String user = ioh.get("Enter your id");
        
        return user;
     
    }
    
    public static boolean isValid(bankInterface bank, String id,IOHandlerInterface ioh )
    {
         //assign the value to false
        boolean returnValue = false;
        
        Account account = bank.search(id);
        // If the search method returns null (when searching for id), this method returns false. Otherwise, returns true.
        if(account!= null && account.getId().equals(id))
        {
            returnValue = true;
        }
        
        
        return(returnValue);
        
    }
        
       
        
        
  
    
    
// end IOHandlerStandard
} // end ATM