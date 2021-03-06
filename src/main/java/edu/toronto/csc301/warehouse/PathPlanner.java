package edu.toronto.csc301.warehouse;

import edu.toronto.csc301.grid.GridCell;
import edu.toronto.csc301.robot.IGridRobot;
import edu.toronto.csc301.robot.IGridRobot.Direction;

import java.util.AbstractMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

public class PathPlanner implements IPathPlanner {
	public Map.Entry<IGridRobot, Direction> nextStep(IWarehouse warehouse, Map<IGridRobot, GridCell> robot2dest) {

		Iterator<IGridRobot> bots = warehouse.getRobots();
		IGridRobot r = bots.next();
		GridCell dest = robot2dest.get(r);
		GridCell curr = r.getLocation();
		IGridRobot other = null;
		GridCell otherbotloc = null;
		boolean otherbots = true;

		try {
			other = bots.next();
			otherbotloc = other.getLocation();
		} catch (NoSuchElementException e) {
			otherbots = false;
		}

		if (dest.equals(curr)) {
			return null;
		}

		if (dest.x > curr.x && warehouse.getFloorPlan().hasCell(GridCell.at(curr.x + 1, curr.y))) {
			if (!otherbots || !otherbotloc.equals(GridCell.at(curr.x + 1, curr.y))) {
				return new AbstractMap.SimpleEntry<IGridRobot, Direction>(r, Direction.EAST);
			}
		} else if (dest.x < curr.x && warehouse.getFloorPlan().hasCell(GridCell.at(curr.x - 1, curr.y))) {
			if (!otherbots || !otherbotloc.equals(GridCell.at(curr.x - 1, curr.y))) {
				return new AbstractMap.SimpleEntry<IGridRobot, Direction>(r, Direction.WEST);
			}
		}

		if (dest.y > curr.y && warehouse.getFloorPlan().hasCell(GridCell.at(curr.x, curr.y + 1))) {
			if (!otherbots || !otherbotloc.equals(GridCell.at(curr.x, curr.y + 1))) {
				return new AbstractMap.SimpleEntry<IGridRobot, Direction>(r, Direction.NORTH);
			}
		} else if (dest.y < curr.y && warehouse.getFloorPlan().hasCell(GridCell.at(curr.x + 1, curr.y - 1))) {
			if (!otherbots || !otherbotloc.equals(GridCell.at(curr.x, curr.y - 1))) {
				return new AbstractMap.SimpleEntry<IGridRobot, Direction>(r, Direction.SOUTH);
			}
		}

		if (warehouse.getFloorPlan().hasCell(GridCell.at(curr.x, curr.y + 1))) {
			return new AbstractMap.SimpleEntry<IGridRobot, Direction>(r, Direction.NORTH);
		} else if (warehouse.getFloorPlan().hasCell(GridCell.at(curr.x, curr.y - 1))) {
			return new AbstractMap.SimpleEntry<IGridRobot, Direction>(r, Direction.SOUTH);
		} else if (warehouse.getFloorPlan().hasCell(GridCell.at(curr.x + 1, curr.y))) {
			return new AbstractMap.SimpleEntry<IGridRobot, Direction>(r, Direction.EAST);
		} else {
			return new AbstractMap.SimpleEntry<IGridRobot, Direction>(r, Direction.WEST);
		}
	}
}
