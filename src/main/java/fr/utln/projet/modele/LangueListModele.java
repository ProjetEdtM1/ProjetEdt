package fr.utln.projet.modele;

import javax.swing.*;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by CLAIN on 12/11/2018.
 *
 * but : traduire l'application en anglais( et d'autre langues si le temps le permet)
 */
public class LangueListModele extends DefaultComboBoxModel<String> implements Observer {
    private final List<String> langues;

    public LangueListModele(List<String> langue) {
        this.langues = langue;
    }

    @Override
    public void update(Observable o, Object arg) {
        //si l'etudiant selectionné n'est plus dans la liste on déselectionne.
        if (!langues.contains(getSelectedItem()))
            setSelectedItem(null);
        else System.out.println(getSelectedItem()+" in "+ langues);
        fireContentsChanged(this, 0, langues.size() - 1);
    }

    @Override
    public int getSize() {
        return langues.size();
    }

    @Override
    public String getElementAt(int index) {
        if (langues.size() > index)
            return langues.get(index);

        return null;
    }
}