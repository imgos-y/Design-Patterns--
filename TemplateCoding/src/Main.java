public class Main {
    public static void main(String[] args) {

        System.out.println(" CashWithdrawal ");
        BankingSystem cash = new CashWithdrawal();
        cash.processTransaction();

        System.out.println();

        System.out.println(" InternationalTransfer ");
        BankingSystem intl = new InternationalTransfer();
        intl.processTransaction();

    }
}

abstract class BankingSystem{
    // This is the base class for all banking transactions It defines the steps of the transaction in order

    public final void processTransaction() {
        // This method runs all steps in the correct order, Subclasses cannot change this order (final)

        verifyIdentity();
        amlHook(); // This is a hook. empty by default.
        checkBalance();
        executeTransfer();
        updateLedger();
        sendNotification();

    }


    protected void  verifyIdentity() {
        System.out.println("Verifying identity.. ");

    }
    protected  void amlHook(){} // Hook method only International Transfer uses this step,Default is empty, so Cash Withdrawal skips it

    protected void checkBalance(){
        System.out.println("Checking available balance in savings account.");
    }
    protected abstract void executeTransfer();
    // each subclass does this differently so that ı use abstract

    protected  void updateLedger(){
        System.out.println("Updating transaction ledger.");
    }
    protected  void sendNotification(){
        System.out.println("Sending notification to customer…\n");
    }

}

class CashWithdrawal extends BankingSystem{
    // This class handles cash withdrawals from ATM

    @Override
    protected void executeTransfer() {  // This step is different: give cash to the customer
        System.out.println("Dispensing cash from ATM.");

    }

}

class InternationalTransfer  extends BankingSystem{



    @Override
    protected void executeTransfer() {
        // This step is different: send money to another bank
        System.out.println("Processing SWIFT transfer to the destination bank");

    }
    @Override
    protected void amlHook() {
        System.out.println("Performing AML compliance check and fraud screening...");
    }
    // International transfers need an extra security check
    // This checks for fraud and money laundering (AML)

}