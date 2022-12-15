package coursework.gui;

import coursework.database.AUTHOR;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

// The class makes use of ComboBoxModel that provide a data model for components like JComboBox that allows the user to select one item from a list of options.
// and ProperTyChangeListener provides support for managing property change events.
// The AuthorComboBoxModel is used to create an author JComboBox for the authors
public class AuthorComboBoxModel implements ComboBoxModel, PropertyChangeListener {

    private AUTHOR selectedItem;

    // Setter for setAuthorList
    public void setAuthorList(List<AUTHOR> authorList) {
        this.authorList = authorList;
    }

    // Define the authorList to manage the database of author
    private List<AUTHOR> authorList;

    // Setter for setAuthorList
    public AuthorComboBoxModel(List<AUTHOR> authorList) {
        this.authorList = authorList;
    }


    @Override
    public void setSelectedItem(Object anItem) {
        selectedItem = (AUTHOR) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selectedItem;
    }

    @Override
    public int getSize() {
        return authorList.size();
    }

    @Override
    public Object getElementAt(int index) {
        return authorList.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {

    }

    @Override
    public void removeListDataListener(ListDataListener l) {

    }

    // Update the event value to refresh the Author List
    @Override                               // Lambda
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println(this + ":" + evt.toString());
        if (evt.getPropertyName() == "authorList") {
            setAuthorList((List<AUTHOR>) evt.getNewValue());
        }

    }
}
