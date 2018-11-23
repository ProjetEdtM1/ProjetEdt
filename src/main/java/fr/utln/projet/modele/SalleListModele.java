package fr.utln.projet.modele;

import fr.utln.projet.utilisateur.Salle;

import javax.swing.*;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by CLAIN on 23/11/2018.
 */
public class SalleListModele  extends DefaultComboBoxModel<Salle> implements Observer {

    private final List<Salle> salles;

    public SalleListModele(List<Salle> etudiant) {
        this.salles = etudiant;
    }

    @Override
    public void update(Observable o, Object arg) {
        //si l'etudiant selectionné n'est plus dans la liste on déselectionne.
        if (!salles.contains(getSelectedItem()))
            setSelectedItem(null);
        else System.out.println(getSelectedItem()+" in "+ salles);
        fireContentsChanged(this, 0, salles.size() - 1);
    }

    @Override
    public int getSize() {
        return salles.size();
    }

    @Override
    public Salle getElementAt(int index) {
        if (salles.size() > index)
            return salles.get(index);

        return null;
    }
}