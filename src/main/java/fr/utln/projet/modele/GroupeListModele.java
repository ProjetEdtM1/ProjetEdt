package fr.utln.projet.modele;

import fr.utln.projet.DAO.DAOEtudiant;
import fr.utln.projet.controleur.EtudiantControleurAuth;
import fr.utln.projet.utilisateur.Etudiant;

import javax.swing.*;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.ArrayList;

/**
 * Created by CLAIN on 16/11/2018.
 */

public class GroupeListModele extends DefaultComboBoxModel<String> implements Observer{
    private final List<String> groupesList;

    public GroupeListModele(List<String> str) {
        this.groupesList = str;
    }

    @Override
    public void update(Observable o, Object arg) {
        //si l'etudiant selectionné n'est plus dans la liste on déselectionne.
        if (!groupesList.contains(getSelectedItem()))
            setSelectedItem(null);
        else System.out.println(getSelectedItem()+" in "+groupesList);
        fireContentsChanged(this, 0, groupesList.size() - 1);
    }

    @Override
    public int getSize() {
        return groupesList.size();
    }

    @Override
    public String getElementAt(int index) {
        return groupesList.get(index);
    }
}