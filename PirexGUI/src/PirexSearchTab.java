import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * "Search for Documents" tab layout.
 *
 * @author CSC 131-01 Group 4 : Arshia Atai, Dakota Conn, Camoob Lee, 
 *                              Thuyvy Nguyen, Roberto Ochoa-Sanchez, 
 *                              Andrew Tran, Sergio Zavala
 */
public class PirexSearchTab extends JPanel 
		implements ActionListener, ListSelectionListener {
	private static final int I_PADDING = PirexTabbedPane.I_PADDING;
	private static final int O_PADDING = PirexTabbedPane.O_PADDING;;

	private SearchResult[] searchResults;

	private class SearchResult {
		private static final int MAX_LENGTH = 70;
		public final String author, title, fullText;
		public final int    docNumber;

		public SearchResult(String author, String title, int docNumber, String fullText) {
			this.author    = author;
			this.title     = title;
			this.docNumber = docNumber;
			this.fullText  = fullText;
		}
		
    	public String toString() {
        	return String.format("%s  %s  %4d  %s", 
				author, title, docNumber, this.shortenedText());
    	}

		private String shortenedText() {
			String[] words = this.fullText.split(" ");
			String shortText = words.length != 0 ? words[0] : ""; 

			for (int i = 1; i < words.length && (shortText + words[i]).length() < MAX_LENGTH; i++) {
				shortText += " " + words[i];
			}

			return shortText;
		}
	}

	//Layer 4:
    private final JList      i_searchResultList     = new JList(); 
    private final JLabel     i_searchResultCount    = new JLabel(" "); 

    //Layer 3:
    private final JTextField  i_queryTextField       = new JTextField();
    private final JScrollPane searchResultList       = new JScrollPane(i_searchResultList); 
    private final JPanel      searchResultCount      = new JPanel(new BorderLayout());
    private final JTextArea   i_selectedDocumentText = new JTextArea(""); 

	//Layer 2:
    private final JLabel      queryLabel           = new JLabel("Query:");
	private final JPanel      queryTextField       = new JPanel(new BorderLayout());
    private final JButton     queryClearButton     = new JButton("Clear");
	private final JPanel      resultsPanel         = new JPanel(new BorderLayout());
    private final JScrollPane selectedDocumentText = new JScrollPane(i_selectedDocumentText);

	//Layer 1:
    private final JPanel queryPanel           = new JPanel(new BorderLayout());
	private final JPanel resultSelectionPanel = new JPanel(new GridLayout(2, 1));

	//Layer 0:
    private final JPanel searchTabPanel = new JPanel(new BorderLayout());

    private ListSelectionModel resultListSelectionModel;

    /**
     * Constructor for "Search for Documents" tab.
     */
    public PirexSearchTab() {
        super();
        this.setupLayout();        
        this.setupComponentBehavior();
    }

    /**
     * Add and layout components onto the "Search for Documents" tab.
     */
    private void setupLayout() {
        this.setLayout(new BorderLayout());
		
		//Layer 0:
		this.add(searchTabPanel, BorderLayout.CENTER);

		//Layer 1:
		searchTabPanel.add(queryPanel,           BorderLayout.NORTH);
		searchTabPanel.add(resultSelectionPanel, BorderLayout.CENTER);

		//Layer 2
		queryLabel.setVerticalAlignment(JLabel.TOP);
		queryPanel.add(queryLabel,       BorderLayout.WEST); 
		queryPanel.add(queryTextField,   BorderLayout.CENTER); 
		queryPanel.add(queryClearButton, BorderLayout.EAST); 
		resultSelectionPanel.add(resultsPanel);
		resultSelectionPanel.add(selectedDocumentText);

		//Layer 3
		queryTextField.add(i_queryTextField, BorderLayout.CENTER);
		resultsPanel.add(searchResultList,   BorderLayout.CENTER);
		resultsPanel.add(searchResultCount,  BorderLayout.SOUTH);

		//Layer 4
		searchResultCount.add(i_searchResultCount, BorderLayout.WEST);
		
		//Component Padding
		queryTextField.setBorder(new EmptyBorder(0, I_PADDING, 0, I_PADDING));
		queryPanel.setBorder(new EmptyBorder(0, 0, I_PADDING, 0));
		searchResultCount.setBorder(new EmptyBorder(I_PADDING / 2, 0, I_PADDING, 0));
		searchTabPanel.setBorder(new EmptyBorder(O_PADDING, O_PADDING, O_PADDING, O_PADDING));
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

		this.queryClearButton.addActionListener(this);
        this.i_queryTextField.addActionListener(this);
        this.resultListSelectionModel = this.i_searchResultList.getSelectionModel();
        this.resultListSelectionModel.addListSelectionListener(this);
    }

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == queryClearButton) {
			this.toBlankQueryState();
		}
		else {
			this.toSearchResultsState(e.getActionCommand());
		}
	}

    public void valueChanged(ListSelectionEvent e) {
		ListSelectionModel lsm = (ListSelectionModel) e.getSource();
		
		if (lsm.isSelectionEmpty()) {
		    i_selectedDocumentText.setText(""); 
		}
		else {
			int min = e.getFirstIndex();
			int max = e.getLastIndex();

			for (int i = min; i <= max; i++) {
				if (lsm.isSelectedIndex(i)) {
					this.toLongFormDisplayState(i);
				}
			}
		}
	}

    private void toBlankQueryState() {
        i_queryTextField.setText("");

        i_searchResultCount.setText(" ");
        i_selectedDocumentText.setText("");
        i_searchResultList.setListData(new String[0]);
    }

    private void toSearchResultsState(String query) {
		this.searchResults = this.getResultList(query);
		String[] resultsToString = Arrays.stream(this.searchResults)
										 .map(result -> result.toString())
										 .toArray(String[]::new);
        i_searchResultCount.setText(
			String.format("Retrieved %d documents", searchResults.length));

        i_selectedDocumentText.setText("");
        i_searchResultList.setListData(resultsToString);
    }

    private void toLongFormDisplayState(int index) {
        this.i_selectedDocumentText.setText(this.searchResults[index].fullText);
    }

	private SearchResult[] getResultList(String query) {
		return new SearchResult[] {
			new SearchResult("Author", "Title", 1, "Calculates the number of times that this tokenizer's nextToken method can be called before it generates an exception."),
			new SearchResult("Author", "Title", 2, "Returns the same value as the hasMoreTokens method."),
			new SearchResult("Author", "Title", 3, "Returns the same value as the hasMoreTokens method."),
			new SearchResult("Author", "Title", 4, "Returns the same value as the nextToken method, except that its declared return value is Object rather than String."),
			new SearchResult("Author", "Title", 5, "Returns the next token from this string tokenizer."),
			new SearchResult("Author", "Title", 6, "Returns the next token in this string tokenizer's string."),
		};
	}
}
