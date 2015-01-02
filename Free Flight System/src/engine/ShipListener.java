package engine;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class ShipListener implements MouseMotionListener {

	private DrawEngine stellar;

	public ShipListener(DrawEngine stellar) {
		this.stellar = stellar;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		stellar.mouseMoved(e);
	}

}
