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

    public int getSize() {
        return listeModule.size();
    }

    public Module getElement(int i){
        return this.listeModule.get(i);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (!listeModule.contains(getSelectedItem()))
            setSelectedItem(null);
        else
            System.out.println(getSelectedItem() + " in " + listeModule);
            fireContentsChanged(this, 0, listeModule.size() - 1);
    }
}
