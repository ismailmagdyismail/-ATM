public class BalanceInquiry extends Transaction {

    public BalanceInquiry(int accountNumber , Screen screen , DataBase dataBase){
        super(accountNumber , screen ,dataBase);
    }

    @Override
    public void performTransaction() {
        int accountNumber = getAccountNumber();
        Screen screen = getScreen();
        DataBase dataBase = getDataBase();
        double amount = dataBase.getAccountBalance(accountNumber);
        screen.display("Available balance :"+amount+"$\n");
    }
}
