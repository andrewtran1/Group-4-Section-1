import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PirexSummarizeTab extends JPanel {
	private final int O_PADDING = PirexTabbedPane.O_PADDING;
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
     * Setup component behavior and action listeners.
     */
    private void setupComponentBehavior() {
        summaryTextArea.setEditable(false);
        summaryTextArea.setLineWrap(true);
        summaryTextArea.setWrapStyleWord(true);
        summaryScrollPane.setVerticalScrollBarPolicy(
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    /**
     * Add and layout components onto the "Summarize Documents" tab.
     */
    private void setupLayout() {
        this.setLayout(new BorderLayout());
		this.add(Pirex.withBorder(Pirex.borderLayoutWrap(
			null, null, summaryScrollPane, null, null)), BorderLayout.CENTER);
    }
}
