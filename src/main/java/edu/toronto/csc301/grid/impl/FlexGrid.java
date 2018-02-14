package edu.toronto.csc301.grid.impl;

import edu.toronto.csc301.grid.GridCell;
import edu.toronto.csc301.grid.IGrid;

import java.util.ArrayList;
import java.util.Iterator;

public class FlexGrid<T> implements IGrid<T> {

	private GridCell sw;
	private T t;
	private ArrayList al;

	public T getItem(GridCell cell){
		return t;
	}
	public Iterator<GridCell> getGridCells(){
		return null;
	}
	public boolean hasCell(GridCell cell){
		return false;
	}
}
