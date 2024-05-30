package avegetablegarden.VueControleur;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import avegetablegarden.VueControleur.Gestion;
import avegetablegarden.VueControleur.Inventaire;
import avegetablegarden.modele.SimulateurPotager;
import avegetablegarden.modele.environnement.*;
import avegetablegarden.modele.environnement.varietes.Legume;
import avegetablegarden.modele.environnement.varietes.Varietes;


/** Cette classe a deux fonctions :
 *  (1) Vue : proposer une représentation graphique de l'application (cases graphiques, etc.)
 *  (2) Controleur : écouter les évènements clavier et déclencher le traitement adapté sur le modèle
 *
 */
public class VueControleurPotager extends JFrame  implements Runnable {
    private SimulateurPotager simulateurPotager; // référence sur une classe de modèle : permet d'accéder aux données du modèle pour le rafraichissement, permet de communiquer les actions clavier (ou souris)

    private Gestion gestion;
    private Inventaire inventaire;

    private int sizeX; // taille de la grille affichée
    private int sizeY;

    // icones affichées dans la grille

    private ImageIcon icoTerre;
    private ImageIcon icoVide;
    private ImageIcon icoMur;
    private ImageIcon icoHorloge;
    private ImageIcon icoDied;
       private ImageIcon icoFeu;
    private ImageIcon icoGlace;


    private ImageIcon[] icoFruits = new ImageIcon[Varietes.values().length];
   

    private JLabel[][] tabJLabel; // cases graphique (au moment du rafraichissement, chaque case va être associée à une icône, suivant ce qui est présent dans le modèle)


    public VueControleurPotager(SimulateurPotager _simulateurPotager, Gestion _gestion, Inventaire _inventaire) {
        sizeX = simulateurPotager.SIZE_X;
        sizeY = _simulateurPotager.SIZE_Y;
        simulateurPotager = _simulateurPotager;
	gestion = _gestion;
	inventaire = _inventaire;

        chargerLesIcones();
        placerLesComposantsGraphiques();
        //ajouterEcouteurClavier(); // si besoin
    }

    @Override
    public void run() {
	mettreAJourAffichage();
    }
/*
    private void ajouterEcouteurClavier() {
        addKeyListener(new KeyAdapter() { // new KeyAdapter() { ... } est une instance de classe anonyme, il s'agit d'un objet qui correspond au controleur dans MVC
            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()) {  // on regarde quelle touche a été pressée
                    case KeyEvent.VK_LEFT : Controle4Directions.getInstance().setDirectionCourante(Direction.gauche); break;
                    case KeyEvent.VK_RIGHT : Controle4Directions.getInstance().setDirectionCourante(Direction.droite); break;
                    case KeyEvent.VK_DOWN : Controle4Directions.getInstance().setDirectionCourante(Direction.bas); break;
                    case KeyEvent.VK_UP : Controle4Directions.getInstance().setDirectionCourante(Direction.haut); break;
                }
            }
        });
    }
*/

    private void chargerLesIcones() {
    	// image libre de droits utilisée pour les légumes : https://www.vecteezy.com/vector-art/2559196-bundle-of-fruits-and-vegetables-icons	
    

    
        icoVide = chargerIcone(Thread.currentThread().getContextClassLoader().getResourceAsStream("Images/Vide.png"));
        icoMur = chargerIcone(Thread.currentThread().getContextClassLoader().getResourceAsStream("Images/Mur.png")); 
        icoTerre = chargerIcone(Thread.currentThread().getContextClassLoader().getResourceAsStream("Images/Terre.png"));
	icoHorloge = chargerIcone(Thread.currentThread().getContextClassLoader().getResourceAsStream("Images/clock.png"));
        icoDied = chargerIcone(Thread.currentThread().getContextClassLoader().getResourceAsStream("Images/dead-tree.png"));

    

	for(int i = 0; i < Varietes.values().length; i++) {

	    int id = Varietes.values()[i].getIndice();
	    int y = id / 10;
	    int x = id % 10;

	    	    	 
	    icoFruits[i] = chargerIcone(Thread.currentThread().getContextClassLoader().getResourceAsStream("Images/data.png"), x*390, y*390, 120, 120);


	    }

	
		
    }

    private void placerLesComposantsGraphiques() {
        setTitle("A vegetable garden");
        setSize(540, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // permet de terminer l'application à la fermeture de la fenêtre

        JPanel infos = new JPanel();
	      infos.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));

        JButton gestionTransparenceBtn = new JButton("Gestion ");
	   JButton InventaireBtn = new JButton("Inventaire") ;


        gestionTransparenceBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

		gestion.setVisible(true);
          
            }
        });

	   InventaireBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                inventaire.setVisible(true);
            }
        });



        infos.add(gestionTransparenceBtn);
	infos.add(InventaireBtn);


      

        add(infos, BorderLayout.LINE_END);



        JComponent grilleJLabels = new JPanel(new GridLayout(sizeY, sizeX)); // grilleJLabels va contenir les cases graphiques et les positionner sous la forme d'une grille

        tabJLabel = new JLabel[sizeX][sizeY];

        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                JLabel jlab = new JLabel();

                tabJLabel[x][y] = jlab; // on conserve les cases graphiques dans tabJLabel pour avoir un accès pratique à celles-ci (voir mettreAJourAffichage() )
                grilleJLabels.add(jlab);
            }
        }
        add(grilleJLabels, BorderLayout.CENTER);

        // écouter les évènements

        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                final int xx = x; // constantes utiles au fonctionnement de la classe anonyme
                final int yy = y;
                tabJLabel[x][y].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        simulateurPotager.actionUtilisateur(xx+ yy*sizeX);
                    }
                });
            }
        }




	JPanel grilleFruits = new JPanel(new GridLayout(0, 2));
	
	JScrollPane scrollPane = new JScrollPane(grilleFruits);

	add(scrollPane, BorderLayout.LINE_START);

	
	ButtonGroup bgroup = new ButtonGroup(); // juste pour deselectionner tout les autres
	for(int i=0; i<icoFruits.length; i++) {
	    final int ii = i;
	    JToggleButton b = new JToggleButton(icoFruits[i]);
	    b.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
			// A deplacer dans simulateurPotager donner la classe pas une vairete associee
			simulateurPotager.setCurrentVegetable(Varietes.values()[ii]);
		    }
		});
	    grilleFruits.add(b);
	    bgroup.add(b);
	    
	}
	
    }

    
    /**
     * Il y a une grille du côté du modèle ( jeu.getGrille() ) et une grille du côté de la vue (tabJLabel)
     */
    private void mettreAJourAffichage() {

        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
		Case e = simulateurPotager.getEntity(y*sizeX+x);
                if (e instanceof CaseCultivable) { // si la grille du modèle contient un Pacman, on associe l'icône Pacman du côté de la vue

                    Legume legume = ((CaseCultivable) e).getLegume();

                    if (legume != null) {
			Varietes v = legume.getVariete();
                  
			// Vérifier si la croissance du légume est inférieure à croissance parfaite
                        if (legume.getGrowth() < legume.getCroissanceParfaite()) {
                            tabJLabel[x][y].setIcon(icoHorloge);
                        } else {
                            tabJLabel[x][y].setIcon(icoFruits[v.ordinal()]);
                        }


                       

                    } else {
                        tabJLabel[x][y].setIcon(icoTerre);
                    }

                    // si transparence : images avec canal alpha + dessins manuels (voir ci-dessous + créer composant qui redéfinie paint(Graphics g)), se documenter
                    //BufferedImage bi = getImage("Images/smick.png", 0, 0, 20, 20);
                    //tabJLabel[x][y].getGraphics().drawImage(bi, 0, 0, null);
                } else if (e instanceof CaseNonCultivable) {
                    tabJLabel[x][y].setIcon(icoMur);
                } else {

                    tabJLabel[x][y].setIcon(icoVide);
                }
            }
        }
    }


    // chargement de l'image entière comme icone
    private ImageIcon chargerIcone(InputStream urlIcone) {
        BufferedImage image = null;

        try {
            image = ImageIO.read(urlIcone);
        } catch (IOException ex) {
            Logger.getLogger(VueControleurPotager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }


        return new ImageIcon(image);
    }

    // chargement d'une sous partie de l'image
    private ImageIcon chargerIcone(InputStream urlIcone, int x, int y, int w, int h) {
        // charger une sous partie de l'image à partir de ses coordonnées dans urlIcone
        BufferedImage bi = getSubImage(urlIcone, x, y, w, h);
        // adapter la taille de l'image a la taille du composant (ici : 20x20)
        return new ImageIcon(bi.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
    }

    private BufferedImage getSubImage(InputStream urlIcone, int x, int y, int w, int h) {
        BufferedImage image = null;

        try {
            image = ImageIO.read(urlIcone);
        } catch (IOException ex) {
            Logger.getLogger(VueControleurPotager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        BufferedImage bi = image.getSubimage(x, y, w, h);
        return bi;
    }

}
