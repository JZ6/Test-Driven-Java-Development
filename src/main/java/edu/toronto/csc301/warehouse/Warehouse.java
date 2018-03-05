package edu.toronto.csc301.warehouse;

import edu.toronto.csc301.grid.GridCell;
import edu.toronto.csc301.grid.IGrid;
import edu.toronto.csc301.robot.GridRobot;
import edu.toronto.csc301.robot.IGridRobot;
import edu.toronto.csc301.robot.IGridRobot.Direction;
import edu.toronto.csc301.robot.IGridRobot.StepListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Consumer;

public class Warehouse implements IWarehouse, StepListener {
	private IGrid<Rack> g;
	private ArrayList<IGridRobot> r;
	private Map<IGridRobot, Direction> rim;
	private ArrayList<Consumer<IWarehouse>> observing;

	public Warehouse(IGrid<Rack> grid)

	{
		if (grid == null) {
			throw new NullPointerException();
		}
		this.g = grid;
		this.r = new ArrayList<IGridRobot>();
		this.rim = new HashMap<IGridRobot, Direction>();
		this.observing = new ArrayList<Consumer<IWarehouse>>();
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

		int i;
		for (i = 0; i < this.observing.size(); i++) {
			this.observing.get(i).accept(this);
		}

		IGridRobot gr = new GridRobot(initialLocation);
		this.r.add(gr);

		gr.startListening(this);

		return gr;
	}

	public Iterator<IGridRobot> getRobots() {
		return this.r.iterator();
	}

	public Map<IGridRobot, Direction> getRobotsInMotion() {
		Map<IGridRobot, Direction> ret = new HashMap<IGridRobot, Direction>(rim);
		return ret;
	}

	public void subscribe(Consumer<IWarehouse> observer) {
		observing.add(observer);
	}

	public void unsubscribe(Consumer<IWarehouse> observer) {
		observing.remove(observer);
	}

	@Override
	public void onStepStart(IGridRobot robot, Direction direction) {
		rim.put(robot, direction);
		int i;
		for (i = 0; i < this.observing.size(); i++) {
			this.observing.get(i).accept(this);
		}

	}

	@Override
	public void onStepEnd(IGridRobot robot, Direction direction) {
		rim.remove(robot, direction);
		int i;
		for (i = 0; i < this.observing.size(); i++) {
			this.observing.get(i).accept(this);
		}

	}
}
