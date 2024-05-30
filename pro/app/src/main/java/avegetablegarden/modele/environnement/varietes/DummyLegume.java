package avegetablegarden.modele.environnement.varietes;

import avegetablegarden.modele.environnement.varietes.Varietes;
import avegetablegarden.modele.environnement.varietes.Legume;
import avegetablegarden.modele.environnement.SoilProperties;


public class DummyLegume extends Legume {
    private Varietes _variete;
    public DummyLegume(Varietes variete) {
	super(0, 0, 0, 0);
	_variete = variete;
    }

    @Override
    public Varietes getVariete() {
	return _variete;

    }

    @Override
    public double getCroissanceParfaite() {
	return 0;
    }

    @Override
    protected void grow(double facteurCroissance) {
	System.out.println("Une XXXX pousse !! Croissance actuelle: " +  getGrowth());
    }

}
