import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class PirexSummarizeTab extends JPanel {
	private final JTextArea summaryTextArea = new JTextArea();
	private final JScrollPane summaryScrollPane = new JScrollPane(summaryTextArea); 
	private final JPanel summaryTextPanel = new JPanel(new BorderLayout());

	/**
	 * Constructor for "Summarize Documents" tab.
	 */
	public PirexSummarizeTab() {
		this.setupComponentBehavior();
		this.setupLayout();
	} 

	/**
	 * Add and layout components onto the "Summarize Documents" tab.
	 */
	private void setupLayout() {
		this.setLayout(new BorderLayout());
		this.add(Pirex.fillBoth(summaryScrollPane, Pirex.OUTER_BORDER));
	}

	/**
	 * Setup component behavior and action listeners.
	 */
	private void setupComponentBehavior() {
		summaryTextArea.setEditable(false);
		summaryTextArea.setLineWrap(true);
		summaryTextArea.setWrapStyleWord(true);
		summaryScrollPane.setVerticalScrollBarPolicy(
			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	}

	public void updateSummaryInformation() {
		
	}
}
