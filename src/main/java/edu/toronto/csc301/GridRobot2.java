package edu.toronto.csc301;

public class GridRobot2 extends BasicRobot implements IGridRobot  {

	public GridRobot2 (GridCell g,IGridRobot.Direction d, int i){
		super(0,0,0);
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
