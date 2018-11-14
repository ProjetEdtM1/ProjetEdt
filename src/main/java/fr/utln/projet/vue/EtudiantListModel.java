package fr.utln.projet.vue;

import fr.utln.projet.utilisateur.Etudiant;

import javax.swing.*;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by CLAIN on 12/11/2018.
 */

public class EtudiantListModel extends DefaultComboBoxModel<Etudiant> implements Observer{
    private final List<Etudiant> etudiants;

    public EtudiantListModel(List<Etudiant> etudiant) {
        this.etudiants = etudiant;
    }

    @Override
    public void update(Observable o, Object arg) {
        //si l'etudiant selectionné n'est plus dans la liste on déselectionne.
        if (!etudiants.contains(getSelectedItem()))
            setSelectedItem(null);
        else System.out.println(getSelectedItem()+" in "+etudiants);
        fireContentsChanged(this, 0, etudiants.size() - 1);
    }

    @Override
    public int getSize() {
        return etudiants.size();
    }

    @Override
    public Etudiant getElementAt(int index) {
        return etudiants.get(index);
    }
}