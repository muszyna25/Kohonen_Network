import java.awt.Point;
import java.util.ArrayList;

public class KohonenMap {
	ArrayList<ArrayList<Neuron>> map;
	int width, height;
	double neighbourhood0, timeConstant, learningRate0 = 0.07;
	
	/**
	 * Create an initialized Kohonen map.
	 * 
	 * @param width     Width of the map.
	 * @param height    Height of the map.
	 * @param neuronDim Dimension of the neurons.
	 */
	public KohonenMap(int width, int height, int neuronDim) {		
		this.width = width;
		this.height = height;
		this.neighbourhood0 = Math.max(width, height) / 2;
		this.map = new ArrayList<ArrayList<Neuron>>();
		this.init(neuronDim);
	}
	
	/**
	 * Initialize the map with new neurons.
	 * 
	 * @param neuronDim Neurons dimension.
	 */
	public void init(int neuronDim) {
		map.clear();
		for	(int i = 0; i < height; i++) {
			map.add(i, new ArrayList<Neuron>());
			for (int j = 0; j < width; j++){
				Neuron n = new Neuron();
				for (int d = 0; d < neuronDim; d++)
					n.add(Math.random());
				map.get(i).add(j, n);
			}
		}
	}

	/**
	 * Compute the map with Kohonen algorithm for a given set of vectors.
	 * 
	 * @param iterations
	 * @param vectors
	 */
	public void compute(int iterations, ArrayList<KVector> vectors) {
		
		timeConstant = iterations / Math.log(neighbourhood0);
		
		for	(int i = 0; i < iterations; i++) {
			double radius = getNeighbourhoodRadius(i);
			double learningRate = getLearningRate(i, iterations);
			
			for(KVector v : vectors) {			
				// Get BMU and set the neuron at this position
				Point bmu = getBmu(v);
				int bmuX = (int)bmu.getX();
				int bmuY = (int)bmu.getY();
				v.setLocation(bmuX, bmuY);
				
				// Mix the BMU neighbors (BMU included) with the neuron
				int yMin = getValidMinIndex((int)(bmuY-radius), 0);
				int yMax = getValidMaxIndex((int)(bmuY+radius), height);
				
				for (int y = yMin; y < yMax; y++) {
					int yPos = y - bmuY;
					int yPosSquare = yPos*yPos;
					double radiusSquare = radius*radius;
					int bound = (int)Math.sqrt(radiusSquare - yPosSquare);
					ArrayList<Neuron> line = map.get(y);
					
					int xMin = getValidMinIndex(bmuX-bound, 0);
					int xMax = getValidMaxIndex(bmuX+bound, width);
					
					
					for (int x = xMin ; x < xMax; x++) {
						Neuron neighbor = line.get(x);
						if(neighbor != null){
							int xPos = x - bmuX;
							double dist = Math.sqrt(xPos*xPos + yPosSquare);
							double magnitude = getInfluence(dist, radiusSquare) * learningRate;
							neighbor.mix(v, magnitude);
						}
					}
				}
			}
		}
	}
	
	public Neuron get(int x, int y) {
		return map.get(y).get(x);
	}

	
	/**
	 * Search the Best Matching Unit for a given vector in the map.
	 * 
	 * @param v Vector to match with a neuron on the map.
	 * @return
	 */
	private Point getBmu(KVector v){
		Double distMin = null;
		Point bmu = new Point();
		
		for	(int y = 0; y < map.size(); y++) {
			ArrayList<Neuron> line = map.get(y);
			for(int x = 0; x < map.size(); x++) {
				double dist = line.get(x).getDistance(v);
				if(distMin == null || dist < distMin) {
					distMin = dist;
					bmu.setLocation(x, y);
				}
			}
		}
		return bmu;
	}
	
	private int getValidMinIndex(int index, int min) { 
		return index < min ? min : index;
	}
	
	private int getValidMaxIndex(int index, int max) {
		return index > max ? max : index;
	}
	
	private double getNeighbourhoodRadius(int iteration) {
		return neighbourhood0 * Math.exp(-iteration/timeConstant);
	}
	
	private double getLearningRate(int iteration, int iterations) {
		return learningRate0 * Math.exp(-iteration/(double)iterations);
	}
	
	private double getInfluence(double dist, double radiusSquare) {
		return Math.exp(-dist*dist / (2 * radiusSquare));
	}
	
}
