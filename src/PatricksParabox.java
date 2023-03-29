/*public class PatricksParabox {

}*/
import java.util.ArrayList;

import java.util.LinkedList;
import java.util.List;
import java.util.Collections;
import java.util.Queue;
import java.util.Scanner;
public class PatricksParabox {

	
	
	//j'avais utilisé une pile avant mais j'avais l'erreur java heap space , j'ai remplacé la pile par une queue et ça a fonctionné
	/*public List<Position> trouverChemin(char[][] labyrinthe,int xDepart,int yDepart,int xArrivee,int yArrivee){
		  Stack<Position> pile=new Stack<>();
		  Position depart=new Position(xDepart, yDepart);
		  depart.setVisitee(true);
		  pile.push(depart);
		  
		  while(!pile.isEmpty()) {
			  Position courante=pile.pop();
			  if(courante.getRow()==xArrivee && courante.getCol() == yArrivee) {
				  return construireChemin(courante);
			  }
			  for(Position voisine: getVoisines(courante, labyrinthe)) {
				  voisine.setVisitee(true);
				  voisine.setParent(courante);
				  pile.push(voisine);
			  }
		  }
		  return null;
		  }*/
		  /*public List<Position> trouverChemin(char[][] labyrinthe,int xDepart,int yDepart,int xArrivee,int yArrivee){
    Queue<Position> queue=new LinkedList<>();
    Position depart=new Position(xDepart, yDepart);
    depart.setVisitee(true);
    queue.add(depart);
    
    while(!queue.isEmpty()) {
        Position courante=queue.poll();
        if(courante.getRow()==xArrivee && courante.getCol() == yArrivee) {
            return construireChemin(courante);
        }
        for(Position voisine: getVoisines(courante, labyrinthe)) {
            voisine.setVisitee(true);
            voisine.setParent(courante);
            queue.add(voisine);
        }
    }
    return null;
}*/
	
	/*public static void afficherContenuFile(Queue<Position> file) {
	    System.out.println("Contenu de la file :");
	    for (Position element : file) {
	    	element.printPosition();
	    }
	}*/
	
	//méthode pour demander à l'utilisateur de saisir les coordonnées de la case d'arrivée
	
	public Position arrivee(Case[][] labyrinthe) {
	    try (Scanner scanner = new Scanner(System.in)) {
			int ligne, colonne;
			
			do {
			    System.out.print("Veuillez saisir le numéro de la ligne d'arrivée : ");
			    ligne = scanner.nextInt();
			    
			    System.out.print("Veuillez saisir le numéro de la colonne d'arrivée: ");
			    colonne = scanner.nextInt();
			    
			    if (ligne < 0 || ligne >= labyrinthe.length || colonne < 0 || colonne >= labyrinthe[0].length) {
			        System.out.println("Position invalide. La ligne et la colonne doivent être dans les limites de la matrice.");
			    }
			} while (ligne < 0 || ligne >= labyrinthe.length || colonne < 0 || colonne >= labyrinthe[0].length);
			
			return new Position(ligne, colonne);
		}
	}

public List<Position> trouverChemin(Case[][] labyrinthe,Position Depart,Position Arrivee) {
	
	//si l'arrivée est un mur on retourne une liste vide 
	if(labyrinthe[Arrivee.getRow()][Arrivee.getCol()].equals(new Case(Cell.Wall))) {
		System.out.println("vous ne voulez quand meme pas entrer dans un mur !!!!! ");
		return Collections.emptyList();
	}
	
    Queue<Position> queue=new LinkedList<>();
    Position depart=new Position(Depart.getRow(), Depart.getCol());
    depart.setVisitee(true);
    queue.add(depart);
    
    int maxNoeuds = (labyrinthe.length * labyrinthe[0].length)*10000; // nombre maximal de nœuds à visiter*10000
    int visiteCount = 0; // nombre de nœuds visités
    //on ajoute une condition dans la boucle while pour arreter la recherche au cas ou y a pas de chemin
    while(!queue.isEmpty() && visiteCount < maxNoeuds) {
        Position courante=queue.poll();
        visiteCount++;
        if(courante.getRow()==Arrivee.getRow() && courante.getCol() == Arrivee.getCol()) {
            return construireChemin(courante);
        }else {
        for(Position voisine: getVoisines(courante, labyrinthe)) {
        	
        	if(!voisine.estVisitee()) {
            voisine.setVisitee(true);
            voisine.setParent(courante);
            queue.add(voisine);
        	}
        	}
        }
    }
    
    //System.out.println("aucun chemin trouvé");
    return Collections.emptyList();
}



