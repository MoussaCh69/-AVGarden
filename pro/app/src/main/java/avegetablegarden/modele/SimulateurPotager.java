/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avegetablegarden.modele;


import avegetablegarden.modele.environnement.Case;
import avegetablegarden.modele.environnement.CaseCultivable;
import avegetablegarden.modele.environnement.CaseNonCultivable;
import avegetablegarden.modele.environnement.varietes.Salade;
import avegetablegarden.modele.environnement.varietes.Varietes;
import avegetablegarden.modele.environnement.varietes.Legume;
import  avegetablegarden.modele.environnement.VegetableFactory;
import avegetablegarden.modele.Scheduler;


import java.util.Map;
import java.util.Random;


public class SimulateurPotager implements Runnable {

    public static final int SIZE_X = 20;
    public static final int SIZE_Y = 10;

    private SimulateurMeteo simMet;
    private Scheduler scheduler;

    public SimulateurPotager(SimulateurMeteo simulateurMeteo, Scheduler scheduler) {
	this.simMet = simulateurMeteo;
	this.scheduler = scheduler;

	initialisationDesEntites();

	scheduler.scheduleBackground(this); // pas ideal;


    }
    
 
    private Case[] grilleCases = new Case[SIZE_X*SIZE_Y]; // permet de récupérer une entité à partir de ses coordonnées


    private VegetableFactory _vegetableFactory = new VegetableFactory(Varietes.salade);


    public void setCurrentVegetable(Varietes v) {
	_vegetableFactory.variete = v;
    }
    

    
    private void initialisationDesEntites() {

        // murs extérieurs horizontaux
        for (int x = 0; x < 20; x++) {
            addEntite(new CaseNonCultivable(_vegetableFactory), x);
            addEntite(new CaseNonCultivable(_vegetableFactory), x+9*SIZE_X);
        }

        // murs extérieurs verticaux
        for (int y = 1; y < 9; y++) {
            addEntite(new CaseNonCultivable(_vegetableFactory), y*SIZE_X);
            addEntite(new CaseNonCultivable(_vegetableFactory), 19+y*SIZE_X);
        }

        addEntite(new CaseNonCultivable(_vegetableFactory), 2+6*SIZE_X);
        addEntite(new CaseNonCultivable(_vegetableFactory), 3+6*SIZE_X);

        Random rnd = new Random();

        for (int x = 5; x < 15; x++) {
            for (int y = 3; y < 7; y++) {
                CaseCultivable cc = new CaseCultivable(_vegetableFactory);
                addEntite(cc , x+y*SIZE_X);
                if (rnd.nextBoolean()) {
                    cc.actionUtilisateur();
                }

                scheduler.scheduleBackground(cc);

            }
        }

    }

    public void actionUtilisateur(int cellId) {
        if (grilleCases[cellId] != null) {
            grilleCases[cellId].actionUtilisateur();
        }
    }

    private void addEntite(Case e, int cellId) {
        grilleCases[cellId] = e;
        //map.put(e, new Point(x, y));
    }

    public Case getEntity(int cellId) {
	return grilleCases[cellId];
    }


    @Override
    public void run() {
	updateWeatherConditions();
	//pas ideal, seulement pour eviter la depencence circulaire entre SimulateurMeteo et SimulateurPotager
    }
    
    public void updateWeatherConditions() {
        // Mettre à jour l'état du potager en fonction des nouvelles conditions météorologiques
        // Par exemple, vous pouvez mettre à jour la croissance des légumes, l'évaporation de l'eau, etc.

        for (int x = 0; x < SIZE_X*SIZE_Y; x++) {
                Case currentCase = grilleCases[x];
                if (currentCase instanceof CaseCultivable) {
                    ((CaseCultivable) currentCase).updateWeatherConditions(simMet.getWeatherConditions());
                }
            }
        
    }



}
