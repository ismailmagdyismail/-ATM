public class Deposit extends Transaction {

    private Keypad keypad ;
    private DepositSlot depositSlot;
    private double amount ;


    public Deposit(int accountNumber , Screen screen , DataBase dataBase,Keypad keypad,DepositSlot depositSlot){
        super(accountNumber,screen,dataBase);
        this.depositSlot = depositSlot;
        this.keypad = keypad;
    }

    @Override
    public void performTransaction() {
        int accountNumber = getAccountNumber();
        DataBase dataBase = getDataBase();
        Screen  screen = getScreen();

        screen.display("Enter the amount to be deposited (or 0 to EXIT): ");

        amount = keypad.getInput();
        if(depositSlot.isEnvelopeReceived() && amount!=0){
            dataBase.deposit(accountNumber,amount);
            screen.display("Money successfully added to your account\n");
        }
        else if(amount == 0 ){
            screen.display("Cancelling Transaction....\n");
        }
        else{
            screen.display("You did not insert an envelope, so the ATM has canceled your transaction.");
        }
    }

}
