import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import java.util.Arrays;
import java.util.List;

/**
 * "Search for Documents" tab layout.
 *
 * @author CSC 131-01 Group 4 : Arshia Atai, Dakota Conn, Camoob Lee, 
 *                              Thuyvy Nguyen, Roberto Ochoa-Sanchez, 
 *                              Andrew Tran, Sergio Zavala
 */
public class PirexSearchTab extends JPanel {
	private static final int I_PADDING = PirexTabbedPane.I_PADDING;
	private static final int O_PADDING = PirexTabbedPane.O_PADDING;;

        String[] resultsList = new String[] {
            resultString("Charles Dickens", "Bleak House", 276, "I knew he meant well in"), 
            resultString("Charles Dickens", "Bleak House", 519, "It was one of those"),
            resultString("Charles Dickens", "Bleak House", 635, "There may be some motions"), 
            resultString("Charles Dickens", "Bleak House", 769, "They attend matins with"), 
            resultString("Charles Dickens", "Bleak House", 947, "This proper name, so used"), 
            resultString("Charles Dickens", "Bleak House", 954, "The red bit, the black") 
        };

        String[] fullText = new String[] { 
            "I knew he meant well in paying me this compliment, so I laughed at myself for blushing at it when he had shut the door and got upon the box; and we all three laughed and chatted about our inexperience and the strangeness of London until we turned up under an archway to our destination—a narrow street of high houses like an oblong cistern to hold the fog. There was a confused little crowd of people, principally children, gathered about the house at which we stopped, which had a tarnished brass plate on the door with the inscription JELLYBY.",
            "It was one of those delightfully irregular houses where you go up and down steps out of one room into another, and where you come upon more rooms when you think you have seen all there are, and where there is a bountiful provision of little halls and passages, and where you find still older cottage-rooms in unexpected places with lattice windows and green growth pressing through them. Mine, which we entered first, was of this kind, with an up-and-down roof that had more corners in it than I ever counted afterwards and a chimney (there was a wood fire on the hearth) paved all around with pure white tiles, in every one of which a bright miniature of the fire was blazing. Out of this room, you went down two steps into a charming little sitting-room looking down upon a flower-garden, which room was henceforth to belong to Ada and me. Out of this you went up three steps into Ada's bedroom, which had a fine broad window commanding a beautiful view (we saw a great expanse of darkness lying underneath the stars), to which there was a hollow window-seat, in which, with a spring-lock, three dear Adas might have been lost at once.",
            "There may be some motions of fancy among the lower animals at Chesney Wold. The horses in the stables—the long stables in a barren, red-brick court-yard, where there is a great bell in a turret, and a clock with a large face, which the pigeons who live near it and who love to perch upon its shoulders seem to be always consulting—THEY may contemplate some mental pictures of fine weather on occasions, and may be better artists at them than the grooms. The old roan, so famous for cross-country work, turning his large eyeball to the grated window near his rack, may remember the fresh leaves that glisten there at other times and the scents that stream in, and may have a fine run with the hounds, while the human helper, clearing out the next stall, never stirs beyond his pitchfork and birch-broom. The grey, whose place is opposite the door and who with an impatient rattle of his halter pricks his ears and turns his head so wistfully when it is opened, and to whom the opener says, \"Woa grey, then, steady! Noabody wants you to-day!\"",
            "\"They attend matins with me (very prettily done) at half-past six o'clock in the morning all the year round, including of course the depth of winter,\" said Mrs. Pardiggle rapidly, \"and they are with me during the revolving duties of the day. I am a School lady, I am a Visiting lady, I am a Reading lady, I am a Distributing lady; I am on the local Linen Box Committee and many general committees; and my canvassing alone is very extensive—perhaps no one's more so. But they are my companions everywhere; and by these means they acquire that knowledge of the poor, and that capacity of doing charitable business in general—in short, that taste for the sort of thing—which will render them in after life a service to their neighbours and a satisfaction to themselves.",
            "This proper name, so used by Mr. Snagsby, has before now sharpened the wit of the Cook's Courtiers to remark that it ought to be the name of Mrs. Snagsby, seeing that she might with great force and expression be termed a Guster, in compliment to her stormy character. It is, however, the possession, and the only possession except fifty shillings per annum and a very small box indifferently filled with clothing, of a lean young woman from a workhouse (by some supposed to have been christened Augusta) who, although she was farmed or contracted for during her growing time by an amiable benefactor of his species resident at Tooting, and cannot fail to have been developed under the most favourable circumstances, \"has fits,\" which the parish can't account for.",
            "The red bit, the black bit, the inkstand top, the other inkstand top, the little sand-box. So! You to the middle, you to the right, you to the left. This train of indecision must surely be worked out now or never. Now! Mr. Tulkinghorn gets up, adjusts his spectacles, puts on his hat, puts the manuscript in his pocket, goes out, tells the middle-aged man out at elbows, \"I shall be back presently.\" Very rarely tells him anything more explicit."};

