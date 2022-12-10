package coursework.gui;


import coursework.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthorPanel extends JPanel {

    public AuthorPanel() {
        super(new GridLayout(1,2));

        add(new AuthorFormPanel());

        AuthorTableModel authorTableModel = new AuthorTableModel(Controller.INSTANCE.getAuthorList());
        Controller.INSTANCE.addPropertyChangeListener(authorTableModel);
        add(new JScrollPane(new JTable(authorTableModel)));
    }

    public static class AuthorFormPanel extends JPanel {
        private  final JTextField nameTextField;
        private  final JButton addButton;


        private void createGUI() {

            setBorder(BorderFactory.createTitledBorder("Author"));
            setLayout(new GridBagLayout());


            GridBagConstraints gc = new GridBagConstraints();
            gc.weighty = 1;
            gc.weightx = 1;
            gc.fill = GridBagConstraints.HORIZONTAL;
            gc.insets = new Insets(4, 4, 4, 5);
            gc.anchor = GridBagConstraints.LINE_END;

            gc.gridx = 0;
            gc.gridy = 0;
            gc.anchor = GridBagConstraints.SOUTHEAST;
            add(new JLabel("Name : ",SwingConstants.RIGHT),gc);

            gc.gridx = 1;
            gc.gridy = 0;
            gc.anchor = GridBagConstraints.SOUTHEAST;
            add(nameTextField,gc);


            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String fullname = nameTextField.getText();
                    String surname = fullname.split(" ")[fullname.split(" ").length-1];
                    String firstname = fullname.substring(0, fullname.length() - surname.length());
                    Controller.INSTANCE.addAuthor(firstname, surname);
                }
            });

            gc.gridx = 1;
            gc.gridy = 1;
            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.NORTHEAST;

            add(addButton,gc);

        }

        public AuthorFormPanel() {
            nameTextField = new JTextField();
            addButton = new JButton("ADD");

            createGUI();
        }
    }

}
