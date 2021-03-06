package edu.toronto.csc301.grid.impl;

import edu.toronto.csc301.grid.GridCell;
import edu.toronto.csc301.grid.IGrid;
import edu.toronto.csc301.grid.IGridSerializerDeserializer;
import edu.toronto.csc301.warehouse.Rack;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.function.Function;

public class RectangularGridSerializerDeserializer implements IGridSerializerDeserializer {

	/**
	 * Serialize <code>grid</code> (i.e. convert it to something like a String
	 * or byte[]) and write the result to <code>output</code>.
	 * This method uses the given <code>item2byteArray</code> function to
	 * serialize grid items (of type T).
	 */
	public <T> void serialize(IGrid<T> grid, OutputStream output,
							  Function<T, byte[]> item2byteArray) throws IOException {

		Iterator itr = grid.getGridCells();
		int minx, miny, maxx, maxy;

		Object element = itr.next();
		GridCell e = (GridCell) element;
		minx = maxx = e.x;
		miny = maxy = e.y;


		while (itr.hasNext()) {
			element = itr.next();
			e = (GridCell) element;
			if (e.x > maxx) {
				maxx = e.x;
			}
			if (e.x < minx) {
				minx = e.x;
			}
			if (e.y > maxy) {
				maxy = e.y;
			}
			if (e.y < miny) {
				miny = e.y;
			}
		}
		//Check is rectangle.
		for (int x = minx; x < maxx; x++) {
			for (int y = miny; y < maxy; y++) {
				if (!grid.hasCell(GridCell.at(x, y))) {
					throw new IllegalArgumentException();
				}
			}
		}

		OutputStreamWriter writer = new OutputStreamWriter(output);
		writer.write(String.format("width: %d\n", maxx - minx + 1));
		writer.write(String.format("height: %d\n", maxy - miny + 1));
		writer.write(String.format("south-west: %d:%d\n", minx, miny));

		Rack r;

		itr = grid.getGridCells();
		while (itr.hasNext()) {
			element = itr.next();
			e = (GridCell) element;
			r = (Rack) grid.getItem(e);
			if (r != null) {
				writer.write(String.format("%d:%d R:%d\n", e.x, e.y, r.getCapacity()));
			}
		}

		writer.close();

	}


	/**
	 * De-serialize the data read from <code>input</code>.
	 * This method uses the given <code>byteArray2item</code> function to
	 * de-serialize grid items (of type T).
	 */
	public <T> IGrid<T> deserialize(InputStream input,
									Function<byte[], T> byteArray2item) throws IOException {
		char c;
		String s = "";
		int a = 0;
		int w, h, x, y;
		w = h = 0;
		RectangularGrid<T> g = null;


		int r = input.read();
		while (r != -1) {

			c = (char) r;
			s += c;

			if (c == '\n') {
				switch (a) {
					case 0:     //width
						w = Integer.parseInt(s.replaceAll("[\\D]", ""));
						break;
					case 1:     //height
						h = Integer.parseInt(s.replaceAll("[\\D]", ""));
						break;
					case 2:     //south west block
						String[] gs = s.split(":");
						x = Integer.parseInt(gs[1].trim());
						y = Integer.parseInt(gs[2].trim());
						g = new RectangularGrid<T>(w, h, GridCell.at(x, y));
						break;
					default:     //racks
						String[] rs = s.split(" ");
						String gx = rs[0].split(":")[0].trim();
						String gy = rs[0].split(":")[1].trim();
						int rackcapcity = Integer.parseInt(rs[1].split(":")[1].trim());
						GridCell gc = GridCell.at(Integer.parseInt(gx), Integer.parseInt(gy));

						assert g != null;
						g.addrack((T) new Rack(rackcapcity), gc);

						break;
				}
				s = "";
				a++;
			}

			r = input.read();
		}

		return g;
	}
}
