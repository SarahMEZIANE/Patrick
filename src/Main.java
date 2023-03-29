import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
/*	public static void main(String[] args) throws IOException, InterruptedException {//regler les deplacements ++ un jeu qui marchera sur la console seulement à 100% !
		// TODO Auto-generated method stub
		Niveau n=new Niveau(2,2);
		
		Position p1=new Position(0,0);
		Position p2=new Position(1,0);
		Position p3=new Position(0,1);
		Position p4=new Position(1,1);
		
		ArrayList<Position> a=new ArrayList<Position>();
		a.add(p1);
		a.add(p2);
		a.add(p3);
		a.add(p4);
		
		Position pp1=new Position(0,0);
		Position pp2=new Position(1,0);
		Position pp3=new Position(0,1);
		Position pp4=new Position(1,1);
		ArrayList<Position> ap=new ArrayList<Position>();
		ap.add(pp1);
		ap.add(pp2);
		ap.add(pp3);
		ap.add(pp4);
		
		n.setBoxes(a);
		n.setCibles(ap);
		n.verifierThread.start();
		System.exit(0);
		//n.verifierThread.sleep(3000);
		//n.verifierThread.interrupt();
	}*/
	
public static void main(String[] args) throws IOException {//regler les deplacements ++ un jeu qui marchera sur la console seulement à 100% !
		// TODO Auto-generated method stub  
	Partie partie= new Partie("niveau2.txt");
	partie.afficher_les_niveaux();
	System.out.println("\nAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
	
	
	
	//cas d'un personnage qui sort d'un monde
	
	
	
	Niveau niveau=Partie.readPuzzles("niveau2.txt").get(0);
	niveau.afficherNiveau();
	System.out.println("boxes:");
	niveau.afficherBoxes();
	
//	niveau.deplacerUP();
	//niveau.afficherNiveau();
	
	
	niveau.deplacerLEFT();
	niveau.afficherNiveau();

	System.out.println("boxes:");
	niveau.afficherBoxes();
	System.out.println("_________________________________________________________________________________________________________________________________");
	niveau.deplacerLEFT();
niveau.afficherNiveau();
	System.out.println("boxes:");
	niveau.afficherBoxes();
	System.out.println("_________________________________________________________________________________________________________________________________");

	
	niveau.deplacerLEFT();
	niveau.afficherNiveau();

	System.out.println("boxes:");
	niveau.afficherBoxes();
	System.out.println("_________________________________________________________________________________________________________________________________");
	niveau.deplacerLEFT();
niveau.afficherNiveau();
	System.out.println("boxes:");
	niveau.afficherBoxes();
	System.out.println("_________________________________________________________________________________________________________________________________");
	niveau.deplacerLEFT();
	niveau.afficherNiveau();

	System.out.println("boxes:");
	niveau.afficherBoxes();
	System.out.println("_________________________________________________________________________________________________________________________________");
	niveau.deplacerLEFT();
niveau.afficherNiveau();
	System.out.println("boxes:");
	niveau.afficherBoxes();
	System.out.println("_________________________________________________________________________________________________________________________________");
	System.out.println("_________________________________________________________________________________________________________________________________");
	niveau.deplacerLEFT();
	niveau.afficherNiveau();

	System.out.println("boxes:");
	niveau.afficherBoxes();
	System.out.println("_________________________________________________________________________________________________________________________________");
	niveau.deplacerLEFT();
niveau.afficherNiveau();
	System.out.println("boxes:");
	niveau.afficherBoxes();
	System.out.println("_________________________________________________________________________________________________________________________________");

	
	
	
	/*
	niveau.deplacerUP();

	System.out.println("boxes:");
	niveau.afficherBoxes();
	niveau.afficherNiveau();
	System.out.println("_________________________________________________________________________________________________________________________________");

	niveau.deplacerUP();
	System.out.println("boxes:");
	niveau.afficherBoxes();
	niveau.afficherNiveau();
	System.out.println("_________________________________________________________________________________________________________________________________");

	niveau.deplacerUP();
	System.out.println("boxes:");
	niveau.afficherBoxes();
	niveau.afficherNiveau();
	System.out.println("_________________________________________________________________________________________________________________________________");

	niveau.deplacerUP();
	System.out.println("boxes:");
	niveau.afficherBoxes();
	niveau.afficherNiveau();
	System.out.println("_________________________________________________________________________________________________________________________________");

	niveau.deplacerUP();
	System.out.println("boxes:");
	niveau.afficherBoxes();
	niveau.afficherNiveau();
	System.out.println("_________________________________________________________________________________________________________________________________");

	niveau.deplacerUP();
	System.out.println("boxes:");
	niveau.afficherBoxes();
	niveau.afficherNiveau();
	niveau.deplacerUP();
	System.out.println("_________________________________________________________________________________________________________________________________");

	niveau.deplacerUP();
	System.out.println("boxes:");
	niveau.afficherBoxes();
	niveau.afficherNiveau();
	niveau.deplacerUP();*/
		
	
	//cas sortir une boite d'un monde <<<< modifier la liste de boites
/*	System.out.println("boxes:");
	niveau.afficherBoxes();
	niveau.afficherNiveau();
	System.out.println("boxes:");
	niveau.afficherBoxes();
	HashMap<Position,Monde> map=niveau.getLesMondes();
	Set<Position> keySet = map.keySet();
	Position firstKey=keySet.iterator().next();
	Monde m=map.get(firstKey);
	System.out.println("le monnnnnnnnnnnnnnde");
	m.afficherMonde();*/
	//et si on fait sortir une boite d'un monde?
	//cas on sort d'un monde qui a en dessus une boite ( on la deplace )
Case[][] board=partie.getNiveauDunePartie().get(0).getBoard();
	
	Position depart=partie.getNiveauDunePartie().get(0).getPositionActuelle();
	
	//Position depart=new Position(7,5);
	
	depart.printPosition();
    

    // instancier PatricksParabox
    PatricksParabox patricksParabox = new PatricksParabox();
    Position arrivee= patricksParabox.arrivee(board);
    List<Position> chemin = patricksParabox.trouverChemin(board, depart, arrivee);
    patricksParabox.affichage(chemin);
    
   

    
}
}
	
		/*Niveau n=new Niveau(10,10);
		n.initWorld();	
		n.displayBoard();
		System.out.println("\n***********");
		int ii=n.getBoxes().size(),j=n.getCibles().size();
		System.out.println("\n***********Boxes i="+ii+"j="+j);
		for (int i=0;i<n.getBoxes().size();i++) {
			System.out.print("-");n.getBoxes().get(i).printPosition();
			
		}
		
		
		
		System.out.println("\n***********Cibles");
		n.getCibles().get(0).printPosition();
		GestionJeu jp=new GestionJeu();
		jp.setNiveau(n);
		jp.petitePartie();
		System.out.println("\n***********Boxes i="+ii+"j="+j);
		for (int i=0;i<n.getBoxes().size();i++) {
			System.out.print("-");n.getBoxes().get(i).printPosition();
			
		}
		*/
		//n.initWorld();
	  //  j.petitePartie();
		/*for (int ii=0;ii<1;i++) {
			n.getCibles().get(ii).printPosition();
		}*/
		
		/*Position p1=new Position(0,0);
		Position p2=new Position(1,0);
		Position p3=new Position(0,1);
		Position p4=new Position(1,1);
		
		ArrayList<Position> a=new ArrayList<Position>();
		a.add(p1);
		a.add(p2);
		a.add(p3);
		a.add(p4);
		
		Position pp1=new Position(0,0);
		Position pp2=new Position(1,0);
		Position pp3=new Position(0,1);
		Position pp4=new Position(1,1);
		ArrayList<Position> ap=new ArrayList<Position>();
		ap.add(pp1);
		ap.add(pp2);
		ap.add(pp3);
		ap.add(pp4);
		
		n.setBoxes(a);
		n.setCibles(ap);*/
		

		
	/*	n.deplacerRIGHT();
		n.displayBoard();
		
		n.deplacerRIGHT();
		n.displayBoard();

		n.deplacerDOWN();
		n.displayBoard();
		
		
		n.deplacerLEFT();
		n.displayBoard();
		
		n.deplacerDOWN();
		n.displayBoard();
		
		n.deplacerLEFT();
		n.displayBoard();
		
		n.deplacerUP();
		n.displayBoard();*/
		
		
		//n.deplacerLEFT();
		//n.displayBoard();
		
		/*GestionJeu j=new GestionJeu();
		j.setNiveau(n);*/
		
		//n.initWorld();
	  //  j.petitePartie();
	//	n.deplacerLEFT();
		
		//j.getNiveau().initWorld();
		//System.out.println(j.chargementNiveau("niveau1.txt"));
		
	/*	Case c=new Case();
		c.setContent(Cell.Box);
		System.out.println(c.displayCase());*/

	/*}

}*/
