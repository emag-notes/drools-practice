package drools.banking;

import java.time.LocalDate;

public class TypedCashflow extends Cashflow {

  public static final int CREDIT = 0;
  public static final int DEBIT  = 1;
  
  private int type;
  
  public TypedCashflow() {}
  
  public TypedCashflow(LocalDate date, double amount, int type) {
    super(date, amount);
    this.type = type;
  }
  
  public int getType() {
    return type;
  }
  
  public void setType(int type) {
    this.type = type;
  }
  
  @Override
  public String toString() {
    return "TypedCashflow[date=" + getDate() +
            ",amount=" + getAmount() +
            ",type=" + (type == CREDIT ? "Credit" : "Debit") + "]"; 
  }
}
