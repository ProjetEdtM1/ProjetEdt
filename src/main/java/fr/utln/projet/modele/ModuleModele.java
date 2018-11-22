package fr.utln.projet.modele;

import fr.utln.projet.DAO.ModuleDAO;
import fr.utln.projet.module.Module;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class ModuleModele extends Observable {
    private ModuleDAO moduleDao = new ModuleDAO();

    private List<Module> listeModule = new ArrayList();

    public enum ModeleModuleEvent {MODULE}

    public List<Module> getListeModule() {
        listeModule = moduleDao.creationListModule();
        return listeModule;
    }


    public int convertionIntStr(String mot) {
        try {
            int res = Integer.parseInt(mot);
            return res;
        } catch (NumberFormatException e) {
            System.out.println("Vous n'avez pas entré un nombre");
        }
        return -1;
    }

    public boolean convertion(String mot) {
        if (convertionIntStr(mot) < 0) {
            return false;
        }
        return true;
    }


    public void ajouterModule(String intitule, String nbHCm, String nbHTd, String nbHTp) {
        try {
            int intNbHCm = 0, intNbHTd = 0, intNbHTp = 0;

            if (convertion(nbHCm)) {
                intNbHCm = convertionIntStr(nbHCm);
            }
            if (convertion(nbHCm)) {
                    intNbHTd = convertionIntStr(nbHTd);
                }
            if (convertion(nbHCm)) {
                intNbHTp = convertionIntStr(nbHTp);
                }


            moduleDao.persistModule(intitule, intNbHCm, intNbHTd, intNbHTp);

            // ajouter le listeModule a la bd
            // on previent les observateurs du changement
            setChanged();
            notifyObservers();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public boolean supprimerModule (Module nouveauModule) {
        System.out.println("toto " + nouveauModule.getIntitule());
        boolean i = moduleDao.supprimerModule(nouveauModule.getIntitule());
        System.out.println("AHHAAHHA " + nouveauModule);
        listeModule.remove(nouveauModule);
        setChanged();
        notifyObservers();
        return i;
    }

    /**
     * Permet de modifier un Module
     * @param intuleModule
     * @param nbHeureCm
     * @param nbHeureTd
     * @param nbHeureTp
     * @author Nicolas Guigou
     */
    public void modifierModule(String intuleModule, int nbHeureCm, int nbHeureTd, int nbHeureTp){
        moduleDao.updateModule(intuleModule,nbHeureCm,nbHeureTd,nbHeureTp);
        Module module = new Module.Builder(intuleModule).
                nbHeureCm(nbHeureCm).
                nbHeureTd(nbHeureTd).
                nbHeureTp(nbHeureTp).build();
        for (Module mod :listeModule) {
            if(mod.getIntitule().compareTo(intuleModule)==0){
                listeModule.remove(mod);
                listeModule.add(module);
            }
        }
        setChanged();
        notifyObservers(ModeleModuleEvent.MODULE);
    }
}
