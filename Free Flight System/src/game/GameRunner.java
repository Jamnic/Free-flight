package game;

import javax.media.opengl.GLCapabilities;
import javax.swing.JFrame;

import engine.ShipListener;
import engine.DrawEngine;

public class GameRunner {

	public static void main(String[] args) {
		JFrame window = new JFrame("Cube");
		GLCapabilities caps = new GLCapabilities(null);
		DrawEngine panel = new DrawEngine(caps);
		window.setContentPane(panel);
		window.pack();
		window.setLocation(50, 50);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		ShipListener mouseListener = new ShipListener(panel);
		window.addMouseMotionListener(mouseListener);
		panel.requestFocusInWindow();
	}
}