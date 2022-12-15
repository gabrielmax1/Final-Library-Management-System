package coursework.gui;

import coursework.controller.Controller;
import coursework.database.BOOK;
import coursework.sorting.Bubble;
import coursework.sorting.MergeSort;
import coursework.sorting.QuickSort;
import coursework.sorting.Radix_kt;
import kotlin.Pair;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// This class present the sorting algorithms to apply to the book database
public class SortPanel extends JPanel {

    // All the buttons and textfield are initialised
    private final JButton sortButton;
    private final JRadioButton bubbleSortRadioButton;
    private final JRadioButton quickSortRadioButton;
    private final JRadioButton mergeSortRadioButon;

    private final JRadioButton radixSortRadioButton;
    private final ButtonGroup sortRadioGroup;
    private final ButtonGroup sortFieldGroup;
    private final ButtonGroup implementationFieldGroup;
    private final JRadioButton bookTitleSortRadioButton;
    private final JRadioButton authorNameSortRadioButton;
    private final BookTableModel bookTableModel;
    private final JLabel ticksTextField;

    private final JRadioButton iterativeRadioButton;
    private final JRadioButton recursiveRadioButton;

    // The GUI is defined through the following funciton
    private void createUILayout() {
        {
            setLayout(new GridBagLayout());
            GridBagConstraints gc = new GridBagConstraints();

            gc.weighty = 1;
            gc.weightx = 1;
            gc.fill = GridBagConstraints.BOTH;

            JPanel panelSort = new JPanel();
            panelSort.setBorder(BorderFactory.createTitledBorder("Select Algorithm "));
            panelSort.setLayout(new BoxLayout(panelSort, BoxLayout.Y_AXIS));

            panelSort.add(quickSortRadioButton);
            panelSort.add(mergeSortRadioButon);
            panelSort.add(bubbleSortRadioButton);
            panelSort.add(radixSortRadioButton);

//            panelSort.add(box);

            JPanel panelField = new JPanel();
            panelField.setBorder(BorderFactory.createTitledBorder("Sort By"));
            panelField.setLayout(new BoxLayout(panelField, BoxLayout.Y_AXIS));

            panelField.add(bookTitleSortRadioButton);
            panelField.add(authorNameSortRadioButton);

            JPanel implementField = new JPanel();
            implementField.setBorder(BorderFactory.createTitledBorder("Implementation"));
            implementField.add(recursiveRadioButton);
            implementField.add(iterativeRadioButton);
            implementField.setLayout(new BoxLayout(implementField, BoxLayout.Y_AXIS));


            JPanel performancePanel = new JPanel();
            performancePanel.setBorder(BorderFactory.createTitledBorder("Performance :"));
//            performancePanel.setLayout(new BoxLayout(performancePanel, BoxLayout.X_AXIS));
//            performancePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            performancePanel.setLayout(new GridBagLayout());
            GridBagConstraints gc1 = new GridBagConstraints();

            gc1.weighty = 1;
            gc1.weightx = 1;
            gc1.fill = GridBagConstraints.HORIZONTAL;
            gc1.insets = new Insets(0, 4, 0, 5);
            gc1.anchor = GridBagConstraints.LINE_END;


            gc1.gridx = 0;
            gc1.gridy = 1;
            performancePanel.add(new JLabel("Ticks: ",SwingConstants.RIGHT),gc1);

            gc1.gridx = 1;
            gc1.gridy = 1;
            gc1.anchor = GridBagConstraints.LINE_END;
            performancePanel.add(ticksTextField,gc1);


            gc.gridx = 0;
            gc.gridy = 0;
            gc.gridheight = GridBagConstraints.BOTH;
            add(panelSort, gc);

            gc.gridx = 1;
            gc.gridy = 0;
            gc.gridheight = GridBagConstraints.REMAINDER;
            add(new JScrollPane(new JTable(bookTableModel)), gc);

            gc.gridx = 0;
            gc.gridy = 1;
            gc.gridheight = GridBagConstraints.BOTH;
            add(panelField, gc);

            gc.gridx = 0;
            gc.gridy = 2;
            gc.gridheight = GridBagConstraints.BOTH;
            add(implementField, gc);

            gc.gridx = 0;
            gc.gridy = 3;
            gc.weighty = 0.5;
            gc.gridheight = GridBagConstraints.BOTH;
            add(performancePanel, gc);

            gc.gridx = 0;
            gc.gridy = 4;
            gc.fill = GridBagConstraints.NONE;
            add(sortButton,gc);

        }

    }

