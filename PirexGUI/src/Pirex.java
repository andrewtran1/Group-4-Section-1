import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

/**
 * The main class for the Pirex application.
 */
public class Pirex {
	private static final int I_PADDING = PirexTabbedPane.I_PADDING;
	private static final int O_PADDING = PirexTabbedPane.O_PADDING;;

    public static void main(String[] args) {
        PirexFrame frame = new PirexFrame();
        frame.setTitle("Pirex");
        frame.setVisible(true);
    }

	public static JPanel borderLayoutWrap(
			JComponent north, JComponent west, JComponent center, JComponent east, JComponent south) {

		JPanel outerPanel = new JPanel(new BorderLayout());
		if (north != null)  outerPanel.add(north,  BorderLayout.NORTH);
		if (west != null)   outerPanel.add(west,   BorderLayout.WEST);
		if (center != null) outerPanel.add(center, BorderLayout.CENTER);
		if (east != null)   outerPanel.add(east,   BorderLayout.EAST);
		if (south != null)  outerPanel.add(south,  BorderLayout.SOUTH);

		return outerPanel;
	}

	public static JPanel inputField(JLabel label, JComponent field, JButton button) {
		int leftPadding  = (label == null) ? 0 : I_PADDING;
		int rightPadding = (button == null) ? 0 : I_PADDING;

		JPanel fieldPanel = borderLayoutWrap(null, null, field, null, null);
		fieldPanel.setBorder(new EmptyBorder(0, leftPadding, 0, rightPadding));

		JPanel inputField = borderLayoutWrap(null, label, fieldPanel, button, null);
		inputField.setBorder(new EmptyBorder(0, 0, I_PADDING, 0));

		return inputField;
	}

	public static JPanel leftAlign(JComponent component) {
		JPanel outerPanel = borderLayoutWrap(null, component, null, null, null);
		outerPanel.setBorder(new EmptyBorder(0, 0, I_PADDING, 0));

		return outerPanel;
	}

	public static JComponent withBorder(JComponent component) {
		component.setBorder(new EmptyBorder(O_PADDING, O_PADDING, O_PADDING, O_PADDING));
		return component;
	}
}
