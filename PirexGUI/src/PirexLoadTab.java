import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;
import java.io.File;

/**
 * "Load Documents" tab layout.
 *
 * @author CSC 131-01 Group 4 : Arshia Atai, Dakota Conn, Camoob Lee, 
 *								Thuyvy Nguyen, Roberto Ochoa-Sanchez, 
 *								Andrew Tran, Sergio Zavala
 */
public class PirexLoadTab extends JPanel implements ActionListener {
	private static final int I_PADDING = PirexTabbedPane.I_PADDING;
	private static final int O_PADDING = PirexTabbedPane.O_PADDING;;
	private static final String[] fileTypes = new String[]{"Project Gutenberg File"};
	
	private final JFileChooser fileChooser = new JFileChooser();

	private final JLabel     fileLabel        = new JLabel("Text File:"); 
	private final JTextField fileTextField    = new JTextField();
	private final JButton	 fileBrowseButton = new JButton("Browse");

	private final JLabel     fileTypeLabel    = new JLabel("Text File Type:");
	private final JComboBox  fileTypeComboBox = new JComboBox(fileTypes);

	private final JLabel     titleLabel       = new JLabel("Title:"); 
	private final JTextField titleTextField   = new JTextField();
	private final JLabel     authorLabel      = new JLabel("Author:"); 
	private final JTextField authorTextField  = new JTextField();

	private final JPanel separator = Pirex.borderLayoutWrap(null, null, new JSeparator(), null, null);

	private final JButton    processButton    = new JButton("Process");
	private final JTextArea  summaryTextArea  = new JTextArea("");
	private final JScrollPane summaryScrollPanel = new JScrollPane(summaryTextArea);

	private GridBagConstraints cTitleField, cAuthorField;

	public PirexLoadTab() {
		this.setupLayout();
		this.setupComponentBehavior();
	} 

    /**
     * Add and layout components onto the "Load Documents" tab.
     */
    private void setupLayout() {
        this.setLayout(new BorderLayout());
		this.setupConstraints();

		JPanel titleAuthorFields = new JPanel(new GridBagLayout());
		titleAuthorFields.add(Pirex.inputField(titleLabel, titleTextField, null),   cTitleField);
		titleAuthorFields.add(Pirex.inputField(authorLabel, authorTextField, null), cAuthorField);

		JPanel inputFields = new JPanel(new GridLayout(3, 0));
		inputFields.add(Pirex.inputField(fileLabel, fileTextField, fileBrowseButton));
		inputFields.add(Pirex.inputField(fileTypeLabel, fileTypeComboBox, null));
		inputFields.add(titleAuthorFields);
		inputFields = Pirex.borderLayoutWrap(null, null, inputFields, null, separator);

		JPanel leftAlignedProcessButton = Pirex.leftAlign(processButton);
		JPanel processPanel = Pirex.borderLayoutWrap(leftAlignedProcessButton, null, summaryScrollPanel, null, null);
		JPanel loadTabPanel = Pirex.borderLayoutWrap(inputFields, null, processPanel, null, null);

		this.add(Pirex.withBorder(loadTabPanel), BorderLayout.CENTER);

    }

	/**
	 * Set GridBagConstraints and Borders for
	 * components.
	 */
	private void setupConstraints() {
		cTitleField = new GridBagConstraints();
		cTitleField.fill = GridBagConstraints.HORIZONTAL;
		cTitleField.weightx = 0.6;

		cAuthorField = (GridBagConstraints) cTitleField.clone();
		cAuthorField.insets = new Insets(0, 2 * I_PADDING, 0, 0);
		cAuthorField.weightx = 1 - cTitleField.weightx;

		separator.setBorder(new EmptyBorder(0, 0, 2 * I_PADDING, 0));
	}

	
    /**
     * Setup component behavior and action listeners.
     */
	private void setupComponentBehavior() {
		fileTextField.setEditable(false);
		processButton.setEnabled(false);

        summaryTextArea.setEditable(false);
        summaryTextArea.setLineWrap(true);
        summaryTextArea.setWrapStyleWord(true);

		fileBrowseButton.addActionListener(this);
		processButton.addActionListener(this);
	}

	private void toBlankLoadState() {
		processButton.setEnabled(false);
		fileTextField.setText("");
		titleTextField.setText("");
		authorTextField.setText("");
	}

	private void toLoadFileSelectedState() {
		processButton.setEnabled(true);
	}

	private void toLoadSummaryState() {
		this.toBlankLoadState();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == fileBrowseButton) {
			int returnVal = fileChooser.showOpenDialog(this);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				fileTextField.setText(file.getPath());
				this.toLoadFileSelectedState();
			}
		}
		else if (e.getSource() == processButton) {
			this.toLoadSummaryState();
		}
	}
}	
