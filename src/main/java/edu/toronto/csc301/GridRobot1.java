package edu.toronto.csc301;

public class GridRobot1 implements IGridRobot  {

	public GridRobot1(IBasicRobot r, int i){

	}

	public GridCell getLocation(){
		return GridCell.at(0,0);
	}
	public Direction getFacingDirection(){
		return Direction.NORTH;
	}

	public void step(Direction direction){

	}
	public void face(Direction direction){

	}
}
