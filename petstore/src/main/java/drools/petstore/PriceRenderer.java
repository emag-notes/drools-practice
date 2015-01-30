package drools.petstore;

import javax.swing.table.DefaultTableCellRenderer;

public class PriceRenderer extends DefaultTableCellRenderer {

  public void setValue(Object object) {
    Product item = (Product) object;
    setText(Double.toString(item.getPrice()));
  }

}
