package drools.petstore;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.TableColumnModel;

public class PetStoreUI extends JPanel {

  private JTextArea output;
  private TableModel tableModel;
  private CheckoutCallback callback;
  
  public PetStoreUI(Vector<Product> items, CheckoutCallback callback) {
    super(new BorderLayout());
    this.callback = callback;
    
    JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    add(splitPane, BorderLayout.CENTER);
    
    JPanel topHalf = new JPanel();
    topHalf.setLayout(new BoxLayout(topHalf, BoxLayout.X_AXIS));
    topHalf.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));
    topHalf.setMinimumSize(new Dimension(400, 50));
    topHalf.setPreferredSize(new Dimension(450, 250));
    splitPane.add(topHalf);
    
    JPanel bottomHalf = new JPanel(new BorderLayout());
    bottomHalf.setMinimumSize(new Dimension(400, 50));
    bottomHalf.setPreferredSize(new Dimension(450, 300));
    splitPane.add(bottomHalf);
    
    JPanel listContainer = new JPanel(new GridLayout(1, 1));
    listContainer.setBorder(BorderFactory.createTitledBorder("List"));
    topHalf.add(listContainer);
    
    JList<Product> list = new JList<>(items);
    ListSelectionModel listSelectionModel = list.getSelectionModel();
    listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    list.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseReleased(MouseEvent e) {
        JList<Product> jlist = (JList<Product>) e.getSource();
        tableModel.addItem(jlist.getSelectedValue());
      }
    });
    JScrollPane listPane = new JScrollPane(list);
    listContainer.add(listPane);
    
    JPanel tableContainer = new JPanel(new GridLayout(1, 1));
    tableContainer.setBorder(BorderFactory.createTitledBorder("Table"));
    topHalf.add(tableContainer);
    
    tableModel = new TableModel();
    JTable table = new JTable(tableModel);
    table.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseReleased(MouseEvent e) {
        JTable jTable = (JTable) e.getSource();
        TableModel tableModel = (TableModel) jTable.getModel();
        tableModel.removeItem(jTable.getSelectedRow());
      }
    });
    ListSelectionModel tableSelectionModel = table.getSelectionModel();
    tableSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    TableColumnModel tableColumnModel = table.getColumnModel();
    tableColumnModel.getColumn(0).setCellRenderer(new NameRenderer());
    tableColumnModel.getColumn(1).setCellRenderer(new PriceRenderer());
    tableColumnModel.getColumn(1).setMaxWidth(50);
    
    JScrollPane tablePane = new JScrollPane(table);
    tablePane.setPreferredSize(new Dimension(150, 100));
    tableContainer.add(tablePane);
    
    JPanel checkoutPane = new JPanel();
    JButton button = new JButton("Checkout");
    button.setVerticalTextPosition(AbstractButton.CENTER);
    button.setHorizontalTextPosition(AbstractButton.LEADING);
    button.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseReleased(MouseEvent e) {
        JButton button = (JButton) e.getComponent();
        callback.checkout((JFrame) button.getTopLevelAncestor(), tableModel.getItems());
      }
    });
    button.setActionCommand("checkout");
    checkoutPane.add(button);
    bottomHalf.add(checkoutPane, BorderLayout.NORTH);
    
    button = new JButton("Reset");
    button.setVerticalTextPosition(AbstractButton.CENTER);
    button.setHorizontalTextPosition(AbstractButton.TRAILING);
    button.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseReleased(MouseEvent e) {
        output.setText(null);
        tableModel.clear();
        System.out.println("----- Reset ------");
      }
    });
    button.setActionCommand("reset");
    checkoutPane.add(button);
    bottomHalf.add(checkoutPane, BorderLayout.NORTH);
    
    output = new JTextArea(1, 10);
    output.setEditable(false);
    JScrollPane outputPane = new JScrollPane(output,
        ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    bottomHalf.add(outputPane, BorderLayout.CENTER);
    
    this.callback.setOutput(this.output);
  }

  public void createAndShowGUI(boolean exitOnClose) {
    JFrame frame = new JFrame("Per Store Demo");
    frame.setDefaultCloseOperation(exitOnClose ? JFrame.EXIT_ON_CLOSE : JFrame.DISPOSE_ON_CLOSE);
    
    setOpaque(true);
    frame.setContentPane(this);
    
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
  
}
