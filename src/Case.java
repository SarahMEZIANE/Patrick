
public class Case {
//	private Position pos;
    private Cell content;
    
  
    
    /**
     * Constructeur de la classe Case
     * @param row, ligne de la case
     * @param col, colonne de la case
     */
    public Case(){
        //this.pos.row=row;
        //this.pos.col=col;
        this.content = Cell.Empty;
    }
   
    
    /**
     * Initialise un contenu pour une instance de la classe
     * @param content 
     */
    public void setContent(Cell content){
        this.content = content;
    }
    
   /* public boolean isEmpty(Case c) {
    	if(c.content.equals(Empty)) {
    		
    	}
    }*/
    /**
     * Permet d'obtenir le contenu de la case qui nous interesse 
     * @return le contenu de la case 
     */
    public  Cell getContent(){
        return this.content;
    }
    
   
    /**
     * Getteur permetant d'obtenir la ligne
     * @return le nombre de la ligne où se situe la case
     */
  /*  public int getRow(){
        return this.pos.row;
    }*/
    
    /**
     * Getteur permettant d'obtenir la colonne
     * @return le nombre de colonne où se situe la case
     */
   /* public int getCol(){
        return this.pos.col;
    }*/
    
    /**
     * Affiche une case
     * @param row, la ligne
     * @param col, la colonne
     * @return, ce qui doit être affiché
     */
     char displayCase(){
        
        switch(content){
            case Wall :
                return '#';
            case Me :
                return 'A';
            case Box :
                return 'B';
            case Target :
                return '@';
            case Empty :
            	  return '_';
            case MeOnTarget:
            	return 'a';
            case BoxOnTarget:
            	return 'b';
            case World :
            	return ' ' ;
            default :
                return ' ';
        }  
    }

	public Case(Cell content) {
		super();
		this.content = content;
	}
	 public boolean equals(Object obj) {
	        if (content == null) {
	            return false;
	        }
	        Case other=(Case) obj;
	        if(this.content.eqals(other.content)) {
	        	return true;
	        }
	        return false;
	    }
    
}
