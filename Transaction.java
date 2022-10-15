public abstract class Transaction {
    private int accountNumber;
    private Screen screen;
    private DataBase dataBase;

    public Transaction(int accountNumber,Screen screen,DataBase dataBase){
        this.accountNumber = accountNumber;
        this.screen = screen;
        this.dataBase = dataBase;
    }

    public abstract void performTransaction();

    public int getAccountNumber() {
        return accountNumber;
    }

    public DataBase getDataBase() {
        return dataBase;
    }

    public Screen getScreen() {
        return screen;
    }


}
