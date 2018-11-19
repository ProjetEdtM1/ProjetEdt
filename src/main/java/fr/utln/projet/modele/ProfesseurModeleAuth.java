package fr.utln.projet.modele;

import fr.utln.projet.DAO.DAOProfesseur;
import fr.utln.projet.controleur.ProfesseurControleurAuth;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class ProfesseurModeleAuth extends Observable {
    private DAOProfesseur professeurDAO;
    private ProfesseurControleurAuth professeurControleurAuth;
    private ArrayList<Observer> listObserver = new ArrayList<Observer>();

    public  ProfesseurModeleAuth(ProfesseurControleurAuth etudiantControleur){
        this.professeurDAO = new DAOProfesseur();
        this.professeurControleurAuth = etudiantControleur;
        addObserver(this.professeurControleurAuth.getAuthentificationVue());
    }

    public boolean connecterProfesseur(String  login,String mdp){
        Boolean bool = professeurDAO.connexionProfesseur(login,mdp);
        notifyObservers();
        setChanged();
        return  bool;
    }

    public boolean connecterProfesseurRef(String login, String mdp){
        Boolean bool = professeurDAO.connexionProfesseurRef(login,mdp);
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