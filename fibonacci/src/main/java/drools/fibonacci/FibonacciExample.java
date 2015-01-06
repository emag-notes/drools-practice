package drools.fibonacci;

import org.kie.api.KieServices;
import org.kie.api.logger.KieRuntimeLogger;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class FibonacciExample {
  
  private static final int SEQUENCE = 5;
  
  public static void main(String[] args) {
    
    KieServices ks = KieServices.Factory.get();
    KieContainer kc = ks.getKieClasspathContainer();
    KieSession ksession = kc.newKieSession("FibonacciKS");
    
    KieRuntimeLogger logger = ks.getLoggers().newFileLogger(ksession, "./fibonacchi");
    
    ksession.insert(new Fibonacci(SEQUENCE));
    ksession.fireAllRules();
    
    logger.close();
    ksession.dispose();
  }

  public static class Fibonacci {
    private int sequence;
    
    private long value;
    
    public Fibonacci() {}
    
    public Fibonacci(int sequence) {
      this.sequence = sequence;
      this.value = -1;
    }
    
    public int getSequence() {
      return this.sequence;
    }
    
    public long getValue() {
      return this.value;
    }
    
    public void setValue(final long value) {
      this.value = value;
    }
    
    @Override
    public String toString() {
      return "Fibonacci(" + this.sequence + "/" + this.value + ")";
    }
    
  }
}
