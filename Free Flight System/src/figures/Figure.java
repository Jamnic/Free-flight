package figures;

import java.io.File;
import java.io.IOException;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureData;
import com.jogamp.opengl.util.texture.TextureIO;

import engine.GLUHolder;

public abstract class Figure {

	protected abstract void createFigure(GLUquadric quadratic, GL2 gl);

	public Figure(GL2 gl, File textureFile, float radius) throws IOException {
		this.radius = radius;
		this.texture = assignTexture(gl, textureFile);
	}

	public void draw(GL2 gl) {

		GLUquadric quadratic = glu.gluNewQuadric();

		glu.gluQuadricDrawStyle(quadratic, GLU.GLU_FILL);
		glu.gluQuadricTexture(quadratic, true);
		glu.gluQuadricNormals(quadratic, GLU.GLU_SMOOTH);

		texture.enable(gl);
		texture.bind(gl);
		createFigure(quadratic, gl);
		texture.disable(gl);
		glu.gluDeleteQuadric(quadratic);
	}

	/* ========== PRIVATE ========== */
	private Texture texture;
	protected GLU glu = GLUHolder.glu;
	protected float radius;

	private static Texture assignTexture(GL2 gl, File textureFile) throws IOException {
		TextureData data = TextureIO.newTextureData(gl.getGLProfile(), textureFile, false, "png");

		return TextureIO.newTexture(data);
	}

}
