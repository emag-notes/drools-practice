package drools.petstore;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Order {

  private List<Purchase> items;
  
  private double grossTotal = -1;
  private double discountedTotal = -1;
  
  private static String newline = System.getProperty("line.separator");
  
  public Order() {
    this.items = new ArrayList<>();
  }
  
  public void addItem(Purchase item) {
    this.items.add(item);
  }
  
  public List<Purchase> getItems() {
    return this.items;
  }

  public double getGrossTotal() {
    return grossTotal;
  }

  public void setGrossTotal(double grossTotal) {
    this.grossTotal = grossTotal;
  }

  public double getDiscountedTotal() {
    return discountedTotal;
  }

  public void setDiscountedTotal(double discountedTotal) {
    this.discountedTotal = discountedTotal;
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    
    sb.append("ShoppingCart: " + newline);
    
    return items.stream()
      .map(Purchase::toString)
      .collect(Collectors.joining("", "\t", newline));
    
//    items.stream().forEach(item -> {
//      sb.append("\t" + item + newline);
//    });
//    return sb.toString();
  }
  
}
