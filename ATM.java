public class ATM {
    private Screen screen;
    private CashDispenser dispenser;
    private DataBase dataBase;
    private DepositSlot depositSlot;
    private Keypad keypad;
    private int currentAccountNumber;
    private boolean userAuthenticated;
    
    ATM(){
        dispenser = new CashDispenser(500*20);
        dataBase = new DataBase();
        screen = new Screen();
        depositSlot = new DepositSlot();
        keypad = new Keypad();
        currentAccountNumber = 0;
        userAuthenticated = false;
    }

    public void run(){
        while (true){
            screen.display("Welcome.....\n");
            authenticateUser();
            while (true){
                int menuOption = displayMenu();
                if(menuOption == 4){
                    screen.display("Logging out.....");
                    userAuthenticated = false;
                    currentAccountNumber = 0;
                    break;
                }
                Transaction transaction ;
                if(menuOption == 1){
                    transaction = new Withdrawal(currentAccountNumber,screen,dataBase,keypad,dispenser);
                }
                else if(menuOption == 2){
                    transaction = new Deposit(currentAccountNumber,screen,dataBase,keypad,depositSlot);
                }
                else{
                    transaction = new BalanceInquiry(currentAccountNumber,screen,dataBase);
                }
                transaction.performTransaction();
            }
        }
    }

    private void  authenticateUser(){
        while (!userAuthenticated){
            // prompting user for account number , PIN
            screen.display("Enter your account number : ");
            int accountNumber = keypad.getInput();
            screen.display("Enter your PIN  : ");
            int PIN = keypad.getInput();

            // search dataBase for user credentials
            userAuthenticated=dataBase.authenticateUser(accountNumber,PIN);
            if(!userAuthenticated)
            {
                // prompting user that account was not found
                screen.display("Credentials not found...\n");
            }
            else{
                currentAccountNumber = accountNumber;
            }
        }
    }

    private int displayMenu(){
        String menu[]={"Withdraw","Deposit","ViewBalance","EXIT"};
        int input = 0 ;
        boolean valid = false;
        while (!valid) // validating user's input
        {
            for (int i = 0; i <4; ++i) {
                String option = new String();
                option += i+1;
                option += '-';
                option += menu[i];
                screen.display(option+'\n');
            }
            input = keypad.getInput();
            if(input>0&&input<5)
            {
                valid = true;
            }
        }
        // return user's choice
        return input;
    }
}
