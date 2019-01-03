import javax.swing.*;
import java.awt.*;

/**
 * JTabbedPane to hold search, load and summarize tabs.
 *
 */
public class PirexTabbedPane extends JTabbedPane {
	public static final int O_PADDING = 20;
	public static final int I_PADDING = 10;

    public PirexTabbedPane() {
        super();
        this.setupLayout();
    }

    public void setupLayout() {
        JPanel searchTab    = new PirexSearchTab();
        JPanel loadTab      = new PirexLoadTab();
        JPanel summarizeTab = new PirexSummarizeTab();

        this.addTab("Search for Documents", null, searchTab,    null);
        this.addTab("Load Documents",       null, loadTab,      null);
        this.addTab("Summarize Documents",  null, summarizeTab, null);
    }

	public static GridBagConstraints bothFill() {
		GridBagConstraints bothFill = new GridBagConstraints();
        bothFill.fill    = GridBagConstraints.BOTH; 
        bothFill.weightx = 1;
        bothFill.weighty = 1;

		return bothFill;
	}
	
	public static GridBagConstraints horizontalFill() {
		GridBagConstraints horizontalFill = new GridBagConstraints();
        horizontalFill.fill    = GridBagConstraints.HORIZONTAL; 
        horizontalFill.weightx = 1;

		return horizontalFill;
	}

	public static GridBagConstraints outerPanelConstraints() {
		GridBagConstraints outerPanelConstraints = bothFill();
		outerPanelConstraints.insets =
			new Insets(O_PADDING, O_PADDING, O_PADDING, O_PADDING);
		return outerPanelConstraints;
	}

	public static GridBagConstraints rowConstraints(int row) {
		GridBagConstraints rowConstraints   = horizontalFill();
        rowConstraints.insets               = new Insets(0, 0, I_PADDING, 0);
		rowConstraints.gridy = row;

		return rowConstraints;
	}

	public static GridBagConstraints fieldConstraints() {
		GridBagConstraints fieldConstraints = horizontalFill();
		fieldConstraints.insets             = new Insets(0, 0, 0, I_PADDING);

		return fieldConstraints;
	}

	public static GridBagConstraints labelConstraints() {
		GridBagConstraints labelConstraints = new GridBagConstraints();
		labelConstraints.insets = new Insets(0, 0, 0, I_PADDING);

		return labelConstraints;
	}
}
