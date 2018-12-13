package fr.utln.projet.modele;

import fr.utln.projet.module.Module;

import javax.swing.*;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ModuleListeModele  extends DefaultComboBoxModel<Module> implements Observer{

    private final List<Module> modules;

    public ModuleListeModele(List<Module> module) {
        this.modules = module;
    }




    @Override
    public void update(Observable o, Object arg) {

        if (!modules.contains(getSelectedItem()))
            setSelectedItem(null);
        else System.out.println(getSelectedItem()+" in "+ modules);
        fireContentsChanged(this, 0, modules.size() - 1);
    }

    @Override
    public int getSize() {
        return modules.size();
    }

    @Override
    public Module getElementAt(int index) {
        if (modules.size() > index)
            return modules.get(index);

        return null;
    }
}