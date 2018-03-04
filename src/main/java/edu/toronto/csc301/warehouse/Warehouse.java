package edu.toronto.csc301.warehouse;

import edu.toronto.csc301.grid.GridCell;
import edu.toronto.csc301.grid.IGrid;
import edu.toronto.csc301.robot.GridRobot;
import edu.toronto.csc301.robot.IGridRobot;
import edu.toronto.csc301.robot.IGridRobot.Direction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Consumer;

public class Warehouse implements IWarehouse {
	private IGrid<Rack> g;
	private ArrayList<IGridRobot> r;
	private Map<IGridRobot, Direction> rim;

	public Warehouse(IGrid<Rack> grid) {
		if (grid == null) {
			throw new NullPointerException();
		}
		this.g = grid;
		this.r = new ArrayList<IGridRobot>();
		this.rim = new HashMap<IGridRobot, Direction>();
	}

	public IGrid<Rack> getFloorPlan() {
		return this.g;
	}

	public IGridRobot addRobot(GridCell initialLocation) {

		if (!this.g.hasCell(initialLocation)) {
			throw new IllegalArgumentException();
		}
		this.r.forEach(robot -> {
			if (robot.getLocation().equals(initialLocation)) {
				throw new IllegalArgumentException();
			}
		});

		IGridRobot gr = new GridRobot(initialLocation);
		this.r.add(gr);

		return gr;
	}

	public Iterator<IGridRobot> getRobots() {
		return this.r.iterator();
	}

	public Map<IGridRobot, Direction> getRobotsInMotion() {
		return rim;
	}

	public void subscribe(Consumer<IWarehouse> observer) {

	}

	public void unsubscribe(Consumer<IWarehouse> observer) {

	}
}
