import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PirexSummarizeTab extends JPanel {
	private final JTextArea   i_summaryTextArea = new JTextArea();
    private final JScrollPane summaryTextArea   = new JScrollPane(i_summaryTextArea); 
	private final GridBagConstraints cSummaryTextArea = 
		PirexTabbedPane.outerPanelConstraints();

    /**
     * Constructor for "Summarize Documents" tab.
     */
    public PirexSummarizeTab() {
		this.setupComponentBehavior();
		this.setupLayout();
    } 

    /**
     * Setup component behavior and action listeners.
     */
    private void setupComponentBehavior() {
        i_summaryTextArea.setEditable(false);
        i_summaryTextArea.setLineWrap(true);
        i_summaryTextArea.setWrapStyleWord(true);
        summaryTextArea.setVerticalScrollBarPolicy(
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    /**
     * Add and layout components onto the "Summarize Documents" tab.
     */
    private void setupLayout() {
        this.setLayout(new GridBagLayout());
		this.add(summaryTextArea, cSummaryTextArea);
    }
}
