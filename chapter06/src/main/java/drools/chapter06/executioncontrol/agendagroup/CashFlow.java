package drools.chapter06.executioncontrol.agendagroup;

import java.util.Date;

/**
 * @author tanabe
 */
public class CashFlow {

  private Date date;
  private double amount;
  private Type type;
  long accountNo;

  public CashFlow(Date date, double amount, Type type, long accountNo) {
    this.date = date;
    this.amount = amount;
    this.type = type;
    this.accountNo = accountNo;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public Type getType() {
    return type;
  }

  public void setType(Type type) {
    this.type = type;
  }

  public long getAccountNo() {
    return accountNo;
  }

  public void setAccountNo(long accountNo) {
    this.accountNo = accountNo;
  }

  @Override
  public String toString() {
    return "CashFlow{" +
      "date=" + date +
      ", amount=" + amount +
      ", type=" + type +
      ", accountNo=" + accountNo +
      '}';
  }

}
