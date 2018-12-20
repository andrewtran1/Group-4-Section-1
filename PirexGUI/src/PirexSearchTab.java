import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.util.Arrays;
import java.util.List;

/**
 * "Search for Documents" tab layout.
 *
 * @author CSC 131-01 Group 4 : Arshia Atai, Dakota Conn, Camoob Lee, 
 *                              Thuyvy Nguyen, Roberto Ochoa-Sanchez, 
 *                              Andrew Tran, Sergio Zavala
 */
public class PirexSearchTab extends JPanel {
    private static final int I_BORDER = 10;
    private static final int O_BORDER = 20;

    private final JComponent queryLabel           = new JLabel("Query:");
    private final JTextField queryTextField       = new JTextField();
    private final JButton    queryClearButton     = new JButton("Clear");
    private final JList      searchResultList     = new JList(); 
    private final JComponent searchResultCount    = new JPanel(new BorderLayout());
    private final JTextArea  selectedDocumentText = new JTextArea();

    private final GridBagConstraints cQueryLabel           = new GridBagConstraints();
    private final GridBagConstraints cQueryTextField       = new GridBagConstraints();
    private final GridBagConstraints cQueryClear           = new GridBagConstraints();
    private final GridBagConstraints cSearchResultList     = new GridBagConstraints();
    private final GridBagConstraints cSearchResultCount    = new GridBagConstraints();
    private final GridBagConstraints cSelectedDocumentText = new GridBagConstraints();

    private final List<JComponent> components = 
        Arrays.asList(queryLabel, queryTextField, queryClearButton,
                      searchResultList, searchResultCount,
                      selectedDocumentText);

    private final List<GridBagConstraints> constraints = 
        Arrays.asList(cQueryLabel, cQueryTextField, cQueryClear,
                      cSearchResultList, cSearchResultCount,
                      cSelectedDocumentText);

    /**
     * Constructor for "Search for Documents" tab.
     */
    public PirexSearchTab() {
        super();
        this.setupComponentBehavior();
        this.setupLayout();        
    }

    /**
     * Setup component behavior and action listeners.
     */
    private void setupComponentBehavior() {
        JComponent searchResultCountText = new JLabel(" ");
        this.searchResultCount.add(searchResultCountText, BorderLayout.WEST);
        //this.selectedDocumentText.setEditable(false);

        this.queryClearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                toBlankQueryState();
            } 
        });

        this.queryTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            } 
        });
    }

    private void toBlankQueryState() {
        this.queryTextField.setText("");

        JLabel searchResultCountLabel = (JLabel) (this.searchResultCount
                                                      .getComponents()[0]);
        searchResultCountLabel.setText(" ");
        this.selectedDocumentText.setText("");
    }

    /**
     * Add and layout components onto the "Search for Documents" tab.
     */
    private void setupLayout() {
        this.setLayout(new GridBagLayout());
        this.setComponentConstraints();

        for (int i = 0; i < components.size(); i++) {
            this.add(components.get(i), constraints.get(i));
        }
    }

    /**
     * Set all of the GridBagConstraints layout options
     * for each component in the "Search for Documents" tab.
     */
    private void setComponentConstraints() {
        cQueryLabel.gridx = 0;
        cQueryLabel.gridy = 0;

        cQueryLabel.insets = new Insets(O_BORDER, O_BORDER, 0, I_BORDER);


        cQueryTextField.gridx   = 1;
        cQueryTextField.gridy   = 0;

        cQueryTextField.fill    = GridBagConstraints.HORIZONTAL; 
        cQueryTextField.insets = new Insets(O_BORDER, 0, 0, I_BORDER);
        cQueryTextField.weightx = 1;


        cQueryClear.gridx = 2;
        cQueryClear.gridy = 0;

        cQueryClear.insets = new Insets(O_BORDER, 0, 0, O_BORDER);


        cSearchResultList.gridx = 0;
        cSearchResultList.gridy = 1;
        cSearchResultList.gridwidth = 3;

        cSearchResultList.fill  = GridBagConstraints.BOTH;
        cSearchResultList.insets = new Insets(I_BORDER, O_BORDER, I_BORDER / 2, O_BORDER);
        cSearchResultList.weightx = 1;
        cSearchResultList.weighty = 1;


        cSearchResultCount.gridx = 0;
        cSearchResultCount.gridy = 2;
        cSearchResultCount.gridwidth = 3;

        cSearchResultCount.fill = GridBagConstraints.HORIZONTAL;
        cSearchResultCount.weightx = 1;
        cSearchResultCount.insets = new Insets(0, O_BORDER, 0, O_BORDER);


        cSelectedDocumentText.gridx = 0;
        cSelectedDocumentText.gridy = 3;
        cSelectedDocumentText.gridwidth = 3;

        cSelectedDocumentText.fill = GridBagConstraints.BOTH;
        cSelectedDocumentText.insets = new Insets(I_BORDER / 2, O_BORDER, O_BORDER, O_BORDER);
        cSelectedDocumentText.weightx = 1;
        cSelectedDocumentText.weighty = 1;
    } 
}
