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
    //Component padding constants
    private static final int I_BORDER = 10;
    private static final int O_BORDER = 20;

    //Components to be wrapped in a JContainer
    private final JList       i_searchResultList     = new JList(); 
    private final JLabel      i_searchResultCount    = new JLabel(" "); 
    private final JTextArea   i_selectedDocumentText = new JTextArea(""); 

    //Search tab components
    private final JLabel      queryLabel           = new JLabel("Query:");
    private final JTextField  queryTextField       = new JTextField();
    private final JButton     queryClearButton     = new JButton("Clear");
    private final JScrollPane searchResultList     = new JScrollPane(i_searchResultList); 
    private final JPanel      searchResultCount    = new JPanel(new BorderLayout());
    private final JScrollPane selectedDocumentText = new JScrollPane(i_selectedDocumentText);

    //Search tab component constraints for GridBagLayout
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
        i_selectedDocumentText.setEditable(false);
        i_selectedDocumentText.setLineWrap(true);
        i_selectedDocumentText.setWrapStyleWord(true);
        selectedDocumentText.setVerticalScrollBarPolicy(
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        this.searchResultCount.add(i_searchResultCount, BorderLayout.WEST);

        this.queryClearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                toBlankQueryState();
            } 
        });

        this.queryTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                toSearchResultsState(queryTextField.getText());
            } 
        });
    }

    private void toBlankQueryState() {
        this.queryTextField.setText("");

        JLabel searchResultCountLabel = (JLabel) (this.searchResultCount
                                                      .getComponents()[0]);
        searchResultCountLabel.setText(" ");
        this.i_selectedDocumentText.setText("");
        this.i_searchResultList.setListData(new String[0]);
    }

    private void toSearchResultsState(String query) {
        //SAMPLE DATA FOR TESTING PURPOSES -------------
        String[] resultsList = new String[] {
            resultString("Charles Dickens", "Bleak House", 276, "I knew he meant well in"), 
            resultString("Charles Dickens", "Bleak House", 519, "It was one of those"),
            resultString("Charles Dickens", "Bleak House", 635, "There may be some motions"), 
            resultString("Charles Dickens", "Bleak House", 769, "They attend matins with"), 
            resultString("Charles Dickens", "Bleak House", 947, "This proper name, so used"), 
            resultString("Charles Dickens", "Bleak House", 954, "The red bit, the black") 
        };
        this.i_selectedDocumentText.setText("I knew he meant well in paying me this compliment, so I laughed at myself for blushing at it when he had shut the door and got upon the box; and we all three laughed and chatted about our inexperience and the strangeness of London until we turned up under an archway to our destination—a narrow street of high houses like an oblong cistern to hold the fog. There was a confused little crowd of people, principally children, gathered about the house at which we stopped, which had a tarnished brass plate on the door with the inscription JELLYBY.");
        //SAMPLE DATA FOR TESTING PURPOSES -------------

        this.i_searchResultList.setListData(resultsList);
        this.i_searchResultCount.setText(String.format(
            "Retrieved %d documents from query \"%s\"", resultsList.length, query
        ));
    }

    private static String resultString(String author, String title, int docNumber, String shortenedText) {
        return String.format("%s  %s  %4d  %s", author, title, docNumber, shortenedText);
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
