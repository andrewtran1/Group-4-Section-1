import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.Arrays;

public class PirexFrame extends JFrame {
	private static final int BORDER = 10;

	JTabbedPane tabbedPane = new JTabbedPane();  
	ImageIcon img = new ImageIcon("Pirex.png");

	/**
	 * PirexFrame constructor.
	 */
	public PirexFrame() {
		super();
		this.setupLayout();
		this.setTitle("Pirex");
		this.setIconImages(Arrays.asList(img.getImage()));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}	

	/**
	 * Setup and layout this PirexFrame.
	 */
	public void setupLayout() {
		this.setSize(600, 600);
		//this.setJMenuBar(setupMenuBar());

		this.getContentPane()
			.add(tabbedPane);

		tabbedPane.addTab("Search for Documents", new PirexSearchTab());
		tabbedPane.addTab("Load Documents", new PirexLoadTab());
		tabbedPane.addTab("Summarize Documents", new PirexSummarizeTab());
		tabbedPane.setBorder(Pirex.OUTER_BORDER);//new EmptyBorder(BORDER, BORDER, BORDER, BORDER));
	}

	/**
	 * Setup menu-bar
	 *
	 * @return menu bar for Pirex application
	 */
	private JMenuBar setupMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu	fileMenu	= new JMenu("File");
		JMenu	helpMenu	= new JMenu("Help");
		JMenu	optionsMenu = new JMenu("Options");

		menuBar.add(fileMenu);
		menuBar.add(helpMenu);
		menuBar.add(optionsMenu);

		return menuBar;
	 }
}
