package drools.banking;

public class BankingExample1 {

  public static void main(String[] args) {
    new RuleRunner().runRules(
        new String[]{"BankingExample1.drl"}, new Object[0]);
  }
}
