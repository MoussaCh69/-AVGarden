package avegetablegarden.modele.environnement;

import avegetablegarden.modele.environnement.varietes.Legume;
import avegetablegarden.modele.environnement.varietes.Salade;
import avegetablegarden.modele.environnement.varietes.Carrotte;

public class CaseCultivable extends Case {

    private Legume legume;
    private SoilProperties properties = new SoilProperties();

    public CaseCultivable(VegetableFactory vegetableFactory) {
        super(vegetableFactory);
    }

    @Override
    public void actionUtilisateur() {
        if (legume == null) {
            legume = _vegetableFactory.newVegetable();

        } else {
            legume = null;
        }
    }

    public Legume getLegume() {
        return legume;
    }

    @Override
    public void run() {
        if (legume != null) {


            //legume.updateWeatherConditions(soilProperties);


        }
    }

    public void updateWeatherConditions(SoilProperties sp) {
        // Mettre à jour l'état de la case en fonction des nouvelles conditions météorologiques
        // Par exemple, vous pouvez mettre à jour la croissance des légumes, l'évaporation de l'eau, etc.
        if (legume != null) {
            legume.updateWeatherConditions(sp);
        }
    }

}
