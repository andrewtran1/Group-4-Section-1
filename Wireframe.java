import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.BoxLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JTextPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import java.awt.TextField;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.List;
import java.awt.TextArea;
import java.awt.Scrollbar;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import java.awt.Choice;
import java.awt.SystemColor;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSeparator;
import javax.swing.JToggleButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;

public class Wireframe {

	private JFrame frmPirex;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Wireframe window = new Wireframe();
					window.frmPirex.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Wireframe() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPirex = new JFrame();
		frmPirex.setTitle("Pirex");
		frmPirex.setBounds(100, 100, 1038, 789);
		frmPirex.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPirex.getContentPane().setLayout(new BoxLayout(frmPirex.getContentPane(), BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		frmPirex.getContentPane().add(panel);
		panel.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(31, 73, 940, 580);
		panel.add(tabbedPane);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Search for Documents", null, panel_1, null);
		panel_1.setLayout(null);
		
		TextField textField_1 = new TextField();
		textField_1.setBounds(128, 35, 667, 45);
		panel_1.add(textField_1);
		
		JTextPane txtpnQuery = new JTextPane();
		txtpnQuery.setBackground(UIManager.getColor("Button.background"));
		txtpnQuery.setText("Query:");
		txtpnQuery.setBounds(15, 35, 107, 45);
		panel_1.add(txtpnQuery);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				textField_1.setText(null);
			}
		});
		btnClear.setBounds(801, 35, 114, 47);
		panel_1.add(btnClear);
		
		textField_6 = new JTextField();
		textField_6.setBounds(15, 86, 900, 201);
		panel_1.add(textField_6);
		textField_6.setColumns(10);
		
		TextArea textArea = new TextArea();
		textArea.setBounds(15, 293, 900, 224);
		panel_1.add(textArea);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Load Documents", null, panel_2, null);
		panel_2.setLayout(null);
		
		TextField textField = new TextField();
		textField.setBounds(163, 5, 600, 45);
		panel_2.add(textField);
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setBounds(769, 5, 135, 47);
		panel_2.add(btnBrowse);
		
		JTextPane txtpnTextFile = new JTextPane();
		txtpnTextFile.setBackground(UIManager.getColor("Button.background"));
		txtpnTextFile.setText("Text File:");
		txtpnTextFile.setBounds(14, 5, 143, 45);
		panel_2.add(txtpnTextFile);
		
		Choice choice = new Choice();
		choice.setBounds(238, 56, 666, 45);
		panel_2.add(choice);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(choice, popupMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Project Gutenburg File");
		popupMenu.add(mntmNewMenuItem);
		
		JTextPane txtpnTextFileType = new JTextPane();
		txtpnTextFileType.setText("Text File Type:");
		txtpnTextFileType.setBackground(SystemColor.menu);
		txtpnTextFileType.setBounds(14, 56, 218, 45);
		panel_2.add(txtpnTextFileType);
		
		JTextPane txtpnTitle = new JTextPane();
		txtpnTitle.setText("Title:");
		txtpnTitle.setBackground(SystemColor.menu);
		txtpnTitle.setBounds(14, 103, 78, 45);
		panel_2.add(txtpnTitle);
		
		JTextPane txtpnAuthor = new JTextPane();
		txtpnAuthor.setText("Author:");
		txtpnAuthor.setBackground(SystemColor.menu);
		txtpnAuthor.setBounds(403, 107, 112, 45);
		panel_2.add(txtpnAuthor);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 154, 703, 2);
		panel_2.add(separator);
		
		JToggleButton tglbtnProcess = new JToggleButton("Process");
		tglbtnProcess.setBounds(14, 160, 156, 47);
		panel_2.add(tglbtnProcess);
		
		TextField textField_2 = new TextField();
		textField_2.setBounds(98, 103, 299, 45);
		panel_2.add(textField_2);
		
		TextField textField_3 = new TextField();
		textField_3.setBounds(521, 107, 383, 45);
		panel_2.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setBounds(14, 240, 890, 273);
		panel_2.add(textField_4);
		textField_4.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Summarize Document", null, panel_3, null);
		panel_3.setLayout(null);
		
		textField_5 = new JTextField();
		textField_5.setBounds(31, 33, 873, 461);
		panel_3.add(textField_5);
		textField_5.setColumns(10);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1002, 59);
		panel.add(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmSave = new JMenuItem("Save Query");
		mntmSave.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				SavedQuery query = new SavedQuery(textField_1.getText());
			}
		});
		mnFile.add(mntmSave);
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}

class SavedQuery
{
	private String query;
	
	public SavedQuery(String query)
	{
		this.query = query;
	}
}
