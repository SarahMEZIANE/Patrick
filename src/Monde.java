import java.util.ArrayList;

public class Monde  {
	private char nom;
	private ArrayList<Position> listeBoites=new ArrayList<Position>();
	private ArrayList<Position> listeCibles=new ArrayList<Position>();
	
	public ArrayList<Position> getListeCibles() {
		return listeCibles;
	}

	public void setListeCibles(ArrayList<Position> listeCibles) {
		this.listeCibles = listeCibles;
	}

	public Monde(char nom, Case[][] matrice, int taille, Position dernPos) {
		super();
		this.nom = nom;
		this.matrice = matrice;
		this.taille = taille;
		this.dernPos = dernPos;
	}
	
	public Monde(char nom) {
		super();
		this.nom = nom;
	}

	public void afficherMonde() {
		//System.out.println("inside afficherMonde");
		for (int i=0;i<taille;i++) {
			for (int j=0;j<taille;j++) {
				System.out.print( matrice[i][j].displayCase());
			}
			System.out.println();
		}
	}
	public char getNom() {
		return nom;
	}
	public void setNom(char nom) {
		this.nom = nom;
	}
	private Case[][] matrice;
	private int taille;
	Position dernPos;
	public Position getDernPos() {
		return dernPos;
	}
	public void setDernPos(Position dernPos) {
		this.dernPos = dernPos;
	}
	public Monde(Case[][] matrice, int taille, boolean recursif, Position dernPos) {
		super();
		this.matrice = matrice;
		this.taille = taille;
		this.dernPos = dernPos;
	}
	public Case[][] getMatrice() {
		return matrice;
	}
	public void setMatrice(Case[][] matrice) {
		this.matrice = matrice;
	}
	public int getTaille() {
		return taille;
	}
	public void setTaille(int taille) {
		this.taille = taille;
	}
	public Monde(Case[][] matrice, int taille, boolean recursif) {
		super();
		this.matrice = matrice;
		this.taille = taille;
	}
	public Monde() {
		this.matrice = new Case[0][0];
		this.taille = 0;
	}
	 //une methode qui construit la matrice de cases à partir d'une matrice de char 
    Case[][] const_brd ( char[][] data,int n){
    	taille=n;
    	Case[][] monBoard= new Case[n][n];
    	for (int i=0;i<n;i++) {
    		for(int j=0;j<n;j++) {
    			monBoard[i][j]=cas(data[i][j],i,j);
    		}
    	}
    	return monBoard;
    }
    
    public Case cas(char c , int row,int col) {
		Case cellule = new Case();
		 switch(c) {
	  		case ' ':cellule.setContent(Cell.Empty);break;
	  		case '#':cellule.setContent(Cell.Wall);break;
	  		case '@':cellule.setContent(Cell.Target);
	  		listeCibles.add(new Position(row,col));
	  		break;
	  		case 'B':cellule.setContent(Cell.Box);
	  		listeBoites.add(new Position(row,col));
	  		break;
	  		case 'b':cellule.setContent(Cell.BoxOnTarget);break;
	  		case 'A':cellule.setContent(Cell.Me);break;
	  		case 'a':cellule.setContent(Cell.MeOnTarget);break;
	  		default :cellule.setContent(Cell.Empty);break;
 }
		 return cellule;
	}

	public ArrayList<Position> getListeBoites() {
		return listeBoites;
	}

	public void setListeBoites(ArrayList<Position> listeBoites) {
		this.listeBoites = listeBoites;
	}
    
}
