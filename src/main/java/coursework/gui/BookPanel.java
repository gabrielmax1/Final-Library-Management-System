package coursework.gui;


import coursework.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class BookPanel extends JPanel {
    // Moved from Constructor to Class attribute
    final BookTableModel bookTableModel = new BookTableModel(Controller.INSTANCE.getBookList());
    private final JTable booksTable = new JTable(bookTableModel);
    private BookFormPanel bookForm = new BookFormPanel();


    public BookPanel() {
        setLayout(new GridLayout(1,2));
        add(bookForm);
        Controller.INSTANCE.addPropertyChangeListener(bookTableModel);
        add(new JScrollPane(booksTable));

        booksTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int idx = booksTable.getSelectedRow();
                int rowNumber = idx+1;
                String title = bookTableModel.getValueAt(idx, 1).toString();
                String author = bookTableModel.getValueAt(idx, 2).toString();
                String yearOfPublication = bookTableModel.getValueAt(idx, 3).toString();
                String publisher = bookTableModel.getValueAt(idx, 4).toString();
                String subject = bookTableModel.getValueAt(idx, 5).toString();
                String id = bookTableModel.getValueAt(idx, 0).toString();
                bookForm.titleTextField.setText(title);
                bookForm.authorTextField.setText(author);
                bookForm.year_of_publicationTextField.setText(yearOfPublication);
                bookForm.publisherTextField.setText(publisher);
                bookForm.subjectTextField.setText(subject);
                bookForm.id = id;
                System.out.println("Row number: " + rowNumber + "\nTitle: " + title);
            }
        });
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
        private final JTextField searchTextField;

        public String id;

        public BookFormPanel() {

            titleTextField = new JTextField();
            authorTextField = new JTextField();
            year_of_publicationTextField = new JTextField();
            publisherTextField = new JTextField();
            subjectTextField = new JTextField();
            searchTextField = new JTextField();
            addButton = new JButton("ADD");
            editButton = new JButton("EDIT");
            deleteButton = new JButton("DELETE");
            searchButton = new JButton("SEARCH");
            // ComboBox for Author
            final AuthorComboBoxModel authorComboBoxModel = new AuthorComboBoxModel(Controller.INSTANCE.getAuthorList());
            authorComboBox = new JComboBox(authorComboBoxModel);
            authorComboBox.setEditable(false);
            Controller.INSTANCE.addPropertyChangeListener(authorComboBoxModel);
            // ComboBox for Publisher
            final PublisherComboBoxModel publisherComboBoxModel = new PublisherComboBoxModel(Controller.INSTANCE.getPublisherList());
            publisherComboBox = new JComboBox(publisherComboBoxModel);
            publisherComboBox.setEditable(false);
            Controller.INSTANCE.addPropertyChangeListener(publisherComboBoxModel);

            createUILayout();

            // Add Button Listener
            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String title = titleTextField.getText();
                    String author = authorTextField.getText();
                    String year_of_publication = year_of_publicationTextField.getText();
                    String publisher = publisherTextField.getText();
                    String subject = subjectTextField.getText();
                    Controller.INSTANCE.addBook(title, author, Long.parseLong(year_of_publication), publisher, subject,
                           Long.valueOf(1), Long.valueOf(1));
                }
            });

            // Edit Button Listener
            editButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // If entry selected then display text in text fields
                    // If change text in textfield and click edit, update entry.
                    String title = titleTextField.getText();
                    String author = authorTextField.getText();
                    String year_of_publication = year_of_publicationTextField.getText();
                    String publisher = publisherTextField.getText();
                    String subject = subjectTextField.getText();
                    String searchWord = searchTextField.getText();

                    Controller.INSTANCE.editBooks(title, author, Long.parseLong(year_of_publication), publisher, subject,
                            Long.parseLong(id));
                    System.out.println(id);

                }
            });

            // Delete Button Listener
            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Selected book
                    // If book selected, and delete button is pressed then delete entry.
                    Controller.INSTANCE.deleteBooks(Long.parseLong(id));
                }
            });

            // Search Button Listener
            searchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    String searchWord = searchTextField.getText();
                    Controller.INSTANCE.searchBooks(searchWord);
                }
            });
        }

        public String getId(){
            return id;
        }

        public void setId(String newID){
            id = newID;
        }

    // Creating the GUI using GridBagContraints, which uses a grid to order the ui objects
    // using values such as x & y panes, is like using Tkinter grid() geometry manager rows and columns
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
            add(editButton,gc);

            gc.gridx = 3;
            gc.gridy = 7 ;
            gc.fill = GridBagConstraints.NONE;
            add(deleteButton,gc);

            gc.gridx = 0;
            gc.gridy = 8 ;
            add(new JLabel("Search Book Title :",SwingConstants.RIGHT),gc);

            gc.gridx = 1;
            gc.gridy = 8 ;
            gc.fill = GridBagConstraints.HORIZONTAL;
            add(searchTextField,gc);

            gc.gridx = 2;
            gc.gridy = 8 ;
            gc.fill = GridBagConstraints.NONE;
            add(searchButton,gc);



        }
    }

}
