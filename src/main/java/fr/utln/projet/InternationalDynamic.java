package fr.utln.projet;
import java.util.*;
import java.text.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class InternationalDynamic extends JFrame
        implements ActionListener
{

    static Locale[] localesSupported = {
            Locale.ENGLISH,Locale.FRANCE
    };

    int localeChoosen = 0;
    Locale localeCurrent;
    ResourceBundle rb;

    ButtonGroup bg;
    JButton btnQuit;
    JRadioButton r0, r1, r2;
    JLabel today;
    boolean defaultDone = false;

    public void initLocale(){
        localeCurrent = localesSupported[localeChoosen];
        this.setLocale(localeCurrent);
        rb = ResourceBundle.getBundle("textBouton", localeCurrent);
    }


    public void initText() {
        setTitle ("title");
        r0.setText("r0");
        r1.setText("r1");
        r2.setText("r2");
        btnQuit.setText(rb.getString("Supprimer"));
        Date d = new Date();
        MessageFormat mf = new MessageFormat
                (rb.getString("Annuler"), localeCurrent);
        today.setText(mf.format(new Object [] { d }));
    }

    public void initGUI(){
        setLayout(new FlowLayout());

        // RADIO buttons
        bg = new ButtonGroup();
        r0 = new JRadioButton();
        r1 = new JRadioButton();
        r2 = new JRadioButton();
        bg.add(r0);
        bg.add(r1);
        bg.add(r2);
        add(r0);
        add(r1);
        add(r2);

        // default RADIO button
        if (!defaultDone) {
            String rDef =  rb.getString("Ajouter");
            if (rDef.equals("r0"))
                r0.setSelected(true);
            else if (rDef.equals("r1"))
                r1.setSelected(true);
            else
                r2.setSelected(true);
            defaultDone = true;
        }
        else {
            if (localeChoosen == 0)
                r0.setSelected(true);
            else if (localeChoosen == 1)
                r1.setSelected(true);
            else
                r2.setSelected(true);
        }


        // QUIT button
        btnQuit = new JButton();
        add(btnQuit);

        // today label
        today = new JLabel();
        add(today);

        //
        r0.addActionListener(this);
        r1.addActionListener(this);
        System.out.println(this);
        r2.addActionListener(this);
        btnQuit.addActionListener(this);

        setSize(400,100);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == btnQuit) {
            System.exit(0);
        }
        else if (ae.getSource() == r0) localeChoosen = 0;
        else if (ae.getSource() == r1) localeChoosen = 1;
        else if (ae.getSource() == r2) localeChoosen = 2;
        initLocale();
        initText();
        pack();
    }

    public static void main(String args[]) {
        System.out.println("Current Locale : " + Locale.getDefault());
        Thread t = new Thread () {
            public void run() {
                InternationalDynamic i = new InternationalDynamic();
                i.initLocale();
                i.initGUI();
                i.initText();
                i.pack();
            }
        };
        SwingUtilities.invokeLater(t);
    }
}