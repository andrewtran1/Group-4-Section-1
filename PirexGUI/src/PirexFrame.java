import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class PirexFrame extends JFrame {
    private static final int BORDER = 10;

    /**
     * PirexFrame constructor.
     */
    public PirexFrame() {
        super();
        this.setupLayout();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }    

    /**
     * Setup and layout this PirexFrame.
     */
    public void setupLayout() {
        this.setSize(600, 600);
        this.setJMenuBar(setupMenuBar());

        PirexTabbedPane tabbedPane = new PirexTabbedPane();
        tabbedPane.setBorder(new EmptyBorder(BORDER, BORDER, BORDER, BORDER));

        this.getContentPane()
            .add(tabbedPane);
    }

    /**
     * Setup menu-bar
     *
     * @return menu bar for Pirex application
     */
     private JMenuBar setupMenuBar() {
        JMenuBar menuBar     = new JMenuBar();
        JMenu    fileMenu    = new JMenu("File");
        JMenu    helpMenu    = new JMenu("Help");
        JMenu    optionsMenu = new JMenu("Options");

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        menuBar.add(optionsMenu);

        return menuBar;
     }
}
