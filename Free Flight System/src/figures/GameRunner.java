package figures;

import javax.media.opengl.GLCapabilities;
import javax.swing.JFrame;

public class GameRunner {

	public static void main(String[] args) {
		JFrame window = new JFrame("Cube");
		GLCapabilities caps = new GLCapabilities(null);
		Sphere panel = new Sphere(caps);
		window.setContentPane(panel);
		window.pack();
		window.setLocation(50, 50);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		panel.requestFocusInWindow();
	}
}