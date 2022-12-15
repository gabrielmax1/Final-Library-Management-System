package coursework.gui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

// MainPanel is used to unify all the JPanel created: Book, Author, Publisher and Sort JPanels.
public class MainPanel extends JPanel {

    private JTabbedPane appTabbedPane;

    // Such panels will be coupled in their Panes, e.g., BookPanel and BookTableModel, will be 2 separate Panes in one JPanel.
    public MainPanel() {

        // Setting of the layout of the JPanel
        super(new GridLayout(1,1)); // Grid Layout will be using 1 row and 1 column (1 Big Box)
        this.appTabbedPane = new JTabbedPane(); //

        appTabbedPane.addTab("BOOKS", new BookPanel());
        appTabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        appTabbedPane.addTab("AUTHORS", new AuthorPanel());
        appTabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        appTabbedPane.addTab("PUBLISHER", new PublisherPanel());
        appTabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

        appTabbedPane.addTab("SORTING",new SortPanel());
        appTabbedPane.setMnemonicAt(3, KeyEvent.VK_4);


        add(appTabbedPane);

        appTabbedPane.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);

    }
}
