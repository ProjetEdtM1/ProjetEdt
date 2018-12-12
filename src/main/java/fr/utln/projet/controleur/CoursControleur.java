package fr.utln.projet.controleur;

import com.sun.deploy.panel.NumberDocument;
import fr.utln.projet.DAO.DAOCours;
import fr.utln.projet.modele.CoursModele;
import fr.utln.projet.utilisateur.Cours;
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

public class CoursControleur {
    private PlanningSuperVue planningVue;
    private CoursModele coursModele;

    private String nouveauGroupeCours = new String();
    private String nouveauIdProfesseurCours = new String();
    private Document nouveauIntituleModule = new PlainDocument();
//    private Document nouveauJourCours = new NumberDocument();
    private int nouveauJourCours;
    private int nouveauMoisCours;
    private int nouveauAnneeCours;
    private int nouveauHeureDebCours;
    private int nouveauMinuteDebCours;
    private int nouveauHeureFinCours;
    private int nouveauMinuteFinCours;
    private Document nouveauNumSalleCours = new PlainDocument();

    public CoursControleur(PlanningSuperVue planningVue){
        this.planningVue = planningVue;
        this.coursModele = new CoursModele();

        DocumentListener ecouterChangementTexte = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        };

        nouveauIntituleModule.addDocumentListener(ecouterChangementTexte);
        nouveauNumSalleCours.addDocumentListener(ecouterChangementTexte);
//        nouveauJourCours.addDocumentListener(ecouterChangementTexte);
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

    private int convertionDocumentInt(Document document) {
        try {
            String aux = document.getText(0, document.getLength());
            return Integer.parseInt(aux);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        return 0;
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
        try {

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
            System.out.println(nouveauIdProfesseurCours);
            System.out.println(nouveauIntituleModule.getText(0, nouveauIntituleModule.getLength()));
            System.out.println(nouveauNumSalleCours.getText(0, nouveauNumSalleCours.getLength()));

            System.out.println(date);
            System.out.println(hDebut);
            System.out.println(hFin);



            coursModele.ajouterCours(
                    nouveauGroupeCours, nouveauIdProfesseurCours,
                    nouveauIntituleModule.getText(0, nouveauIntituleModule.getLength()),
                    date, hDebut, hFin, nouveauNumSalleCours.getText(0, nouveauNumSalleCours.getLength())
            );

        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }


    public Document getNouveauIntituleModule() {
        return nouveauIntituleModule;
    }

    public Document getNouveauNumSalleCours() {
        return nouveauNumSalleCours;
    }


    public void setnouveauGroupeCours(String nouveauGroupeCours) {
        this.nouveauGroupeCours = nouveauGroupeCours;
    }

    public void setNouveauIdProfesseurCours(String nouveauIdProfesseurCours) {
        this.nouveauIdProfesseurCours = nouveauIdProfesseurCours;
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

    // Pas toucher a ca !!
    public List<Cours> getCoursSemaineGroupe(String intituleGroupe){
        return this.coursModele.getCoursSemaineGroupe(intituleGroupe);
    }

    public List<Cours> getCoursSemaineProf(String login){
        return this.coursModele.getCoursSemaineProf(login);
    }

    public  Object[][] remplireTableCoursProf(String login){
        Object[][] donnes = new Object[11][8];
        List<Cours> listeCours = new ArrayList<>();
        listeCours = getCoursSemaineProf(login);

        donnes[0][0]="8h";
        donnes[1][0]="9h";
        donnes[2][0]="10h";
        donnes[3][0]="11h";
        donnes[4][0]="12h";
        donnes[5][0]="13h";
        donnes[6][0]="14h";
        donnes[7][0]="15h";
        donnes[8][0]="16h";
        donnes[9][0]="17h";
        donnes[10][0]="18h";

        for(Cours c: listeCours){
            int jour = c.getDateCours().getDay();

            String heureDeb = c.getHeureDebCours().toString();
            String heureFin = c.getHeureFinCours().toString();

            heureDeb = Character.toString(heureDeb.charAt(0)) + Character.toString(heureDeb.charAt(1));
            heureFin = Character.toString(heureFin.charAt(0)) + Character.toString(heureFin.charAt(1));

            int hDebut = Integer.parseInt(heureDeb);
            int hFin = Integer.parseInt(heureFin);

            int iCours = hDebut-8;
            for(int i = 0;i< hFin-hDebut;++i){
                donnes[iCours][jour] = c.getIntituleModule();
                ++iCours;
            }


        }
        return donnes;
    }

    public  Object[][] remplireTableCours(String intituleGroupe){
        Object[][] donnes = new Object[11][8];
        List<Cours> listeCours = new ArrayList<>();
        listeCours = getCoursSemaineGroupe(intituleGroupe);

        donnes[0][0]="8h";
        donnes[1][0]="9h";
        donnes[2][0]="10h";
        donnes[3][0]="11h";
        donnes[4][0]="12h";
        donnes[5][0]="13h";
        donnes[6][0]="14h";
        donnes[7][0]="15h";
        donnes[8][0]="16h";
        donnes[9][0]="17h";
        donnes[10][0]="18h";

        for(Cours c: listeCours){
            System.out.println(c.getIntituleModule());
            int jour = c.getDateCours().getDay();

            String heureDeb = c.getHeureDebCours().toString();
            String heureFin = c.getHeureFinCours().toString();

            heureDeb = Character.toString(heureDeb.charAt(0)) + Character.toString(heureDeb.charAt(1));
            heureFin = Character.toString(heureFin.charAt(0)) + Character.toString(heureFin.charAt(1));

            int hDebut = Integer.parseInt(heureDeb);
            int hFin = Integer.parseInt(heureFin);
            int iCours = hDebut-8;
            for(int i = 0;i< hFin-hDebut;++i){
                donnes[iCours][jour] = c.getIntituleModule();
                ++iCours;
            }


        }
        return donnes;
    }


    public List<String> getListGroupe() {
        return(coursModele.getListGroupe());
    }
}
