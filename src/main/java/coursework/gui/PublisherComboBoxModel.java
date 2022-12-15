package coursework.gui;

import coursework.database.AUTHOR;
import coursework.database.PUBLISHER;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

// The PublisherComboBoxModel is used to create an author JComboBox for the authors
public class PublisherComboBoxModel implements ComboBoxModel, PropertyChangeListener {

    private PUBLISHER selectedItem;

    public void setPublisherList(List<PUBLISHER> publisherList) {
        this.publisherList = publisherList;
    }

    private List<PUBLISHER> publisherList;

    public PublisherComboBoxModel(List<PUBLISHER> publisherList) {
        this.publisherList = publisherList;
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selectedItem = (PUBLISHER) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selectedItem;
    }

    @Override
    public int getSize() {
        return publisherList.size();
    }

    @Override
    public Object getElementAt(int index) {
        return publisherList.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {

    }

    @Override
    public void removeListDataListener(ListDataListener l) {

    }

    // Implicitly updating the data
    @Override                           // Lambda
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println(this + ":" + evt.toString());
        if (evt.getPropertyName() == "publisherList") {
            setPublisherList((List<PUBLISHER>) evt.getNewValue());
        }

    }
}
