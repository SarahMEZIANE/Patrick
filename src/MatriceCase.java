

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
	
	private Niveau niveau;
    
	private static final long serialVersionUID = 1L;
	private Case[][] matrice;
    private final Image wall, empty,me,box,target,meOnTarget,boxOnTarget;
    
  

    public MatriceCase(Case[][] matrice, Image wall, Image empty, Image me, Image box, Image target, Image meOnTarget,
			Image boxOnTarget,Niveau niveau) {
		super();
		this.matrice = matrice;
		this.wall = wall;
		this.empty = empty;
		this.me = me;
		this.box = box;
		this.target = target;
		this.meOnTarget = meOnTarget;
		this.boxOnTarget = boxOnTarget;
		this.niveau=niveau;
		//cliquer sur une case et se déplacer automatiquement
		 /*addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                int cellWidth = getWidth() / matrice[0].length;
	                int cellHeight = getHeight() / matrice.length;
	                int row = e.getPoint().y / cellHeight;
	                int col = e.getPoint().x / cellWidth;
	                PatricksParabox patricksParabox=new PatricksParabox();
	            	//on peut varier les position en modifiant 
	            	Position arrivee= new Position(row,col);
	            	List<Position> chemin = patricksParabox.trouverChemin(matrice, niveau.getPositionActuelle(), arrivee);
	            	
	            	niveau.deplacerUtilisateur(chemin);
	            	repaint();
	                System.out.println("Clicked on row " + row + " and column " + col);
	            }
	        });*/
		addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int cellWidth = getWidth() / matrice[0].length;
		        int cellHeight = getHeight() / matrice.length;
		        int row = e.getPoint().y / cellHeight;
		        int col = e.getPoint().x / cellWidth;
		        PatricksParabox patricksParabox=new PatricksParabox();
		        //on peut varier les position en modifiant 
		        Position arrivee= new Position(row,col);
		        List<Position> chemin = patricksParabox.trouverChemin(matrice, niveau.getPositionActuelle(), arrivee);
		        
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


	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int cellWidth = getWidth() / matrice[0].length;
        int cellHeight = getHeight() / matrice.length;
        for (int i = 0; i < matrice.length; i++) { 
            for (int j = 0; j < matrice[0].length; j++) {
            	 if (matrice[i][j].getContent().equals(Cell.Empty)) {
                    g.drawImage(empty, j * cellWidth, i * cellHeight, cellWidth, cellHeight, null);
                } else if (matrice[i][j].getContent().equals(Cell.Box)) {
                    g.drawImage(box, j * cellWidth, i * cellHeight, cellWidth, cellHeight, null);
                } else if(matrice[i][j].getContent().equals(Cell.Me)) {
                	 g.drawImage(me, j * cellWidth, i * cellHeight, cellWidth, cellHeight, null);
                }else if(matrice[i][j].getContent().equals(Cell.Target)) {
               	 g.drawImage(target, j * cellWidth, i * cellHeight, cellWidth, cellHeight, null);
               }else if(matrice[i][j].getContent().equals(Cell.Wall)) {
              	 g.drawImage(wall, j * cellWidth, i * cellHeight, cellWidth, cellHeight, null);
               }else if(matrice[i][j].getContent().equals(Cell.MeOnTarget)) {
                 	 g.drawImage(meOnTarget, j * cellWidth, i * cellHeight, cellWidth, cellHeight, null);}
               else if(matrice[i][j].getContent().equals(Cell.BoxOnTarget)) {
               	 g.drawImage(boxOnTarget, j * cellWidth, i * cellHeight, cellWidth, cellHeight, null);}
               else{
                	 g.setColor(Color.white);
                     g.fillRect(j * cellWidth, i * cellHeight, cellWidth, cellHeight);
                }
            }}
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
	            /*else if(keyCode==KeyEvent.VK_Z) {
	            	PatricksParabox patricksParabox=new PatricksParabox();
	            	//on peut varier les position en modifiant 
	            	Position arrivee= new Position(6,7);
	            	List<Position> chemin = patricksParabox.trouverChemin(matrice, niveau.getPositionActuelle(), arrivee);
	            	
	            	niveau.deplacerUtilisateur(chemin);
	            }*/
	         // Vérifier si le joueur a atteint la case M
	           /* int playerRow = niveau.getPositionActuelle().getRow();
	            int playerCol = niveau.getPositionActuelle().getCol();
	            
	            Position p=new Position(playerRow,playerCol);*/
	            /*if (matrice[playerRow][playerCol].equals(new Case(Cell.World))
	     ) 		{
	                // Changer la matrice actuelle pour la deuxième matrice
	                setMatrice(niveau.getLesMondes().getKey(p));
	            }*/
	            repaint();
	            
	        }
	    });}
	    
	    
	    
	}

 
	
	