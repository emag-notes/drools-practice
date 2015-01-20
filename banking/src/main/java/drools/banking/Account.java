package drools.banking;

public class Account {

  private long no;
  private double balance;

  public Account() {}
  
  public Account(long no) {
    this.no = no;
  }

  public long getNo() {
    return no;
  }

  public void setNo(long no) {
    this.no = no;
  }

  public double getBalance() {
    return balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  @Override
  public String toString() {
    return "Account [no=" + no + ", balance=" + balance + "]";
  }

}
