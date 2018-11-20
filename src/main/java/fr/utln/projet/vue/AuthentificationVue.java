package fr.utln.projet.vue;

import fr.utln.projet.controleur.EtudiantControleurAuth;
import fr.utln.projet.controleur.ProfesseurControleurAuth;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class AuthentificationVue extends JFrame implements Observer {
    private static final JLabel labelLogin = new JLabel("Login");
    private static final JLabel labelMdp = new JLabel("Mot de passe");
    private static final JTextField champsLogin = new JTextField("");
    private static final JPasswordField champsMdp = new JPasswordField("");
    private static final JButton boutonConnexion = new JButton("Connexion");

    private EtudiantControleurAuth etudiantControleur;
    private ProfesseurControleurAuth professeurControleur;

    public AuthentificationVue(){
        super("Authentification");

        this.etudiantControleur = new EtudiantControleurAuth(this);
        this.professeurControleur = new ProfesseurControleurAuth(this);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500,300);
        final Container contentPane = getContentPane();

        JPanel panelGeneral = new JPanel();
        panelGeneral.setLayout(new BoxLayout(panelGeneral,BoxLayout.Y_AXIS));

        panelGeneral.add(labelLogin);
        panelGeneral.add(new JLabel(""));
        panelGeneral.add(champsLogin);
        panelGeneral.add(labelMdp);
        panelGeneral.add(new JLabel(""));
        panelGeneral.add(champsMdp);

        panelGeneral.add(boutonConnexion);
        contentPane.add(panelGeneral);

        this.boutonConnexion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(champsLogin.getText().isEmpty() || champsMdp.getPassword().length ==0){
                    JOptionPane.showMessageDialog(contentPane, "Veuillez renseigner tous les champs");
                }
                else {
                    String log = champsLogin.getText();
                    String mdp = new String(champsMdp.getPassword());
                    if(log.charAt(0) == 'e') {
                        if(etudiantControleur.connecterEtudiant(log, mdp)){
                            JOptionPane.showMessageDialog(contentPane, "Bienvenu " + log);
                        }else
                            JOptionPane.showMessageDialog(contentPane, "Le login et le mot de passe ne correspondent pas","Erreur",JOptionPane.ERROR_MESSAGE);
                    }
                    else if(log.charAt(0)=='p') {
                        if(professeurControleur.connecterProfesseurRef(log,mdp)){
                            MenuProfRefVue menu = new MenuProfRefVue(log);
                            setVisible(false);
                        }else if(professeurControleur.connecterProfesseur(log,mdp)){
                            JOptionPane.showMessageDialog(contentPane, "Bienvenu " + log);
                        }
                    }
                    else
                        JOptionPane.showMessageDialog(contentPane, "Login invalide","Erreur",JOptionPane.ERROR_MESSAGE);

                }
            }
        });
        setVisible(true);

    }


    @Override
    public void update(Observable o, Object arg) {

    }
}
