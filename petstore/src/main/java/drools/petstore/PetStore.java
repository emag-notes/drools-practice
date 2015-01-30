package drools.petstore;

import java.util.Vector;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;

public class PetStore {

  public static void main(String[] args) {
    new PetStore().init(true);
  }
  
  public void init(boolean exitOnClose) {
    KieServices ks = KieServices.Factory.get();
    KieContainer kc = ks.getKieClasspathContainer();
    
    Vector<Product> stock = new Vector<>();
    stock.add(new Product("Gold Fish", 5));
    stock.add(new Product("Fish Tank", 25));
    stock.add(new Product("Fish Food", 2));
    
    PetStoreUI ui = new PetStoreUI(stock, new CheckoutCallback(kc));
    ui.createAndShowGUI(exitOnClose);
  }
  
}
