package fr.utln.projet.modele;

import fr.utln.projet.utilisateur.Materiel;

import javax.swing.*;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by CLAIN on 24/11/2018.
 */


public class MaterielListModel extends DefaultComboBoxModel<Materiel> implements Observer {

    private final List<Materiel> materiels;

    public MaterielListModel(List<Materiel> materiel) {
        this.materiels = materiel;
    }

    @Override
    public void update(Observable o, Object arg) {

        if (!materiels.contains(getSelectedItem()))
            setSelectedItem(null);
        else
            System.out.println(getSelectedItem()+" in "+materiels);
        fireContentsChanged(this, 0, materiels.size() - 1);
    }

    @Override
    public int getSize() {
        return materiels.size();
    }

    @Override
    public Materiel getElementAt(int index) {
        if (materiels.size() > index)
            return materiels.get(index);

        return null;
    }
}