	  public List<Position> getVoisines(Position p,Case[][] labyrinthe){
		  List<Position> voisines=new ArrayList<>();
		  int y=p.getRow();
		  int x=p.getCol();
		  //Case au-dessus
		  

		  
		  Case c=new Case(Cell.Empty);
		  Case t=new Case(Cell.Target);
		  Case w=new Case(Cell.World);
		  if(y>0 && (labyrinthe[y-1][x].equals(c) || labyrinthe[y-1][x].equals(t) || labyrinthe[y-1][x].equals(w))) {
			  voisines.add(new Position(y-1,x));
		  }
		  //case en-dessous
		  if(y< labyrinthe.length -1 && (labyrinthe[y+1][x].equals(c) || labyrinthe[y+1][x].equals(t) || labyrinthe[y+1][x].equals(w))  ) {
			  voisines.add(new Position(y+1,x));
		  }
		  
		   // Case à gauche
		   if (x > 0 && (labyrinthe[y][x - 1].equals(c) || labyrinthe[y][x - 1].equals(t) ||labyrinthe[y][x - 1].equals(w)  )) {
		        voisines.add(new Position(y, x-1));
		   }
		    // Case à droite
		   if (x < labyrinthe[0].length - 1 && (labyrinthe[y][x + 1].equals(c) || labyrinthe[y][x + 1].equals(t) || labyrinthe[y][x + 1].equals(w))) {
		        voisines.add(new Position(y, x+1));
		    }
		   if(!voisines.isEmpty()) {
			   return voisines;
		   }else {
			   System.out.println("OUPSSSS !! mes voisines sont toutes des murs , j'ai pas de voisine :(");
			   return Collections.emptyList();
		   }
		   
	  }
	  
	  public List<Position> construireChemin(Position arrivee) {
		    List<Position> chemin = new ArrayList<>();
		    Position courante = arrivee;
		    while (courante != null) {
		        chemin.add(courante);
		        courante = courante.getParent();
		    }
		    Collections.reverse(chemin);
		    if(!chemin.isEmpty()) {
		    	return chemin;
		    }else {
		    	return Collections.emptyList();
		    }
		}
	  
	  //affichage du chemin
	  public void affichage(List<Position> chemin) {
		  if(!chemin.isEmpty()) {
		    	// convertir List<Position> en ArrayList<Position>
		    	ArrayList<Position> arrayListChemin = new ArrayList<>(chemin);
		    
		    	// afficher le chemin
		    	for (Position position : arrayListChemin) {
		    		//System.out.println("(" + position.ligne + ", " + position.colonne + ")");
		    		position.printPosition();
		    	}
		    }else {
		    	System.out.println("aucun chemin trouvéee");
		    }
	  }
	  
	  /*public void deplacerUtilisateur(List<Position> positions) {
		    for (int i = 0; i < positions.size() - 1; i++) {
		        Position currentPos = positions.get(i);
		        Position nextPos = positions.get(i+1);
		        
		        if (nextPos.getRow() < currentPos.getRow()) {
		            deplacerUp();
		        } else if (nextPos.getRow() > currentPos.getRow()) {
		            deplacerDown();
		        } else if (nextPos.getCol() < currentPos.getCol()) {
		            deplacerLeft();
		        } else if (nextPos.getCol() > currentPos.getCol()) {
		            deplacerRight();
		        }
		    }
		}*/
}

