package coursework.gui;


import coursework.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PublisherPanel extends JPanel {

    public PublisherPanel() {
        super(new GridLayout(1,2));
        add(new PublisherFormPanel());

        PublisherTableModel publisherTableModel = new PublisherTableModel(Controller.INSTANCE.getPublisherList());
        Controller.INSTANCE.addPropertyChangeListener(publisherTableModel);
        add(new JScrollPane(new JTable(publisherTableModel)));
    }

    public static class PublisherFormPanel extends JPanel {
        private  final JTextField pubNameTextField;
        private  final JButton addPublisherButton;
        private  final JTextField searchPublisherTextField;
        private  final JButton searchPublisherButton;
        private  final JButton editPublisherButton;
        private  final JButton deletePublisherButton;


        public PublisherFormPanel() {
            pubNameTextField = new JTextField();
            searchPublisherTextField = new JTextField();
            addPublisherButton = new JButton("ADD");
            searchPublisherButton = new JButton("SEARCH");
            editPublisherButton = new JButton("EDIT");
            deletePublisherButton = new JButton("DELETE");


            createGUI();

            addPublisherButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String name = pubNameTextField.getText();
                    Controller.INSTANCE.addPublisher(name);
                }
            });

            searchPublisherButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String name = pubNameTextField.getText();
                    Controller.INSTANCE.getPublisher();
                }
            });


            editPublisherButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
//                    String fullname = nameTextField.getText();
//                    String surname = fullname.split(" ")[fullname.split(" ").length-1];
//                    String firstname = fullname.substring(0, fullname.length() - surname.length());
//                    Controller.INSTANCE.addAuthor(firstname, surname);
                }
            });

            deletePublisherButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
//                    String fullname = nameTextField.getText();
//                    String surname = fullname.split(" ")[fullname.split(" ").length-1];
//                    String firstname = fullname.substring(0, fullname.length() - surname.length());
//                    Controller.INSTANCE.addAuthor(firstname, surname);
                }
            });

        }
        private void createGUI() {

            setBorder(BorderFactory.createTitledBorder("Publisher"));
            setLayout(new GridBagLayout());


            GridBagConstraints gc = new GridBagConstraints();
            gc.weighty = 1;
            gc.weightx = 1;
            gc.fill = GridBagConstraints.HORIZONTAL;
            gc.insets = new Insets(4, 4, 4, 5);
            gc.anchor = GridBagConstraints.LINE_END;

            gc.gridx = 0;
            gc.gridy = 0;
//            gc.anchor = GridBagConstraints.WEST;
            add(new JLabel("Name : ",SwingConstants.RIGHT),gc);

            gc.gridx = 1;
            gc.gridy = 0;
            gc.fill = GridBagConstraints.HORIZONTAL;
//            gc.anchor = GridBagConstraints.WEST;
            add(pubNameTextField,gc);

            gc.gridx = 2;
            gc.gridy = 0;
            gc.fill = GridBagConstraints.NONE;
//            gc.anchor = GridBagConstraints.WEST;
            add(addPublisherButton,gc);

            gc.gridx = 0;
            gc.gridy = 1;
//            gc.anchor = GridBagConstraints.WEST;
            add(new JLabel("Search Publisher : ",SwingConstants.RIGHT),gc);

            gc.gridx = 1;
            gc.gridy = 1;
            gc.fill = GridBagConstraints.HORIZONTAL;
//            gc.anchor = GridBagConstraints.NONE;
            add(searchPublisherTextField,gc);

            gc.gridx = 2;
            gc.gridy = 1;
            gc.fill = GridBagConstraints.NONE;
//            gc.anchor = GridBagConstraints.NONE;
            add(searchPublisherButton,gc);

            gc.gridx = 3;
            gc.gridy = 1;
            gc.fill = GridBagConstraints.NONE;
//            gc.anchor = GridBagConstraints.NONE;
            add(editPublisherButton,gc);

        }


    }

}
