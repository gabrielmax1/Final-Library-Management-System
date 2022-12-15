package coursework.gui;


import coursework.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// AuthorPanel is the JPanel in which the author list, textfield and functions buttons are used
public class AuthorPanel extends JPanel {

    final AuthorTableModel authorTableModel = new AuthorTableModel(Controller.INSTANCE.getAuthorList());
    private final JTable authorTable = new JTable(authorTableModel);
    private AuthorFormPanel authorForm = new AuthorFormPanel();

    // Constructor
    public AuthorPanel() {
        setLayout(new GridLayout(1,2));
        add(authorForm);
        Controller.INSTANCE.addPropertyChangeListener(authorTableModel);
        add(new JScrollPane(authorTable));

        // The listener is used to select the data of the row picked by the mouse
        authorTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int idx = authorTable.getSelectedRow();
                int rowNumber = idx + 1;
                String name = authorTableModel.getValueAt(idx, 1).toString();
                String surname = authorTableModel.getValueAt(idx, 2).toString();
                String id = authorTableModel.getValueAt(idx, 0).toString();
                authorForm.editAuthorTextField.setText(name +" " + surname);
                authorForm.id = id;
                System.out.println("Row number: " + rowNumber + "\nTitle: " + name);
            }
        });
    }
    // AuthorFormPanel defines the textfield and the buttons
    public static class AuthorFormPanel extends JPanel {
        private final JTextField nameTextField;
        private final JTextField editAuthorTextField;
        private final JTextField searchAuthorTextField;
        private final JButton addAuthorButton;
        private final JButton searchAuthorButton;
        private final JButton editAuthorButton;
        private final JButton deleteAuthorButton;

        public String id;


        public AuthorFormPanel() {
            nameTextField = new JTextField();
            searchAuthorTextField = new JTextField();
            editAuthorTextField = new JTextField();
            addAuthorButton = new JButton("ADD");
            searchAuthorButton = new JButton("SEARCH");
            editAuthorButton = new JButton("EDIT");
            deleteAuthorButton = new JButton("DELETE");

            // The function instantialise the GUI of the author
            createGUI();

            // Add button to add the author to the database
            addAuthorButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String fullname = nameTextField.getText();
                    String surname = fullname.split(" ")[fullname.split(" ").length-1];
                    String firstname = fullname.substring(0, fullname.length() - surname.length());
                    Controller.INSTANCE.addAuthor(firstname, surname);
                }
            });

            // Search button to search the author in the database
            searchAuthorButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String fullname = searchAuthorTextField.getText();
                    String surname = fullname.split(" ")[fullname.split(" ").length-1];
                    String firstname = fullname.substring(0, fullname.length() - surname.length());
                    Controller.INSTANCE.searchAuthor(firstname);
                }
            });

            // Edit button to edit the data of given author in the database
            editAuthorButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String fullname = editAuthorTextField.getText();
                    String surname = fullname.split(" ")[fullname.split(" ").length-1];
                    String firstname = fullname.substring(0, fullname.length() - surname.length());
                    Controller.INSTANCE.editAuthor(firstname, surname, Long.parseLong(id));
                }
            });
            // Delete button to delete the row of an author
            deleteAuthorButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Controller.INSTANCE.deleteAuthor(Long.parseLong(id));
                }
            });


        }

        // Set and get of id
        public String getId(){
            return id;
        }

        public void setId(String newID){
            id = newID;
        }

        // The GUI define using Grid the position of every element
        private void createGUI() {

            setBorder(BorderFactory.createTitledBorder("Author"));
            setLayout(new GridBagLayout());


            GridBagConstraints gc = new GridBagConstraints();
            gc.weighty = 1;
            gc.weightx = 1;
            gc.fill = GridBagConstraints.HORIZONTAL;
            gc.insets = new Insets(0, 6, 0, 5);
            gc.anchor = GridBagConstraints.LINE_END;

            gc.gridx = 0;
            gc.gridy = 0;
//            gc.anchor = GridBagConstraints.WEST;
            add(new JLabel("Fullname : ",SwingConstants.RIGHT),gc);

            gc.gridx = 1;
            gc.gridy = 0;
            gc.fill = GridBagConstraints.HORIZONTAL;
//            gc.anchor = GridBagConstraints.WEST;
            add(nameTextField,gc);

            gc.gridx = 2;
            gc.gridy = 0;
            gc.fill = GridBagConstraints.NONE;
//            gc.anchor = GridBagConstraints.WEST;
            add(addAuthorButton,gc);

            gc.gridx = 0;
            gc.gridy = 1;
            gc.anchor = GridBagConstraints.EAST;
            add(new JLabel("Search Author : ",SwingConstants.RIGHT),gc);

            gc.gridx = 1;
            gc.gridy = 1;
            gc.fill = GridBagConstraints.HORIZONTAL;
//            gc.anchor = GridBagConstraints.NONE;
            add(searchAuthorTextField,gc);

            gc.gridx = 2;
            gc.gridy = 1;
            gc.fill = GridBagConstraints.NONE;
//            gc.anchor = GridBagConstraints.NONE;
            add(searchAuthorButton,gc);

            gc.gridx = 0;
            gc.gridy = 2;
            gc.anchor = GridBagConstraints.EAST;
            add(new JLabel("Edit or Delete Author: ",SwingConstants.RIGHT),gc);

            gc.gridx = 1;
            gc.gridy = 2;
            gc.fill = GridBagConstraints.HORIZONTAL;
//            gc.anchor = GridBagConstraints.NONE;
            add(editAuthorTextField,gc);

            gc.gridx = 2;
            gc.gridy = 2    ;
            gc.fill = GridBagConstraints.NONE;
            //gc.anchor = GridBagConstraints.SOUTHWEST;
            add(editAuthorButton,gc);

            gc.gridx = 3;
            gc.gridy = 2;
            gc.fill = GridBagConstraints.NONE;
//            gc.anchor = GridBagConstraints.NONE;
            add(deleteAuthorButton,gc);
        }


    }

}
