package figures;

import java.io.File;
import java.io.IOException;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLUquadric;

public class Sphere extends Figure {

	public Sphere(GL2 gl, File textureFile, float radius) throws IOException {
		super(gl, textureFile, radius);
	}

	@Override
	protected void createFigure(GLUquadric quadratic, GL2 gl) {
		glu.gluSphere(quadratic, radius, 64, 64);
	}
}