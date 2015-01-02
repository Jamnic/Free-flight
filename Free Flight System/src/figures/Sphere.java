package figures;

import java.io.File;
import java.io.IOException;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLUquadric;

public class Sphere extends Figure {

	private double rotation;
	private double rotationSpeed;

	public Sphere(GL2 gl, File textureFile, float radius, double rotationSpeed) throws IOException {
		super(gl, textureFile, radius);

		this.rotationSpeed = rotationSpeed;
	}

	@Override
	protected void createFigure(GLUquadric quadratic, GL2 gl) {
		gl.glRotated(rotation += rotationSpeed, 0, 0, 1);
		glu.gluSphere(quadratic, radius, 64, 64);
	}
}