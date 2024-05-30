package avegetablegarden.modele.environnement.varietes;

import java.util.logging.Level;
import java.util.logging.Logger;

public enum Varietes { // permet la localisation
    salade ("Salade", 0),
    champignon ("Champignon", 1),
    orange ("Orange", 2),
    citron ("Citron", 3),
    prune ("Prune", 4),
    pasteque ("Pasteque", 5),
    avocat ("Avocat", 6),
    panais ("Panais", 7),

    carrotte ("Carotte", 11);

    private final String _name;
    private final int _index;
 


    Varietes(String name, int index) {
	_name = name;
	_index = index;

       
    }

    public String getNom() {return _name;};
    public int getIndice() {return _index;};
  
    
}
