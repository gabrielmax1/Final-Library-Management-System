package coursework.gui;

import coursework.database.PUBLISHER;

import javax.swing.table.AbstractTableModel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class PublisherTableModel extends AbstractTableModel implements PropertyChangeListener {


    // explictly adding data. (non event)
    public void setPublisherList(List<PUBLISHER> publisherList) {
        this.publisherList = publisherList;
        fireTableDataChanged();
    }

    // implicitly addomg data (non event)
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println(this + ":" + evt.toString());
        if (evt.getPropertyName() == "authorList") {
            setPublisherList((List<PUBLISHER>) evt.getNewValue());
        }
    }

    private List<PUBLISHER> publisherList;

    public PublisherTableModel(List<PUBLISHER> publisherList) {
        this.publisherList = publisherList;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID";
            case 1:
                return "NAME";
            default:
                return "ERROR";
        }
    }

    @Override
    public int getRowCount() {
        return publisherList.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int row, int column) {
        final PUBLISHER book = publisherList.get(row);
        switch (column) {
            case 0:
                return book.getId();
            case 1:
                return book.getNAME();
            default:
                return "ERROR";
        }
    }


}
