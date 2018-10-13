package edu.toronto.csc301;

public class GridRobot1 implements IGridRobot {
	private IBasicRobot r;
	private int i;

	public GridRobot1(IBasicRobot r, int i) {
		if (r == null) {
			throw new NullPointerException();
		} else if (i <= 0 || (r.getXCoordinate() % i) != 0 || (r.getYCoordinate() % i) != 0 || (r.getRotation() % 90) != 0) {
			throw new IllegalArgumentException();
		}
		this.r = r;
		this.i = i;
	}

	public GridCell getLocation() {
		return GridCell.at((int) Math.round(r.getXCoordinate() / i), (int) Math.round(r.getYCoordinate() / i));
	}

	public Direction getFacingDirection() {
		Direction result = null;
		if (this.r.getRotation() == 0) {
			result = Direction.NORTH;
		} else if (this.r.getRotation() == 90) {
			result = Direction.EAST;
		} else if (this.r.getRotation() == 180) {
			result = Direction.SOUTH;
		} else if (this.r.getRotation() == 270) {
			result = Direction.WEST;
		}
		return result;
	}

	public void step(Direction direction) {
		Direction d = getFacingDirection();
		face(direction);
		this.r.moveForward(this.i);
		face(d);
	}

	public void face(Direction direction) {

		if (direction == Direction.NORTH) {
			this.r.rotateLeft(this.r.getRotation());
		} else if (direction == Direction.EAST) {
			this.r.rotateLeft(this.r.getRotation() - 90);
		} else if (direction == Direction.SOUTH) {
			this.r.rotateLeft(this.r.getRotation() - 180);
		} else if (direction == Direction.WEST) {
			this.r.rotateLeft(this.r.getRotation() - 270);
		}
	}
}
