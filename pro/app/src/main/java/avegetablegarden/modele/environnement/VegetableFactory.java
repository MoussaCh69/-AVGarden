package avegetablegarden.modele.environnement;

import avegetablegarden.modele.environnement.varietes.Legume;
import avegetablegarden.modele.environnement.varietes.Salade;
import avegetablegarden.modele.environnement.varietes.DummyLegume;
import avegetablegarden.modele.environnement.varietes.Carrotte;
import avegetablegarden.modele.environnement.varietes.Varietes;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VegetableFactory {

    public VegetableFactory(Varietes initialVariete) {
	variete = initialVariete;
    }
    
    
    public Varietes variete;

    
    public Legume newVegetable() {
        switch (variete) {
	case salade:
	    return new Salade();
	case carrotte:
	    return new Carrotte();
	default:
	    return new DummyLegume(variete);
	    
	  	    
	}
	
	
    }

    
    
}
