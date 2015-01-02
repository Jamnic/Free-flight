package engine;

import javax.media.opengl.GL2;

import figures.Sphere;

public class SkyBox {

	private Sphere skyBox;

	public SkyBox(Sphere skyBox) {
		this.skyBox = skyBox;
	}

	public void draw(GL2 gl) {
		gl.glDepthMask(false);
		skyBox.draw(gl);
		gl.glDepthMask(true);
	}

}
