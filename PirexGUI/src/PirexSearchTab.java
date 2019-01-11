import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * "Search for Documents" tab layout.
 *
 * @author CSC 131-01 Group 4 : Arshia Atai, Dakota Conn, Camoob Lee, 
 *							  Thuyvy Nguyen, Roberto Ochoa-Sanchez, 
 *							  Andrew Tran, Sergio Zavala
 */
public class PirexSearchTab extends JPanel 
		implements ActionListener, ListSelectionListener {
	private static final int I_PADDING = Pirex.I_PADDING;
	private static final int O_PADDING = Pirex.O_PADDING;;

	private final JLabel queryLabel = new JLabel("Query:");
	private final JTextField queryTextField = new JTextField();
	private final JButton clearButton = new JButton("Clear");
	private final JList searchResultList = new JList(); 
	private final JLabel searchResultCount = new JLabel(" "); 
	private final JTextArea selectedDocumentText = new JTextArea(""); 

	private List<PirexDocument> searchResults;
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

		this.add(Pirex.gridColumn(Arrays.asList(
		Pirex.northCenter(
			Pirex.inputField(queryLabel, queryTextField, clearButton, Pirex.ROW_PADDING),
			Pirex.addScrollBars(searchResultList, false, Pirex.ROW_PADDING)),
		Pirex.northCenter(
			Pirex.leftAlign(searchResultCount, Pirex.ROW_PADDING), 
			Pirex.addScrollBars(selectedDocumentText, true, Pirex.NO_PADDING))), Pirex.OUTER_BORDER));
	}

	/**
	 * Setup component behavior and action listeners.
	 */
	private void setupComponentBehavior() {
		selectedDocumentText.setEditable(false);
		selectedDocumentText.setLineWrap(true);
		selectedDocumentText.setWrapStyleWord(true);

		this.clearButton.addActionListener(this);
		this.queryTextField.addActionListener(this);
		this.resultListSelectionModel = this.searchResultList.getSelectionModel();
		this.resultListSelectionModel.addListSelectionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == clearButton) {
			this.toBlankQueryState();
		} else {
			this.toPirexDocumentsState(e.getActionCommand());
		}
	}

	public void valueChanged(ListSelectionEvent e) {
		ListSelectionModel lsm = (ListSelectionModel) e.getSource();
		
		if (lsm.isSelectionEmpty()) {
			selectedDocumentText.setText(""); 
		} else {
			IntStream.range(e.getFirstIndex(), e.getLastIndex() + 1)
				.filter(lsm::isSelectedIndex)
				.forEach(this::toLongFormDisplayState);
		}
	}

	private void toBlankQueryState() {
		queryTextField.setText("");

		searchResultCount.setText(" ");
		selectedDocumentText.setText("");
		searchResultList.setListData(new String[0]);
	}

	private void toPirexDocumentsState(String query) {
		selectedDocumentText.setText("");

		searchResults = this.getResultList(query);

		searchResultList.setListData(searchResults
			.stream()
			.map(result -> result.toString())
			.toArray(String[]::new)
		);

		searchResultCount.setText(
			String.format("Retrieved %d documents", searchResults.size()));
	}

	private void toLongFormDisplayState(int index) {
		this.selectedDocumentText.setText(searchResults.get(index).fullText);
	}

	private List<PirexDocument> getResultList(String query) {
		return Arrays.asList (
			new PirexDocument("Author", "Title", 1, "Calculates the number of times that this tokenizer's nextToken method can be called before it generates an exception."),
			new PirexDocument("Author", "Title", 2, "Returns the same value as the hasMoreTokens method."),
			new PirexDocument("Author", "Title", 3, "Returns the same value as the hasMoreTokens method."),
			new PirexDocument("Author", "Title", 4, "Returns the same value as the nextToken method, except that its declared return value is Object rather than String."),
			new PirexDocument("Author", "Title", 5, "Returns the next token from this string tokenizer."),
			new PirexDocument("Author", "Title", 6, "Returns the next token in this string tokenizer's string.")
		);
	}
}
