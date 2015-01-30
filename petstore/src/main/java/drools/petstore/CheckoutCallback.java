package drools.petstore;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class CheckoutCallback {

  KieContainer kc;
  JTextArea output;
  
  public CheckoutCallback(KieContainer kc) {
    this.kc = kc;
  }
  
  public void setOutput(JTextArea output) {
    this.output = output;
  }
  
  public String checkout(JFrame frame, List<Product> items) {
    Order order = new Order();
    
    items.forEach(item -> {
      order.addItem(new Purchase(order, item));
    });
    
    KieSession ksession = kc.newKieSession("PetStoreKS");
    ksession.setGlobal("frame", frame);
    ksession.setGlobal("textArea", this.output);
    
    ksession.insert(new Product("Gold Fish", 5));
    ksession.insert(new Product("Fish Tank", 25));
    ksession.insert(new Product("Fish Food", 2));
    ksession.insert(new Product("Fish Food Sample", 0));
    
    ksession.insert(order);
    
    ksession.fireAllRules();
    
    return order.toString();
  }
}
