import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

/**
 * "Load Documents" tab layout.
 *
 * @author CSC 131-01 Group 4 : Arshia Atai, Dakota Conn, Camoob Lee, 
 *								Thuyvy Nguyen, Roberto Ochoa-Sanchez, 
 *								Andrew Tran, Sergio Zavala
 */
public class PirexLoadTab extends JPanel {
	private static final int I_PADDING = PirexTabbedPane.I_PADDING;
	private static final int O_PADDING = PirexTabbedPane.O_PADDING;;
	
	private final JLabel     fileLabel         = new JLabel("Text File:"); 
	private final JTextField fileTextField     = new JTextField();
	private final JButton	 fileBrowseButton  = new JButton("Browse");

	private final JLabel     fileTypeLabel     = new JLabel("Text File Type:");
	private final JComboBox  fileTypeComboBox  = new JComboBox();

	private final JLabel     titleLabel        = new JLabel("Title:"); 
	private final JTextField titleTextField    = new JTextField();
	private final JLabel     authorLabel       = new JLabel("Author:"); 
	private final JTextField authorTextField   = new JTextField();

	private final JSeparator separator         = new JSeparator();
	private final JButton    i_processButton   = new JButton("Process");
	private final JTextArea  i_summaryTextArea = new JTextArea("");


	private final JPanel      filePanel        = new JPanel(new GridBagLayout());
	private final JPanel      fileTypePanel    = new JPanel(new GridBagLayout());
	private final JPanel 	  titleAuthorPanel = new JPanel(new GridBagLayout());
	private final JPanel      processButton    = new JPanel(new BorderLayout());
	private final JScrollPane summaryTextArea  = new JScrollPane(i_summaryTextArea);

	private final JPanel      loadTabPanel     = new JPanel(new GridBagLayout());

	private GridBagConstraints 
		cFileLabel, cFileTextField, cFileBrowseButton, 
		cFileTypeLabel, cFileTypeComboBox, 
		cTitleLabel, cTitleTextField, cAuthorLabel, cAuthorTextField,
		cSeparator,
		cProcessButton,
		cSummaryTextArea,
		cFilePanel, cFileTypePanel, cTitleAuthorPanel,
		cLoadTabPanel;

	public PirexLoadTab() {
		this.setupLayout();
	} 

	private void setupComponentBehavior() {
	}

    /**
     * Add and layout components onto the "Load Documents" tab.
     */
    private void setupLayout() {
        this.setLayout(new GridBagLayout());
        this.setComponentConstraints();

		this.add(loadTabPanel, cLoadTabPanel);

		loadTabPanel.add(filePanel,        cFilePanel);
		loadTabPanel.add(fileTypePanel,    cFileTypePanel);
		loadTabPanel.add(titleAuthorPanel, cTitleAuthorPanel);
		loadTabPanel.add(separator,        cSeparator);
		loadTabPanel.add(processButton,    cProcessButton);
		loadTabPanel.add(summaryTextArea,  cSummaryTextArea);

		filePanel.add(fileLabel,        cFileLabel);
		filePanel.add(fileTextField,    cFileTextField);
		filePanel.add(fileBrowseButton, cFileBrowseButton);
		
		fileTypePanel.add(fileTypeLabel,    cFileTypeLabel);
		fileTypePanel.add(fileTypeComboBox, cFileTypeComboBox);

		titleAuthorPanel.add(titleLabel,      cTitleLabel);
		titleAuthorPanel.add(titleTextField,  cTitleTextField);
		titleAuthorPanel.add(authorLabel,     cAuthorLabel);
		titleAuthorPanel.add(authorTextField, cAuthorTextField);

		processButton.add(i_processButton, BorderLayout.WEST);
    }

    /**
     * Set all of the GridBagConstraints layout options
     * for each component in the "Load Documents" tab.
     */
    private void setComponentConstraints() {
		cLoadTabPanel = PirexTabbedPane.outerPanelConstraints();

		cFilePanel              = PirexTabbedPane.rowConstraints(0);
		cFileTypePanel          = PirexTabbedPane.rowConstraints(1); 
		cTitleAuthorPanel       = PirexTabbedPane.rowConstraints(2); 
		cSeparator              = PirexTabbedPane.rowConstraints(3);
        cSeparator.insets       = new Insets(I_PADDING, 0, 2 * I_PADDING, 0);
		cProcessButton          = PirexTabbedPane.rowConstraints(4);
		cSummaryTextArea        = PirexTabbedPane.bothFill();
		cSummaryTextArea.gridy  = 5;

		cFileLabel            = PirexTabbedPane.labelConstraints();
		cFileTextField        = PirexTabbedPane.horizontalFill();
        cFileTextField.insets = new Insets(0, 0, 0, I_PADDING);
		cFileBrowseButton     = new GridBagConstraints();

		cFileTypeLabel    = PirexTabbedPane.labelConstraints(); 
		cFileTypeComboBox = PirexTabbedPane.horizontalFill();

		cTitleLabel             = PirexTabbedPane.labelConstraints(); 
		cTitleTextField         = PirexTabbedPane.fieldConstraints();
        cTitleTextField.weightx = 0.6;

		cAuthorLabel             = PirexTabbedPane.labelConstraints(); 
		cAuthorTextField         = PirexTabbedPane.horizontalFill();
        cAuthorTextField.weightx = 1 - cTitleTextField.weightx;
	}
}
