package fr.utln.projet.controleur;

import fr.utln.projet.modele.ReserverSalleModele;
import fr.utln.projet.vue.ReserverSalleVue;
import javafx.beans.binding.IntegerExpression;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

public class ReserverSalleControleur {
    private ReserverSalleModele reserverSalleModele;
    private ReserverSalleVue reserverSalleVue;

    private Document numeroSalle = new PlainDocument();
    private Document idProfesseur = new PlainDocument();
    private Document dateReservation = new PlainDocument();
    private Document heureDebRes = new PlainDocument();
    private Document heureFinRes = new PlainDocument();
    private Document etat = new PlainDocument();


    private Document nouveauNumeroSalle = new PlainDocument();
    private Document nouveauIdProfesseur = new PlainDocument();
    private Document nouveauDateReservation = new PlainDocument();
    private Document nouveauHeureDebRes = new PlainDocument();
    private Document nouveauHeureFinRes = new PlainDocument();
    private Document nouveauEtat = new PlainDocument();

    public ReserverSalleControleur(final ReserverSalleVue reserverSalleVue, ReserverSalleModele reserverSalleModele) {
        this.reserverSalleModele = reserverSalleModele;
        this.reserverSalleVue = reserverSalleVue;

        DocumentListener ecouterChangementtext = new DocumentListener() {
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

                boolean conditionAjout = ((numeroSalle.getLength() == 0) || (idProfesseur.getLength() == 0) || (dateReservation.getLength() == 0)
                || (heureDebRes.getLength()) == 0);

                if(conditionAjout) {
                    ReserverSalleVue.setBoutonAjouter(false);
                }
                else {
                    ReserverSalleVue.setBoutonAjouter(true);
                }
            }
        };

        numeroSalle.addDocumentListener(ecouterChangementtext);
        idProfesseur.addDocumentListener(ecouterChangementtext);
        dateReservation.addDocumentListener(ecouterChangementtext);
        heureDebRes.addDocumentListener(ecouterChangementtext);
        heureFinRes.addDocumentListener(ecouterChangementtext);
        etat.addDocumentListener(ecouterChangementtext);

        nouveauNumeroSalle.addDocumentListener(ecouterChangementtext);
        nouveauIdProfesseur.addDocumentListener(ecouterChangementtext);
        nouveauDateReservation.addDocumentListener(ecouterChangementtext);
        nouveauHeureDebRes.addDocumentListener(ecouterChangementtext);
        nouveauHeureFinRes.addDocumentListener(ecouterChangementtext);
        nouveauEtat.addDocumentListener(ecouterChangementtext);


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

    public void ajouterReservationSalle() {
        try{
            String idProfesseur = nouveauIdProfesseur.getText(0, nouveauIdProfesseur.getLength());
            int newIdProf;
            if(convertion(idProfesseur)) {
                newIdProf = convertionIntStr(idProfesseur);
            }
        } catch (ClassCastException | BadLocationException e){
            System.out.println(e);
        }

//        try{
//            reserverSalleModele.ajouterReservationSalle(
//                    nouveauNumeroSalle.getText(0, nouveauNumeroSalle.getLength()),
//                    nouveauIdProfesseur.getText(0, nouveauIdProfesseur.getLength()),
//                    nouveauDateReservation.getText(0, nouveauDateReservation.getLength()),
//                    nouveauHeureDebRes.getText(0, nouveauHeureDebRes.getLength()),
//                    nouveauHeureFinRes.getText(0, nouveauHeureFinRes.getLength()),
//                    nouveauEtat.getText(0, nouveauEtat.getLength())
//            );
//        } catch (BadLocationException e) {
//            e.printStackTrace();
//        }
    }
}
