package figures;

import java.awt.event.KeyEvent;

public class Ship implements Runnable {

	private float xSpeed, ySpeed, zSpeed;
	private Sphere sphere;

	@Override
	public void run() {
		
		long previous = System.currentTimeMillis();
		while(true) {
			long current = System.currentTimeMillis();
			
			if (current - previous > (1000 / 30)) {
				sphere.rotateX += xSpeed;
				sphere.rotateY += ySpeed;
				sphere.rotateZ += zSpeed;
				
				sphere.repaint();
				previous = current;
			}
			
		}
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT)
			ySpeed -= 1;
		else if (key == KeyEvent.VK_RIGHT)
			ySpeed += 1;
		else if (key == KeyEvent.VK_DOWN)
			xSpeed += 1;
		else if (key == KeyEvent.VK_UP)
			xSpeed -= 1;
		else if (key == KeyEvent.VK_PAGE_UP)
			zSpeed += 1;
		else if (key == KeyEvent.VK_PAGE_DOWN)
			zSpeed -= 1;
		else if (key == KeyEvent.VK_HOME)
			xSpeed = ySpeed = zSpeed = 0;
	}

	public void setSphere(Sphere sphere) {
		this.sphere = sphere;
	}

}
