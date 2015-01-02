package engine;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Ship implements Runnable {

	private float xRotate, yRotate, zRotate;
	private DrawEngine sphere;
	private float xTranslate, yTranslate, zTranslate;

	@Override
	public void run() {

		long previous = System.currentTimeMillis();
		while (true) {
			long current = System.currentTimeMillis();

			if (current - previous > (1000 / 30)) {
				sphere.rotateX += xRotate;
				sphere.rotateY += yRotate;
				sphere.rotateZ += zRotate;
				sphere.translateX += xTranslate;
				sphere.translateY += yTranslate;
				sphere.traslateZ += zTranslate;

				sphere.repaint();
				previous = current;
			}

		}
	}

	public void mouseMovent(MouseEvent e, int width, int height) {
		int centerWidth = width / 2;
		int centerHeight = height / 2;
		xRotate = (e.getX() - centerWidth) / 500f;
		yRotate = (e.getY() - centerHeight) / 500f;

		System.out.println(xRotate + " " + yRotate);
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_S) {
			if (xTranslate != 10)
				xTranslate += 1;
		} else if (key == KeyEvent.VK_W) {
			if (xTranslate != -10)
				xTranslate -= 1;
		} else if (key == KeyEvent.VK_A) {
			if (zTranslate != 10)
				zTranslate += 1;
		} else if (key == KeyEvent.VK_D) {
			if (zTranslate != -10)
				zTranslate -= 1;
		} else if (key == KeyEvent.VK_F) {
			if (yTranslate != 10)
				yTranslate += 1;
		} else if (key == KeyEvent.VK_R) {
			if (yTranslate != -10)
				yTranslate -= 1;
		} else if (key == KeyEvent.VK_HOME) {
			xRotate = yRotate = zRotate = 0;
			xTranslate = yTranslate = zTranslate = 0;
		}
	}

	public void setSphere(DrawEngine sphere) {
		this.sphere = sphere;
	}
}