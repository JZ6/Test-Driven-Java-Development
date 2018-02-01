package edu.toronto.csc301;

public class GridRobot2 extends BasicRobot implements IGridRobot {

    public GridRobot2(GridCell g, IGridRobot.Direction d, int size) {

        super(g.x * size, g.y * size, 0);
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

    public GridCell getLocation() {
        return GridCell.at(0, 0);
    }

    public Direction getFacingDirection() {
        return Direction.NORTH;
    }

    public void step(Direction direction) {

    }

    public void face(Direction direction) {

    }
}
