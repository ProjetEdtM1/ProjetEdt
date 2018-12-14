package fr.utln.projet.controleur;


import fr.utln.projet.modele.CoursModele;
import fr.utln.projet.module.Module;
import fr.utln.projet.utilisateur.Cours;
import fr.utln.projet.utilisateur.Professeur;
import fr.utln.projet.utilisateur.Salle;
import fr.utln.projet.vue.CoursVue;
import fr.utln.projet.vue.PlanningSuperVue;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import java.sql.Date;
import java.sql.Time;

import java.util.ArrayList;
import java.util.List;

public class GererCoursControleur {
    private CoursModele coursModele;

    private String nouveauGroupeCours = new String();
    private String nouveauIdProfesseurCours = new String();
    private String nouveaModuleCours = new String();
    private int nouveauJourCours;
    private int nouveauMoisCours;
    private int nouveauAnneeCours;
    private int nouveauHeureDebCours;
    private int nouveauMinuteDebCours;
    private int nouveauHeureFinCours;
    private int nouveauMinuteFinCours;
    private int nouveauNumSalleCours;

    public GererCoursControleur(CoursModele coursModele){
        this.coursModele = coursModele;
    }


    // format de la date: annee-mois-jours
    //yyyy-m[m]-j[j]
    private Date convertionDate(int annee, int mois, int jour) {
        String tmpAnnee = new String();
        String tmpMois = new String();
        String tmpJour = new String();
        String tmpDate = new String();
        Date date;

        // converti les int en String
        tmpAnnee = String.format("%s%s", tmpAnnee, annee);
        tmpMois = String.format("%s%s", tmpMois, mois);
        tmpJour = String.format("%s%s", tmpJour, jour);
        tmpDate = tmpAnnee + "-" + tmpMois + "-" + tmpJour;

        // Converti la String tmpDate en Date
        date = Date.valueOf(tmpDate);
        return date;
    }

    // format de l'heure: heure:minute:secondes
    // h[h]:m[m]:s[s]
    private Time convertionHeure(int heure, int minute) {
        System.out.println("bbb");
        String tmpHeure = new String();
        String tmpMinute = new String();
        String tmpHeuretot = new String();
        Time time;

        tmpHeure = String.format("%s%s", tmpHeure, heure);
        tmpMinute = String.format("%s%s", tmpMinute, minute);
        tmpHeuretot = tmpHeure + ":" + tmpMinute + ":" + "00";

        time = Time.valueOf(tmpHeuretot);
        return time;

    }



    private void initialisationAnnee() {
        nouveauAnneeCours = 2018;
    }

    private void initialisationMinuteDebut() {
        nouveauMinuteDebCours = 0;
    }

    private void initialisationheureDebut() {
        nouveauHeureDebCours = 8;
    }

    private void initialisationMinuteFin() {
        nouveauMinuteFinCours = 0;
    }

    public void ajoutCours() {
        Date date;
        Time hDebut;
        Time hFin;

        // mettre un try / catch
        // ATTENTION il faut que la JCombobox soit instanciee sinon c'est 0 et ca plante
//            date = convertionDate(nouveauAnneeCours, nouveauMoisCours, convertionDocumentInt(nouveauJourCours));
        if (nouveauAnneeCours == 0) {
            initialisationAnnee();
        }

        if (nouveauMinuteDebCours == 0) {
            initialisationMinuteDebut();
        }

        if (nouveauHeureDebCours == 0) {
            initialisationheureDebut();
        }

        if (nouveauMinuteFinCours == 0) {
            initialisationMinuteFin();
        }

        System.out.println(nouveauAnneeCours);
        date = convertionDate(nouveauAnneeCours, nouveauMoisCours, nouveauJourCours);

        hDebut = convertionHeure(nouveauHeureDebCours, nouveauMinuteDebCours);
        hFin = convertionHeure(nouveauHeureFinCours, nouveauMinuteFinCours);

        System.out.println(nouveauGroupeCours);
        System.out.println(nouveauNumSalleCours);
        System.out.println("aaaaaaaaa" + nouveaModuleCours);
        String nouveauSalleCours = String.valueOf(nouveauNumSalleCours);
        System.out.println(nouveauIdProfesseurCours);
//            System.out.println(nouveauIntituleModule.getText(0, nouveauIntituleModule.getLength()));

        System.out.println(date);
        System.out.println(hDebut);
        System.out.println(hFin);


        coursModele.ajouterCours(
                nouveauGroupeCours, nouveauIdProfesseurCours,
                nouveaModuleCours,
                date, hDebut, hFin, nouveauSalleCours
        );

    }


    public void setnouveauGroupeCours(String nouveauGroupeCours) {
        this.nouveauGroupeCours = nouveauGroupeCours;
    }

    public void setNouveauIdProfesseurCours(String nouveauIdProfesseurCours) {
        this.nouveauIdProfesseurCours = nouveauIdProfesseurCours;
    }

    public void setNouveaModuleCours(String nouveaModuleCours) {
        this.nouveaModuleCours = nouveaModuleCours;
    }

    public void setNouveauJourCours(int nouveauJourCours) {
        this.nouveauJourCours = nouveauJourCours;
    }

    public void setNouveauMoisCours(int nouveauMoisCours) {
        this.nouveauMoisCours = nouveauMoisCours;
    }

    public void setNouveauAnneeCours(int nouveauAnneeCours) {
        this.nouveauAnneeCours = nouveauAnneeCours;
    }

    public void setNouveauHeureDebCours(int nouveauHeureDebCours) {
        this.nouveauHeureDebCours = nouveauHeureDebCours;
    }

    public void setNouveauMinuteDebCours(int nouveauMinuteDebCours) {
        this.nouveauMinuteDebCours = nouveauMinuteDebCours;
    }

    public void setNouveauHeureFinCours(int nouveauHeureFinCours) {
        this.nouveauHeureFinCours = nouveauHeureFinCours;
    }

    public void setNouveauMinuteFinCours(int nouveauMinuteFinCours) {
        this.nouveauMinuteFinCours = nouveauMinuteFinCours;
    }

    public void setNouvauSalleCours(int nouveauNumSalleCours) {
        this.nouveauNumSalleCours = nouveauNumSalleCours;
    }




    public List<String> getListGroupe() {
        return(coursModele.getListGroupe());
    }

    public List<Professeur> getListProfesseur() {
        return(coursModele.getProfesseurs());
    }

    public List<Salle> getListSalle() {
        return coursModele.getListSalle();
    }

    public ArrayList<String> getListeModule() {
        return coursModele.getListeModule();
    }

}
