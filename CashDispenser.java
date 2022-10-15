public class CashDispenser {
    CashDispenser(double amount){
        this.availableMoney = amount;
    }

    public void dispenseCash(double amount){
        if(isSufficient(amount))
            this.availableMoney -= amount;
    }

    public boolean isSufficient(double amount){
        return amount<=this.availableMoney;
    }
    
    private  double availableMoney;
}
