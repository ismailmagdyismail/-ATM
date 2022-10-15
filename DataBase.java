import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private List<Account> accounts = new ArrayList<>(); // list of account stored in the dataBase

    public DataBase(){
        // dummy account for testing
        Account account1 = new Account(12345,12345,1200.0);
        Account account2 = new Account(98765,56789,200.0);

        // storing account in dataBase
        accounts.add(account1);
        accounts.add(account2);
    }

    public boolean authenticateUser(int accountNumber , int PIN){ // validating user's credentials
        Account account = findAccount(accountNumber); // searching for account
        if(account!=null)
            return account.validatePin(PIN); // validating PIN
        return false;
    }


    public double getAccountBalance(int accountNumber) { // getting account balance
        Account account = findAccount(accountNumber);// searching for account
        return account.getBalance();
    }

    public void deposit(int accountNumber,double amount){ // depositing money into user's account
        Account account = findAccount(accountNumber);
        account.deposit(amount);
    }

    public void withdraw(int accountNumber , double amount){ // withdrawing money from user's account
        Account account = findAccount(accountNumber);
        account.withdraw(amount);
    }

    public boolean isSufficient(int accountNumber , double amount){
        Account account = findAccount(accountNumber);
        return account.isSufficient(amount);
    }
    private Account findAccount(int accountNumber){ // utility function that searches for the account
        for (Account account: accounts){ // searching for user's account number
            if(accountNumber == account.getAccountNumber()) {
                return account;
            }
        }
        return null;
    }

}
