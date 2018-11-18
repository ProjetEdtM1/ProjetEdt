package fr.utln.projet.modele;

import fr.utln.projet.DAO.DAOEtudiant;
import fr.utln.projet.controleur.EtudiantControleurAuth;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class EtudiantModeleAuth extends Observable {
    private DAOEtudiant etudiantDAO;
    private EtudiantControleurAuth etudiantControleurAuth;
    private ArrayList<Observer> listObserver = new ArrayList<Observer>();

    public EtudiantModeleAuth(EtudiantControleurAuth etudiantControleur) {
        this.etudiantDAO = new DAOEtudiant();
        this.etudiantControleurAuth = etudiantControleur;
        addObserver(this.etudiantControleurAuth.getAuthentificationVue());
    }

    public boolean connecterEtudiant(String login, String mdp) {
        Boolean bool = etudiantDAO.connexionEtudiant(login, mdp);
        notifyObservers();
        setChanged();
        return bool;
    }



    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
    }

    @Override
    public synchronized void deleteObserver(Observer o) {
        super.deleteObserver(o);
    }

    @Override
    public void notifyObservers(Object arg) {
        super.notifyObservers(arg);
    }

}