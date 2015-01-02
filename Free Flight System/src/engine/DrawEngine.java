package engine;

import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLJPanel;
import javax.media.opengl.glu.GLU;

import entities.StarSystem;

public class DrawEngine extends GLJPanel implements GLEventListener, KeyListener {

	private GLU glu = GLUHolder.glu;
	private Position position = new Position();

	private StarSystem sunSystem = new StarSystem();

	@Override
	public void init(GLAutoDrawable drawable) {

		GL2 gl = drawable.getGL().getGL2();

		gl.glEnable(GL2.GL_DEPTH_TEST);
		gl.glDisable(GL2.GL_CULL_FACE);

		try {
			sunSystem.init(gl);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {

	}

	@Override
	public void display(GLAutoDrawable drawable) {

		// initialization of variables
		GL2 gl = drawable.getGL().getGL2();

		applyLight(gl);

		position.setRotationX(rotateX);

		gl.glRotated(rotateX, 0, 1, 0);
		gl.glRotated(rotateY, 0, 0, 1);

		sunSystem.drawSkyBox(gl);

		position.setVelocity(translateX / 10);
		position.fly();
		gl.glTranslated(position.getPositionX(), position.getPositionY(), position.getPositionZ());

		sunSystem.sunSystem(gl);

		setCamera(gl, 1);
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

	public DrawEngine(GLCapabilities capabilities) {
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

	public void mouseMoved(java.awt.event.MouseEvent e) {
		ship.mouseMovent(e, 500, 500);
	}

	/* ========== PRIVATE ========== */
	private static final long serialVersionUID = -7419599978736850207L;
	public double rotateX, rotateY, rotateZ;
	public double translateX, translateY, traslateZ;

	private Ship ship;

	private void applyLight(GL2 gl) {
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
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();

		float widthHeightRatio = (float) getWidth() / (float) getHeight();

		glu.gluPerspective(50, widthHeightRatio, 1, 1000000000);
		glu.gluLookAt(-distance, 0, 0, 0, 0, 0, 0, 1, 0);

		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
	}
}