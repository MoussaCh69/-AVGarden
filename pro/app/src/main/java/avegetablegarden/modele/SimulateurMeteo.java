package avegetablegarden.modele;

import avegetablegarden.modele.SimulateurPotager;
import avegetablegarden.modele.Scheduler;
import java.util.Random;
import avegetablegarden.modele.environnement.SoilProperties;

// Change les SoilPRoperties des cases du simulateurpotager
public class SimulateurMeteo implements Runnable {

    private SoilProperties weatherConditions = new SoilProperties();
    private Random random;

    public SimulateurMeteo() {
        random = new Random();
        weatherConditions.rainfall = 70;
        weatherConditions.temperature = 20; // -10 à 100 degrés Celsius
        weatherConditions.sunshine = 1 ; // 0 à 1 (0 = nuit, 1 = plein soleil)
    }

    public SoilProperties getWeatherConditions() {
        return weatherConditions;
    }

    public void setWeatherConditions(SoilProperties p) {
	weatherConditions = p;
    }

        public void setTemperature(double temperature) {
        weatherConditions.temperature = temperature;
    }

    public void setHydrometrie(double hydrometrie) {
        weatherConditions.rainfall = hydrometrie;
    }
    public void setEnsoleillement (int ensoleillement) {
        weatherConditions.sunshine = ensoleillement;
    }


    @Override
    public void run() {
        // Mise à jour de l'hydrométrie, de la température et de l'ensoleillement
        weatherConditions.rainfall = Math.min(Math.max(weatherConditions.rainfall + (random.nextDouble() * 10 - 5), 0), 100);
         weatherConditions.temperature = Math.min(Math.max( weatherConditions.temperature + (random.nextDouble() * 6 - 3), -10), 30);
        weatherConditions.sunshine = Math.min(Math.max(weatherConditions.sunshine + (random.nextDouble() * 0.1 - 0.05), 0), 1);

    }
}
