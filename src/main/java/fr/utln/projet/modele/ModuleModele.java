package fr.utln.projet.modele;

import fr.utln.projet.module.Module;

import java.util.Observable;

public class ModuleModele extends Observable {

//    public Module getModule(final String intitule) {
//        Module module;
//        // chercher un module dans la DB
//        return module;
//    }


    public void ajouterModule(String intitule, int nbHCm, int nbHTd, int nbHTp) {
        ajouterModule(new Module.Builder(intitule).nbHeureCm(nbHCm).nbHeureTd(nbHTd).nbHeureTp(nbHTp).build());
    }

    public void ajouterModule(Module module) {
        // ajouter le module a la bd
        // on previent les observateurs du changement
        setChanged();
        notifyObservers();
    }

//    public void supprimerModule(final String intitule) {
//        supprimerModule(getModule(intitule));
//    }

    public void supprimerModule(Module module) {

    }
}
