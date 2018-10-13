package edu.toronto.csc301.grid.impl;

import edu.toronto.csc301.grid.GridCell;
import edu.toronto.csc301.grid.IGrid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class FlexGrid<T> implements IGrid<T> {

	private ArrayList g;
	private HashMap<Integer, T> rack;

	public FlexGrid() {
		this.g = new ArrayList<GridCell>();
		this.rack = new HashMap<Integer, T>();
	}


	public T getItem(GridCell cell) {
		if (hasCell(cell)) {
			return this.rack.get(cell.hashCode());
		} else {
			throw new IllegalArgumentException();
		}
	}

	public Iterator<GridCell> getGridCells() {
		return this.g.iterator();
	}

	public boolean hasCell(GridCell cell) {
		return this.g.contains(cell);
	}

	public void addrack(T r, GridCell cell) {
		this.rack.put(cell.hashCode(), r);
	}

	public void addcell(GridCell cell) {
		this.g.add(cell);
	}
}
