package drools.banking;

import java.time.LocalDate;

public class AllocatedCashflow extends TypedCashflow {

  private Account account;
  
  public AllocatedCashflow() {}

  public AllocatedCashflow(Account account, LocalDate date, double amount, int type) {
    super(date, amount, type);
    this.account = account;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  @Override
  public String toString() {
    return "AllocatedCashflow[" + "account=" + account + ",date=" + getDate() + 
            ",type=" + (getType() == CREDIT ? "Credit" : "Debit") + 
            ",amount=" + getAmount() + "]";
  }

}
