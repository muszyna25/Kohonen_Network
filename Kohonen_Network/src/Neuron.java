import java.util.Vector;

public class Neuron extends Vector<Double> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public double getDistance(KVector v) {
		double d = 0;
		for (int i = 0; i < this.size(); i++)
			d += Math.pow(this.get(i) - v.get(i), 2);
		return Math.sqrt(d);
	}
	
	public void mix(KVector v, double magnitude){
		for (int i = 0; i < this.size(); i++)
			this.set(i, this.get(i) + magnitude * (v.get(i) - this.get(i)));
	}		
}
