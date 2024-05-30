package avegetablegarden.modele.environnement.varietes;

import avegetablegarden.modele.environnement.SoilProperties;

public class Carrotte extends Legume {

    public Carrotte() {
        super(70, 15, 1, 1.0); // Ces valeurs sont des exemples, ajustez-les en fonction des conditions optimales pour une carotte
    }

    @Override
    public Varietes getVariete() {
        return Varietes.carrotte;
    }

        @Override
    public double getCroissanceParfaite() {
        return 20.0;
    }

    
    @Override
    protected void grow(double facteurCroissance) {
        // Mettre à jour la croissance actuelle du légume
        growth += croissanceMax * facteurCroissance;

        System.out.println("Une carotte pousse !! Croissance actuelle: " + getGrowth());
    }
}
