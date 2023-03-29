
public enum Cell {
	Me,
	Box,
	Wall,
	Empty,
	World,
	Target,
	MeOnTarget,
	BoxOnTarget,
	RECURSIFWORLD;
	
	public boolean eqals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Cell)) {
            return false;
        }
        Cell other = (Cell) obj;
        return this.name().equals(other.name());
    }
}