    //Components to be wrapped in a JContainer
    private final JList       i_searchResultList     = new JList(); 
    private final JLabel      i_searchResultCount    = new JLabel(" "); 
    private final JTextArea   i_selectedDocumentText = new JTextArea(""); 
    private final JLabel      queryLabel           = new JLabel("Query:");
    private final JTextField  queryTextField       = new JTextField();
    private final JButton     queryClearButton     = new JButton("Clear");

    //Search tab components
    private final JPanel      queryPanel           = new JPanel(new GridBagLayout());
    private final JScrollPane searchResultList     = new JScrollPane(i_searchResultList); 
    private final JPanel      searchResultCount    = new JPanel(new BorderLayout());
    private final JScrollPane selectedDocumentText = new JScrollPane(i_selectedDocumentText);
    private final JPanel      searchTabPanel       = new JPanel(new GridBagLayout());

    //Search tab component constraints for GridBagLayout
    private GridBagConstraints 
		cQueryLabel, cQueryTextField, cQueryClearButton, 
		cQueryPanel, 
		cSearchResultList, 
		cSearchResultCount, 
		cSelectedDocumentText, 
		cSearchTabPanel;

    private ListSelectionModel resultListSelectionModel;

    /**
     * Constructor for "Search for Documents" tab.
     */
    public PirexSearchTab() {
        super();
        this.setupComponentBehavior();
        this.setupLayout();        
    }

    /**
     * Setup component behavior and action listeners.
     */
    private void setupComponentBehavior() {
        i_selectedDocumentText.setEditable(false);
        i_selectedDocumentText.setLineWrap(true);
        i_selectedDocumentText.setWrapStyleWord(true);
        selectedDocumentText.setVerticalScrollBarPolicy(
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        this.searchResultCount.add(i_searchResultCount, BorderLayout.WEST);

        this.queryClearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                toBlankQueryState();
            } 
        });

        this.queryTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                toSearchResultsState(queryTextField.getText());
            } 
        });

        this.resultListSelectionModel = this.i_searchResultList.getSelectionModel();
        this.resultListSelectionModel.addListSelectionListener(
            new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent e) {
                    ListSelectionModel lsm = (ListSelectionModel)e.getSource();

                    if (lsm.isSelectionEmpty()) {
                        i_selectedDocumentText.setText(""); 
                    }
                    else {
                        toLongFormDisplayState(e.getLastIndex());
                    }
                }
            }
        );
    }

    private void toBlankQueryState() {
        this.queryTextField.setText("");

        this.i_searchResultCount.setText(" ");
        this.i_selectedDocumentText.setText("");
        this.i_searchResultList.setListData(new String[0]);
    }

    private void toSearchResultsState(String query) {
        this.i_selectedDocumentText.setText("");
        this.i_searchResultList.setListData(resultsList);
        this.i_searchResultCount.setText(String.format(
            "Retrieved %d documents from query \"%s\"", resultsList.length, query
        ));
    }

    private void toLongFormDisplayState(int index) {
        this.i_selectedDocumentText.setText(fullText[index]);
    }

    private static String resultString(String author, String title, int docNumber, String shortenedText) {
        return String.format("%s  %s  %4d  %s", author, title, docNumber, shortenedText);
    }

    /**
     * Add and layout components onto the "Search for Documents" tab.
     */
    private void setupLayout() {
        this.setLayout(new GridBagLayout());
        this.setComponentConstraints();

		this.add(searchTabPanel, cSearchTabPanel);

		searchTabPanel.add(queryPanel, cQueryPanel);
		searchTabPanel.add(searchResultList, cSearchResultList);
		searchTabPanel.add(searchResultCount, cSearchResultCount);
		searchTabPanel.add(selectedDocumentText, cSelectedDocumentText);

		queryPanel.add(queryLabel, cQueryLabel); 
		queryPanel.add(queryTextField, cQueryTextField); 
		queryPanel.add(queryClearButton, cQueryClearButton); 
    }

    /**
     * Set all of the GridBagConstraints layout options
     * for each component in the "Search for Documents" tab.
     */
    private void setComponentConstraints() {
		cSearchTabPanel = PirexTabbedPane.outerPanelConstraints();

		cQueryPanel = PirexTabbedPane.rowConstraints(0);

        cSearchResultList        = PirexTabbedPane.bothFill();
        cSearchResultList.insets = new Insets(0, 0, I_PADDING / 2, 0);
        cSearchResultList.gridy  = 1;

        cSearchResultCount        = PirexTabbedPane.rowConstraints(2);

        cSelectedDocumentText       = PirexTabbedPane.bothFill();
        cSelectedDocumentText.gridy = 3;

        cQueryLabel       = PirexTabbedPane.labelConstraints();
		cQueryTextField   = PirexTabbedPane.fieldConstraints();
        cQueryClearButton = new GridBagConstraints();
    } 
}
