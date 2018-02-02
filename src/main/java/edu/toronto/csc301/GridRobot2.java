package edu.toronto.csc301;

public class GridRobot2 extends BasicRobot implements IGridRobot {
	GridCell g;
	int size;

	public GridRobot2(GridCell g, IGridRobot.Direction d, int size) {

		super(g.x * size, g.y * size, 0);

		this.g = g;
		this.size = size;

		if (d == null) {
			throw new NullPointerException();
		}
		if (size <= 0) {
			throw new IllegalArgumentException();
		}
		if (d == Direction.NORTH) {
			super.setRotation(0);
		} else if (d == Direction.EAST) {
			super.setRotation(90);
		} else if (d == Direction.SOUTH) {
			super.setRotation(180);
		} else if (d == Direction.WEST) {
			super.setRotation(270);
		}
	}

	public void rotateRight(int degrees) {
		if (degrees % 90 != 0) {
			throw new IllegalArgumentException();
		}
		super.rotateRight(degrees);
	}

	public void rotateLeft(int degrees) {
		if (degrees % 90 != 0) {
			throw new IllegalArgumentException();
		}
		super.rotateLeft(degrees);
	}

	public GridCell getLocation() {
		return GridCell.at((int) Math.round(super.getXCoordinate() / this.size), (int) Math.round(super.getYCoordinate() / this.size));
	}

	public Direction getFacingDirection() {
		Direction result = null;
		if (super.getRotation() == 0) {
			result = Direction.NORTH;
		} else if (super.getRotation() == 90) {
			result = Direction.EAST;
		} else if (super.getRotation() == 180) {
			result = Direction.SOUTH;
		} else if (super.getRotation() == 270) {
			result = Direction.WEST;
		}
		return result;
	}

	public void step(Direction direction) {
		Direction d = getFacingDirection();
		face(direction);
		super.moveForward(this.size);
		face(d);
	}

	public void face(Direction direction) {
		if (direction == Direction.NORTH) {
			super.setRotation(0);
		} else if (direction == Direction.EAST) {
			super.setRotation(90);
		} else if (direction == Direction.SOUTH) {
			super.setRotation(180);
		} else if (direction == Direction.WEST) {
			super.setRotation(270);
		}
	}

	public void moveForward(int millimeters) {
		if (millimeters % this.size != 0) {
			throw new IllegalArgumentException();
		}
		super.moveForward(millimeters);
	}
}
