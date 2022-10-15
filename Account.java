public class Account {
    private  int accountNumber ;
    private  int PIN;
    private  double balance;
    public Account(int accountNumber , int PIN , double balance){
        this.accountNumber = accountNumber;
        this.PIN = PIN;
        this.balance = balance;
    }

    public int getAccountNumber(){
        return accountNumber;
    }

    public boolean validatePin(int PIN) {
        return this.PIN == PIN;
    }

    public double getBalance() {
        return balance;
    }

    public boolean isSufficient(double amount ){
        return amount<=balance;
    }

    public void withdraw(double amount){
        if(isSufficient(amount))
            balance -= amount;
    }

    public void deposit(double amount ){
        balance+=amount;
    }


}
