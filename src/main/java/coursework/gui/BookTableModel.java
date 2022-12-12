package coursework.gui;

import coursework.database.BOOK;

import javax.swing.table.AbstractTableModel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class BookTableModel extends AbstractTableModel implements PropertyChangeListener {

    // explictly adding data.
    public void setBookList(List<BOOK> bookList) {
        this.bookList = bookList;
        fireTableDataChanged();
    }

    private List<BOOK> bookList;

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

    @Override                            // LAMBDA
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println(this + ":" + evt.toString());
        if (evt.getPropertyName() == "bookList") {
            setBookList((List<BOOK>) evt.getNewValue());
        }
    }
}
