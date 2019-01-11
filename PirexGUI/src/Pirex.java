import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.util.List;

/**
 * The main class for the Pirex application.
 */
public class Pirex {
	public static final int I_PADDING = 10; 
	public static final int O_PADDING = 20;

	public static final EmptyBorder OUTER_BORDER = 
		new EmptyBorder(O_PADDING, O_PADDING, O_PADDING, O_PADDING);
	public static final EmptyBorder ROW_PADDING = 
		new EmptyBorder(0, 0, I_PADDING, 0);
	public static final EmptyBorder SEPARATOR_PADDING = 
		new EmptyBorder(0, 0, 2 * I_PADDING, 0);
	public static final EmptyBorder NO_PADDING = 
		new EmptyBorder(0, 0, 0, 0);

    public static void main(String[] args) {
        PirexFrame frame = new PirexFrame();
        frame.setTitle("Pirex");
        frame.setVisible(true);
    }

	/**
	 * Wrap JComponent in a BorderLayout JPanel.
	 */
	private static JPanel borderLayout (
		JComponent north, JComponent west, JComponent center, JComponent east, JComponent south, EmptyBorder border) {

		JPanel panel = new JPanel(new BorderLayout());

		if (north != null) panel.add(north, BorderLayout.NORTH);
		if (west != null) panel.add(west, BorderLayout.WEST);
		if (center != null) panel.add(center, BorderLayout.CENTER);
		if (east != null) panel.add(east, BorderLayout.EAST);
		if (south != null) panel.add(south, BorderLayout.SOUTH);

		panel.setBorder(border);

		return panel;
	}

	public static JPanel fillBoth(JComponent component, EmptyBorder border) {
		return borderLayout(null, null, component, null, null, border);
	}

	public static JPanel leftAlign(JComponent component, EmptyBorder border) {
		return borderLayout(null, component, null, null, null, border);
	}

	public static JPanel northCenter(JComponent north, JComponent center) {
		return borderLayout(north, null, center, null, null, NO_PADDING);
	}

	public static JPanel stackNorth(JComponent base, List<JComponent> rows, EmptyBorder border) {
		return fillBoth(rows.stream()
			.reduce(base, (center, north) -> Pirex.northCenter(north, center)), border);
	}

	public static JPanel gridColumn(List<JComponent> components, EmptyBorder border) {
		JPanel column = new JPanel(new GridLayout(components.size(), 0));
		components.stream().forEach(column::add);

		return fillBoth(column, border);
	}

	public static JPanel inputField(
		JLabel label, JComponent field, JButton button, EmptyBorder border) {
		
		EmptyBorder padding = new EmptyBorder(
			0, label == null ? 0 : I_PADDING, 0, button == null ? 0 : I_PADDING);

		return borderLayout(null, label, fillBoth(field, padding), button, null, border);
	}

	public static JPanel addScrollBars(
		JComponent component, boolean alwaysShow, EmptyBorder border) {
		JScrollPane scrollPane = new JScrollPane(component);

		if (alwaysShow) {
			scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		}

		return fillBoth(scrollPane, border);
	}
}
