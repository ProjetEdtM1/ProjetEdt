package fr.utln.projet.utilisateur;

import java.sql.Date;
import java.sql.Time;

public class Cours {
    private String IntituleGroupe;
    private int idProfesseur;
    private String intituleModule;
    private Date dateCours;
    private Time heureDebCours;
    private Time heureFinCours;
    private int numeroSalle;

    public Cours(/**String intituleGroupe, int idProfesseur, String intituleModule, Date dateCours, Date heureDebCours, Date heureFinCours, Date numeroSalle **/) {
      /**  IntituleGroupe = intituleGroupe;
        this.idProfesseur = idProfesseur;
        this.intituleModule = intituleModule;
        this.dateCours = dateCours;
        this.heureDebCours = heureDebCours;
        this.heureFinCours = heureFinCours;
        this.numeroSalle = numeroSalle;**/
    }

    public String getIntituleGroupe() {
        return IntituleGroupe;
    }

    public void setIntituleGroupe(String intituleGroupe) {
        IntituleGroupe = intituleGroupe;
    }

    public int getIdProfesseur() {
        return idProfesseur;
    }

    public void setIdProfesseur(int idProfesseur) {
        this.idProfesseur = idProfesseur;
    }

    public String getIntituleModule() {
        return intituleModule;
    }

    public void setIntituleModule(String intituleModule) {
        this.intituleModule = intituleModule;
    }

    public Date getDateCours() {
        return dateCours;
    }

    public void setDateCours(Date dateCours) {
        this.dateCours = dateCours;
    }

    public Time getHeureDebCours() {
        return heureDebCours;
    }

    public void setHeureDebCours(Time heureDebCours) {
        this.heureDebCours = heureDebCours;
    }

    public Time getHeureFinCours() {
        return heureFinCours;
    }

    public void setHeureFinCours(Time heureFinCours) {
        this.heureFinCours = heureFinCours;
    }

    public int getNumeroSalle() {
        return numeroSalle;
    }

    public void setNumeroSalle(int numeroSalle) {
        this.numeroSalle = numeroSalle;
    }
}
