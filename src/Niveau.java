
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


//FAUT MODIFIER LES MOUVEMENTS POUR MODIFIER LA LISTE DES BOXES, PAR CONTRE ON NE MODIFIE PAS LISTE DES CIBLES
public class Niveau  {
	//classe interne 
	/*public class CoupleCharMonde<T1,T2>{//pour le monde 
		private T1 String;
		private T2 Monde;
		public CoupleCharMonde(T1 string, T2 monde) {
			super();
			String = string;
			Monde = monde;
		}
		public T1 getString() {
			return String;
		}
		public void setString(T1 string) {
			String = string;
		}
		public T2 getMonde() {
			return Monde;
		}
		public void setMonde(T2 monde) {
			Monde = monde;
		}
		
		
		
	}*/
	//donc faut modifier init world pour initialiser la liste des mondes 
	
	public void afficherMondei() {
		
		for(Map.Entry<Position,Monde> entry : lesMondes.entrySet()) {
			System.out.println("position du monde: ");entry.getKey().printPosition();
			System.out.println("AFFICHAGE du monde: ");
			entry.getValue().afficherMonde();
			System.out.println("FIN DE L AFFICHAGE du monde. ");
			
		}
	}	
	
	public void afficherNiveau () {
		//System.out.println("DANS AFFICHER NIVEAU");
		
		for (int i=0;i<colNb;i++) {
			for (int j=0;j<colNb;j++) {
				System.out.print(board[i][j].displayCase());
			}
			System.out.println();
		}
	/*	System.out.println("les coordonnes des boites:");
		for (int i=0;i<boxes.size();i++) {
			System.out.println("("+boxes.get(i).getRow()+","+ boxes.get(i).getCol()+")");
		}
		System.out.println("les coordonnes des cibles:");
		System.out.println("size de lalloste des cibles :"+cibles.size());
		for (int i=0;i<cibles.size();i++) {
			System.out.println("("+cibles.get(i).getRow()+","+ cibles.get(i).getCol()+")");
		}*/
		System.out.println("les mondes: ");
		if(!lesMondes.isEmpty()) {
			System.out.println("les monds n'est pas vide ");
			afficherMondei();
		}else System.out.println("les mondes est vide");
	}
	
	private HashMap < Position , Monde > lesMondes = new HashMap < Position , Monde >() ;
	//private ArrayList<Monde> lesMondes;//A CHAQUE FOIS QU ON ENTRE DANS UN MONDE ON ADD A LA LISTE DES MONDES MAIS ON DOIT JAMAIS PERDRE LE MONDE ACTUELLE 
	//QUI EST BIEN SUR DANS LA VARIABLE BOARD
	
	public HashMap < Position , Monde > getLesMondes() {
		return lesMondes;
	}

	public void setLesMondes(HashMap < Position , Monde > lesMondes) {
		this.lesMondes = lesMondes;
	}

	Boolean next;
	String nom_partie;// IL SE MODIFIE A CHAQUE FOIS QU ON AVANCE DANS LE NIVEAU 
	
    public Niveau(String nom_partie, int r, char[][] data ) {
		super();
		this.nom_partie = nom_partie;
		this.rowNb = r;
		this.colNb = r;
		this.level = data;
		estMonde=false;
		//this.board=this.const_brd(data,r);
		
	}

	public Niveau(String nom_partie, int r, Case[][] board) {
		super();
		this.nom_partie = nom_partie;
		this.rowNb = r;
		this.colNb = r;
		this.board = board;
	}

	public String getNom_partie() {
		return nom_partie;
	}

	public void setNom_partie(String nom_partie) {
		this.nom_partie = nom_partie;
	}

	private int rowNb;
    private int colNb;
  //  public Thread verifierThread = new Thread();
    private char[][] level;
    //la liste des boîtes qui ont des cibles à placer sur 
    private ArrayList<Position> cibles=new ArrayList<Position>();//les coordonnes des cibles // ne se modifie pas 
	private ArrayList<Position> boxes=new ArrayList<Position>();//les coordonnees des boites // se modifie
    
    
    public int getRowNb() {
		return rowNb;
	}

	public void setRowNb(int rowNb) {
		this.rowNb = rowNb;
	}

	public int getColNb() {
		return colNb;
	}

	public void setColNb(int colNb) {
		this.colNb = colNb;
	}

	public ArrayList<Position> getCibles() {
		return cibles;
	}
	
	public void setCibles(ArrayList<Position> cibles) {
		this.cibles = cibles;
	}

	public ArrayList<Position> getBoxes() {
		return boxes;
	}

	public void setBoxes(ArrayList<Position> boxes) {
		this.boxes = boxes;
	}

	public Case[][] getBoard() {
		return board;
	}

	public void setBoard(Case[][] board) {
		this.board = board;
	}
	 
    public Niveau(int rowNb, int colNb, Thread verifierThread, char[][] level, ArrayList<Position> cibles,
			ArrayList<Position> boxes, Position positionActuelle, Case[][] board) {
		super();
		this.rowNb = rowNb;
		this.colNb = colNb;
		this.verifierThread = verifierThread;
		this.level = level;
		this.cibles = cibles;
		this.boxes = boxes;
		this.positionActuelle = positionActuelle;
		this.board = board;
	}


    
    /**
     * Constructeur de la classe Niveau
     * @param row, nombre totale de ligne
     * @param col, nombre totale de colonne
     */
    public Niveau(int row, int col){
        this.rowNb = row;
        this.colNb = col;
        this.board = new Case[rowNb][colNb];
        
        for(int i = 0 ; i < this.rowNb ; i++){
            for(int j = 0 ; j < this.colNb ; j++){
            	this.board[i][j] = new Case();
            }
        }
        //boxes = new ArrayList<Position>();
        //cibles = new ArrayList<Position>();
    }
	//pour verifier le succes on verifie l'egalite entre les deux listes
    //definir equals pour les deux listes 
   // public class MyThread extends Thread {//une classe interne 
    
