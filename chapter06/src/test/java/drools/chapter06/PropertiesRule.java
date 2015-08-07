package drools.chapter06;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * @author tanabe
 */
public class PropertiesRule implements TestRule {

  @Override
  public Statement apply(Statement base, Description description) {
    return new Statement() {
      @Override
      public void evaluate() throws Throwable {
        System.setProperty("drools.dateformat", "yyyy-MM-dd");
        base.evaluate();
      }
    };
  }

}
