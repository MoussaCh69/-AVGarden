/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avegetablegarden.modele.environnement;

import avegetablegarden.modele.environnement.SoilProperties;

public abstract class Case implements Runnable {
    protected VegetableFactory _vegetableFactory; // pour eviter dependence circulaire

    protected SoilProperties soilProperties;

    
    public Case(VegetableFactory vegetableFactory) {
        _vegetableFactory = vegetableFactory;
    }

    public abstract void actionUtilisateur();


  }
