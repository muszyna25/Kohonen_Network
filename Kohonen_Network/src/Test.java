
public class Test {

	static int WIDTH = 100;
	static int HEIGHT = 100;
	static int NEURON_SIZE = 3;
	static int ITERATION = 100;
	static int NB_COLORS = 15;
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	//public static void main(String[] args) throws IOException {
	public static void main(final String[] args) {
		KFrame win = new KFrame(WIDTH, HEIGHT, NEURON_SIZE, ITERATION, NB_COLORS);
		//win.setTitle("TP6");
		win.setVisible(true);
		win.setLocationRelativeTo(null); // Put window on center of screen
		win.setDefaultCloseOperation(KFrame.EXIT_ON_CLOSE);	
	}

}
