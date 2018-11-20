package fr.utln.projet.modele;

import fr.utln.projet.DAO.ModuleDAO;
import fr.utln.projet.module.Module;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import static java.lang.Integer.*;

public class ModuleModele extends Observable {
    private ModuleDAO moduleDao = new ModuleDAO();

    List<Module> module = new ArrayList();

//    public enum ModuleModeleEvent {MODULE}

    public List<Module> getModule() {
        module = moduleDao.creationListModule();
        return module;
    }

//    public Module getModule(final String intitule) {
//        Module module;
//        // chercher un module dans la DB
//        return module;
//    }

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

            // Module module = new Module.Builder(intitule).nbHeureCm(intNbHCm).nbHeureTd(intNbHTd).nbHeureTp(intNbHTp).build();

            // ajouter le module a la bd
            // on previent les observateurs du changement
            setChanged();
            notifyObservers();
        } catch (SQLException e) {
            e.printStackTrace();
        }


//    public void supprimerModule(final String intitule) {
//        supprimerModule(getModule(intitule));
//    }

//        public void supprimerModule (Module module){
//
//        }
    }

    public void supprimerModule (Module module) {
        moduleDao.supprimerModule(module.getIntitule());
        setChanged();
        notifyObservers();
    }
}
