

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.*;

public class MatriceCase extends JPanel {
	
	private static final long serialVersionUID = 1L;
    private final Image wall, empty,me,box,target,meOnTarget,boxOnTarget;
    private Case[][] matrice;
    Case [][] mm;
	Niveau niveau;
	Partie partie= new Partie("niveauofficiel.txt");
	
  

    public MatriceCase(int i) {
		super();
		this.wall = new ImageIcon("images/mur.png").getImage();
        this.me = new ImageIcon("images/personnage.jpg").getImage();
        this.box = new ImageIcon("images/box1.png").getImage();
        this.empty = new ImageIcon("images/empty.png").getImage();
        this.target = new ImageIcon("images/cible.png").getImage();
        this.meOnTarget = new ImageIcon("images/personnage.jpg").getImage();
        this.boxOnTarget = new ImageIcon("images/boxontarget.jpg").getImage();
		this.niveau=this.partie.getNiveauDunePartie().get(i);
        this.matrice=this.niveau.getBoard();
        
	}

	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int cellWidthM = 0;
        int cellHeightM=0;
        if(niveau.getLesMondes().get(niveau.getpositionMonde())!=null) {
        	 mm= niveau.getLesMondes().get(niveau.getpositionMonde()).getMatrice();
        	 cellWidthM=getWidth() / (mm[0].length*getMatrice()[0].length);
             cellHeightM=getHeight() / (mm.length*getMatrice()[0].length);
        } 
        int cellWidth = getWidth() / getMatrice()[0].length;
        int cellHeight = getHeight() / getMatrice().length;
        for (int i = 0; i < getMatrice().length; i++) { 
            for (int j = 0; j < getMatrice()[0].length; j++) {
            	 if (getMatrice()[i][j].getContent().equals(Cell.Empty)) {
                    g.drawImage(empty, j * cellWidth, i * cellHeight, cellWidth, cellHeight, null);
                } else if (getMatrice()[i][j].getContent().equals(Cell.Box)) {
                    g.drawImage(box, j * cellWidth, i * cellHeight, cellWidth, cellHeight, null);
                } else if(getMatrice()[i][j].getContent().equals(Cell.Me)) {
                	 g.drawImage(me, j * cellWidth, i * cellHeight, cellWidth, cellHeight, null);
                }else if(getMatrice()[i][j].getContent().equals(Cell.Target)) {
               	 g.drawImage(target, j * cellWidth, i * cellHeight, cellWidth, cellHeight, null);
               }else if(getMatrice()[i][j].getContent().equals(Cell.Wall)) {
              	 g.drawImage(wall, j * cellWidth, i * cellHeight, cellWidth, cellHeight, null);
               }else if(getMatrice()[i][j].getContent().equals(Cell.MeOnTarget)) {
                 	 g.drawImage(meOnTarget, j * cellWidth, i * cellHeight, cellWidth, cellHeight, null);}
               else if(getMatrice()[i][j].getContent().equals(Cell.BoxOnTarget)) {
               	 g.drawImage(boxOnTarget, j * cellWidth, i * cellHeight, cellWidth, cellHeight, null);}
               else{
            	   for (int k = 0; k < mm.length; k++) { 
                       for (int l = 0; l < mm[0].length; l++) {
                       
						if (getMatrice()[k][l].getContent().equals(Cell.Empty)) {
                                g.drawImage(empty, l * cellWidthM, k * cellHeightM, cellWidthM, cellHeightM, null);
                           } else if (mm[k][l].getContent().equals(Cell.Box)) {
                               g.drawImage(box, l * cellWidthM, k * cellHeightM, cellWidthM, cellHeightM, null);
                           } else if(mm[k][l].getContent().equals(Cell.Me)) {
                           	 g.drawImage(me, l * cellWidthM, k * cellHeightM, cellWidthM, cellHeightM, null);
                           }else if(mm[k][l].getContent().equals(Cell.Target)) {
                          	 g.drawImage(target, l * cellWidthM, k * cellHeightM, cellWidthM, cellHeightM, null);
                          }else if(mm[k][l].getContent().equals(Cell.Wall)) {
                         	 g.drawImage(wall, l * cellWidthM, k * cellHeightM, cellWidthM, cellHeightM, null);
                          }else if(mm[k][l].getContent().equals(Cell.MeOnTarget)) {
                            	 g.drawImage(meOnTarget, l * cellWidthM, k * cellHeightM, cellWidthM, cellHeightM, null);}
                          else if(mm[k][l].getContent().equals(Cell.BoxOnTarget)) {
                          	 g.drawImage(boxOnTarget, l * cellWidthM, k * cellHeightM, cellWidthM, cellHeightM, null);}
            	   
                }
            }}
            }}
        }
    
	public void addMousseListenerToPanel(){
		addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
	        int cellWidth = getWidth() / getMatrice()[0].length;
	        int cellHeight = getHeight() / getMatrice().length;
	        int row = e.getPoint().y / cellHeight;
	        int col = e.getPoint().x / cellWidth;
	        PatricksParabox patricksParabox=new PatricksParabox();
	        //on peut varier les position en modifiant 
	        Position arrivee= new Position(row,col);
	        List<Position> chemin = patricksParabox.trouverChemin(getMatrice(), niveau.getPositionActuelle(), arrivee);
	        
	        int delay = 50; // fréquence du timer en millisecondes
	         // indice de la case en cours de traitement
	        javax.swing.Timer timer = new javax.swing.Timer(delay, new ActionListener() {
	        	int index = 0;
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                if (index < chemin.size()) {
	                    Position pos = chemin.get(index);
	                    niveau.deplacerUtilisateur(pos);
	                    index++;
	                    repaint();
	                } else {
	                     // Arrêter le timer une fois que la boîte a atteint sa destination
	                    ((javax.swing.Timer) e.getSource()).stop();
	                }
	            }
	        });
	        timer.start(); // Démarrer le timer
	    }
	});
	}
	public void addKeyListenerToPanel() {
	    setFocusable(true);
	    requestFocusInWindow();
	    addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyPressed(KeyEvent e) {
	            int keyCode = e.getKeyCode(); 
	            if (keyCode == KeyEvent.VK_LEFT) {
	                // mouvement vers la gauche
	            	niveau.deplacerLEFT();
	            	 
	            } else if (keyCode == KeyEvent.VK_RIGHT) {
	                // mouvement vers la droite
	            	niveau.deplacerRIGHT();
	             	 
	            	
	            } else if (keyCode == KeyEvent.VK_UP) {
	                // mouvement vers le haut
	            	niveau.deplacerUP();
	            	 
	            	
	            } else if (keyCode == KeyEvent.VK_DOWN) {
	                // mouvement vers le bas
	            	niveau.deplacerDOWN();
	            	 
	            }
	            else if (e.isControlDown() && keyCode == KeyEvent.VK_Z) {
	            	niveau.PositionAvant();
	            }
	            repaint();
	            
	        } 
	    });}
 
	public Case[][] getMatrice() {
		return matrice;
	}

	public void setMatrice(Case[][] board) {
		this.matrice=board;
		
	}
   
	    
	}

 
	
	
