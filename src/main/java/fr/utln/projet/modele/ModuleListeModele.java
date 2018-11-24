package fr.utln.projet.modele;

import fr.utln.projet.module.Module;

import javax.swing.*;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ModuleListeModele extends DefaultComboBoxModel<Module> implements Observer {
    public List<Module> listeModule;

    public ModuleListeModele(List<Module> module) {
        this.listeModule = module;
    }


    @Override
    public void update(Observable o, Object arg) {
        if (!listeModule.contains(getSelectedItem()))
            setSelectedItem(null);
        else
            System.out.println(getSelectedItem() + " in " + listeModule);
        fireContentsChanged(this, 0, listeModule.size() - 1);
    }

    @Override
    public int getSize() {
        return listeModule.size();
    }

    @Override
    public Module getElementAt(int index) {
        if (listeModule.size() > index)
            return listeModule.get(index);
        return null;
    }
}
