package edu.toronto.csc301.warehouse;

import edu.toronto.csc301.grid.GridCell;
import edu.toronto.csc301.grid.IGrid;
import edu.toronto.csc301.robot.IGridRobot;

import java.util.Iterator;
import java.util.Map;
import java.util.function.Consumer;

public class Warehouse implements IWarehouse {

	public Warehouse(IGrid<Rack> grid){

	}

	public IGrid<Rack> getFloorPlan() {
		return null;
	}

	public IGridRobot addRobot(GridCell initialLocation) {
		return null;
	}

	public Iterator<IGridRobot> getRobots() {
		return null;
	}

	public Map<IGridRobot, IGridRobot.Direction> getRobotsInMotion() {
		return null;
	}

	public void subscribe(Consumer<IWarehouse> observer) {

	}

	public void unsubscribe(Consumer<IWarehouse> observer) {

	}
}
