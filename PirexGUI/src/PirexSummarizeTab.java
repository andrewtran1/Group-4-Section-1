import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PirexSummarizeTab extends JPanel {
	private final int O_PADDING                 = PirexTabbedPane.O_PADDING;
	private final JTextArea   i_summaryTextArea = new JTextArea();
    private final JScrollPane summaryTextArea   = 
		new JScrollPane(i_summaryTextArea); 
	private final JPanel      summaryTextPanel  = new JPanel(new BorderLayout());

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
        this.setLayout(new BorderLayout());
		this.add(summaryTextPanel, BorderLayout.CENTER);

		summaryTextPanel.add(summaryTextArea, BorderLayout.CENTER);
		summaryTextPanel.setBorder(
			new EmptyBorder(O_PADDING, O_PADDING, O_PADDING, O_PADDING));
    }
}
