package edu.toronto.csc301.robot;

import edu.toronto.csc301.grid.GridCell;

public class GridRobot implements IGridRobot {
	private GridCell location;

	public GridRobot(GridCell g) {
		if (g == null) {
			throw new NullPointerException();
		}
		this.location = g;

	}

	public GridCell getLocation() {
		return this.location;
	}

	public void step(Direction direction) {
		if (direction == Direction.NORTH) {
			this.location = GridCell.at(this.location.x, this.location.y + 1);
		} else if (direction == Direction.EAST) {
			this.location = GridCell.at(this.location.x + 1, this.location.y);
		} else if (direction == Direction.SOUTH) {
			this.location = GridCell.at(this.location.x, this.location.y - 1);
		} else if (direction == Direction.WEST) {
			this.location = GridCell.at(this.location.x - 1, this.location.y);
		}
	}


	public void startListening(StepListener listener) {

	}

	public void stopListening(StepListener listener) {

	}
}
