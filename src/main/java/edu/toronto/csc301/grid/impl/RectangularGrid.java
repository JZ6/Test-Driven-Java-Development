package edu.toronto.csc301.grid.impl;

import edu.toronto.csc301.grid.GridCell;
import edu.toronto.csc301.grid.IGrid;

import java.util.Iterator;


public class RectangularGrid<T> implements IGrid {

	private double x, y;
	private int r;

	public RectangularGrid(int x, int y, GridCell g) {

	}

	public T getItem(GridCell cell){
		return null;
	}
	public Iterator<GridCell> getGridCells(){
		return null;
	}
	public boolean hasCell(GridCell cell){
		return true;
	}
}
