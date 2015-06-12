import java.awt.Container;

public class KFrame extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public KFrame(int width, int height, int neuronSize, int iterations, int nbColors){
		super();
		Container content = this.getContentPane();
		content.setLayout(new java.awt.BorderLayout());
		KPanel panel = new KPanel(width, height, neuronSize, iterations, nbColors);
		panel.setVisible(true);
		content.add(panel);
		this.pack();
	}
	
	
}
