package fr.utln.projet.modele;

import fr.utln.projet.utilisateur.Professeur;

import javax.swing.*;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


/**
 * Created by CLAIN on 16/11/2018.
 */

public class ProfesseurListModele extends DefaultComboBoxModel<Professeur> implements Observer{
    private final List<Professeur> professeurs;

    public ProfesseurListModele(List<Professeur> professeurs) {
        this.professeurs = professeurs;
    }

    @Override
    public void update(Observable o, Object arg) {
        //si l'etudiant selectionné n'est plus dans la liste on déselectionne.
        if (!professeurs.contains( getSelectedItem()))
            setSelectedItem(null);
        else System.out.println(getSelectedItem()+" in "+professeurs);
        fireContentsChanged(this, 0, professeurs.size() - 1);
    }

    @Override
    public int getSize() {
        return professeurs.size();
    }

    @Override
    public Professeur getElementAt(int index) {
        if (professeurs.size() > index)
            return professeurs.get(index);
        return null;
    }

}