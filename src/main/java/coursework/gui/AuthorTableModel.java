package coursework.gui;

import coursework.database.AUTHOR;

import javax.swing.table.AbstractTableModel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class AuthorTableModel extends AbstractTableModel implements PropertyChangeListener {


    // explictly adding data. (non event)
    public void setAuthorList(List<AUTHOR> authorList) {
        this.authorList = authorList;
        fireTableDataChanged();
    }

    // implicitly addomg data (non event)
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println(this + ":" + evt.toString());
        if (evt.getPropertyName() == "authorList") {
            setAuthorList((List<AUTHOR>) evt.getNewValue());
        }
    }

    private List<AUTHOR> authorList;

    public AuthorTableModel(List<AUTHOR> authorList) {
        this.authorList = authorList;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID";
            case 1:
                return "FIRSTNAME";
            case 2:
                return "SURNAME";
            default:
                return "ERROR";
        }
    }

    @Override
    public int getRowCount() {
        return authorList.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int row, int column) {
        final AUTHOR book = authorList.get(row);
        switch (column) {
            case 0:
                return book.getId();
            case 1:
                return book.getFIRSTNAME();
            case 2:
                return book.getSURNAME();
            default:
                return "ERROR";
        }
    }
    // implicitly add data (non event)
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println(this + ":" + evt.toString());
        if (evt.getPropertyName() == "authorList") {
            setAuthorList((List<AUTHOR>) evt.getNewValue());
        }
    }

}
