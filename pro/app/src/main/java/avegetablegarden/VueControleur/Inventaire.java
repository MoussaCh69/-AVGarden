package avegetablegarden.VueControleur;

import avegetablegarden.modele.environnement.varietes.Varietes;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Inventaire extends JFrame {
    private HashMap<Varietes, JLabel> legumeLabels;
    private JPanel panel;

    public Inventaire() {
        setTitle("Inventaire");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 400);
        setLocationRelativeTo(null);

        legumeLabels = new HashMap<>();
        panel = new JPanel();
        panel.setLayout(new GridLayout(Varietes.values().length, 2));

        for (Varietes variete : Varietes.values()) {
            JLabel legumeIcon = new JLabel(variete.getNom());
            JLabel legumeCount = new JLabel("0");

            legumeLabels.put(variete, legumeCount);
            panel.add(legumeIcon);
            panel.add(legumeCount);
        }

        add(panel);
        
    }

    public void incrementLegumeCount(Varietes variete) {
        JLabel label = legumeLabels.get(variete);
        int count = Integer.parseInt(label.getText());
        count++;
        label.setText(String.valueOf(count));
    }
}
