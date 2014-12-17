package figures;

import java.io.File;
import java.io.IOException;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLUquadric;

public class Cube extends Figure {

	public Cube(GL2 gl, File textureFile, float radius) throws IOException {
		super(gl, textureFile, radius);
	}

	protected void createFigure(GLUquadric quadratic, GL2 gl) {
		gl.glBegin(GL2.GL_QUADS);
		gl.glTexCoord2f(0, 0);
		gl.glVertex3f(0.5f, -0.5f, -0.5f);
		gl.glTexCoord2f(1, 0);
		gl.glVertex3f(-0.5f, -0.5f, -0.5f);
		gl.glTexCoord2f(1, 1);
		gl.glVertex3f(-0.5f, 0.5f, -0.5f);
		gl.glTexCoord2f(0, 1);
		gl.glVertex3f(0.5f, 0.5f, -0.5f);
		gl.glEnd();
		gl.glBegin(GL2.GL_QUADS);
		gl.glTexCoord2f(0, 0);
		gl.glVertex3f(0.5f, -0.5f, 0.5f);
		gl.glTexCoord2f(1, 0);
		gl.glVertex3f(0.5f, -0.5f, -0.5f);
		gl.glTexCoord2f(1, 1);
		gl.glVertex3f(0.5f, 0.5f, -0.5f);
		gl.glTexCoord2f(0, 1);
		gl.glVertex3f(0.5f, 0.5f, 0.5f);
		gl.glEnd();
		gl.glBegin(GL2.GL_QUADS);
		gl.glTexCoord2f(0, 0);
		gl.glVertex3f(-0.5f, -0.5f, 0.5f);
		gl.glTexCoord2f(1, 0);
		gl.glVertex3f(0.5f, -0.5f, 0.5f);
		gl.glTexCoord2f(1, 1);
		gl.glVertex3f(0.5f, 0.5f, 0.5f);
		gl.glTexCoord2f(0, 1);
		gl.glVertex3f(-0.5f, 0.5f, 0.5f);
		gl.glEnd();
		gl.glBegin(GL2.GL_QUADS);
		gl.glTexCoord2f(0, 0);
		gl.glVertex3f(-0.5f, -0.5f, -0.5f);
		gl.glTexCoord2f(1, 0);
		gl.glVertex3f(-0.5f, -0.5f, 0.5f);
		gl.glTexCoord2f(1, 1);
		gl.glVertex3f(-0.5f, 0.5f, 0.5f);
		gl.glTexCoord2f(0, 1);
		gl.glVertex3f(-0.5f, 0.5f, -0.5f);
		gl.glEnd();
		gl.glBegin(GL2.GL_QUADS);
		gl.glTexCoord2f(0, 1);
		gl.glVertex3f(-0.5f, 0.5f, -0.5f);
		gl.glTexCoord2f(0, 0);
		gl.glVertex3f(-0.5f, 0.5f, 0.5f);
		gl.glTexCoord2f(1, 0);
		gl.glVertex3f(0.5f, 0.5f, 0.5f);
		gl.glTexCoord2f(1, 1);
		gl.glVertex3f(0.5f, 0.5f, -0.5f);
		gl.glEnd();
		gl.glBegin(GL2.GL_QUADS);
		gl.glTexCoord2f(0, 0);
		gl.glVertex3f(-0.5f, -0.5f, -0.5f);
		gl.glTexCoord2f(0, 1);
		gl.glVertex3f(-0.5f, -0.5f, 0.5f);
		gl.glTexCoord2f(1, 1);
		gl.glVertex3f(0.5f, -0.5f, 0.5f);
		gl.glTexCoord2f(1, 0);
		gl.glVertex3f(0.5f, -0.5f, -0.5f);
		gl.glEnd();
		gl.glPopAttrib();
		gl.glPopMatrix();
	}
}