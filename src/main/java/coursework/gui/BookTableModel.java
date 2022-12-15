package coursework.gui;

import coursework.database.BOOK;

import javax.swing.table.AbstractTableModel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

// BookTableModel class is used to show in GUI the database of the author list and interact with it
public class BookTableModel extends AbstractTableModel implements PropertyChangeListener {

    private List<BOOK> bookList;

    // explictly adding data.
    public void setBookList(List<BOOK> bookList) {
        this.bookList = bookList;
        fireTableDataChanged(); // Notifies all listeners that all cell values in the table's rows may have changed.
    } // The number of rows may also have changed and the JTable should redraw the table from scratch.
    // The structure of the table (as in the order of the columns) is assumed to be the same.


    public BookTableModel(List<BOOK> bookList) {
        this.bookList = bookList;

    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID";
            case 1:
                return "TITLE";
            case 2:
                return "AUTHOR";
            case 3:
                return "YEAR_OF_PUBLICATION";
            case 4:
                return "PUBLISHER";
            case 5:
                return "SUBJECT";
            case 6:
                return "AUTHOR_ID";
            case 7:
                return "PUBLISHER_ID";
            default:
                return "ERROR";
        }
    }

    @Override
    public int getRowCount() {
        return bookList.size();
    }


    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Object getValueAt(int row, int column) {
        final BOOK book = bookList.get(row);
        switch (column) {
            case 0:
                return book.getId();
            case 1:
                return book.getTITLE();
            case 2:
                return book.getAUTHOR();
            case 3:
                return book.getYEAR_OF_PUBLICATION();
            case 4:
                return book.getPUBLISHER();
            case 5:
                return book.getSUBJECT();
            case 6:
                return book.getAUTHOR_ID();
            case 7:
                return book.getPUBLISHER_ID();
            default:
                return "ERROR";
        }
    }

    // PropertyChangeEvent is an event that is triggered when the value of a property is changed.
    // This can be useful for keeping track of changes to certain properties,
    // such as the value of a text field or the selection in a dropdown menu, and responding to those changes in your code.
    // Can be used to update e.g., a label with the new value of a text field whenever the user types something in the field.
    // In our case is used to Add and refresh data from the List, that comes from the Database
    // Implicitly updating data
    @Override                            // LAMBDA?
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println(this + ":" + evt.toString());
        if (evt.getPropertyName() == "bookList") {
            setBookList((List<BOOK>) evt.getNewValue());
//        } else if (evt.getPropertyName() == "searchList") {
//            getBookSearchList((List<BOOK>) evt.getNewValue());
        }


    }

}
