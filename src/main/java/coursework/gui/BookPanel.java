package coursework.gui;


import coursework.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookPanel extends JPanel {

    public BookPanel() {
        setLayout(new GridLayout(1,2));
        add(new BookFormPanel());
        final BookTableModel bookTableModel = new BookTableModel(Controller.INSTANCE.getBookList());
        Controller.INSTANCE.addPropertyChangeListener(bookTableModel);
        add(new JScrollPane(new JTable(bookTableModel)));

    }

    public static class BookFormPanel extends JPanel {
        private final JTextField titleTextField;
        private final JTextField authorTextField;
        private final JTextField year_of_publicationTextField;
        private final JTextField publisherTextField;
        private final JTextField subjectTextField;
        private final JButton addButton;
        private final JButton deleteButton;
        private final JButton editButton;
        private final JButton searchButton;
        private final JComboBox authorComboBox;
        private final JComboBox publisherComboBox; // Check with original repository

        public BookFormPanel() {

            titleTextField = new JTextField();
            authorTextField = new JTextField();
            year_of_publicationTextField = new JTextField();
            publisherTextField = new JTextField();
            subjectTextField = new JTextField();
            addButton = new JButton("ADD");
            editButton = new JButton("EDIT");
            deleteButton = new JButton("DELETE");
            searchButton = new JButton("SEARCH");
            final AuthorComboBoxModel authorComboBoxModel = new AuthorComboBoxModel(Controller.INSTANCE.getAuthorList());
            authorComboBox = new JComboBox(authorComboBoxModel);
            authorComboBox.setEditable(false);
            Controller.INSTANCE.addPropertyChangeListener(authorComboBoxModel);

            final PublisherComboBoxModel publisherComboBoxModel = new PublisherComboBoxModel(Controller.INSTANCE.getPublisherList());
            publisherComboBox = new JComboBox(authorComboBoxModel);
            publisherComboBox.setEditable(false);
            Controller.INSTANCE.addPropertyChangeListener(publisherComboBoxModel);

            createUILayout();

            //
            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String title = titleTextField.getText();
                    String author = authorTextField.getText();
                    String year_of_publication = year_of_publicationTextField.getText();
                    String publisher = publisherTextField.getText();
                    String subject = subjectTextField.getText();
                    Controller.INSTANCE.add_book(title, author, Long.parseLong(year_of_publication), publisher, subject,
                           Long.valueOf(1), Long.valueOf(1));
                }
            });
//            editButton.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//
//                }
//            });
//            deleteButton.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    // Selected book
//                }
//            })
//            searchButton.addActionListener(new ActionListener()
//            {
//                public void actionPerformed(ActionEvent e)
//                {
//                    String name = JOptionPane.showInputDialog("Wat wil je zoeken?");
//                }
//            });
        }


        private void createUILayout() {
            setLayout(new GridBagLayout());
            setBorder(BorderFactory.createTitledBorder("Book"));
            GridBagConstraints gc = new GridBagConstraints();
            gc.weighty = 1;
            gc.weightx = 1;
            gc.fill = GridBagConstraints.HORIZONTAL;
            gc.insets = new Insets(0, 4, 0, 5);
            gc.anchor = GridBagConstraints.LINE_END;


            gc.gridx = 0;
            gc.gridy = 0 ;
            add(new JLabel("Title :",SwingConstants.RIGHT),gc);

            gc.gridx = 1;
            gc.gridy = 0 ;
            add(titleTextField,gc);

            gc.gridx = 0;
            gc.gridy = 1 ;
            add(new JLabel("Author :",SwingConstants.RIGHT),gc);

            gc.gridx = 1;
            gc.gridy = 1 ;
            add(authorTextField,gc );

            gc.gridx = 0;
            gc.gridy = 2 ;
            add(new JLabel("Year of Publication :",SwingConstants.RIGHT),gc);


            gc.gridx = 1;
            gc.gridy = 2 ;
            add(year_of_publicationTextField,gc);

            gc.gridx = 0;
            gc.gridy = 3 ;
            add(new JLabel("Publisher :",SwingConstants.RIGHT),gc);

            gc.gridx = 1;
            gc.gridy = 3 ;
            add(publisherTextField,gc);

            gc.gridx = 0;
            gc.gridy = 4 ;
            add(new JLabel("Subject :",SwingConstants.RIGHT),gc);

            gc.gridx = 1;
            gc.gridy = 4 ;
            add(subjectTextField,gc);


            gc.gridx = 0;
            gc.gridy = 5 ;
            add(new JLabel("Author_ID :",SwingConstants.RIGHT),gc);

            gc.gridx = 1;
            gc.gridy = 5 ;
            add(authorComboBox,gc);

            gc.gridx = 0;
            gc.gridy = 6 ;
            add(new JLabel("Publisher_ID :",SwingConstants.RIGHT),gc);

            gc.gridx = 1;
            gc.gridy = 6 ;
            add(publisherComboBox,gc);

            gc.gridx = 1;
            gc.gridy = 7 ;
            gc.fill = GridBagConstraints.NONE;
            add(addButton,gc);

            gc.gridx = 2;
            gc.gridy = 7 ;
            gc.fill = GridBagConstraints.NONE;
            add(searchButton,gc);

            gc.gridx = 1;
            gc.gridy = 8 ;
            gc.fill = GridBagConstraints.NONE;
            add(searchTextField,gc);


        }
    }

}
