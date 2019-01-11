import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.*;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * "Load Documents" tab layout.
 *
 * @author CSC 131-01 Group 4 : Arshia Atai, Dakota Conn, Camoob Lee, 
 *								Thuyvy Nguyen, Roberto Ochoa-Sanchez, 
 *								Andrew Tran, Sergio Zavala
 */
public class PirexLoadTab extends JPanel implements ActionListener {
	private static final int I_PADDING = Pirex.I_PADDING;
	private static final int O_PADDING = Pirex.O_PADDING;;
	private static final String summaryFormat = "Opus: %s%nTitle: %s%nAuthor: %s%nOpus size: %d documents%nOpus number: %d%nNew index terms: %d%nNew postings: %d%nTotal index terms: %d%nTotal postings: %d";
	private static final List<FileNameExtensionFilter> extensionFilters = 
		Arrays.asList(
			new FileNameExtensionFilter("Text File", "*.txt", "txt")
		);
	private static final String[] fileTypes = new String[]{
		"Project Gutenberg File"};

	private final JFileChooser fileChooser = new JFileChooser();

	private final JLabel fileLabel = new JLabel("Text File:"); 
	private final JTextField fileTextField = new JTextField();
	private final JButton browseButton = new JButton("Browse");

	private final JLabel fileTypeLabel = new JLabel("Text File Type:");
	private final JComboBox fileTypeComboBox = new JComboBox(fileTypes);

	private final JLabel titleLabel = new JLabel("Title:");
	private final JTextField titleTextField = new JTextField();
	private final JLabel authorLabel = new JLabel("Author:"); 
	private final JTextField authorTextField = new JTextField();

	private final JButton processButton = new JButton("Process");
	private final JTextArea summaryTextArea = new JTextArea("");
	private final JScrollPane summaryScrollPanel = new JScrollPane(summaryTextArea);

	public PirexLoadTab() {
		this.setupLayout();
		this.setupComponentBehavior();
	} 

	/**
	 * Add and layout components onto the "Load Documents" tab.
	 */
	private void setupLayout() {
		this.setLayout(new BorderLayout());

		GridBagConstraints cTitleField, cAuthorField;
		cTitleField = new GridBagConstraints();
		cTitleField.fill = GridBagConstraints.HORIZONTAL;
		cTitleField.weightx = 0.6;
		cAuthorField = (GridBagConstraints) cTitleField.clone();
		cAuthorField.insets = new Insets(0, 2 * I_PADDING, 0, 0);
		cAuthorField.weightx = 1 - cTitleField.weightx;

		JPanel titleAuthorFields = new JPanel(new GridBagLayout());
		titleAuthorFields.add(Pirex.inputField(
			titleLabel, titleTextField, null, Pirex.NO_PADDING),  cTitleField);
		titleAuthorFields.add(Pirex.inputField(
			authorLabel, authorTextField, null, Pirex.NO_PADDING), cAuthorField);

		this.add(Pirex.stackNorth(
			Pirex.addScrollBars(summaryTextArea, false, Pirex.NO_PADDING),
			Arrays.asList(
				Pirex.leftAlign(processButton, Pirex.ROW_PADDING),
				Pirex.fillBoth(new JSeparator(), Pirex.SEPARATOR_PADDING),
				Pirex.fillBoth(titleAuthorFields, Pirex.SEPARATOR_PADDING),
				Pirex.inputField(fileTypeLabel, fileTypeComboBox, null, Pirex.ROW_PADDING), 
				Pirex.inputField(fileLabel, fileTextField, browseButton, Pirex.ROW_PADDING)
			), Pirex.OUTER_BORDER));
	}
	
	/**
	 * Setup component behavior and action listeners.
	 */
	private void setupComponentBehavior() {
		fileTextField.setEditable(false);
		extensionFilters.stream()
			.forEach(fileChooser::addChoosableFileFilter);

		processButton.setEnabled(false);

		summaryTextArea.setEditable(false);
		summaryTextArea.setLineWrap(true);
		summaryTextArea.setWrapStyleWord(true);

		browseButton.addActionListener(this);
		processButton.addActionListener(this);
	}

	/**
	 * Respond to events.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == browseButton) {
			int returnVal = fileChooser.showOpenDialog(this);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				this.toLoadFileSelectedState();
			} else if (returnVal == JFileChooser.CANCEL_OPTION) {
				this.toLoadFileSelectionCanceledState();
			}
		} else if (e.getSource() == processButton) {
			this.toLoadSummaryState();
		}
	}

	/**
	 * Clear out all input fields.
	 */
	private void toBlankLoadState() {
		processButton.setEnabled(false);
		fileTypeComboBox.setSelectedIndex(0);
		fileTextField.setText("");
		titleTextField.setText("");
		authorTextField.setText("");
	}

	/**
	 * After file is selected, display path in fileTextField
	 * and enable the process button.
	 */
	private void toLoadFileSelectedState() {
		fileTextField.setText(fileChooser
			.getSelectedFile()
			.getPath());
		processButton.setEnabled(true);
		summaryTextArea.setText("");
	}

	/**
	 * If the cancel button is hit in the JFileChooser,
	 * clear the file text field and disable the process button.
	 */
	private void toLoadFileSelectionCanceledState() {
		processButton.setEnabled(false);
		fileTextField.setText("");
	}

	/**
	 * Process choosen file and display summary info.
	 */
	private void toLoadSummaryState() {
		String title = titleTextField.getText();
		title = title.matches("\\s*") ? "None" : title;
		String author = authorTextField.getText();
		author = author.matches("\\s*") ? "Anonymous" : author;

		String loadSummary = 
			String.format(
				summaryFormat,
				fileTextField.getText(),
				title,
				author,
				0, 0, 0, 0, 0, 0);

		this.toBlankLoadState();
		this.summaryTextArea.setText(loadSummary);
	}
}	
