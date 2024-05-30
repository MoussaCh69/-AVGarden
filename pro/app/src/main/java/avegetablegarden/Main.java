package avegetablegarden;

import avegetablegarden.VueControleur.VueControleurPotager;
import avegetablegarden.VueControleur.Gestion;
import avegetablegarden.VueControleur.Inventaire;
import avegetablegarden.modele.SimulateurPotager;
import javax.swing.UIManager;
import avegetablegarden.modele.Scheduler;
import avegetablegarden.modele.SimulateurPotager;
import avegetablegarden.modele.SimulateurMeteo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class Main {
    public static void main(String[] args) {
	try {
	 UIManager.setLookAndFeel(
            "com.sun.java.swing.plaf.gtk.GTKLookAndFeel");

	} catch (Exception e) {

	}

	Scheduler s = new Scheduler();
	SimulateurMeteo sm = new SimulateurMeteo();
	s.scheduleBackground(sm);
	
	SimulateurPotager sp = new SimulateurPotager(sm, s);

	Gestion g = new Gestion(sm);
	Inventaire i = new Inventaire();
	VueControleurPotager vc = new VueControleurPotager(sp, g, i);

	s.scheduleUi(vc);
	vc.setVisible(true);
	

    }
}
