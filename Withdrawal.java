public class Withdrawal extends Transaction {
    private Keypad keypad;
    private CashDispenser cashDispenser;
    private double amount;


    public Withdrawal(int accountNumber,Screen screen,DataBase dataBase,Keypad keypad,CashDispenser cashDispenser){
        super(accountNumber,screen,dataBase);
        this.cashDispenser = cashDispenser;
        this.keypad = keypad;
    }

    @Override
    public void performTransaction() {
        int accountNumber = getAccountNumber();
        Screen screen  = getScreen();
        DataBase dataBase = getDataBase();

        boolean isPerformedTransaction = false;
        boolean cancelled = false;

        while (!cancelled && !isPerformedTransaction){
            int input = displayMenu();
            amount = input;

            if(input !=0 ){
                if(dataBase.isSufficient(accountNumber,amount)){
                    if(cashDispenser.isSufficient(amount)){
                        dataBase.withdraw(accountNumber,amount);
                        cashDispenser.dispenseCash(amount);
                        isPerformedTransaction = true;
                        screen.display("Please take your cash from the cash dispenser\n");
                    }
                    else{
                        screen.display("Insufficient cash available in the ATM, please choose a lower amount\n");
                    }
                }
                else{
                    screen.display("Insufficient funds in your account, please choose a lower amount\n");
                }
            }
            else{
                screen.display("Cancelling Transaction....\n");
                cancelled = true;
            }
        }
    }

    private int displayMenu(){
        int[] menu={20,40,60,100,200,0}; // list of  available withdrawal options

        Screen screen = getScreen();
        boolean valid = false;
        int input = 0;

        while (!valid)
        {
            for (int i = 0; i <5; ++i) {
                String option = new String();
                option += i+1;
                option += "-";
                option += menu[i];
                option += '$';
                screen.display(option+'\n');
            }
            screen.display("6-Cancel Transaction\n");
            screen.display("Choose option : ");
            input = keypad.getInput();
            if(validInput(input))
            {
                valid = true;
            }
        }
        return menu[input-1];
    }

    boolean validInput(int input){
        if(input<1||input>6)
            return false;
        return true;
    }
}
