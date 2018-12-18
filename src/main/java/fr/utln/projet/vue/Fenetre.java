package fr.utln.projet.vue;

import javax.swing.*;
import java.awt.*;

public class Fenetre extends JFrame {


    public Fenetre() {
        super();
        this.setTitle("");
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int) dimension.getHeight();
        int width = (int) dimension.getWidth();
        setSize(3 * width / 4, 3 * height / 4);

        this.setVisible(true);
    }
}
