package drools.petstore;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {

  private String[] columnNames = {"Name", "Price"};
  private List<Product> items;
  
  public TableModel() {
    super();
    this.items = new ArrayList<>();
  }

  @Override
  public int getRowCount() {
    return items.size();
  }

  @Override
  public int getColumnCount() {
    return columnNames.length;
  }

  public String getColumnName(int col) {
    return columnNames[col];
  }
  
  public Class<?> getColumnClass() {
    return Product.class;
  }
  
  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    return items.get(rowIndex);
  }
  
  public void addItem(Product item) {
    items.add(item);
    fireTableRowsInserted(items.size(), items.size());
  }
  
  public void removeItem(int row) {
    items.remove(row);
    fireTableRowsDeleted(row, row);
  }
  
  public List<Product> getItems() {
    return items;
  }
  
  public void clear() {
    int lastRow = items.size();
    items.clear();
    fireTableRowsDeleted(0, lastRow);
  }

}