    Runnable maFonction = () -> {
        // Code de la fonction

    	System.out.println("\ndebut thread:");
        // Code à exécuter dans le thread
    	//c'est comme le equals 
    	boolean bool=false;
    	while(true) {
    	if(cibles.size()==boxes.size()) { // le equals doit être modifier car on doit comparer mais on s'en fout de <<l'ordre des éléments>>
    		// en tt cas tu dois utiliser le contains pour chaque élément
    		//N'OUBLIE PAS CONTAINS POUR CHAQUE ELEMENT !!!!!!!!!!
    		bool=true;
    		//on vérifie l'égalité 
    		for (int i=0;i<cibles.size();i++) {
    			if(!cibles.get(i).equals(boxes.get(i))) {
    				bool=false;
    			}
    		}
    	}
    	if (bool==true) {
    		System.out.println("\negale");
    		//on a l'égalite entre les deux listes 
    		//on incrémente la partie ou on charge un nouveau niveau
    		//...
    		next=true;//et quand on incremnet on la remets a false 
    		
    	}
    	/*try {
           Thread.sleep(10000); // Vérifie toutes les secondes
         
    		
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    	System.out.println("\nfin thread.");
    	//	Thread.currentThread().interrupt();
    	}
    	/*POUR L UTILISER : n.verifierThread.start(); DANS LE MAIN*/
    
   
    };

    Thread verifierThread = new Thread(maFonction);
   
    
  public char[][] getLevel() {
		return level;
	}

	public void setLevel(char[][] level) {
		this.level = level;
	}

    private Position positionActuelle = new Position();
    private Case[][] board ;//notre matrice
   // private ArrayList<Case> targets= new ArrayList<Case>();//la liste des buts
    
    
    public Position getPositionActuelle() {
		return positionActuelle;
	}

	public void setPositionActuelle(Position positionActuelle) {
		this.positionActuelle = positionActuelle;
	}
	public String chargementNiveau (String nomFich) {//prend en parametre le nom d un fichier
		//transforme le fichier en chaine de caracteres
		String res="";
		try  
		{  
			File file=new File(nomFich);
		//constructor of file class having file as argument   
		FileInputStream fis=new FileInputStream(file);     //opens a connection to an actual file  
		//System.out.println("file content: ");   
		int r=0;  
		while((r=fis.read())!=-1)  
		{  res+=(char)r;
		}  
		}  
		catch(Exception e)  
		{  
		e.printStackTrace();  
		}  
	//	niveau.setLevel(res);
	  // setLevel(res);
		//System.out.println("inside chrgmt:\n"+res);
		//displayBoard();
		return res;
	}
	// les methodes de deplacement 
	
	
	
	//MODIFIABLE POUR MODIFIER LES LISTES ET LES REMPLIR A CHAQUE INSTANT DE DEPLACEMENT 
	//TRAITER LE CAS DE PLUSIEURS BOITES AU MEME TEMPS
	
	
	
	// IF PERSONNAGE EST SUR UN MONDE ON CHANGE LA MATRICE ACTUELLE ET ON SAUVEGARDE LA PRECENCTE POUR Y REVENIR 
	
	
	//MAINTENNAT REGLER LES MOUVEMENTS, COMME POUSSER PLUS D'UNE BOITE A LA FOIS 
	private int numNiveauActuel;
	private boolean estMonde ;
	private Position positionMonde;
	private Case[][] boardSauv;
	void aff_mat ( Case[][] m ,int s) {
		System.out.println("*************************aff***************************");
		for(int i=0;i<s;i++) {
			for(int j=0;j<s;j++)
				System.out.print(m[i][j].displayCase());
			System.out.println();
		}
		System.out.println("**************************fin aff*******************************");
}
	
	static Monde rechMonde( Position p,HashMap<Position,Monde> map) {
		for(Map.Entry<Position, Monde> entry : map.entrySet()) {
			if(entry.getKey().equals(p)) {
				return entry.getValue(); 
			}
		}
		return null;
	}
	
	void deplacerUP() { 
		if(positionActuelle.getRow()-2>0) {
			if(board[positionActuelle.getRow()-1][positionActuelle.getCol()].getContent().equals(Cell.Box)&&board[positionActuelle.getRow()-2][positionActuelle.getCol()].getContent().equals(Cell.Target)) {
				board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
				board[positionActuelle.getRow()-1][positionActuelle.getCol()].setContent(Cell.Me);
				board[positionActuelle.getRow()-2][positionActuelle.getCol()].setContent(Cell.BoxOnTarget);
				positionActuelle.setRow(positionActuelle.getRow()-1);
			}
		}
		
		if(board[positionActuelle.getRow()-1][positionActuelle.getCol()].getContent().equals(Cell.World) ) {
				
				if(positionActuelle.getRow()-2>=0) {
					
					if(board[positionActuelle.getRow()-2][positionActuelle.getCol()].getContent().equals(Cell.Empty)) {
						Position p=new Position(positionActuelle.getRow()-1,positionActuelle.getCol());
				Monde m=rechMonde(p,lesMondes);
				lesMondes.remove(p, m);
				p.setRow(p.getRow()-1);
				lesMondes.put(p, m);
				board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
				board[positionActuelle.getRow()-2][positionActuelle.getCol()].setContent(Cell.World);
				board[positionActuelle.getRow()-1][positionActuelle.getCol()].setContent(Cell.Me);
				positionActuelle.setRow(positionActuelle.getRow()-1);
					}
					
					
					
					
				}
				/*else { 
					board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
					board[positionActuelle.getRow()-2][positionActuelle.getCol()].setContent(Cell.World);
					board[positionActuelle.getRow()-1][positionActuelle.getCol()].setContent(Cell.Me);
					positionActuelle.setRow(positionActuelle.getRow()-1);
				}*/
				if(positionActuelle.getRow()-1==0) {//on entre le monde
					positionMonde=new Position(positionActuelle.getRow()-1,positionActuelle.getCol());
					Monde monde= lesMondes.get(new Position(positionActuelle.getRow()-1,positionActuelle.getCol()));
					Position p;
					if ( (p=trouverPorte(monde,0)) != null  ) {//on a trouvé une porte et on peut y accéder
						//on doit sauvegarder la position sur le board et on doit se déplacer sur le monde 
						board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
						estMonde=true;
						boardSauv=board;
						board=monde.getMatrice();
						colNb=rowNb=board.length;
						positionActuelle=p;
					    board[p.getRow()][p.getCol()].setContent(Cell.Me);
				}
				}
				else if (positionActuelle.getRow()-2==0 && board[positionActuelle.getRow()-2][positionActuelle.getCol()].getContent().equals(Cell.Wall) ) {
					positionMonde=new Position(positionActuelle.getRow()-1,positionActuelle.getCol());
					Monde monde= lesMondes.get(new Position(positionActuelle.getRow()-1,positionActuelle.getCol()));
					Position p;
					if ( (p=trouverPorte(monde,0)) != null  ) {//on a trouvé une porte et on peut y accéder
						//on doit sauvegarder la position sur le board et on doit se déplacer sur le monde 
						board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
						estMonde=true;
						boardSauv=board;
						board=monde.getMatrice();
						colNb=rowNb=board.length;
						positionActuelle=p;
					    board[p.getRow()][p.getCol()].setContent(Cell.Me);
					
				}}
				
				
				
				
				
		}
	/*	else	if(board[positionActuelle.getRow()-1][positionActuelle.getCol()].getContent().equals(Cell.World) &&(board[positionActuelle.getRow()-1][positionActuelle.getCol()].getContent().equals(Cell.Wall) || positionActuelle.getRow()-1==0) ) {
			///on recherche une porte si on peut entrer 
			//dans la bordure en dessous
			System.out.println(")))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))<<<");
			positionMonde=new Position(positionActuelle.getRow()-1,positionActuelle.getCol());
			Monde monde= lesMondes.get(new Position(positionActuelle.getRow()-1,positionActuelle.getCol()));
			Position p;
			if ( (p=trouverPorte(monde,0)) != null  ) {//on a trouvé une porte et on peut y accéder
				//on doit sauvegarder la position sur le board et on doit se déplacer sur le monde 
				board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
				estMonde=true;
				boardSauv=board;
				board=monde.getMatrice();
				colNb=rowNb=board.length;
				positionActuelle=p;
			    board[p.getRow()][p.getCol()].setContent(Cell.Me);
			}
		}*/
		
		
		
		else
		//this.afficherBoxes();
		//if (positionActuelle.getRow()==0 && !estMonde) return ; //on est à la premiere ligne
		if( positionActuelle.getRow()>0) {// c-a-d on est dans la matrice
		// permier cas : un vide en dessus
			if(board[positionActuelle.getRow()-1][positionActuelle.getCol()].getContent().equals(Cell.Empty)&& board[positionActuelle.getRow()][positionActuelle.getCol()].getContent().equals(Cell.Me)) {
				board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
				positionActuelle.setRow(positionActuelle.getRow()-1);
				board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Me);
			}
			else  {
				if (board[positionActuelle.getRow()-1][positionActuelle.getCol()].getContent().equals(Cell.Wall)) return ;//si c un mur on ne faot rien
				else {
					if (board[positionActuelle.getRow()-1][positionActuelle.getCol()].getContent().equals(Cell.Box) || board[positionActuelle.getRow()-1][positionActuelle.getCol()].getContent().equals(Cell.BoxOnTarget) ) {//Faut traiter le cas d'une liste de boîtes
						//vérifier s'il y en a des boîtes en haut et puis faire le déplacement à la fois
						//on utilisera une boucle qui cherche sk y en a des boîtes et sk on est arrivé au mur ou si on est sur la bordure
						// et si on pousse une boite qui se trouve sa cible ?
					 // System.out.println("hdgfhkdgfhqsgfkjf");
						//aff_mat(boardSauv,boardSauv.length);
						if( boardSauv==null) {if( positionActuelle.getRow()>0) {
							// permier cas : un vide en dessus
							if(board[positionActuelle.getRow()-1][positionActuelle.getCol()].getContent().equals(Cell.Empty)&& board[positionActuelle.getRow()][positionActuelle.getCol()].getContent().equals(Cell.Me)) {
								board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
								positionActuelle.setRow(positionActuelle.getRow()-1);
								board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Me);
							}
							else  {
								if (board[positionActuelle.getRow()-1][positionActuelle.getCol()].getContent().equals(Cell.Wall)) return ;
								else {
									if (board[positionActuelle.getRow()-1][positionActuelle.getCol()].getContent().equals(Cell.Box)) {//Faut traiter le cas d'une liste de boîtes
										//vérifier s'il y en a des boîtes en haut et puis faire le déplacement à la fois
										//on utilisera une boucle qui cherche sk y en a des boîtes et sk on est arrivé au mur ou si on est sur la bordure
										int ligne=positionActuelle.getRow()-1;
										ArrayList<Position> contBoites=new  ArrayList<Position>();
										while (ligne>0) {
											if(board[ligne][positionActuelle.getCol()].getContent().equals(Cell.Box)) {
												//on peut créer une nouvelle liste qui comporte ces boîtes et qu'on modifiera
												//soit on ajoute a la liste au meme temps ou bien on ajoute a la fin
												contBoites.add(new Position(ligne,positionActuelle.getCol()));
												ligne--;
											}
											if(board[ligne][positionActuelle.getCol()].getContent().equals(Cell.Empty)) {
												//on fait le déplacement de tous les boîtes
												//on revient a la position actuelle et on change les modifications 
												board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
												board[positionActuelle.getRow()-1][positionActuelle.getCol()].setContent(Cell.Me);
												board[ligne][positionActuelle.getCol()].setContent(Cell.Box);
												//on doit faire les changement dans la liste des boîtes
												int indicePrm = boxes.indexOf(contBoites.get(0));
												//CA MARCHEEEEEEEEEE!!!!!!!!!
												boxes.set(indicePrm,new Position(ligne,positionActuelle.getCol()));
												positionActuelle.setRow(positionActuelle.getRow()-1);
												return;
											}if(board[ligne][positionActuelle.getCol()].getContent().equals(Cell.Wall)) return;
										}
										if((positionActuelle.getRow()-2)>=0 ) {
											if(board[positionActuelle.getRow()-2][positionActuelle.getCol()].getContent().equals(Cell.Empty)) {
												board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
												board[positionActuelle.getRow()-1][positionActuelle.getCol()].setContent(Cell.Me);
												board[positionActuelle.getRow()-2][positionActuelle.getCol()].setContent(Cell.Box);
												positionActuelle.setRow(positionActuelle.getRow()-1);
											}//les autres cas on ne fait rien 	
											if ( (positionActuelle.getRow()-2)>-1)
												if(board[positionActuelle.getRow()-2][positionActuelle.getCol()].getContent().equals(Cell.Target)) {
													board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
													board[positionActuelle.getRow()-1][positionActuelle.getCol()].setContent(Cell.Me);
													board[positionActuelle.getRow()-2][positionActuelle.getCol()].setContent(Cell.BoxOnTarget);
													positionActuelle.setRow(positionActuelle.getRow()-1);
												}
										}
										
									}
									else {
										//if(board[positionActuelle.getRow()-1][positionActuelle.getCol()].getContent().equals(Cell.Target)) {
										if(board[positionActuelle.getRow()-1][positionActuelle.getCol()].getContent().equals(Cell.Target)) {
											board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
											board[positionActuelle.getRow()-1][positionActuelle.getCol()].setContent(Cell.MeOnTarget);
											positionActuelle.setRow(positionActuelle.getRow()-1);
											
										}else {
											if(board[positionActuelle.getRow()][positionActuelle.getCol()].getContent().equals(Cell.MeOnTarget)) {
												if(board[positionActuelle.getRow()-1][positionActuelle.getCol()].getContent().equals(Cell.Empty)) {
													board[positionActuelle.getRow()-1][positionActuelle.getCol()].setContent(Cell.Me);
													board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Target);
													positionActuelle.setRow(positionActuelle.getRow()-1);
												}
											}
										
										}
										
									}
								} 
							}	
						}}else 
						if( /*board.length!=boardSauv.length &&*/ positionActuelle.getRow()-1==0 && board[positionActuelle.getRow()-1][positionActuelle.getCol()].getContent().equals(Cell.Box) && boardSauv[positionMonde.getRow()-1][positionMonde.getCol()].getContent().equals(Cell.Empty)  ) {
							//on sort la boite du monde
					    	Position positionBoite= new Position(positionActuelle.getRow()-1,positionActuelle.getCol());
							board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
							positionActuelle.setRow(positionActuelle.getRow()-1);
							board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Me);
							boardSauv[positionMonde.getRow()-1][positionMonde.getCol()].setContent(Cell.Box);
							int i=getIndex(boxes,positionBoite);
							boxes.get(i).setRow(positionMonde.getRow()-1);
							boxes.get(i).setCol(positionMonde.getCol());
						}
					else {		//et le cas q"on on sort d'un monde et y a en dessus une boite après un vide donc on pourra pousser à partir du monde la boite
						//je traite le cas d'une seule boite :
						
						
						
						

					
					/*	
						if(board[positionActuelle.getRow()-1][positionMonde.getCol()].getContent().equals(Cell.Box) && board[positionActuelle.getRow()-2][positionActuelle.getCol()].getContent().equals(Cell.Empty)&& board.length==boardSauv.length) {
							Position positionBoite= new Position(positionActuelle.getRow()-1,positionActuelle.getCol());
							board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
							board[positionActuelle.getRow()-1][positionActuelle.getCol()].setContent(Cell.Me);
							board[positionActuelle.getRow()-2][positionActuelle.getCol()].setContent(Cell.Box);
							positionActuelle.setRow(positionActuelle.getRow()-1);
							//n oublie pas de modifier la liste des boites
							//on supprime de la liste des boites la boite avec l ancienne position pour la mettre a jour
							int i=getIndex(boxes,positionBoite);
							boxes.get(i).setRow(positionBoite.getRow()-1);
							boxes.get(i).setCol(positionMonde.getCol());
						}
						
						else
						*/
						
						
						
						if(board[positionActuelle.getRow()-1][positionActuelle.getCol()].getContent().equals(Cell.Box) && board[positionActuelle.getRow()-2][positionActuelle.getCol()].getContent().equals(Cell.Empty)&& board.length==boardSauv.length) {
							Position positionBoite= new Position(positionActuelle.getRow()-1,positionActuelle.getCol());
							board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
							board[positionActuelle.getRow()-1][positionActuelle.getCol()].setContent(Cell.Me);
							board[positionActuelle.getRow()-2][positionActuelle.getCol()].setContent(Cell.Box);
							positionActuelle.setRow(positionActuelle.getRow()-1);
							//n oublie pas de modifier la liste des boites
							//on supprime de la liste des boites la boite avec l ancienne position pour la mettre a jour
							int i=getIndex(boxes,positionBoite);
							boxes.get(i).setRow(positionBoite.getRow()-1);
							boxes.get(i).setCol(positionMonde.getCol());
						}
						else if(positionActuelle.getRow()==0 && boardSauv[positionMonde.getRow()-1][positionMonde.getCol()].getContent().equals(Cell.Box)&& boardSauv[positionMonde.getRow()-2][positionMonde.getCol()].getContent().equals(Cell.Empty)&& board.length!=boardSauv.length ) {
							Position positionBoite= new Position(positionMonde.getRow()-1,positionMonde.getCol());
							board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
							boardSauv[positionMonde.getRow()-1][positionMonde.getCol()].setContent(Cell.Me);
							boardSauv[positionMonde.getRow()-2][positionMonde.getCol()].setContent(Cell.Box);
							positionActuelle.setRow(positionMonde.getRow()-1);
							positionActuelle.setCol(positionMonde.getCol());
							board=boardSauv;
							int i=getIndex(boxes,positionBoite);							
							boxes.get(i).setRow(positionBoite.getRow()-1);
							boxes.get(i).setCol(positionMonde.getCol());
						}
						else {
						int ligne=positionActuelle.getRow()-1;
						ArrayList<Position> contBoites=new  ArrayList<Position>();
						while (ligne>0) {//cas juste une seule boite!
							if(board[ligne][positionActuelle.getCol()].getContent().equals(Cell.Box) /*|| board[ligne][positionActuelle.getCol()].getContent().equals(Cell.BoxOnTarget)*/) {
								//on peut créer une nouvelle liste qui comporte ces boîtes et qu'on modifiera
								//soit on ajoute a la liste au meme temps ou bien on ajoute a la fin
							contBoites.add(new Position(ligne,positionActuelle.getCol()));
								ligne--;
								}
							//traitement premiere boite et derniere boite sue une cible
							if(board[ligne][positionActuelle.getCol()].getContent().equals(Cell.Empty)) {
								//on fait le déplacement de tous les boîtes
								//on revient a la position actuelle et on change les modifications 
								board[positionActuelle.getRow()-1][positionActuelle.getCol()].setContent(Cell.Me);
								board[ligne][positionActuelle.getCol()].setContent(Cell.Box);
								//on doit faire les changement dans la liste des boîtes
								int indicePrm = boxes.indexOf(contBoites.get(0));
								//CA MARCHEEEEEEEEEE!!!!!!!!!
								boxes.set(indicePrm,new Position(ligne,positionActuelle.getCol()));
								if(board[positionActuelle.getRow()][positionActuelle.getCol()].getContent().equals(Cell.MeOnTarget)) {
									board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Target);
									//le cas si on est sur cible et on la quitte 
								}
								else board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
								positionActuelle.setRow(positionActuelle.getRow()-1);
								//on traite le cas une boîte sur la cible 
								//ATTENTION on doit modifie
								//on arrête de chercher d autres boîtes
								//et on sort de la méthode 
								return;
							}
							else {
								if(board[ligne][positionActuelle.getCol()].getContent().equals(Cell.Target)) {
									//on traite le cas une cible est en haut 
									board[ligne][positionActuelle.getCol()].setContent(Cell.BoxOnTarget);// WORKS !!!
								}
							}
							//si c est un mur on arrête de chercher
							//on sort carrément de la méthode 
							if(board[ligne][positionActuelle.getCol()].getContent().equals(Cell.Wall)) return;
						}
						if((positionActuelle.getRow()-2)>=0 ) { 
							if(board[positionActuelle.getRow()-2][positionActuelle.getCol()].getContent().equals(Cell.Empty)) {
								board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
								board[positionActuelle.getRow()-1][positionActuelle.getCol()].setContent(Cell.Me);
								board[positionActuelle.getRow()-2][positionActuelle.getCol()].setContent(Cell.Box);
								positionActuelle.setRow(positionActuelle.getRow()-1);
							}//les autres cas on ne fait rien 	
							if ( (positionActuelle.getRow()-2)>-1)
								if(board[positionActuelle.getRow()-2][positionActuelle.getCol()].getContent().equals(Cell.Target)) {
									board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
									board[positionActuelle.getRow()-1][positionActuelle.getCol()].setContent(Cell.Me);
									board[positionActuelle.getRow()-2][positionActuelle.getCol()].setContent(Cell.BoxOnTarget);
									positionActuelle.setRow(positionActuelle.getRow()-1);
								}
						}
					}
					}	
					}
					else {
						if(board[positionActuelle.getRow()-1][positionActuelle.getCol()].getContent().equals(Cell.Target)) {
							board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
							board[positionActuelle.getRow()-1][positionActuelle.getCol()].setContent(Cell.MeOnTarget);
							positionActuelle.setRow(positionActuelle.getRow()-1);
						}else {
							if(board[positionActuelle.getRow()][positionActuelle.getCol()].getContent().equals(Cell.MeOnTarget)) {
								if(board[positionActuelle.getRow()-1][positionActuelle.getCol()].getContent().equals(Cell.Empty)) {
									board[positionActuelle.getRow()-1][positionActuelle.getCol()].setContent(Cell.Me);
									board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Target);
									positionActuelle.setRow(positionActuelle.getRow()-1);
								}
							}
							else {//on fera juste un monde au plus ALLAH GHALEB on n'a ni le temps ni les compétences pour faire plus
								if(board[positionActuelle.getRow()-1][positionActuelle.getCol()].getContent().equals(Cell.World)) {
									///on recherche une porte si on peut entrer 
									//dans la bordure en dessous
									positionMonde=new Position(positionActuelle.getRow()-1,positionActuelle.getCol());
									Monde monde= lesMondes.get(new Position(positionActuelle.getRow()-1,positionActuelle.getCol()));
									Position p;
									if ( (p=trouverPorte(monde,0)) != null  ) {//on a trouvé une porte et on peut y accéder
										//on doit sauvegarder la position sur le board et on doit se déplacer sur le monde 
										board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
										estMonde=true;
										boardSauv=board;
										board=monde.getMatrice();
		colNb=rowNb=board.length;
										positionActuelle=p;
									    board[p.getRow()][p.getCol()].setContent(Cell.Me);
									}
								}else {// cas on est dans un monde et si on sorte du monde
									if( estMonde) {
										if (positionActuelle.getRow()==0 && positionMonde.getRow()>0) {
											//donc on peut sortir du monde 
											if(board[positionMonde.getRow()-1][positionMonde.getCol()].getContent().equals(Cell.Empty)) {
												positionActuelle.setCol(positionMonde.getCol());
												positionActuelle.setRow(positionMonde.getRow()-1);
											}
											else {//on bouge le monde comme une boite
												//on modifie sa position dans la liste des mondes 
												Position posMonde=positionMonde;
												board[positionMonde.getRow()][positionMonde.getCol()].setContent(Cell.Box);
												deplacerUP();
												if(!posMonde.equals(new Position(posMonde.getRow()-1,posMonde.getCol()))) {
												board[positionMonde.getRow()-1][positionMonde.getCol()].setContent(Cell.World);
												//MAJ de la liste des world 
												Monde m= lesMondes.get(posMonde);
												lesMondes.remove(positionMonde);
												lesMondes.put(new Position(positionMonde.getRow()-1,positionMonde.getCol()), m);
												}
											}
										}
									}
								}
							}
						}
					}
				} 
			}	
		}
		else {
			//cas pour sortir d un monde
			if(positionActuelle.getRow()==0) {
				if(boardSauv[positionMonde.getRow()-1][positionMonde.getCol()].getContent().equals(Cell.Empty) || boardSauv[positionMonde.getRow()][positionMonde.getCol()].getContent().equals(Cell.Target) ){
					board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
					positionActuelle.setCol(positionMonde.getCol());
					positionActuelle.setRow(positionMonde.getRow()-1);
					board=boardSauv;
					colNb=rowNb=board.length;
					board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Me);
				}
				else if(boardSauv[positionMonde.getRow()-1][positionMonde.getCol()].getContent().equals(Cell.Box) && boardSauv[positionMonde.getRow()-2][positionMonde.getCol()].getContent().equals(Cell.Empty) ) {
					Position positionBoite = new Position(positionMonde.getRow()-1,positionMonde.getCol());
					board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
					positionBoite.printPosition();
					boardSauv[positionMonde.getRow()-1][positionMonde.getCol()].setContent(Cell.Me);
					boardSauv[positionMonde.getRow()-2][positionMonde.getCol()].setContent(Cell.Box);
					positionActuelle.setRow(positionMonde.getRow()-1);
					board=boardSauv;
					colNb=rowNb=board.length;
							//n oublie pas de modifier la liste des boites
					int i=getIndex(boxes,positionBoite);
					boxes.get(i).setRow(positionBoite.getRow()-1);
					boxes.get(i).setCol(positionMonde.getCol());
				}
			}	
			else {
			estMonde=false;
			deplacerUP();
			estMonde=true;
			}
		}
	}
	
	public static int getIndex(ArrayList<Position> positions,Position p) {
		for(int i = 0 ;i<positions.size();i++) {
			if(positions.get(i).getCol()==p.getCol() && positions.get(i).getRow()==p.getRow()) return i ;
		}
		return -1;
	}
	
	
	Position trouverPorte ( Monde monde,int direction) {//0 UP   1 RIGHT  2 LEFT  3 DOWN
			switch (direction) {
			case 0 : // deplacer up
				for (int i=0;i<monde.getTaille();i++) {
					if( monde.getMatrice()[monde.getTaille()-1][i].getContent().equals(Cell.Empty)) {
						return new Position(monde.getTaille()-1,i);
					}
				}
			return null;
			
			case 1 : // deplacer right
				for (int i=0;i<monde.getTaille();i++) {
					if( monde.getMatrice()[i][0].getContent().equals(Cell.Empty)) {
						return new Position(i,0);
					}
				}
			return null;
			
			case 2 : // deplacer up
				for (int i=0;i<monde.getTaille();i++) {
					if( monde.getMatrice()[i][monde.getTaille()-1].getContent().equals(Cell.Empty)) {
						return new Position(i,monde.getTaille()-1);
					}
				}
			return null;
			
			
			case 3 : // deplacer up
				for (int i=0;i<monde.getTaille();i++) {
					if( monde.getMatrice()[0][i].getContent().equals(Cell.Empty)) {
						return new Position(0,i);
					}
				}
			return null;
			
			default : return null;
			}
	}
	private void Niveau(Niveau mondeActuel) {
		// TODO Auto-generated method stub
		//faut copier les champs un a un
		
	}

	void deplacerDOWN() { 
		if(boardSauv!=null && positionActuelle.getRow()==colNb-1  )
		//	if(boardSauv[positionActuelle.getRow()+1][positionActuelle.getCol()].getContent().equals(Cell.World))
			if (positionActuelle.getRow()==rowNb-1 && positionMonde.getRow()<rowNb-1) {
				//donc on peut sortir du monde 
				System.out.println("ezghifkjdbvhik");
				if(board[positionMonde.getRow()+1][positionMonde.getCol()].getContent().equals(Cell.Empty)) {
					positionActuelle.setCol(positionMonde.getCol());
					positionActuelle.setRow(positionMonde.getRow()+1);
				}
				else {//on bouge le monde comme une boite
					//on modifie sa position dans la liste des mondes 
					Position posMonde=positionMonde;
					board[positionMonde.getRow()][positionMonde.getCol()].setContent(Cell.Box);
					deplacerDOWN();
					if(!posMonde.equals(new Position(posMonde.getRow()+1,posMonde.getCol()))) {
					board[positionMonde.getRow()+1][positionMonde.getCol()].setContent(Cell.World);
					//MAJ de la liste des world 
					Monde m= lesMondes.get(posMonde);
					lesMondes.remove(positionMonde);
					lesMondes.put(new Position(positionMonde.getRow()+1,positionMonde.getCol()), m);
					}
				}
			}
		
		
		
		if(positionActuelle.getRow()+2<colNb-1) {
			if(board[positionActuelle.getRow()+1][positionActuelle.getCol()].getContent().equals(Cell.Box)&&board[positionActuelle.getRow()+2][positionActuelle.getCol()].getContent().equals(Cell.Target)) {
				board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
				board[positionActuelle.getRow()+1][positionActuelle.getCol()].setContent(Cell.Me);
				board[positionActuelle.getRow()+2][positionActuelle.getCol()].setContent(Cell.BoxOnTarget);
				positionActuelle.setRow(positionActuelle.getRow()+1);
			}
		}
		
	if(	positionActuelle.getRow()+1<colNb )	if(board[positionActuelle.getRow()+1][positionActuelle.getCol()].getContent().equals(Cell.World) ) {
				
				if(positionActuelle.getRow()+2<=colNb-1) {
					
					if(board[positionActuelle.getRow()+2][positionActuelle.getCol()].getContent().equals(Cell.Empty)) {
						Position p=new Position(positionActuelle.getRow()+1,positionActuelle.getCol());
				Monde m=rechMonde(p,lesMondes);
				lesMondes.remove(p, m);
				p.setRow(p.getRow()+1);
				lesMondes.put(p, m);
				board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
				board[positionActuelle.getRow()+2][positionActuelle.getCol()].setContent(Cell.World);
				board[positionActuelle.getRow()+1][positionActuelle.getCol()].setContent(Cell.Me);
				positionActuelle.setRow(positionActuelle.getRow()+1);
					}
					
					
					
					
				}
				if(positionActuelle.getRow()+1==colNb-1) {//on entre le monde
					positionMonde=new Position(positionActuelle.getRow()+1,positionActuelle.getCol());
					Monde monde= lesMondes.get(new Position(positionActuelle.getRow()+1,positionActuelle.getCol()));
					Position p;
					if ( (p=trouverPorte(monde,3)) != null  ) {//on a trouvé une porte et on peut y accéder
						//on doit sauvegarder la position sur le board et on doit se déplacer sur le monde 
						board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
						estMonde=true;
						boardSauv=board;
						board=monde.getMatrice();
						colNb=rowNb=board.length;
						positionActuelle=p;
					    board[p.getRow()][p.getCol()].setContent(Cell.Me);
				}
				}
				else if (positionActuelle.getRow()+2==colNb-1 && board[positionActuelle.getRow()+2][positionActuelle.getCol()].getContent().equals(Cell.Wall) ) {
					positionMonde=new Position(positionActuelle.getRow()+1,positionActuelle.getCol());
					Monde monde= lesMondes.get(new Position(positionActuelle.getRow()+1,positionActuelle.getCol()));
					Position p;
					if ( (p=trouverPorte(monde,3)) != null  ) {//on a trouvé une porte et on peut y accéder
						//on doit sauvegarder la position sur le board et on doit se déplacer sur le monde 
						board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
						estMonde=true;
						boardSauv=board;
						board=monde.getMatrice();
						colNb=rowNb=board.length;
						positionActuelle=p;
					    board[p.getRow()][p.getCol()].setContent(Cell.Me);
					
				}}
		}
		
		
		else
		
		
		
		
		//if (positionActuelle.getRow()==rowNb-1 && !estMonde) return ; //on est à la derniere ligne
		if(positionActuelle.getRow()+2>colNb-1) {
			if(board[positionActuelle.getRow()+1][positionActuelle.getCol()].getContent().equals(Cell.Box)&&board[positionActuelle.getRow()+2][positionActuelle.getCol()].getContent().equals(Cell.Target)) {
				board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
				board[positionActuelle.getRow()+1][positionActuelle.getCol()].setContent(Cell.Me);
				board[positionActuelle.getRow()+2][positionActuelle.getCol()].setContent(Cell.BoxOnTarget);
				positionActuelle.setRow(positionActuelle.getRow()+1);
			}
		}
		
	if(positionActuelle.getRow()+1<colNb)	if(board[positionActuelle.getRow()+1][positionActuelle.getCol()].getContent().equals(Cell.World) ) {
				
				if(positionActuelle.getRow()+2>=colNb-1) {
					
					if(board[positionActuelle.getRow()+2][positionActuelle.getCol()].getContent().equals(Cell.Empty)) {
						Position p=new Position(positionActuelle.getRow()+1,positionActuelle.getCol());
				Monde m=rechMonde(p,lesMondes);
				lesMondes.remove(p, m);
				p.setRow(p.getRow()+1);
				lesMondes.put(p, m);
				board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
				board[positionActuelle.getRow()+2][positionActuelle.getCol()].setContent(Cell.World);
				board[positionActuelle.getRow()+1][positionActuelle.getCol()].setContent(Cell.Me);
				positionActuelle.setRow(positionActuelle.getRow()+1);
					}
				}
				if(positionActuelle.getRow()+1==colNb-1) {//on entre le monde
					positionMonde=new Position(positionActuelle.getRow()+1,positionActuelle.getCol());
					Monde monde= lesMondes.get(new Position(positionActuelle.getRow()+1,positionActuelle.getCol()));
					Position p;
					if ( (p=trouverPorte(monde,3)) != null  ) {//on a trouvé une porte et on peut y accéder
						//on doit sauvegarder la position sur le board et on doit se déplacer sur le monde 
						board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
						estMonde=true;
						boardSauv=board;
						board=monde.getMatrice();
						colNb=rowNb=board.length;
						positionActuelle=p;
					    board[p.getRow()][p.getCol()].setContent(Cell.Me);
				}
				}
				
				
				if( positionActuelle.getRow()<rowNb) {
					if(board[positionActuelle.getRow()+1][positionActuelle.getCol()].getContent().equals(Cell.Empty)&& board[positionActuelle.getRow()][positionActuelle.getCol()].getContent().equals(Cell.Me)) {
						board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
						positionActuelle.setRow(positionActuelle.getRow()+1);
						board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Me);
					}
					else  {
						if (board[positionActuelle.getRow()+1][positionActuelle.getCol()].getContent().equals(Cell.Wall)) return ;
						else {
							if (board[positionActuelle.getRow()+1][positionActuelle.getCol()].getContent().equals(Cell.Box)) {
								if((positionActuelle.getRow()+2)<rowNb ) {
									if(board[positionActuelle.getRow()+2][positionActuelle.getCol()].getContent().equals(Cell.Empty)) {
										board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
										board[positionActuelle.getRow()+1][positionActuelle.getCol()].setContent(Cell.Me);
										board[positionActuelle.getRow()+2][positionActuelle.getCol()].setContent(Cell.Box);
										positionActuelle.setRow(positionActuelle.getRow()+1);
									}//les autres cas on ne fait rien 	
									if ( (positionActuelle.getRow()+2)<rowNb)
										if(board[positionActuelle.getRow()+2][positionActuelle.getCol()].getContent().equals(Cell.Target)) {
											board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
											board[positionActuelle.getRow()+1][positionActuelle.getCol()].setContent(Cell.Me);
											board[positionActuelle.getRow()+2][positionActuelle.getCol()].setContent(Cell.BoxOnTarget);
											positionActuelle.setRow(positionActuelle.getRow()+1);
										}
									
								}
								
							}
							else {
								//if(board[positionActuelle.getRow()-1][positionActuelle.getCol()].getContent().equals(Cell.Target)) {
								if(board[positionActuelle.getRow()+1][positionActuelle.getCol()].getContent().equals(Cell.Target)) {
									board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
									board[positionActuelle.getRow()+1][positionActuelle.getCol()].setContent(Cell.MeOnTarget);
									positionActuelle.setRow(positionActuelle.getRow()+1);
									
								}else {
									if(board[positionActuelle.getRow()][positionActuelle.getCol()].getContent().equals(Cell.MeOnTarget)) {
										if(board[positionActuelle.getRow()+1][positionActuelle.getCol()].getContent().equals(Cell.Empty)) {
											board[positionActuelle.getRow()+1][positionActuelle.getCol()].setContent(Cell.Me);
											board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Target);
											positionActuelle.setRow(positionActuelle.getRow()+1);
										}
										
									}
								}
								
							}
						} 
					}	
				}
				else if (positionActuelle.getRow()+2==colNb && board[positionActuelle.getRow()+2][positionActuelle.getCol()].getContent().equals(Cell.Wall) ) {
					positionMonde=new Position(positionActuelle.getRow()+1,positionActuelle.getCol());
					Monde monde= lesMondes.get(new Position(positionActuelle.getRow()-1,positionActuelle.getCol()));
					Position p;
					if ( (p=trouverPorte(monde,3)) != null  ) {//on a trouvé une porte et on peut y accéder
						//on doit sauvegarder la position sur le board et on doit se déplacer sur le monde 
						board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
						estMonde=true;
						boardSauv=board;
						board=monde.getMatrice();
						colNb=rowNb=board.length;
						positionActuelle=p;
					    board[p.getRow()][p.getCol()].setContent(Cell.Me);
					
				}}
				
				
				
				
				
		}else
		if( positionActuelle.getRow()<rowNb-1) {// c-a-d on est dans la matrice
		// permier cas : un vide en dessus
			if(board[positionActuelle.getRow()+1][positionActuelle.getCol()].getContent().equals(Cell.Empty)&& board[positionActuelle.getRow()][positionActuelle.getCol()].getContent().equals(Cell.Me)) {
				board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
				positionActuelle.setRow(positionActuelle.getRow()+1);
				board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Me);
			}
			else  {
				if (board[positionActuelle.getRow()+1][positionActuelle.getCol()].getContent().equals(Cell.Wall)) return ;//si c un mur on ne faot rien
				else {
					if (board[positionActuelle.getRow()+1][positionActuelle.getCol()].getContent().equals(Cell.Box) || board[positionActuelle.getRow()+1][positionActuelle.getCol()].getContent().equals(Cell.BoxOnTarget) ) {//Faut traiter le cas d'une liste de boîtes
						//vérifier s'il y en a des boîtes en haut et puis faire le déplacement à la fois
						//on utilisera une boucle qui cherche sk y en a des boîtes et sk on est arrivé au mur ou si on est sur la bordure
						// et si on pousse une boite qui se trouve sa cible ?
					 
					//	if(board[positionActuelle.getRow()+1][positionActuelle.getCol()].getContent())
					if(boardSauv==null) {if(positionActuelle.getRow()<colNb-1)	{
						
					
						if( positionActuelle.getRow()<rowNb) {
							if(board[positionActuelle.getRow()+1][positionActuelle.getCol()].getContent().equals(Cell.Empty)&& board[positionActuelle.getRow()][positionActuelle.getCol()].getContent().equals(Cell.Me)) {
								board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
								positionActuelle.setRow(positionActuelle.getRow()+1);
								board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Me);
							}
							else  {
								if (board[positionActuelle.getRow()+1][positionActuelle.getCol()].getContent().equals(Cell.Wall)) return ;
								else {
									if (board[positionActuelle.getRow()+1][positionActuelle.getCol()].getContent().equals(Cell.Box)) {
										if((positionActuelle.getRow()+2)<rowNb ) {
											if(board[positionActuelle.getRow()+2][positionActuelle.getCol()].getContent().equals(Cell.Empty)) {
												board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
												board[positionActuelle.getRow()+1][positionActuelle.getCol()].setContent(Cell.Me);
												board[positionActuelle.getRow()+2][positionActuelle.getCol()].setContent(Cell.Box);
												positionActuelle.setRow(positionActuelle.getRow()+1);
											}//les autres cas on ne fait rien 	
											if ( (positionActuelle.getRow()+2)<rowNb)
												if(board[positionActuelle.getRow()+2][positionActuelle.getCol()].getContent().equals(Cell.Target)) {
													board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
													board[positionActuelle.getRow()+1][positionActuelle.getCol()].setContent(Cell.Me);
													board[positionActuelle.getRow()+2][positionActuelle.getCol()].setContent(Cell.BoxOnTarget);
													positionActuelle.setRow(positionActuelle.getRow()+1);
												}
											
										}
										
									}
									else {
										//if(board[positionActuelle.getRow()-1][positionActuelle.getCol()].getContent().equals(Cell.Target)) {
										if(board[positionActuelle.getRow()+1][positionActuelle.getCol()].getContent().equals(Cell.Target)) {
											board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
											board[positionActuelle.getRow()+1][positionActuelle.getCol()].setContent(Cell.MeOnTarget);
											positionActuelle.setRow(positionActuelle.getRow()+1);
											
										}else {
											if(board[positionActuelle.getRow()][positionActuelle.getCol()].getContent().equals(Cell.MeOnTarget)) {
												if(board[positionActuelle.getRow()+1][positionActuelle.getCol()].getContent().equals(Cell.Empty)) {
													board[positionActuelle.getRow()+1][positionActuelle.getCol()].setContent(Cell.Me);
													board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Target);
													positionActuelle.setRow(positionActuelle.getRow()+1);
												}
												
											}
											
										}
										
									}
								} 
							}	
						}
					
					
					}
					}
					else	
						if( /*board.length!=boardSauv.length &&*/ positionActuelle.getRow()==rowNb-2 && board[positionActuelle.getRow()+1][positionActuelle.getCol()].getContent().equals(Cell.Box) && boardSauv[positionMonde.getRow()+1][positionMonde.getCol()].getContent().equals(Cell.Empty)  ) {
							//on sort la boite du monde
					    	Position positionBoite= new Position(positionActuelle.getRow()+1,positionActuelle.getCol());
							board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
							positionActuelle.setRow(positionActuelle.getRow()+1);
							board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Me);
							boardSauv[positionMonde.getRow()+1][positionMonde.getCol()].setContent(Cell.Box);
							int i=getIndex(boxes,positionBoite);
							boxes.get(i).setRow(positionMonde.getRow()+1);
							boxes.get(i).setCol(positionMonde.getCol());
						}
					else {
						if(boardSauv[positionMonde.getRow()+1][positionMonde.getCol()].getContent().equals(Cell.Box) && boardSauv[positionMonde.getRow()+2][positionMonde.getCol()].getContent().equals(Cell.Empty)&& board.length==boardSauv.length ) {
							Position positionBoite= new Position(positionActuelle.getRow()+1,positionActuelle.getCol());
							board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
							board[positionActuelle.getRow()+1][positionActuelle.getCol()].setContent(Cell.Me);
							board[positionActuelle.getRow()+2][positionActuelle.getCol()].setContent(Cell.Box);
							positionActuelle.setRow(positionActuelle.getRow()+1);
							int i=getIndex(boxes,positionBoite);
							boxes.get(i).setRow(positionBoite.getRow()+1);
							boxes.get(i).setCol(positionMonde.getCol());
						}
						else if(positionActuelle.getRow()==rowNb+1 && boardSauv[positionMonde.getRow()+1][positionMonde.getCol()].getContent().equals(Cell.Box)&& boardSauv[positionMonde.getRow()+2][positionMonde.getCol()].getContent().equals(Cell.Empty)&& board.length!=boardSauv.length ) {
							Position positionBoite= new Position(positionMonde.getRow()+1,positionMonde.getCol());
							board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
							boardSauv[positionMonde.getRow()+1][positionMonde.getCol()].setContent(Cell.Me);
							boardSauv[positionMonde.getRow()+2][positionMonde.getCol()].setContent(Cell.Box);
							positionActuelle.setRow(positionMonde.getRow()+1);
							positionActuelle.setCol(positionMonde.getCol());
							board=boardSauv;
							int i=getIndex(boxes,positionBoite);							
							boxes.get(i).setRow(positionBoite.getRow()+1);
							boxes.get(i).setCol(positionMonde.getCol());
						}
						else {
						int ligne=positionActuelle.getRow()+1;
						ArrayList<Position> contBoites=new  ArrayList<Position>();
						while (ligne<rowNb-1) {//cas juste une seule boite!
							if(board[ligne][positionActuelle.getCol()].getContent().equals(Cell.Box) /*|| board[ligne][positionActuelle.getCol()].getContent().equals(Cell.BoxOnTarget)*/) {
								//on peut créer une nouvelle liste qui comporte ces boîtes et qu'on modifiera
								//soit on ajoute a la liste au meme temps ou bien on ajoute a la fin
							contBoites.add(new Position(ligne,positionActuelle.getCol()));
								ligne++;
								}
							//traitement premiere boite et derniere boite sue une cible
							if(board[ligne][positionActuelle.getCol()].getContent().equals(Cell.Empty)) {
								//on fait le déplacement de tous les boîtes
								//on revient a la position actuelle et on change les modifications 
								board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
								board[positionActuelle.getRow()+1][positionActuelle.getCol()].setContent(Cell.Me);
								board[ligne][positionActuelle.getCol()].setContent(Cell.Box);
								//on doit faire les changement dans la liste des boîtes
								int indicePrm = boxes.indexOf(contBoites.get(contBoites.size()-1));
								//CA MARCHEEEEEEEEEE!!!!!!!!!
								boxes.set(indicePrm,new Position(ligne,positionActuelle.getCol()));
								if(board[positionActuelle.getRow()][positionActuelle.getCol()].getContent().equals(Cell.MeOnTarget)) {
									board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Target);
									//le cas si on est sur cible et on la quitte 
								}
								else board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
								positionActuelle.setRow(positionActuelle.getRow()+1);
								//on traite le cas une boîte sur la cible 
								//ATTENTION on doit modifie
								//on arrête de chercher d autres boîtes
								//et on sort de la méthode 
								return;
							}
							else {
								if(board[ligne][positionActuelle.getCol()].getContent().equals(Cell.Target)) {
									//on traite le cas une cible est en haut 
									board[ligne][positionActuelle.getCol()].setContent(Cell.BoxOnTarget);// WORKS !!!
								}
							}
							//si c est un mur on arrête de chercher
							//on sort carrément de la méthode 
							if(board[ligne][positionActuelle.getCol()].getContent().equals(Cell.Wall)) return;
						}
						if((positionActuelle.getRow()+2)<rowNb-1 ) { 
							if(board[positionActuelle.getRow()+2][positionActuelle.getCol()].getContent().equals(Cell.Empty)) {
								board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
								board[positionActuelle.getRow()+1][positionActuelle.getCol()].setContent(Cell.Me);
								board[positionActuelle.getRow()+2][positionActuelle.getCol()].setContent(Cell.Box);
								positionActuelle.setRow(positionActuelle.getRow()+1);
							}//les autres cas on ne fait rien 	
							if ( (positionActuelle.getRow()+2)<rowNb)
								if(board[positionActuelle.getRow()+2][positionActuelle.getCol()].getContent().equals(Cell.Target)) {
									board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
									board[positionActuelle.getRow()+1][positionActuelle.getCol()].setContent(Cell.Me);
									board[positionActuelle.getRow()+2][positionActuelle.getCol()].setContent(Cell.BoxOnTarget);
									positionActuelle.setRow(positionActuelle.getRow()+1);
								}
						}
					}
					}	
					}
					else {
						if(board[positionActuelle.getRow()+1][positionActuelle.getCol()].getContent().equals(Cell.Target)) {
							board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
							board[positionActuelle.getRow()+1][positionActuelle.getCol()].setContent(Cell.MeOnTarget);
							positionActuelle.setRow(positionActuelle.getRow()+1);
						}else {
							if(board[positionActuelle.getRow()][positionActuelle.getCol()].getContent().equals(Cell.MeOnTarget)) {
								if(board[positionActuelle.getRow()+1][positionActuelle.getCol()].getContent().equals(Cell.Empty)) {
									board[positionActuelle.getRow()+1][positionActuelle.getCol()].setContent(Cell.Me);
									board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Target);
									positionActuelle.setRow(positionActuelle.getRow()+1);
								}
							}
							else {//on fera juste un monde au plus ALLAH GHALEB on n'a ni le temps ni les compétences pour faire plus
								if(board[positionActuelle.getRow()+1][positionActuelle.getCol()].getContent().equals(Cell.World)) {
									///on recherche une porte si on peut entrer 
									//dans la bordure en dessous
									positionMonde=new Position(positionActuelle.getRow()+1,positionActuelle.getCol());
									Monde monde= lesMondes.get(new Position(positionActuelle.getRow()+1,positionActuelle.getCol()));
									Position p;
									if ( (p=trouverPorte(monde,3)) != null  ) {//on a trouvé une porte et on peut y accéder
										//on doit sauvegarder la position sur le board et on doit se déplacer sur le monde 
										board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
										estMonde=true;
										boardSauv=board;
										board=monde.getMatrice();
										colNb=rowNb=board.length;
										positionActuelle=p;
									    board[p.getRow()][p.getCol()].setContent(Cell.Me);
									}
								}else {// cas on est dans un monde et si on sorte du monde
									
										if (positionActuelle.getRow()==rowNb-1 && positionMonde.getRow()<rowNb-1) {
											//donc on peut sortir du monde 
											if(board[positionMonde.getRow()+1][positionMonde.getCol()].getContent().equals(Cell.Empty)) {
												positionActuelle.setCol(positionMonde.getCol());
												positionActuelle.setRow(positionMonde.getRow()+1);
											}
											else {//on bouge le monde comme une boite
												//on modifie sa position dans la liste des mondes 
												Position posMonde=positionMonde;
												board[positionMonde.getRow()][positionMonde.getCol()].setContent(Cell.Box);
												deplacerDOWN();
												if(!posMonde.equals(new Position(posMonde.getRow()+1,posMonde.getCol()))) {
												board[positionMonde.getRow()+1][positionMonde.getCol()].setContent(Cell.World);
												//MAJ de la liste des world 
												Monde m= lesMondes.get(posMonde);
												lesMondes.remove(positionMonde);
												lesMondes.put(new Position(positionMonde.getRow()+1,positionMonde.getCol()), m);
												}
											}
										}
									
								}
							}
						}
					}
				} 
			}	
		}
		else {
			//cas pour sortir d un monde
			if(positionActuelle.getRow()==board.length-1) {
				if(boardSauv[positionMonde.getRow()+1][positionMonde.getCol()].getContent().equals(Cell.Empty) || boardSauv[positionMonde.getRow()+1][positionMonde.getCol()].getContent().equals(Cell.Target) ){
					board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
					positionActuelle.setCol(positionMonde.getCol());
					positionActuelle.setRow(positionMonde.getRow()+1);
					board=boardSauv;
					colNb=rowNb=board.length;
					board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Me);
				}
				else if(boardSauv[positionMonde.getRow()+1][positionMonde.getCol()].getContent().equals(Cell.Box) && boardSauv[positionMonde.getRow()+2][positionMonde.getCol()].getContent().equals(Cell.Empty) ) {
					Position positionBoite = new Position(positionMonde.getRow()+1,positionMonde.getCol());
					board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
					positionBoite.printPosition();
					boardSauv[positionMonde.getRow()+1][positionMonde.getCol()].setContent(Cell.Me);
					boardSauv[positionMonde.getRow()+2][positionMonde.getCol()].setContent(Cell.Box);
					positionActuelle.setRow(positionMonde.getRow()+1);
					board=boardSauv;
					colNb=rowNb=board.length;
							//n oublie pas de modifier la liste des boites
					int i=getIndex(boxes,positionBoite);
					boxes.get(i).setRow(positionBoite.getRow()+1);
					boxes.get(i).setCol(positionMonde.getCol());
				}
			}	
			else {
			estMonde=false;
			deplacerDOWN();
			estMonde=true;
			}
		}
	}
	
	
	
	
	void deplacerRIGHT() { 
		if (positionActuelle.getCol()== colNb-1 && !estMonde) return ; 
		if( positionActuelle.getCol() < colNb-1) {// c-a-d on est dans la matrice
		// permier cas : un vide en dessus
			if(board[positionActuelle.getRow()][positionActuelle.getCol()+1].getContent().equals(Cell.Empty)&& board[positionActuelle.getRow()][positionActuelle.getCol()].getContent().equals(Cell.Me)) {
				board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
				positionActuelle.setCol(positionActuelle.getCol()+1);
				board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Me);
			}
			else  {
				if (board[positionActuelle.getRow()][positionActuelle.getCol()+1].getContent().equals(Cell.Wall)) return ;//si c un mur on ne faot rien
				else {
					if (board[positionActuelle.getRow()][positionActuelle.getCol()+1].getContent().equals(Cell.Box) || board[positionActuelle.getRow()][positionActuelle.getCol()+1].getContent().equals(Cell.BoxOnTarget) ) {//Faut traiter le cas d'une liste de boîtes
						//vérifier s'il y en a des boîtes en haut et puis faire le déplacement à la fois
						//on utilisera une boucle qui cherche sk y en a des boîtes et sk on est arrivé au mur ou si on est sur la bordure
						// et si on pousse une boite qui se trouve sa cible ?
					if(boardSauv!=null)    if( board.length!=boardSauv.length && positionActuelle.getCol()+2==colNb && board[positionActuelle.getRow()][positionActuelle.getCol()+1].getContent().equals(Cell.Box) && boardSauv[positionMonde.getRow()][positionMonde.getCol()+1].getContent().equals(Cell.Empty)  ) {
							//on sort la boite du monde
					    	
							Position positionBoite= new Position(positionActuelle.getRow(),positionActuelle.getCol()+1);
							board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
							positionActuelle.setCol(positionActuelle.getCol()+1);
							board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Me);
							boardSauv[positionMonde.getRow()][positionMonde.getCol()+1].setContent(Cell.Box);
							int i=getIndex(boxes,positionBoite);
							boxes.get(i).setRow(positionMonde.getRow());
							boxes.get(i).setCol(positionMonde.getCol()+1);
						}
					else {		//et le cas q"on on sort d'un monde et y a en dessus une boite après un vide donc on pourra pousser à partir du monde la boite
						//je traite le cas d'une seule boite :
						if(boardSauv[positionMonde.getRow()][positionMonde.getCol()+1].getContent().equals(Cell.Box) && boardSauv[positionMonde.getRow()][positionMonde.getCol()+2].getContent().equals(Cell.Empty)&& board.length==boardSauv.length ) {
							Position positionBoite= new Position(positionActuelle.getRow(),positionActuelle.getCol()+1);
							board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
							board[positionActuelle.getRow()][positionActuelle.getCol()+1].setContent(Cell.Me);
							board[positionActuelle.getRow()][positionActuelle.getCol()+2].setContent(Cell.Box);
							positionActuelle.setCol(positionActuelle.getCol()+1);
									//n oublie pas de modifier la liste des boites
							//on supprime de la liste des boites la boite avec l ancienne position pour la mettre a jour
							int i=getIndex(boxes,positionBoite);
							boxes.get(i).setRow(positionBoite.getRow());
							boxes.get(i).setCol(positionMonde.getCol()+1);
						}
						else if(positionActuelle.getCol()==colNb-1 && boardSauv[positionMonde.getRow()][positionMonde.getCol()+1].getContent().equals(Cell.Box)&& boardSauv[positionMonde.getRow()][positionMonde.getCol()+2].getContent().equals(Cell.Empty)&& board.length!=boardSauv.length ) {
							Position positionBoite= new Position(positionMonde.getRow(),positionMonde.getCol()+1);
							board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
							boardSauv[positionMonde.getRow()][positionMonde.getCol()+1].setContent(Cell.Me);
							boardSauv[positionMonde.getRow()][positionMonde.getCol()+2].setContent(Cell.Box);
							positionActuelle.setRow(positionMonde.getRow());
							positionActuelle.setCol(positionMonde.getCol()+1);
							board=boardSauv;
							int i=getIndex(boxes,positionBoite);							
							boxes.get(i).setRow(positionBoite.getRow());
							boxes.get(i).setCol(positionMonde.getCol()+1);
						}
						else {
						int col=positionActuelle.getCol()+1;
						
						ArrayList<Position> contBoites=new  ArrayList<Position>();
						while (col<board.length) {
							//cas juste une seule boite!
							if(board[positionActuelle.getRow()][col].getContent().equals(Cell.Box) /*|| board[ligne][positionActuelle.getCol()].getContent().equals(Cell.BoxOnTarget)*/) {
								//on peut créer une nouvelle liste qui comporte ces boîtes et qu'on modifiera
								//soit on ajoute a la liste au meme temps ou bien on ajoute a la fin
								
							contBoites.add(new Position(positionActuelle.getRow(),col));
							col++;
								}
							//traitement premiere boite et derniere boite sue une cible
							if(board[positionActuelle.getRow()][col].getContent().equals(Cell.Empty)) {
								//on fait le déplacement de tous les boîtes
								//on revient a la position actuelle et on change les modifications 
								board[positionActuelle.getRow()][positionActuelle.getCol()+1].setContent(Cell.Me);
								board[positionActuelle.getRow()][col].setContent(Cell.Box);
								//on doit faire les changement dans la liste des boîtes
								int indicePrm = boxes.indexOf(contBoites.get(0));
								boxes.set(indicePrm,new Position(positionActuelle.getRow(),col));
								if(board[positionActuelle.getRow()][positionActuelle.getCol()].getContent().equals(Cell.MeOnTarget)) {
									board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Target);
								}
								else board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
								positionActuelle.setCol(positionActuelle.getCol()+1);
								return;
							}
							else {
								if(board[positionActuelle.getRow()][col].getContent().equals(Cell.Target)) {
									//on traite le cas une cible est en haut 
									board[positionActuelle.getRow()][col].setContent(Cell.BoxOnTarget);// WORKS !!!
								}
							}
							//si c est un mur on arrête de chercher
							//on sort carrément de la méthode 
							if(board[positionActuelle.getRow()][col].getContent().equals(Cell.Wall)) return;
						}
						if((positionActuelle.getCol()+2)<colNb ) { 
							if(board[positionActuelle.getRow()][positionActuelle.getCol()+2].getContent().equals(Cell.Empty)) {
								board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
								board[positionActuelle.getRow()][positionActuelle.getCol()+1].setContent(Cell.Me);
								board[positionActuelle.getRow()][positionActuelle.getCol()+2].setContent(Cell.Box);
								positionActuelle.setCol(positionActuelle.getCol()+1);
							}//les autres cas on ne fait rien 	
							if ( (positionActuelle.getCol()+2)>colNb)
								if(board[positionActuelle.getRow()][positionActuelle.getCol()+2].getContent().equals(Cell.Target)) {
									board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
									board[positionActuelle.getRow()][positionActuelle.getCol()+1].setContent(Cell.Me);
									board[positionActuelle.getRow()][positionActuelle.getCol()+2].setContent(Cell.BoxOnTarget);
									positionActuelle.setCol(positionActuelle.getCol()+1);
								}
						}
					}
					}	
					}
					else {
						if(board[positionActuelle.getRow()][positionActuelle.getCol()+1].getContent().equals(Cell.Target)) {
							board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
							board[positionActuelle.getRow()][positionActuelle.getCol()+1].setContent(Cell.MeOnTarget);
							positionActuelle.setCol(positionActuelle.getCol()+1);
						}else {
							if(board[positionActuelle.getRow()][positionActuelle.getCol()].getContent().equals(Cell.MeOnTarget)) {
								if(board[positionActuelle.getRow()][positionActuelle.getCol()+1].getContent().equals(Cell.Empty)) {
									board[positionActuelle.getRow()][positionActuelle.getCol()+1].setContent(Cell.Me);
									board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Target);
									positionActuelle.setCol(positionActuelle.getCol()+1);
								}
							}
							else {
								if(board[positionActuelle.getRow()][positionActuelle.getCol()+1].getContent().equals(Cell.World)) {
									///on recherche une porte si on peut entrer 
									//dans la bordure en dessous
									positionMonde=new Position(positionActuelle.getRow(),positionActuelle.getCol()+1);
									Monde monde= lesMondes.get(new Position(positionActuelle.getRow(),positionActuelle.getCol()+1));
									Position p;
									if ( (p=trouverPorte(monde,1)) != null  ) {//on a trouvé une porte et on peut y accéder
										//on doit sauvegarder la position sur le board et on doit se déplacer sur le monde 
										board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
										estMonde=true;
										boardSauv=board;
										board=monde.getMatrice();
										colNb=rowNb=board.length;
										positionActuelle=p;
									    board[p.getRow()][p.getCol()].setContent(Cell.Me);
									}
								}else {// cas on est dans un monde et si on sorte du monde
									if( estMonde) {
										if (positionActuelle.getCol()==colNb-1 && positionMonde.getRow()<boardSauv.length-1) {
											//donc on peut sortir du monde 
											if(board[positionMonde.getRow()][positionMonde.getCol()+1].getContent().equals(Cell.Empty)) {
												positionActuelle.setCol(positionMonde.getCol());
												positionActuelle.setCol(positionMonde.getCol()+1);
											}
											else {//on bouge le monde comme une boite
												//on modifie sa position dans la liste des mondes 
												Position posMonde=positionMonde;
												board[positionMonde.getRow()][positionMonde.getCol()].setContent(Cell.Box);
												deplacerRIGHT();
												if(!posMonde.equals(new Position(posMonde.getRow(),posMonde.getCol()+1))) {
												board[positionMonde.getRow()][positionMonde.getCol()+1].setContent(Cell.World);
												//MAJ de la liste des world 
												Monde m= lesMondes.get(posMonde);
												lesMondes.remove(positionMonde);
												lesMondes.put(new Position(positionMonde.getRow(),positionMonde.getCol()+1), m);
												}
											}
										}
									}
								}
							}
						}
					}
				} 
			}	
		}
		else {
			//cas pour sortir d un monde
			if(positionActuelle.getCol()==colNb-1) {
				if(boardSauv[positionMonde.getRow()][positionMonde.getCol()+1].getContent().equals(Cell.Empty) || boardSauv[positionMonde.getRow()][positionMonde.getCol()].getContent().equals(Cell.Target) ){
					board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
					positionActuelle.setCol(positionMonde.getCol()+1);
					positionActuelle.setRow(positionMonde.getRow());
					board=boardSauv;
					colNb=rowNb=board.length;
					board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Me);
				}
				else if(boardSauv[positionMonde.getRow()][positionMonde.getCol()+1].getContent().equals(Cell.Box) && boardSauv[positionMonde.getRow()][positionMonde.getCol()+2].getContent().equals(Cell.Empty) ) {
					Position positionBoite = new Position(positionMonde.getRow(),positionMonde.getCol()+1);
					board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
					boardSauv[positionMonde.getRow()][positionMonde.getCol()+1].setContent(Cell.Me);
					boardSauv[positionMonde.getRow()][positionMonde.getCol()+2].setContent(Cell.Box);
					positionActuelle.setCol(positionMonde.getCol()+1);
					board=boardSauv;
					colNb=rowNb=board.length;
							//n oublie pas de modifier la liste des boites
					int i=getIndex(boxes,positionBoite);
					boxes.get(i).setRow(positionBoite.getRow());
					boxes.get(i).setCol(positionBoite.getCol()+1);
				}
			}	
			else {
			estMonde=false;
			deplacerRIGHT();
			estMonde=true;
			}
		}
	}
	
	void deplacerLEFT() { 
		if (positionActuelle.getCol()== 0 && !estMonde) return ; 
		if( positionActuelle.getCol() > 0) {// c-a-d on est dans la matrice
		// permier cas : un vide en dessus
			if(board[positionActuelle.getRow()][positionActuelle.getCol()-1].getContent().equals(Cell.Empty)&& board[positionActuelle.getRow()][positionActuelle.getCol()].getContent().equals(Cell.Me)) {
				board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
				positionActuelle.setCol(positionActuelle.getCol()-1);
				board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Me);
			}
			else  {
				if (board[positionActuelle.getRow()][positionActuelle.getCol()-1].getContent().equals(Cell.Wall)) return ;//si c un mur on ne faot rien
				else {
					if (board[positionActuelle.getRow()][positionActuelle.getCol()-1].getContent().equals(Cell.Box) || board[positionActuelle.getRow()][positionActuelle.getCol()-1].getContent().equals(Cell.BoxOnTarget) ) {//Faut traiter le cas d'une liste de boîtes
						//vérifier s'il y en a des boîtes en haut et puis faire le déplacement à la fois
						//on utilisera une boucle qui cherche sk y en a des boîtes et sk on est arrivé au mur ou si on est sur la bordure
						// et si on pousse une boite qui se trouve sa cible ?
						
						
						
						
					    if( board.length!=boardSauv.length && positionActuelle.getCol()-2==0 && board[positionActuelle.getRow()][positionActuelle.getCol()-1].getContent().equals(Cell.Box) && boardSauv[positionMonde.getRow()][positionMonde.getCol()-2].getContent().equals(Cell.Empty)  ) {
							//on sort la boite du monde
							/*
							if(board[positionActuelle.getRow()][positionActuelle.getCol()-2].getContent().equals(Cell.Empty)){
								boardSauv[positionMonde.getRow()][positionMonde.getCol()-2].setContent(Cell.Box);

							}*/
							Position positionBoite= new Position(positionActuelle.getRow(),positionActuelle.getCol()-1);
							board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
							positionActuelle.setCol(positionActuelle.getCol()-1);
							board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Me);
							boardSauv[positionMonde.getRow()][positionMonde.getCol()-1].setContent(Cell.Box);
							
							int i=getIndex(boxes,positionBoite);
							boxes.get(i).setRow(positionMonde.getRow());
							boxes.get(i).setCol(positionMonde.getCol()-1);
						}
					else {		//et le cas q"on on sort d'un monde et y a en dessus une boite après un vide donc on pourra pousser à partir du monde la boite
						//je traite le cas d'une seule boite :
						if(boardSauv[positionMonde.getRow()][positionMonde.getCol()-1].getContent().equals(Cell.Box) && boardSauv[positionMonde.getRow()][positionMonde.getCol()-2].getContent().equals(Cell.Empty)&& board.length==boardSauv.length ) {
							Position positionBoite= new Position(positionActuelle.getRow(),positionActuelle.getCol()-1);
							board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
							board[positionActuelle.getRow()][positionActuelle.getCol()-1].setContent(Cell.Me);
							board[positionActuelle.getRow()][positionActuelle.getCol()-2].setContent(Cell.Box);
							positionActuelle.setCol(positionActuelle.getCol()-1);
									//n oublie pas de modifier la liste des boites
							//on supprime de la liste des boites la boite avec l ancienne position pour la mettre a jour
							int i=getIndex(boxes,positionBoite);
							boxes.get(i).setRow(positionBoite.getRow());
							boxes.get(i).setCol(positionMonde.getCol()-1);
						}
						else if(positionActuelle.getCol()==0 && boardSauv[positionMonde.getRow()][positionMonde.getCol()-1].getContent().equals(Cell.Box)&& boardSauv[positionMonde.getRow()][positionMonde.getCol()-2].getContent().equals(Cell.Empty)&& board.length!=boardSauv.length ) {
							Position positionBoite= new Position(positionMonde.getRow(),positionMonde.getCol()-1);
							board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
							boardSauv[positionMonde.getRow()][positionMonde.getCol()-1].setContent(Cell.Me);
							boardSauv[positionMonde.getRow()][positionMonde.getCol()-2].setContent(Cell.Box);
							positionActuelle.setRow(positionMonde.getRow());
							positionActuelle.setCol(positionMonde.getCol()-1);
							board=boardSauv;
							int i=getIndex(boxes,positionBoite);							
							boxes.get(i).setRow(positionBoite.getRow());
							boxes.get(i).setCol(positionMonde.getCol()-1);

						}
						else {
						int col=positionActuelle.getCol()-1;
						ArrayList<Position> contBoites=new  ArrayList<Position>();
						while (col>0) {
							System.out.println("****************************************************************************************");
							//cas juste une seule boite!
							if(board[positionActuelle.getRow()][col].getContent().equals(Cell.Box) /*|| board[ligne][positionActuelle.getCol()].getContent().equals(Cell.BoxOnTarget)*/) {
								//on peut créer une nouvelle liste qui comporte ces boîtes et qu'on modifiera
								//soit on ajoute a la liste au meme temps ou bien on ajoute a la fin
								
							contBoites.add(new Position(positionActuelle.getRow(),col));
							col--;
								}
							
							//traitement premiere boite et derniere boite sue une cible
							System.out.println("==================================================================================col="+col);
							if(board[positionActuelle.getRow()][col].getContent().equals(Cell.Empty)) {
								//on fait le déplacement de tous les boîtes
								//on revient a la position actuelle et on change les modifications 
								board[positionActuelle.getRow()][positionActuelle.getCol()-1].setContent(Cell.Me);
								board[positionActuelle.getRow()][col].setContent(Cell.Box);
								//on doit faire les changement dans la liste des boîtes
								int indicePrm = boxes.indexOf(contBoites.get(0));
								boxes.set(indicePrm,new Position(positionActuelle.getRow(),col));
								if(board[positionActuelle.getRow()][positionActuelle.getCol()].getContent().equals(Cell.MeOnTarget)) {
									board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Target);
								}
								else board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
								positionActuelle.setCol(positionActuelle.getCol()-1);
								return;
							}
							else {
								if(board[positionActuelle.getRow()][col].getContent().equals(Cell.Target)) {
									//on traite le cas une cible est en haut 
									board[positionActuelle.getRow()][col].setContent(Cell.BoxOnTarget);// WORKS !!!
								}
							}
							//si c est un mur on arrête de chercher
							//on sort carrément de la méthode 
							if(board[positionActuelle.getRow()][col].getContent().equals(Cell.Wall)) return;
						}
						if((positionActuelle.getCol()-2)>=0 ) { 
							if(board[positionActuelle.getRow()][positionActuelle.getCol()-2].getContent().equals(Cell.Empty)) {
								board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
								board[positionActuelle.getRow()][positionActuelle.getCol()-1].setContent(Cell.Me);
								board[positionActuelle.getRow()][positionActuelle.getCol()-2].setContent(Cell.Box);
								positionActuelle.setCol(positionActuelle.getCol()-1);
							}//les autres cas on ne fait rien 	
							if ( (positionActuelle.getCol()-2)>-1)
								if(board[positionActuelle.getRow()][positionActuelle.getCol()-2].getContent().equals(Cell.Target)) {
									board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
									board[positionActuelle.getRow()][positionActuelle.getCol()-1].setContent(Cell.Me);
									board[positionActuelle.getRow()][positionActuelle.getCol()-2].setContent(Cell.BoxOnTarget);
									positionActuelle.setCol(positionActuelle.getCol()-1);
								}
						}
					}
					}	
					}
					else {
						if(board[positionActuelle.getRow()][positionActuelle.getCol()-1].getContent().equals(Cell.Target)) {
							board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
							board[positionActuelle.getRow()][positionActuelle.getCol()-1].setContent(Cell.MeOnTarget);
							positionActuelle.setCol(positionActuelle.getCol()-1);
						}else {
							if(board[positionActuelle.getRow()][positionActuelle.getCol()].getContent().equals(Cell.MeOnTarget)) {
								if(board[positionActuelle.getRow()][positionActuelle.getCol()-1].getContent().equals(Cell.Empty)) {
									board[positionActuelle.getRow()][positionActuelle.getCol()-1].setContent(Cell.Me);
									board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Target);
									positionActuelle.setCol(positionActuelle.getCol()-1);
								}
							}
							else {
								if(board[positionActuelle.getRow()][positionActuelle.getCol()-1].getContent().equals(Cell.World)) {
									///on recherche une porte si on peut entrer 
									//dans la bordure en dessous
									positionMonde=new Position(positionActuelle.getRow(),positionActuelle.getCol()-1);
									Monde monde= lesMondes.get(new Position(positionActuelle.getRow(),positionActuelle.getCol()-1));
									Position p;
									if ( (p=trouverPorte(monde,2)) != null  ) {//on a trouvé une porte et on peut y accéder
										//on doit sauvegarder la position sur le board et on doit se déplacer sur le monde 
										board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
										estMonde=true;
										boardSauv=board;
										board=monde.getMatrice();
										colNb=rowNb=board.length;
										positionActuelle=p;
									    board[p.getRow()][p.getCol()].setContent(Cell.Me);
									}
								}else {// cas on est dans un monde et si on sorte du monde
									if( estMonde) {
										if (positionActuelle.getCol()==0 && positionMonde.getRow()>0) {////DDDDDDDDDDDDDDDDDDDDDOOOOOOOOOOOUUUUUUUUUUUUUUUUUUUTTTTTTTTTTTTTEEEEEEEEEEEE
											//donc on peut sortir du monde 
											if(board[positionMonde.getRow()][positionMonde.getCol()-1].getContent().equals(Cell.Empty)) {
												positionActuelle.setCol(positionMonde.getCol());
												positionActuelle.setCol(positionMonde.getCol()-1);
											}
											else {//on bouge le monde comme une boite
												//on modifie sa position dans la liste des mondes 
												Position posMonde=positionMonde;
												board[positionMonde.getRow()][positionMonde.getCol()].setContent(Cell.Box);
												deplacerLEFT();
												if(!posMonde.equals(new Position(posMonde.getRow(),posMonde.getCol()-1))) {
												board[positionMonde.getRow()][positionMonde.getCol()-1].setContent(Cell.World);
												//MAJ de la liste des world 
												Monde m= lesMondes.get(posMonde);
												lesMondes.remove(positionMonde);
												lesMondes.put(new Position(positionMonde.getRow(),positionMonde.getCol()-1), m);
												}
											}
										}
									}
								}
							}
						}
					}
				} 
			}	
		}
		else {
			//cas pour sortir d un monde
			if(positionActuelle.getCol()==0) {
				if(boardSauv[positionMonde.getRow()][positionMonde.getCol()-1].getContent().equals(Cell.Empty) || boardSauv[positionMonde.getRow()][positionMonde.getCol()].getContent().equals(Cell.Target) ){
					board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
					positionActuelle.setCol(positionMonde.getCol()-1);
					positionActuelle.setRow(positionMonde.getRow());
					board=boardSauv;
					colNb=rowNb=board.length;
					board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Me);
				}
				else if(boardSauv[positionMonde.getRow()][positionMonde.getCol()-1].getContent().equals(Cell.Box) && boardSauv[positionMonde.getRow()][positionMonde.getCol()-2].getContent().equals(Cell.Empty) ) {
					Position positionBoite = new Position(positionMonde.getRow(),positionMonde.getCol()-1);
					board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
					boardSauv[positionMonde.getRow()][positionMonde.getCol()-1].setContent(Cell.Me);
					boardSauv[positionMonde.getRow()][positionMonde.getCol()-2].setContent(Cell.Box);
					positionActuelle.setCol(positionMonde.getCol()-1);
					board=boardSauv;
					colNb=rowNb=board.length;
							//n oublie pas de modifier la liste des boites
					int i=getIndex(boxes,positionBoite);
					boxes.get(i).setRow(positionBoite.getRow());
					boxes.get(i).setCol(positionBoite.getCol()-1);
				}
			}	
			else {
			estMonde=false;
			deplacerLEFT();
			estMonde=true;
			}
		}
	}
	
	
	void deplacerRIGHTancien() { // it wprks fine but modify the others to resemble to this , its same just small details 
	//traiter le cas boite sur la cible
		if (positionActuelle.getCol()==colNb-1) return ; //on est à la derniere colonne
		else {
			// permier cas : un vide à la droite
			if(board[positionActuelle.getRow()][positionActuelle.getCol()+1].getContent().equals(Cell.Empty) && board[positionActuelle.getRow()][positionActuelle.getCol()].getContent().equals(Cell.Me)) {
				//System.out.println("cas wall");
				board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
				positionActuelle.setCol(positionActuelle.getCol()+1);
				board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Me);
			}
			else  {
				if (board[positionActuelle.getRow()][positionActuelle.getCol()+1].getContent().equals(Cell.Wall)) return;
				else {
					if (board[positionActuelle.getRow()][positionActuelle.getCol()+1].getContent().equals(Cell.Box)){
						if((positionActuelle.getCol()+2)<=colNb ) {
							if(board[positionActuelle.getRow()][positionActuelle.getCol()+2].getContent().equals(Cell.Empty)) {
								board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
								board[positionActuelle.getRow()][positionActuelle.getCol()+1].setContent(Cell.Me);
								board[positionActuelle.getRow()][positionActuelle.getCol()+2].setContent(Cell.Box);
								positionActuelle.setCol(positionActuelle.getCol()+1);
							}//les autres cas on ne fait rien 	
						}
						//CAS UNE CIBLE A COTE DE LA BOITE  
						if ( (positionActuelle.getCol()+2)<colNb)
						if(board[positionActuelle.getRow()][positionActuelle.getCol()+2].getContent().equals(Cell.Target)) {
							board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
							board[positionActuelle.getRow()][positionActuelle.getCol()+1].setContent(Cell.Me);
							board[positionActuelle.getRow()][positionActuelle.getCol()+2].setContent(Cell.BoxOnTarget);
							positionActuelle.setCol(positionActuelle.getCol()+1);
						}
					}
					else {
						if(board[positionActuelle.getRow()][positionActuelle.getCol()+1].getContent().equals(Cell.Target)) {
							board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
							board[positionActuelle.getRow()][positionActuelle.getCol()+1].setContent(Cell.MeOnTarget);
							positionActuelle.setCol(positionActuelle.getCol()+1);
							
						}else {
							if(board[positionActuelle.getRow()][positionActuelle.getCol()].getContent().equals(Cell.MeOnTarget)) {
								if(board[positionActuelle.getRow()][positionActuelle.getCol()+1].getContent().equals(Cell.Empty)) {
									board[positionActuelle.getRow()][positionActuelle.getCol()+1].setContent(Cell.Me);
									board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Target);
									positionActuelle.setCol(positionActuelle.getCol()+1);
								}
								
							}
							else {
								System.out.println("cas world");
					//cas si c'est un World
							}
						}
					}
				} 
			}	
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//////////
	void deplacerDOWNancien() { 
		if (positionActuelle.getRow()==rowNb-1) return ; //on est à la premiere ligne
		if( positionActuelle.getRow()<rowNb) {
			if(board[positionActuelle.getRow()+1][positionActuelle.getCol()].getContent().equals(Cell.Empty)&& board[positionActuelle.getRow()][positionActuelle.getCol()].getContent().equals(Cell.Me)) {
				board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
				positionActuelle.setRow(positionActuelle.getRow()+1);
				board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Me);
			}
			else  {
				if (board[positionActuelle.getRow()+1][positionActuelle.getCol()].getContent().equals(Cell.Wall)) return ;
				else {
					if (board[positionActuelle.getRow()+1][positionActuelle.getCol()].getContent().equals(Cell.Box)) {
						if((positionActuelle.getRow()+2)<rowNb ) {
							if(board[positionActuelle.getRow()+2][positionActuelle.getCol()].getContent().equals(Cell.Empty)) {
								board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
								board[positionActuelle.getRow()+1][positionActuelle.getCol()].setContent(Cell.Me);
								board[positionActuelle.getRow()+2][positionActuelle.getCol()].setContent(Cell.Box);
								positionActuelle.setRow(positionActuelle.getRow()+1);
							}//les autres cas on ne fait rien 	
							if ( (positionActuelle.getRow()+2)<rowNb)
								if(board[positionActuelle.getRow()+2][positionActuelle.getCol()].getContent().equals(Cell.Target)) {
									board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
									board[positionActuelle.getRow()+1][positionActuelle.getCol()].setContent(Cell.Me);
									board[positionActuelle.getRow()+2][positionActuelle.getCol()].setContent(Cell.BoxOnTarget);
									positionActuelle.setRow(positionActuelle.getRow()+1);
								}
							
						}
						
					}
					else {
						//if(board[positionActuelle.getRow()-1][positionActuelle.getCol()].getContent().equals(Cell.Target)) {
						if(board[positionActuelle.getRow()+1][positionActuelle.getCol()].getContent().equals(Cell.Target)) {
							board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
							board[positionActuelle.getRow()+1][positionActuelle.getCol()].setContent(Cell.MeOnTarget);
							positionActuelle.setRow(positionActuelle.getRow()+1);
							
						}else {
							if(board[positionActuelle.getRow()][positionActuelle.getCol()].getContent().equals(Cell.MeOnTarget)) {
								if(board[positionActuelle.getRow()+1][positionActuelle.getCol()].getContent().equals(Cell.Empty)) {
									board[positionActuelle.getRow()+1][positionActuelle.getCol()].setContent(Cell.Me);
									board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Target);
									positionActuelle.setRow(positionActuelle.getRow()+1);
								}
								
							}
							else {
								System.out.println("cas world");
					//cas si c'est un World
							}
						}
						
					}
				} 
			}	
		}
	}
	void deplacerDOWN1() { //it works fine 
		if (positionActuelle.getRow()==rowNb-1) return ; //on est à la premiere ligne
		if( positionActuelle.getRow()<rowNb-1) {
			// permier cas : un vide en dessous
			if(board[positionActuelle.getRow()+1][positionActuelle.getCol()].getContent().equals(Cell.Empty) && board[positionActuelle.getRow()][positionActuelle.getCol()].getContent().equals(Cell.Me)) {
				board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
				positionActuelle.setRow(positionActuelle.getRow()+1);
				board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Me);
			}
			else  {
				if (board[positionActuelle.getRow()+1][positionActuelle.getCol()].getContent().equals(Cell.Wall)) return ;
				else {
					if (board[positionActuelle.getRow()+1][positionActuelle.getCol()].getContent().equals(Cell.Box)) {
						if((positionActuelle.getRow()+2)>=rowNb ) {
							if(board[positionActuelle.getRow()+2][positionActuelle.getCol()].getContent().equals(Cell.Empty)) {
								board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
								board[positionActuelle.getRow()+1][positionActuelle.getCol()].setContent(Cell.Me);
								board[positionActuelle.getRow()+2][positionActuelle.getCol()].setContent(Cell.Box);
								positionActuelle.setRow(positionActuelle.getRow()+1);
							}//les autres cas on ne fait rien 	
						}
						
					}
					else {
						//if(board[positionActuelle.getRow()+1][positionActuelle.getCol()].getContent().equals(Cell.Target)) {
						if(board[positionActuelle.getRow()+1][positionActuelle.getCol()].getContent().equals(Cell.Target)) {
							board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
							board[positionActuelle.getRow()+1][positionActuelle.getCol()].setContent(Cell.MeOnTarget);
							positionActuelle.setRow(positionActuelle.getRow()+1);
							
						}else {
							if(board[positionActuelle.getRow()][positionActuelle.getCol()].getContent().equals(Cell.MeOnTarget)) {
								if(board[positionActuelle.getRow()+1][positionActuelle.getCol()].getContent().equals(Cell.Empty)) {
									board[positionActuelle.getRow()+1][positionActuelle.getCol()].setContent(Cell.Me);
									board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Target);
									positionActuelle.setRow(positionActuelle.getRow()+1);
								}
								
							}
							else {
								System.out.println("cas world");
					//cas si c'est un World
							}
						}
					}
				} 
			}	
		}
	} 
	void deplacerLEFTancien() {
		if (positionActuelle.getCol()==0) return ; //on est à la derniere colonne
		else {
			// permier cas : un vide à la gauche
			if(board[positionActuelle.getRow()][positionActuelle.getCol()-1].getContent().equals(Cell.Empty)) {
				board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
				positionActuelle.setCol(positionActuelle.getCol()-1);
				board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Me);
			}
			else  {
				if (board[positionActuelle.getRow()][positionActuelle.getCol()-1].getContent().equals(Cell.Wall)) return;
				else {
					if (board[positionActuelle.getRow()][positionActuelle.getCol()-1].getContent().equals(Cell.Box)){
						if((positionActuelle.getCol()-2)>=0 ) {
							if(board[positionActuelle.getRow()][positionActuelle.getCol()-2].getContent().equals(Cell.Empty)) {
								board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
								board[positionActuelle.getRow()][positionActuelle.getCol()-1].setContent(Cell.Me);
								board[positionActuelle.getRow()][positionActuelle.getCol()-2].setContent(Cell.Box);
								positionActuelle.setCol(positionActuelle.getCol()-1);
							}//les autres cas on ne fait rien 
							if ( (positionActuelle.getCol()-2)>-1)
								if(board[positionActuelle.getRow()][positionActuelle.getCol()-2].getContent().equals(Cell.Target)) {
									board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
									board[positionActuelle.getRow()][positionActuelle.getCol()-1].setContent(Cell.Me);
									board[positionActuelle.getRow()][positionActuelle.getCol()-2].setContent(Cell.BoxOnTarget);
									positionActuelle.setCol(positionActuelle.getCol()-1);
								}
						}
					}
					else {
						//if(board[positionActuelle.getRow()][positionActuelle.getCol()-1].getContent().equals(Cell.Target)) {
						if(board[positionActuelle.getRow()][positionActuelle.getCol()-1].getContent().equals(Cell.Target)) {
							board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Empty);
							board[positionActuelle.getRow()][positionActuelle.getCol()-1].setContent(Cell.MeOnTarget);
							positionActuelle.setCol(positionActuelle.getCol()-1);
							
						}else {
							if(board[positionActuelle.getRow()][positionActuelle.getCol()].getContent().equals(Cell.MeOnTarget)) {
								if(board[positionActuelle.getRow()][positionActuelle.getCol()-1].getContent().equals(Cell.Empty)) {
									board[positionActuelle.getRow()][positionActuelle.getCol()-1].setContent(Cell.Me);
									board[positionActuelle.getRow()][positionActuelle.getCol()].setContent(Cell.Target);
									positionActuelle.setCol(positionActuelle.getCol()-1);
								}
								
							}
							else {
								System.out.println("cas world");
					//cas si c'est un World
							}
						}
					}
				} 
			}	
		}
		
		
	}

    
    /**
     * Retourne le nombre de la ligne
     * @return le nombre de ligne du tableau
     */
    public int getNbRows(){
        return this.rowNb;
    }
    
    /**
     * Retourne le nombre de la colonne
     * @return le nombre de colonne du tableau
     */
    public int getNbCols(){
        return this.colNb;
    }
     
    /**
     * Retourne la case en fonction de sa ligne et sa colonne
     * @param row, ligne de la case
     * @param col, colonne de la case
     * @return la case demander
     */
    public Cell getCase(int row, int col){
        return this.board[row-1][col-1].getContent();
    }
            
    /**
     * Initialise la couleur d'une case
     * @param row, ligne de la case
     * @param col, colonne de la case
     * @param content, couleur voulu
     */
    public void setCase(int row, int col, Cell content){ 
        this.board[row-1][col-1].setContent(content);
    }
    
    /**
     * Permet d'afficher le plateau de jeu
     */
    public void displayBoard(){
        
        //affichage de toutes les cases
        System.out.println("");
        for(int i = 0 ; i < this.rowNb  ; i++) //à modifier
        {          
            for(int j = 0 ; j < this.colNb; j++)
            {
                System.out.print(board[i][j].displayCase());
            }
            System.out.println("");
        }
        
    }
    
 
    /**
     * Dessine une ligne verticale d'une certaine longueur
     * @param start, le point de départ
     * @param length, la longueur de la ligne
     */
   /* public void drawVerticalWall(Case start, int length){
        
        for(int i = start.getRow(); i < start.getRow()+length; i++){
            if(i<=this.getNbRows()){this.setCase(i, start.getCol(), Cell.Wall);}
            else{break;}
        }
    }*/
    
    /**
     * Dessine une ligne horizontale d'une certaine longueur
     * @param start, le point de départ
     * @param length, la longueur de la ligne
     */
  /*  public void drawHorizontalWall(Case start, int length){
        
        for(int i = start.getCol(); i < start.getCol()+length; i++){
            if(i<=this.getNbCols()){
            	this.setCase(start.getRow(), i, Cell.Wall);
            }
            else{
            	break;
            }
        }
    }
    */
    /**
     * Rajoute une boîte
     * @param row, la ligne
     * @param col, la colonne
     */
    public void addBox(int row, int col){
        if(row<this.rowNb+1 && col<this.colNb+1 && row > 0 && col > 0){this.setCase(row, col, Cell.Box);}
    } 
    
    /**
     * Rajoute une cible
     * @param row, la ligne
     * @param col, la colonne
     */
    public void addTarget(int row, int col){
        if(row<this.rowNb+1 && col<this.colNb+1 && row > 0 && col > 0){
        	this.setCase(row, col, Cell.Target);
        }
    }
    
    //une fonction qui calcule le nombre de lignes d'une chaine de caracteres 
    static int nbLignes( String s ) {
    	int cpt=0;
    	for ( int i = 0 ;i<s.length();i++) {
    		if( s.charAt(i)=='\n') cpt++;
    	}
    	return cpt;
    }
    //une methode qui construit la matrice de cases à partir d'une matrice de char 
    Case[][] const_brd ( char[][] data,int n){
    	
    	Case[][] monBoard= new Case[n][n];
    	for (int i=0;i<n;i++) {
    		for(int j=0;j<n;j++) {
    			monBoard[i][j]=cas(data[i][j],i,j);
    		}
    	}
    	return monBoard;
    }
    public void afficherCibles() {
    	for (Position p: cibles) {
    		p.printPosition();
    	}
    	
    }
    
    public void afficherBoxes() {
    	for (Position p: boxes) {
    		p.printPosition();
    	}
    	
    }
    public Case cas(char c , int row,int col) {
		Case cellule = new Case();
		 switch(c) {
	  		case ' ':cellule.setContent(Cell.Empty);break;
	  		case '#':cellule.setContent(Cell.Wall);break;
	  		case 'B':cellule.setContent(Cell.Box);
	  			boxes.add(new Position(row,col));
	  			break;
	  		case 'A':cellule.setContent(Cell.Me);
	  				this.setPositionActuelle(new Position(row,col));
	  			break;
	  		case '@':cellule.setContent(Cell.Target);
  				cibles.add(new Position(row,col));
	  			break;
	  		case 'a':cellule.setContent(Cell.MeOnTarget);break;
	  		case 'b':cellule.setContent(Cell.BoxOnTarget);break;
	  		default :cellule.setContent(Cell.World); break;
	  }
		 return cellule;
	}
    
    /*
public   void initWorld() {//cette fonction va creer un niveau a partir d'un fichier 
	
	this.setLevel(chargementNiveau("niveau2.txt"));
	
	  Case cellule=  new Case();
	  int row=0,col=0;
	  for ( int i=0;i<(level.length());i++) {
		  char c = level.charAt(i);
		  if(i%(rowNb+2)>colNb-1) { continue;}//on saute les sauts de lignes 
		 // System.out.print("*"+c+"*");
		 
		  switch(c) {
		  		case ' ':cellule.setContent(Cell.Empty);break;
		  		case '#':cellule.setContent(Cell.Wall);break;
		  		case 'B':cellule.setContent(Cell.Box);
		  			boxes.add(new Position(row,col));
		  			break;
		  		case 'A':cellule.setContent(Cell.Me);break;
		  		case '@':cellule.setContent(Cell.Target);
		  				cibles.add(new Position(row,col));
		  				break;
		  		case 'a':cellule.setContent(Cell.MeOnTarget);break;
		  		case 'b':cellule.setContent(Cell.BoxOnTarget);break;
		  		//case '\n':break;
		  		default : //cas d'un monde
		  			cellule.setContent(Cell.World);
		  			
		  			// A CONTINUER ..............................................................................
		  			break;
		  }
		  //gestion du replissage de la matrice
		   
		/*  if(row==0 && col==0) {
		  board[0][0].setContent(cellule.getContent());  
		  
		  }
		  else {*/
		 /*
		  if(col<colNb) {
			  board[row][col].setContent(cellule.getContent());
			  if(cellule.getContent().equals(Cell.Me) ) {
				  positionActuelle.setCol(col);
				  positionActuelle.setRow(row);
			  }
			   col++;
			  
		  }
		  if(col>=colNb) {
			  col=0;
			  row++;
		  }
		//  if( row==rowNb) break;
		 // }
		 //  System.out.print("-"+cellule.displayCase()+"-");
		  
		  
	  }
			 
    }
*/
    public void deplacerUtilisateur(Position pos) {
	    
        Position currentPos = getPositionActuelle();
        //Position nextPos = positions.get(i+1);
        
        if (pos.getRow() < currentPos.getRow()) {
            deplacerUP();
        } else if (pos.getRow() > currentPos.getRow()) {
             deplacerDOWN();
        } else if (pos.getCol() < currentPos.getCol()) {
            deplacerLEFT();
        } else if (pos.getCol() > currentPos.getCol()) {
            deplacerRIGHT();
        }
    }

    
}

