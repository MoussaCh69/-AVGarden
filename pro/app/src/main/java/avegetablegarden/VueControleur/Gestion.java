package avegetablegarden.VueControleur;

import avegetablegarden.modele.SimulateurMeteo;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class Gestion extends JFrame implements ChangeListener {

    JFrame frame;
    JPanel panel;
    JLabel L_Temperature;
    JSlider slider_C;
    JSlider slider_H;
    JLabel Label_H;
    JLabel Label_E;
    JRadioButton radioDay;
    JRadioButton radioNight;
    ButtonGroup buttonGroup;

    private SimulateurMeteo simulateurMeteo;

    public Gestion(SimulateurMeteo simulateurMeteo) {
        this.simulateurMeteo = simulateurMeteo;


	setTitle("Gestion");
        panel = new JPanel();
        L_Temperature = new JLabel();
        Label_E = new JLabel();
        Label_H = new JLabel();
        slider_C = new JSlider(0, 100, 20);

        slider_C.setPreferredSize(new Dimension(400, 200));

        slider_C.setPaintTicks(true);
        slider_C.setMinorTickSpacing(10);

        slider_C.setPaintTrack(true);
        slider_C.setMajorTickSpacing(25);

        slider_C.setPaintLabels(true);


        slider_C.setOrientation(SwingConstants.VERTICAL);

        L_Temperature.setText("°C = " + slider_C.getValue());

        slider_C.addChangeListener(this);
        slider_H = new JSlider(0, 100, 60);

        slider_H.setPreferredSize(new Dimension(400, 200));

        slider_H.setPaintTicks(true);
        slider_H.setMinorTickSpacing(10);

        slider_H.setPaintTrack(true);
        slider_H.setMajorTickSpacing(25);

        slider_H.setPaintLabels(true);
	
        slider_H.setOrientation(SwingConstants.VERTICAL);

        Label_H.setText("Hydrometrie= " + slider_H.getValue());

        slider_H.addChangeListener(this);
        radioDay = new JRadioButton("Jour");
        radioNight = new JRadioButton("Nuit");

        buttonGroup = new ButtonGroup();
        buttonGroup.add(radioDay);
        buttonGroup.add(radioNight);

        radioDay.addActionListener(e -> {
            simulateurMeteo.setEnsoleillement(1);
        });

        radioNight.addActionListener(e -> {
            simulateurMeteo.setEnsoleillement(0);
        });

        panel.add(radioDay);
        panel.add(radioNight);


        panel.add(slider_C);
        panel.add(L_Temperature);
        panel.add(slider_H);
        panel.add(Label_H);
        add(panel);
        setSize(420, 800);
        
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == slider_C) {
            simulateurMeteo.setTemperature(slider_C.getValue());
            L_Temperature.setText("°C = " + slider_C.getValue());
        } else if (e.getSource() == slider_H) {
            simulateurMeteo.setHydrometrie(slider_H.getValue());
            Label_H.setText("Hydrometrie= " + slider_H.getValue());
        }
    }
}
