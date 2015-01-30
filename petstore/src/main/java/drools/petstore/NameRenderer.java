package drools.petstore;

import javax.swing.table.DefaultTableCellRenderer;

public class NameRenderer extends DefaultTableCellRenderer {
  
  public void setValue(Object object) {
    Product item = (Product) object;
    setText(item.getName());
  }

}
