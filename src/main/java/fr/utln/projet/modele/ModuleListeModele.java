package fr.utln.projet.modele;

import fr.utln.projet.module.Module;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ModuleListeModele implements Observer {
    public List<Module> listeModule;

    public ModuleListeModele(List<Module> module) {
        this.listeModule = module;
    }

    public int getSize() {
        return listeModule.size();
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