    // The sort panel present the books in which the sorting will happen
    public SortPanel() {

        sortButton = new JButton("Sort");
        sortButton.setPreferredSize(sortButton.getMinimumSize());
        bubbleSortRadioButton = new JRadioButton("Bubble Sort");
        quickSortRadioButton = new JRadioButton("Quick Sort");
        mergeSortRadioButon = new JRadioButton("Merg eSort");
        radixSortRadioButton = new JRadioButton("Radix Sort");
        mergeSortRadioButon.setSelected(true);

        sortRadioGroup = new ButtonGroup();
        sortRadioGroup.add(bubbleSortRadioButton);
        sortRadioGroup.add(quickSortRadioButton);
        sortRadioGroup.add(mergeSortRadioButon);
        sortRadioGroup.add(radixSortRadioButton);



        bookTitleSortRadioButton = new JRadioButton("Book Title");
        authorNameSortRadioButton = new JRadioButton("Author Name");
        bookTitleSortRadioButton.setSelected(true);

        sortFieldGroup = new ButtonGroup();
        sortFieldGroup.add(authorNameSortRadioButton);
        sortFieldGroup.add(bookTitleSortRadioButton);


        iterativeRadioButton = new JRadioButton("Iterative Implementation");
        recursiveRadioButton = new JRadioButton("Recusive Implementation");
        recursiveRadioButton.setSelected(true);

        implementationFieldGroup = new ButtonGroup();
        implementationFieldGroup.add(iterativeRadioButton);
        implementationFieldGroup.add(recursiveRadioButton);



        bookTableModel = new BookTableModel(Controller.INSTANCE.getBookList());

        ticksTextField = new JLabel();
        ticksTextField.setEnabled(true);
        ticksTextField.setHorizontalAlignment(JTextField.RIGHT);

        // The GUI is instantiated
        createUILayout();

        // Sort button
        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<BOOK> bookList = Controller.INSTANCE.getBookList();
                int ticks = 0;

                // Bubble sort
                if (bubbleSortRadioButton.isSelected()) {

                    // Bubble sort entry and implementation choice
                    if (authorNameSortRadioButton.isSelected()) {

                        if (recursiveRadioButton.isSelected()) {
                            ticks = Bubble.INSTANCE.sortRecursive((ArrayList<BOOK>) bookList, "AUTHOR", bookList.size(), ticks);
                        }else{
                            ticks = Bubble.INSTANCE.sortIterative((ArrayList<BOOK>) bookList, "AUTHOR");
                        }

                    } else {

                        if(recursiveRadioButton.isSelected()){
                            ticks = Bubble.INSTANCE.sortRecursive((ArrayList<BOOK>) bookList, "TITLE", bookList.size(), ticks);
                        }else{
                            ticks = Bubble.INSTANCE.sortIterative((ArrayList<BOOK>) bookList, "TITLE");
                        }

                    }
                }

                //Quick sort
                else if (quickSortRadioButton.isSelected()){

                    //Quick sort entry and implemetation choice
                    if (authorNameSortRadioButton.isSelected()) {
                        if(recursiveRadioButton.isSelected()){
                            System.out.println("NOT IMPLEMENTED YET");
                        }
                        ticks = QuickSort.INSTANCE.sort((ArrayList<BOOK>) bookList, "AUTHOR");
                    }
                    else
                    {
                        if(iterativeRadioButton.isSelected()){
                            System.out.println("NOT IMPLEMENTED YET");
                        }
                        ticks = QuickSort.INSTANCE.sort((ArrayList<BOOK>) bookList, "TITLE");
                    }

                }
                //Merge Sort
                else if (mergeSortRadioButon.isSelected()) {
                    // MergeSort is not in-memory result.
                    // so we have to update explicitly the TableModel


                    if (authorNameSortRadioButton.isSelected()) {
                        if(iterativeRadioButton.isSelected()){
                            System.out.println("NOT IMPLEMENTED YET");
                        }
                        Pair<ArrayList<BOOK>, Integer> pair = MergeSort.INSTANCE.sort((ArrayList<BOOK>) bookList, "AUTHOR");
                        bookList = pair.component1();
                        ticks = pair.component2();
                    }
                    else
                    {
                        if(iterativeRadioButton.isSelected()){
                            System.out.println("NOT IMPLEMENTED YET");
                        }
                        Pair<ArrayList<BOOK>, Integer> pair = MergeSort.INSTANCE.sort((ArrayList<BOOK>) bookList, "TITLE");
                        bookList = pair.component1();
                        ticks = pair.component2();
                    }
                }
                else if (radixSortRadioButton.isSelected()){

                    if(authorNameSortRadioButton.isSelected()){

                        if (recursiveRadioButton.isSelected()) {
                            ticks = Radix_kt.INSTANCE.sortRecursive((ArrayList<BOOK>) bookList, "AUTHOR", bookList.size(), ticks);
                        }else{
                            ticks = Radix_kt.INSTANCE.sortIterative((ArrayList<BOOK>) bookList, "AUTHOR");
                        }

                    }else{
                        if (recursiveRadioButton.isSelected()) {
                            ticks = Radix_kt.INSTANCE.sortRecursive((ArrayList<BOOK>) bookList,"TITLE", bookList.size(), ticks);
                        }else{
                            ticks = Radix_kt.INSTANCE.sortIterative((ArrayList<BOOK>) bookList, "TITLE");

                        }
                    }

                }

                bookTableModel.setBookList(bookList);
                ticksTextField.setText(Integer.toString(ticks));
                System.out.println("ticks " + ticks);
            }

        });

    }
}
