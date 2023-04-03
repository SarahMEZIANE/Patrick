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
		    frame.setResizable(false);
		    frame.setSize(700,700);
		    
		    Partie partie= new Partie("niveauofficiel.txt");
		    partie.afficher_les_niveaux();
		    System.out.println("\nAAAAAAAA"); 

		    JPanel panel = new JPanel(new BorderLayout());
		    panel.setBackground(Color.black);
		    
		 // Créer une nouvelle JPanel pour ajouter le label et le bouton
		    JPanel headerPanel = new JPanel(new BorderLayout());
		    headerPanel.setBackground(Color.black);
		    headerPanel.setOpaque(false);
		    
		    ImageIcon backgroundImage = new ImageIcon("images/back.jpg");
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
		    JButton PlayButton= new JButton("PLAY");
		    PlayButton.setPreferredSize(new Dimension(150, 50));
		    PlayButton.setBackground(Color.GREEN);
		    headerPanel.add(PlayButton, BorderLayout.CENTER);

		  // Ajouter la nouvelle JPanel à la région NORTH de JPanel panel
		    panel.add(headerPanel, BorderLayout.NORTH);

		    // Ajouter JLabel backgroundLabel contenant l'image au JPanel panel
		    panel.add(backgroundLabel, BorderLayout.CENTER);
		    
		    frame.add(panel);
		    
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

		    PlayButton.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		            // Supprimer le contenu existant
		            frame.getContentPane().removeAll(); 
		            frame.revalidate(); 
		            frame.repaint();
		            
		            // Créer le JPanel parent
		            JPanel mainPanel = new JPanel(new BorderLayout());
                    int i=1;
		            // Ajouter le premier JPanel
		            MatriceCase matricePanel = new MatriceCase(i);
		            matricePanel.setPreferredSize(new Dimension(300,200));
		            matricePanel.addKeyListenerToPanel();
		            matricePanel.addMousseListenerToPanel();
		            mainPanel.add(matricePanel, BorderLayout.CENTER);
		            System.out.println(matricePanel.niveau.getwon());
		            
		            if(matricePanel.niveau.getwon() && i<10) {
		            	i++;
		                mainPanel.remove(matricePanel); // 
		                mainPanel.add(new MatriceCase(i));
		                matricePanel.revalidate(); // force le JPanel à se redessiner avec le nouveau contenu
		                matricePanel.repaint();
	
		            }
		            
		            // Ajouter le deuxième JPanel 
		            JPanel btnPanel = new JPanel();
		            btnPanel.setPreferredSize(new Dimension(100, 50));
		            mainPanel.add(btnPanel, BorderLayout.SOUTH);
		            btnPanel.setBackground(Color.black);
		            
		            JButton btnReset= new JButton("Reset");
		            btnPanel.add(btnReset);
		            btnReset.addActionListener(new ActionListener() {
		                @Override
		                public void actionPerformed(ActionEvent e) {
		                	
		                    matricePanel.niveau.ReinitialiserNiveau();
		                    matricePanel.repaint(); // Ajout de repaint() pour redessiner le JPanel matricePanel après avoir modifié la matrice
		                    matricePanel.niveau.getPositionActuelle().printPosition();
		                    matricePanel.requestFocusInWindow();
		                    
		                    
		                   
		                } 
		            });
		            
		            
		            JButton btnHelp = new JButton("Help");
		            btnPanel.add(btnHelp);
		            btnHelp.addActionListener(new ActionListener() {
		                @Override
		                public void actionPerformed(ActionEvent e) {
		                    JOptionPane.showMessageDialog(null, "si vous voulez la résolution automatique du chemin ! vous n'avez qu'à cliquer sur la case ou vous "
		                        + "voullez aller !");
		                    matricePanel.requestFocusInWindow();
		                } 
		                

		            });
 
		            // Ajouter le JPanel parent à la JFrame
		            frame.getContentPane().add(mainPanel);
		            matricePanel.requestFocusInWindow(); // ajout de focus sur matricePanel
		            frame.revalidate();
		        } 
		    });
		}
}
	

