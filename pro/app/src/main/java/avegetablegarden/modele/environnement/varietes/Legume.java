package avegetablegarden.modele.environnement.varietes;


import avegetablegarden.modele.environnement.SoilProperties;

public abstract class Legume {
    protected double growth = 1; // 0.0 - 1.0
    
    public abstract Varietes getVariete();

    protected abstract void grow(double facteurCroissance); // définir selon les conditions
        public abstract double getCroissanceParfaite();


    public double getGrowth() {
	return growth;
    }

    protected double hydrometrieOptimale;
    protected double temperatureOptimale;
    protected double ensoleillementOptimal;
    protected double croissanceMax;



    public Legume(double hydrometrieOptimale, double temperatureOptimale, double ensoleillementOptimal, double croissanceMax) {
        this.hydrometrieOptimale = hydrometrieOptimale;
        this.temperatureOptimale = temperatureOptimale;
        this.ensoleillementOptimal = ensoleillementOptimal;
        this.croissanceMax = croissanceMax;
    }


    public void updateWeatherConditions(SoilProperties sp) {
        // Mettre à jour l'état du légume en fonction des nouvelles conditions météorologiques
        double facteurHydrometrie = 1 - Math.abs(sp.rainfall - hydrometrieOptimale) / hydrometrieOptimale;
        double facteurTemperature = 1 - Math.abs(sp.temperature - temperatureOptimale) / temperatureOptimale;
        double facteurEnsoleillement = 1 - Math.abs(sp.sunshine - ensoleillementOptimal) / ensoleillementOptimal;

        double facteurCroissance = (facteurHydrometrie + facteurTemperature + facteurEnsoleillement) / 3;

        grow(facteurCroissance);
    }

}

