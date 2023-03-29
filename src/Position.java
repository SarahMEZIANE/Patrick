
public class Position {
	 /**
     * Nombre de colonnes
     */
    public int col;

    /**
     * nombre de lignes
     */
    public int row;
    
    private boolean visitee;
    private Position parent;

    /**
     * constructeur de la position en fonctuion de la ligne et de la colonnes
     * @param row
     * @param col
     */
    public Position(int row, int col) {
        this.col = col;
        this.row= row;
    }
    void printPosition() {
    	System.out.print("(x="+row+",y="+col+")\n");
    	
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.col;
        hash = 59 * hash + this.row;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Position other = (Position) obj;
        if (this.col != other.col) {
            return false;
        }
        if (this.row!= other.row) {
            return false;
        }
        return true;
    }

	public Position() {
		super();
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}
	public boolean estVisitee() {
		return visitee;
	}
	
	public void setVisitee(boolean visitee) {
	        this.visitee = visitee;
	}
	
	public Position getParent() {
		return parent;
	}
	
	public void setParent(Position parent) {
		this.parent=parent;
	}
}
