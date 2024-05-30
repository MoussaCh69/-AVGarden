package avegetablegarden.modele.environnement.varietes;

import avegetablegarden.modele.environnement.SoilProperties;

public class Salade extends Legume {

    public Salade() {
        super(80, 20, 1, 1); // Ces valeurs sont des exemples, ajustez-les en fonction des conditions optimales pour une salade
    }

    @Override
    public Varietes getVariete() {
        return Varietes.salade;
    }

       @Override
    public double getCroissanceParfaite() {
        return 9.0; // La valeur de croissance parfaite pour la salade est 20
    }


    @Override
    protected void grow(double facteurCroissance) {
        // Mettre à jour la croissance actuelle du légume
        growth += croissanceMax * facteurCroissance;
        if (growth >= 10) {

            // Le légume est complètement développé
        }

        // TODO: Ajoutez la logique
        System.out.println("Une salade pousse !! Croissance actuelle: " + getGrowth());
    }
}
