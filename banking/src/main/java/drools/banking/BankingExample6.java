package drools.banking;

import java.time.LocalDate;

public class BankingExample6 {

  public static void main(String[] args) {

    Account account1 = new Account(1);
    Account account2 = new Account(2);
    
    Cashflow[] cashflows = {
        new AllocatedCashflow(account1, LocalDate.of(2007, 1, 1), 300.00, TypedCashflow.CREDIT),
        new AllocatedCashflow(account1, LocalDate.of(2007, 2, 5), 100.00, TypedCashflow.CREDIT),
        new AllocatedCashflow(account2, LocalDate.of(2007, 3, 11), 500.00, TypedCashflow.CREDIT),
        new AllocatedCashflow(account1, LocalDate.of(2007, 2, 7), 800.00, TypedCashflow.DEBIT),
        new AllocatedCashflow(account2, LocalDate.of(2007, 3, 2), 400.00, TypedCashflow.DEBIT),
        new AllocatedCashflow(account1, LocalDate.of(2007, 4, 1), 200.00, TypedCashflow.CREDIT),
        new AllocatedCashflow(account1, LocalDate.of(2007, 4, 5), 300.00, TypedCashflow.CREDIT),
        new AllocatedCashflow(account2, LocalDate.of(2007, 5, 11), 700.00, TypedCashflow.CREDIT),
        new AllocatedCashflow(account1, LocalDate.of(2007, 5, 7), 900.00, TypedCashflow.DEBIT),
        new AllocatedCashflow(account2, LocalDate.of(2007, 5, 2), 100.00, TypedCashflow.DEBIT),
    };

    new RuleRunner()
        .runRules(new String[] { "BankingExample6.drl" }, cashflows);
  }

}
