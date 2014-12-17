package figures;

import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLJPanel;
import javax.media.opengl.glu.GLU;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureData;
import com.jogamp.opengl.util.texture.TextureIO;

public class Stellar extends GLJPanel implements GLEventListener, KeyListener {

	private Sphere earth;
	private Sphere sun;
	private Sphere skyBox;
	private GLU glu = new GLU();
	private float earthRotation = 0f;
	private float sunRotation = 0f;

	@Override
	public void init(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();

		gl.glEnable(GL2.GL_DEPTH_TEST);
		gl.glDisable(GL2.GL_CULL_FACE);

		try {
			sun = new Sphere(gl, new File("src/texture_sun.png"), 1000f);
			earth = new Sphere(gl, new File("src/earth.png"), 60.800f);
			skyBox = new Sphere(gl, new File("src/space.png"), 100000f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {

	}

	@Override
	public void display(GLAutoDrawable drawable) {

		GL2 gl = drawable.getGL().getGL2();

		float sunDistance = 3000f;

		sunLight(gl);

		gl.glDepthMask(false);
		skyBox.draw(gl);
		gl.glDepthMask(true);

		gl.glLoadIdentity();

		gl.glTranslatef(rotateX, 0, 0);

		setCamera(gl, 1);

		gl.glTranslatef(sunDistance, 0, 0);
		gl.glRotated(earthRotation += 0.3, 0, 1, 0);
		sun.draw(gl);
		gl.glLoadIdentity();

		gl.glTranslatef(sunDistance, 0, 0);
		earthLight(gl, sunDistance);
		gl.glRotatef(90, 1, 0, 0);
		gl.glTranslated(10000 * Math.sin(earthRotation/50), 10000 * Math.cos(earthRotation/50), 0);
		gl.glRotated(earthRotation += 0.3, 0, 0, 1);
		earth.draw(gl);
		gl.glLoadIdentity();

		gl.glRotatef(180, 1.0f, 0.0f, 0.0f);
	}

	private void earthLight(GL2 gl, float sunDistance) {
		float[] lightColorAmbient2 = { 0.01f, 0.01f, 0.01f, 1f };

		float SHINE_ALL_DIRECTIONS = 1;
		float[] lightPos = { sunDistance, 0, 0, SHINE_ALL_DIRECTIONS };
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_POSITION, lightPos, 0);
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_AMBIENT, lightColorAmbient2, 0);
	}

	private void sunLight(GL2 gl) {
		// Prepare light parameters.
		float[] lightColorAmbient = { 1.0f, 1.0f, 1.0f, 1f };
		float[] lightColorSpecular = { 1.0f, 1.0f, 1.0f, 1f };

		// Set light parameters.
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_AMBIENT, lightColorAmbient, 0);
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_SPECULAR, lightColorSpecular, 0);

		// Enable lighting in GL.
		gl.glEnable(GL2.GL_LIGHT1);
		gl.glEnable(GL2.GL_LIGHTING);

		// Set material properties.
		float[] rgba = { 1f, 1f, 1f };
		gl.glMaterialfv(GL.GL_FRONT, GL2.GL_AMBIENT, rgba, 0);
		gl.glMaterialfv(GL.GL_FRONT, GL2.GL_SPECULAR, lightColorSpecular, 0);

		gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
	}

	private void setCamera(GL2 gl, float distance) {
		// Change to projection matrix.

		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();

		// Perspective.
		float widthHeightRatio = (float) getWidth() / (float) getHeight();

		// glu.gluPerspective(k¹t widzenia, stosunek szerokoœci do wysokoœci, frustrum near, frustrum far).
		glu.gluPerspective(50, widthHeightRatio, 1, 100000000);

		// pozycja oka, pozycja tego, na co patrzymy, wskazanie, gdzie jet góra (?)
		glu.gluLookAt(-distance, 0, 0, 0, 0, 0, 0, 1, 0);

		// gl.glRotatef(rotateX, 1.0f, 0.0f, 0.0f);
		gl.glRotatef(rotateY, 0.0f, 1.0f, 0.0f);
		gl.glRotatef(rotateZ, 0.0f, 0.0f, 1.0f);

		// Change back to model view matrix.
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

	}

	@Override
	public void keyPressed(java.awt.event.KeyEvent e) {
		ship.keyPressed(e);
	}

	@Override
	public void keyReleased(java.awt.event.KeyEvent e) {
	}

	@Override
	public void keyTyped(java.awt.event.KeyEvent e) {
	}

	public Stellar(GLCapabilities capabilities) {
		// TODO Auto-generated constructor stub
		super(capabilities);
		setPreferredSize(new Dimension(500, 500));
		addGLEventListener(this);
		addKeyListener(this);
		rotateX = 0;
		rotateY = 0;
		rotateZ = 0;

		Ship ship = new Ship();
		ship.setSphere(this);

		new Thread(ship).start();

		this.ship = ship;
	}

	/* ========== PRIVATE ========== */
	private static final long serialVersionUID = -7419599978736850207L;
	public float rotateX, rotateY, rotateZ;
	private Ship ship;

}