package edu.toronto.csc301.robot;

import edu.toronto.csc301.grid.GridCell;

import java.util.ArrayList;

public class GridRobot implements IGridRobot {
	private GridCell location;
	private ArrayList<StepListener> listening;

	public GridRobot(GridCell g) {
		if (g == null) {
			throw new NullPointerException();
		}
		this.location = g;
		this.listening = new ArrayList<StepListener>();
	}

	public GridCell getLocation() {
		return this.location;
	}

	public void step(Direction direction) {
		int i;
		for (i = 0; i < listening.size(); i++) {
			listening.get(i).onStepStart(this, direction);
		}
		if (direction == Direction.NORTH) {
			this.location = GridCell.at(this.location.x, this.location.y + 1);
		} else if (direction == Direction.EAST) {
			this.location = GridCell.at(this.location.x + 1, this.location.y);
		} else if (direction == Direction.SOUTH) {
			this.location = GridCell.at(this.location.x, this.location.y - 1);
		} else if (direction == Direction.WEST) {
			this.location = GridCell.at(this.location.x - 1, this.location.y);
		}
		for (i = 0; i < listening.size(); i++) {
			listening.get(i).onStepEnd(this, direction);
		}
	}

	public void startListening(StepListener listener) {
		listening.add(listener);
	}

	public void stopListening(StepListener listener) {
		listening.remove(listener);
	}
}
