package drools.decisiontable;

import java.util.Arrays;

import org.kie.api.KieServices;
import org.kie.api.logger.KieRuntimeLogger;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;

public class PricingRuleDTExample {

  public static void main(String[] args) {
    PricingRuleDTExample launcher = new PricingRuleDTExample();
    launcher.executeExapmle();
  }
  
  public int executeExapmle() {
    KieServices ks = KieServices.Factory.get();
    KieContainer kc = ks.getKieClasspathContainer();
    System.out.println(kc.verify().getMessages());
    
    StatelessKieSession ksession = kc.newStatelessKieSession("DecisionTableKS");
    
    KieRuntimeLogger logger = ks.getLoggers().newFileLogger(ksession, "./decisiontable");
    
    Driver driver = new Driver();
    Policy policy = new Policy();
    
    ksession.execute(Arrays.asList(new Object[]{driver, policy}));
    
    System.out.println("BASE PRICE IS: " + policy.getBasePrice());
    System.out.println("DISCOUNT IS: " + policy.getDiscountPercent());
    
    logger.close();
    return policy.getBasePrice();
  }
}
