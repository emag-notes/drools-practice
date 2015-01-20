package drools.banking;

import java.time.LocalDate;

public class BankingExample4 {

  public static void main(String[] args) {

    Cashflow[] cashflows = {
        new Cashflow(LocalDate.of(2007, 1, 1), 300.00),
        new Cashflow(LocalDate.of(2007, 1, 5), 100.00),
        new Cashflow(LocalDate.of(2007, 1, 11), 500.00),
        new Cashflow(LocalDate.of(2007, 1, 7), 800.00),
        new Cashflow(LocalDate.of(2007, 1, 2), 400.00), };

    new RuleRunner()
        .runRules(new String[] { "BankingExample4.drl" }, cashflows);
  }

}
