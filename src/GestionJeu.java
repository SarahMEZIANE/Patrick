import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GestionJeu {
	private Niveau niveau;
	private int numNiveau;
	
	
	public Niveau getNiveau() {
		return niveau;
	}

	public void setNiveau(Niveau niveau) {
		this.niveau = niveau;
	}

	public int getNumNiveau() {
		return numNiveau;
	}

	public void setNumNiveau(int numNiveau) {
		this.numNiveau = numNiveau;
	}

	
	
	public void petitePartie() throws IOException {
		System.out.println("Bienvenue dans le jeu :\nVous etes au niveau 1"
				+ "\nVous etes A\n");
		//niveau.chargementNiveau("niveau1.txt");
		//niveau.displayBoard();
		String c="O";
		
		while(!c.equals("Q")) {
			niveau.displayBoard();
			System.out.println("enter the key (UP,DOWN,RIGHT,LEFT) :");
			// Enter data using BufferReader
	        BufferedReader reader = new BufferedReader(
	            new InputStreamReader(System.in));
	 
	        // Reading data using readLine
	        String choice = reader.readLine();
	        switch(choice) {
	        case  "UP" : niveau.deplacerUP();break;
	        case  "DOWN" : niveau.deplacerDOWN();break;
	   //     case  "RIGHT" : niveau.deplacerRIGHT();break;
	        case  "LEFT" : niveau.deplacerLEFT();break;
	        case "0":return;
	        default : break;
	        }
	        niveau.displayBoard();
	      /*  System.out.println("\nEntrer Q si vous voulez quitter");
	         reader = new BufferedReader(
		            new InputStreamReader(System.in));
		 
		        // Reading data using readLine
		         c = reader.readLine();*/
		}
		System.out.println("\nAu revoir merci!");
		
		
		
		
		
		
	}
	
	

}
