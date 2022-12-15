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
        fireTableDataChanged(); // Notifies all listeners that all cell values in the table's rows may have changed.
    } // The number of rows may also have changed and the JTable should redraw the table from scratch.
    // The structure of the table (as in the order of the columns) is assumed to be the same.

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
    // PropertyChangeEvent is an event that is triggered when the value of a property is changed.
    // This can be useful for keeping track of changes to certain properties,
    // such as the value of a text field or the selection in a dropdown menu, and responding to those changes in your code.
    // Can be used to update e.g., a label with the new value of a text field whenever the user types something in the field.
    // In our case is used to Add and refresh data from the List, that comes from the Database
    // implicitly addomg data (non event)
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println(this + ":" + evt.toString());
        if (evt.getPropertyName() == "publisherList") {
            setPublisherList((List<PUBLISHER>) evt.getNewValue());
        }
    }
}
