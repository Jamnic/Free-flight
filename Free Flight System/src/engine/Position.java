package engine;

public class Position {

	private double positionX, positionY, positionZ;
	private double rotationX, rotationY, rotationZ;

	private double velocity;

	public Position() {
		this(0, 0, 0, 0, 0, 0);
	}

	public Position(double positionX, double positionY, double positionZ, double rotateX, double rotateY, double rotateZ) {
		this.positionX = positionX;
		this.positionY = positionY;
		this.positionZ = positionZ;

		this.rotationX = rotateX;
		this.rotationY = rotateY;
		this.rotationZ = rotateZ;
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}

	public void fly() {
		double aX = velocity * Math.sin(Math.toRadians(rotationX));
		double bX = velocity * Math.cos(Math.toRadians(rotationX));
		
		positionX += bX;
		positionZ += aX;
		
		double aY = velocity * Math.sin(Math.toRadians(rotationY));
		double bY = velocity * Math.cos(Math.toRadians(rotationY));
		
		System.out.println(velocity);
		
		positionY += bY;
		positionZ += aY;
	}

	public double getPositionX() {
		return positionX;
	}

	public void setPositionX(double positionX) {
		this.positionX = positionX;
	}

	public double getPositionY() {
		return positionY;
	}

	public void setPositionY(double positionY) {
		this.positionY = positionY;
	}

	public double getPositionZ() {
		return positionZ;
	}

	public void setPositionZ(double positionZ) {
		this.positionZ = positionZ;
	}

	public double getRotationX() {
		return rotationX;
	}

	public void setRotationX(double rotationX) {
		this.rotationX = rotationX;
	}

	public double getRotationY() {
		return rotationY;
	}

	public void setRotationY(double rotationY) {
		this.rotationY = rotationY;
	}

	public double getRotationZ() {
		return rotationZ;
	}

	public void setRotationZ(double rotationZ) {
		this.rotationZ = rotationZ;
	}

	public double getVelocity() {
		return velocity;
	}

}
