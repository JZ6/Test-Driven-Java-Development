package edu.toronto.csc301;

public class BasicRobot implements IBasicRobot {
	private double x, y;
	private int r;

	public BasicRobot(double x, double y, int r) {
		this.x = x;
		this.y = y;
		if (r < 0 || r >= 360) {
			throw new IllegalArgumentException();
		}
		this.r = r;
	}

	public double getXCoordinate() {
		return this.x;
	}

	public double getYCoordinate() {
		return this.y;
	}

	public int getRotation() {
		return this.r;
	}

	public void rotateRight(int degrees) {
		if (degrees < 0) {
			this.r = this.r + (360 + degrees);
		} else {
			this.r = (this.r + degrees) % 360;
		}

	}

	public void rotateLeft(int degrees) {
		degrees %= 360;
		this.r -= degrees;
		if (this.r < 0) {
			this.r = 360 + this.r;
		}
	}

	public void moveForward(int millimeters) {
		double r = Math.toRadians(this.r);
		this.y += millimeters * Math.cos(r);
		this.x += millimeters * Math.sin(r);

	}
}
