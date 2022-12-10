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
                return "SUBJECT_ID";
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
        return 6;
    }

    @Override
    public Object getValueAt(int row, int column) {
        final BOOK lecturer = bookList.get(row);
        switch (column) {
            case 0:
                return lecturer.getId();
            case 1:
                return lecturer.getTITLE();
            case 2:
                return lecturer.getAUTHOR();
            case 3:
                return lecturer.getYEAR_OF_PUBLICATION();
            case 4:
                return lecturer.getPUBLISHER();
            case 5:
                return lecturer.getSUBJECT();
            case 6:
                return lecturer.getAUTHOR_ID();
            case 7:
                return lecturer.getPUBLISHER_ID();
            default:
                return "ERROR";
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println(this + ":" + evt.toString());
        if (evt.getPropertyName() == "lecturerList") {
            setBookList((List<BOOK>) evt.getNewValue());
        }
    }
}
