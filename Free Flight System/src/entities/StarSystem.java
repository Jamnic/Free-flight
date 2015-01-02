package entities;

import java.io.File;
import java.io.IOException;

import javax.media.opengl.GL2;

import engine.SkyBox;
import figures.Sphere;

public class StarSystem {

	private Sphere earth;
	private Sphere sun;
	private Sphere uranus;
	private Sphere neptune;
	private Sphere jupiter;
	private float earthRotation = 0f;
	private float sunRotation = 0f;

	private SkyBox skyBox;

	public void init(GL2 gl) throws IOException {
		sun = new Sphere(gl, new File("src/texture_sun.png"), 696.42f, 0.1);
		earth = new Sphere(gl, new File("src/earth.png"), 60.80f, 0.14);
		uranus = new Sphere(gl, new File("src/uranus.png"), 255.50f, 0.08);
		neptune = new Sphere(gl, new File("src/neptune.png"), 247.50f, 0.1);
		jupiter = new Sphere(gl, new File("src/jupiter.png"), 710.00f, 0.2);
		skyBox = new SkyBox(new Sphere(gl, new File("src/space.png"), 100000f, 0.0));
	}

	public void sunSystem(GL2 gl) {

		float sunDistance = 3000f;
		
		gl.glPushMatrix();

		float rotation = earthRotation += 0.3;

		gl.glTranslatef(sunDistance, 0, 0);
		gl.glRotated(rotation, 0, 1, 0);
		sun.draw(gl);

		gl.glPushMatrix();

		earthLight(gl, sunDistance);
		gl.glRotatef(90, 1, 0, 0);
		gl.glTranslated(1000 * Math.sin(rotation / 500), 1000 * Math.cos(rotation / 500), 0);
		earth.draw(gl);

		gl.glPopMatrix();

		gl.glPushMatrix();

		earthLight(gl, sunDistance);
		gl.glRotatef(90, 1, 0, 0);
		gl.glTranslated(10000 * Math.sin(rotation / 500), 10000 * Math.cos(rotation / 500), 0);
		jupiter.draw(gl);

		gl.glPopMatrix();

		gl.glPushMatrix();

		earthLight(gl, sunDistance);
		gl.glRotatef(90, 1, 0, 0);
		gl.glTranslated(12000 * Math.sin(rotation / 500), 12000 * Math.cos(rotation / 500), 0);
		uranus.draw(gl);

		gl.glPopMatrix();

		gl.glPushMatrix();

		earthLight(gl, sunDistance);
		gl.glRotatef(90, 1, 0, 0);
		gl.glTranslated(14000 * Math.sin(rotation / 500), 14000 * Math.cos(rotation / 500), 0);
		neptune.draw(gl);

		gl.glPopMatrix();
	}

	private void earthLight(GL2 gl, float sunDistance) {
		float[] lightColorAmbient2 = { 0.01f, 0.01f, 0.01f, 1f };

		float SHINE_ALL_DIRECTIONS = 1;
		float[] lightPos = { sunDistance, 0, 0, SHINE_ALL_DIRECTIONS };
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_POSITION, lightPos, 0);
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_AMBIENT, lightColorAmbient2, 0);
	}
	
	public void drawSkyBox(GL2 gl) {
		skyBox.draw(gl);
	}

}
