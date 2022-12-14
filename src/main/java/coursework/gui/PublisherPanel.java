package coursework.gui;


import coursework.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PublisherPanel extends JPanel {
    // Moved from Constructor to Class attribute
    final PublisherTableModel publisherTableModel = new PublisherTableModel(Controller.INSTANCE.getPublisherList());
    private final JTable publisherTable = new JTable(publisherTableModel);
    private PublisherFormPanel publisherForm = new PublisherFormPanel();

    public PublisherPanel() {
        setLayout(new GridLayout(1,2));
        add(publisherForm);
        Controller.INSTANCE.addPropertyChangeListener(publisherTableModel);
        add(new JScrollPane(publisherTable));

        publisherTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int idx = publisherTable.getSelectedRow();
                int rowNumber = idx + 1;
                String name = publisherTableModel.getValueAt(idx, 1).toString();
                String id = publisherTableModel.getValueAt(idx, 0).toString();
                publisherForm.editPublisherTextField.setText(name);
                publisherForm.id = id;
                System.out.println("Row number: " + rowNumber + "\nTitle: " + name);
            }
        });
    }

    public static class PublisherFormPanel extends JPanel {
        private final JTextField pubNameTextField;
        private final JButton addPublisherButton;
        private final JTextField searchPublisherTextField;
        private final JTextField editPublisherTextField;
        private final JButton searchPublisherButton;
        private final JButton editPublisherButton;
        private final JButton deletePublisherButton;

        public String id;


        public PublisherFormPanel() {
            pubNameTextField = new JTextField();
            searchPublisherTextField = new JTextField();
            editPublisherTextField = new JTextField();
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
                    String name = searchPublisherTextField.getText();
                    Controller.INSTANCE.searchPublisher(name);
                }
            });


            editPublisherButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String name = editPublisherTextField.getText();
                    Controller.INSTANCE.editPublisher(name, Long.parseLong(id));
                }
            });

            deletePublisherButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Controller.INSTANCE.deletePublisher(Long.parseLong(id));
                  }
            });

        }

        public String getId(){ return id; }

        public void setId(String newID){id = newID;}

        private void createGUI() {

            setBorder(BorderFactory.createTitledBorder("Publisher"));
            setLayout(new GridBagLayout());


            GridBagConstraints gc = new GridBagConstraints();
            gc.weighty = 5;
            gc.weightx = 5;
            gc.fill = GridBagConstraints.HORIZONTAL;
            gc.insets = new Insets(4, 4, 4, 5);
            gc.anchor = GridBagConstraints.LINE_END;

            gc.gridx = 0;
            gc.gridy = 0;
//            gc.anchor = GridBagConstraints.WEST;
            add(new JLabel("Publisher Name : ",SwingConstants.RIGHT),gc);

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

            gc.gridx = 0;
            gc.gridy = 2;
//            gc.anchor = GridBagConstraints.WEST;
            add(new JLabel("Edit or Delete Publisher : ",SwingConstants.RIGHT),gc);

            gc.gridx = 1;
            gc.gridy = 2;
//            gc.ipadx = 50;
//            gc.gridwidth = 1;
            gc.fill = GridBagConstraints.HORIZONTAL;
//            gc.anchor = GridBagConstraints.NONE;
            add(editPublisherTextField,gc);

            gc.gridx = 2;
            gc.gridy = 2;
//            gc.weightx = 0.5;
            gc.fill = GridBagConstraints.NONE;
//            gc.anchor = GridBagConstraints.NONE;
            add(editPublisherButton,gc);

            gc.gridx = 2;
            gc.gridy = 3;
//            gc.weightx = 0.5;
            gc.fill = GridBagConstraints.EAST;
//            gc.anchor = GridBagConstraints.NONE;
            add(deletePublisherButton,gc);

        }


    }

}
