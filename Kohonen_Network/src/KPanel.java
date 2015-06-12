import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;


public class KPanel extends javax.swing.JPanel {

	private static final long serialVersionUID = -9151597672890564219L;
	int width, height, neuronSize;

	KohonenMap map;
	ArrayList<KVector> vectors;
	final int MARGIN = 10;
	
	public KPanel(int width, int height, int neuronSize, int iterations, int nbColors){
		this.width = width;
		this.height = height;
		this.neuronSize = neuronSize;
		
		vectors = new 	ArrayList<KVector>();
		for (int i = 0; i < nbColors; i++){
			KVector v = new KVector();
					
			v.add(Math.random());
			v.add(Math.random());
			v.add(Math.random());
			
			vectors.add(v);
		}
		
		map = new KohonenMap(width, height, 3);
		map.compute(iterations, vectors);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(width*neuronSize+MARGIN*2, height*neuronSize+MARGIN*2);
	}
	
	@Override
	public void paint(Graphics gr){
		super.paint(gr);
		for (int y = 0; y < height; y++)
			for (int x = 0; x < width; x++) {
				Neuron n = map.get(x, y);
				float r = (float)(n.get(0).doubleValue());
				float g = (float)(n.get(1).doubleValue());
				float b = (float)(n.get(2).doubleValue());
				gr.setColor(new Color(r, g, b));
				gr.fillRect(x * neuronSize + MARGIN, 
						    y * neuronSize + MARGIN, 
						    neuronSize, neuronSize);
			}
		
		Integer i = 0;
		
		gr.setColor(Color.black);
		for(KVector v : vectors){
			int x = v.getX()*neuronSize+MARGIN;
			int y = v.getY()*neuronSize+MARGIN;
			gr.drawString((i++).toString(), x, y);
		}
	}

	
}
