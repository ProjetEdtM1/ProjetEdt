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

        int res = Integer.parseInt(mot);
        return res;

    }

    public boolean convertion(String mot) {
        if (convertionIntStr(mot) < 0) {
            return false;
        }
        return true;
    }


    /**
     *
     * @param intitule
     * @param nbHCm
     * @param nbHTd
     * @param nbHTp
     */

    public void ajouterModule(String intitule, String nbHCm, String nbHTd, String nbHTp) {
        try {
            int intNbHCm = 0, intNbHTd = 0, intNbHTp = 0;

            if (convertion(nbHCm)) {
                intNbHCm = convertionIntStr(nbHCm);
            }
            if (convertion(nbHTd)) {
                    intNbHTd = convertionIntStr(nbHTd);
                }
            if (convertion(nbHTp)) {
                intNbHTp = convertionIntStr(nbHTp);
                }


            moduleDao.persistModule(intitule, intNbHCm, intNbHTd, intNbHTp);
            Module module = new Module.Builder(intitule).nbHeureCm(intNbHCm).nbHeureTd(intNbHTd).nbHeureTp(intNbHTp).build();
            listeModule.add(module);


            // ajouter le listeModule a la bd
            // on previent les observateurs du changement

            setChanged();
            notifyObservers(ModeleModuleEvent.MODULE);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public boolean supprimerModule (Module nouveauModule) {

        if (moduleDao.supprimerModule(nouveauModule.getIntitule()));{

            listeModule.remove(nouveauModule);
            setChanged();
            notifyObservers(ModeleModuleEvent.MODULE);
        }

        return moduleDao.supprimerModule(nouveauModule.getIntitule());
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
        for(Module m : listeModule){

            if ((m.getIntitule().compareTo(intuleModule))==0) {

                m.setNbHeureCm(nbHeureCm);
                m.setNbHeureTd(nbHeureTd);
                m.setNbHeureTP(nbHeureTp);


            }}

        setChanged();
        notifyObservers(ModeleModuleEvent.MODULE);
    }
}
