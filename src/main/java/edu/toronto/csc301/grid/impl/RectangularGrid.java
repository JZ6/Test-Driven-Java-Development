package edu.toronto.csc301.grid.impl;

import edu.toronto.csc301.grid.GridCell;
import edu.toronto.csc301.grid.IGrid;

import java.util.ArrayList;
import java.util.Iterator;


public class RectangularGrid<T> implements IGrid<T> {

	private int w, h;
	private GridCell sw;
	private T t;
	private ArrayList al;

	public RectangularGrid(int w, int h, GridCell sw) {
		if (sw == null) {
			throw new NullPointerException();
		}
		if (w <= 0 || h <= 0) {
			throw new IllegalArgumentException();
		}
		this.w = w;
		this.h = h;
		this.sw = sw;
		this.al = new ArrayList<GridCell>();
		for (int i = this.sw.x; i < this.sw.x + this.w; i++) {
			for (int j = this.sw.y; j < this.sw.y + this.h; j++) {
				this.al.add(GridCell.at(i,  j));
			}
		}
	}

	public T getItem(GridCell cell){
		if (cell.x > this.sw.x + this.w || cell.x < this.sw.x || cell.y > this.sw.y + this.h || cell.y < this.sw.y){
			throw new IllegalArgumentException();
		}
		return t;
	}


	public Iterator<GridCell> getGridCells(){
		return this.al.iterator();
	}
	public boolean hasCell(GridCell cell){
		return this.al.contains(cell);
	}
}
