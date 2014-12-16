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
import javax.media.opengl.glu.GLUquadric;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureData;
import com.jogamp.opengl.util.texture.TextureIO;

public class Sphere extends GLJPanel implements GLEventListener, KeyListener {

	private Texture earth;
	private Texture sun;
	private GLU glu = new GLU();
	private float earthRotation = 0f;
	private float sunRotation = 0f;

	@Override
	public void init(GLAutoDrawable drawable) {
		// called when the panel is created
		GL2 gl = drawable.getGL().getGL2();

		gl.glEnable(GL2.GL_DEPTH_TEST);

		try {
			TextureData data = TextureIO.newTextureData(gl.getGLProfile(), new File(
					"C:/Job/Workspaces/Edu/Free Flight System/src/earth.png"), false, "png");
			earth = TextureIO.newTexture(data);

			data = TextureIO.newTextureData(gl.getGLProfile(), new File(
					"C:/Job/Workspaces/Edu/Free Flight System/src/texture_sun.jpg"), false, "jpg");
			sun = TextureIO.newTexture(data);
		} catch (IOException exc) {
			exc.printStackTrace();
			System.exit(1);
		}

	}

	@Override
	public void dispose(GLAutoDrawable drawable) {

	}

	@Override
	public void display(GLAutoDrawable drawable) {

		GL2 gl = drawable.getGL().getGL2();

		float sunDistance = 3000f;

		createSun(gl, sunDistance);

		setCamera(gl, 30);

		createEarth(gl, sunDistance);
	}

	private void createEarth(GL2 gl, float sunDistance) {
		float[] lightColorAmbient = { 0.01f, 0.01f, 0.01f, 1f };

		float SHINE_ALL_DIRECTIONS = 1;
		float[] lightPos = { sunDistance, 0, 0, SHINE_ALL_DIRECTIONS };
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_POSITION, lightPos, 0);
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_AMBIENT, lightColorAmbient, 0);

		earth.enable(gl);
		earth.bind(gl);
		final float radius = 6.378f;
		gl.glRotatef(90, 1, 0, 0);
		gl.glRotatef(earthRotation += 0.3f, 0, 0, 1);
		drawSphere(gl, radius);
		earth.disable(gl);
	}

	private void createSun(GL2 gl, float sunDistance) {
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

		sun.enable(gl);
		sun.bind(gl);
		gl.glRotatef(sunRotation += 0.1f, 1, 0, 0);
		gl.glTranslatef(sunDistance, 0, 0);
		drawSphere(gl, 1000f);
		sun.disable(gl);
	}

	private void drawSphere(GL2 gl, float radius) {
		GLUquadric quadratic = glu.gluNewQuadric();

		glu.gluQuadricTexture(quadratic, true);
		glu.gluSphere(quadratic, radius, 64, 64);
		glu.gluDeleteQuadric(quadratic);

		gl.glLoadIdentity();
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
		glu.gluLookAt(distance, 0, 0, 0, 0, 0, 0, 1, 0);

		// Rotacja sceny
		gl.glRotatef(rotateX, 1.0f, 0.0f, 0.0f);
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

	public Sphere(GLCapabilities capabilities) {
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