package drools.banking;

import java.time.LocalDate;

public class BankingExample5 {

  public static void main(String[] args) {

    Cashflow[] cashflows = {
        new TypedCashflow(LocalDate.of(2007, 1, 1), 300.00, TypedCashflow.CREDIT),
        new TypedCashflow(LocalDate.of(2007, 1, 5), 100.00, TypedCashflow.CREDIT),
        new TypedCashflow(LocalDate.of(2007, 1, 11), 500.00, TypedCashflow.CREDIT),
        new TypedCashflow(LocalDate.of(2007, 1, 7), 800.00, TypedCashflow.DEBIT),
        new TypedCashflow(LocalDate.of(2007, 1, 2), 400.00, TypedCashflow.DEBIT), };

    new RuleRunner()
        .runRules(new String[] { "BankingExample5.drl" }, cashflows);
  }

}
