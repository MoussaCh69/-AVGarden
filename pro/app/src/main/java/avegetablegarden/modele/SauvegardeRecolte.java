package avegetablegarden.modele;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import avegetablegarden.modele.environnement.varietes.Varietes;

public class SauvegardeRecolte {
    private final String fileName;
    private final Map<Varietes, Integer> recolte;

    public SauvegardeRecolte(String fileName) {
        this.fileName = fileName;
        this.recolte = new HashMap<>();
        for (Varietes variete : Varietes.values()) {
            recolte.put(variete, 0);
        }
    }

    public void ajouterRecolte(Varietes variete, int quantite) {
        recolte.put(variete, recolte.get(variete) + quantite);
    }

    public void sauvegarder() {
        File file = new File(fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Varietes variete : Varietes.values()) {
                writer.write(variete.name() + ": " + recolte.get(variete));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde du fichier : " + e.getMessage());
        }
    }
}