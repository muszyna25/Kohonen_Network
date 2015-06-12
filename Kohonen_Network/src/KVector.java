import java.util.Vector;

public class KVector extends Vector<Double> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int x, y;
	
	int getX(){ return x; }
	int getY(){ return y; }
	void setLocation(int x, int y) { 
		this.x = x; 
		this.y = y;
	}

}
