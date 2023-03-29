import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Main2 {
		
		public static void main(String[] args) {
			
		    JFrame frame = new JFrame("Patrick's Parabox Game");
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		    JPanel panel = new JPanel(new BorderLayout());
		    
		 // Créer une nouvelle JPanel pour ajouter le label et le bouton
		    JPanel headerPanel = new JPanel(new BorderLayout());
		    headerPanel.setOpaque(false);
		    
		    ImageIcon backgroundImage = new ImageIcon("images/back.gif");
		    JLabel backgroundLabel = new JLabel(backgroundImage);
		    backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
		    panel.add(backgroundLabel);
		    panel.setOpaque(false);
		    
		  // Ajouter le label "Welcome to Patrick's Parabox Game!" au centre de la nouvelle JPanel
		    JLabel label = new JLabel("Welcome to Patrick's Parabox Game!");
		    label.setFont(new Font("Arial", Font.BOLD, 21));
		    label.setHorizontalAlignment(JLabel.CENTER);
		    label.setForeground(Color.BLACK);
		    headerPanel.add(label, BorderLayout.WEST);
		    
		 // Ajouter le bouton "PLAY" à droite de la nouvelle JPanel
		    JButton bouton = new JButton("PLAY");
		    bouton.setPreferredSize(new Dimension(150, 50));
		    bouton.setBackground(Color.GREEN);
		    headerPanel.add(bouton, BorderLayout.CENTER);

		  // Ajouter la nouvelle JPanel à la région NORTH de JPanel panel
		    panel.add(headerPanel, BorderLayout.NORTH);

		    // Ajouter JLabel backgroundLabel contenant l'image au JPanel panel
		    panel.add(backgroundLabel, BorderLayout.CENTER);
		    
		    frame.add(panel);
		    frame.setResizable(false);
		    frame.setSize(700,700);
		    
		    JButton exitButton = new JButton("EXIT");
		    exitButton.setPreferredSize(new Dimension(150, 50)); // Définir une taille de 100 pixels de largeur et 50 pixels de hauteur
		    exitButton.setBackground(Color.RED);
		    headerPanel.add(exitButton, BorderLayout.EAST);

		    exitButton.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		            System.exit(0); // Quitter l'application
		        }
		    }); 

		    frame.setVisible(true);

		    bouton.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		        	int i=0;
		        	//while(i<5) {
		            Partie partie= new Partie("niveauofficiel.txt");
		            partie.afficher_les_niveaux();
		            System.out.println("\nAAAAAAAA");
		            Niveau niveau=Partie.readPuzzles("niveauofficiel.txt").get(i);
		            Case[][] matrice = niveau.getBoard();
		            
		            Image wall = new ImageIcon("images/mur.png").getImage();
		            Image me = new ImageIcon("images/personnage.jpg").getImage();
		            Image box = new ImageIcon("images/box1.png").getImage();
		            Image empty = new ImageIcon("images/normal.png").getImage();
		            Image target = new ImageIcon("images/cible.png").getImage();
		            Image meOnTarget = new ImageIcon("images/personnage.jpg").getImage();
		            Image boxOnTarget = new ImageIcon("images/box1.png").getImage();
		            
		           
		            frame.getContentPane().removeAll(); // Supprimer le contenu existant
		            //JPanel jeu= new JPanel();
		        	JButton btn = new JButton("Help");
		        	//jeu.add(btn);
		        	//setLayout(new BorderLayout());
		        	

		            MatriceCase matricePanel = new MatriceCase(matrice, wall, empty,me,box,target,meOnTarget,boxOnTarget,niveau);
		            matricePanel.add(btn);
		            btn.addActionListener(new ActionListener() {
		        		   @Override
		        		   public void actionPerformed(ActionEvent e) {
		        		       JOptionPane.showMessageDialog(null, "si vous voulez la résolution automatique du chemin ! vous n'avez qu'à cliquer sur la case ou vous "
		        		       		+ "voullez aller !");
		        		   }
		        		});
		            //add(jeu,BorderLayout.SOUTH);
		            //add(MatricePanel,BorderLayout.CENTER);
		            frame.add(matricePanel);
		            matricePanel.addKeyListenerToPanel();
		   
		            //frame.add(jeu);
		            frame.revalidate(); // Actualiser la JFrame
		            frame.repaint();
		            
		           /* if(Niveau.compare(niveau.getBoxes(), niveau.getCibles())) {
		            	i++;*/
		            
		            
		        }
		        }); 
		}
	}

