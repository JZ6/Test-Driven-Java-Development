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

public class FlexGridSerializerDeserializer implements IGridSerializerDeserializer {

	/**
	 * Serialize <code>grid</code> (i.e. convert it to something like a String
	 * or byte[]) and write the result to <code>output</code>.
	 * This method uses the given <code>item2byteArray</code> function to
	 * serialize grid items (of type T).
	 */
	public <T> void serialize(IGrid<T> grid, OutputStream output,
							  Function<T, byte[]> item2byteArray) throws IOException {

		Iterator itr = grid.getGridCells();
		Object element;
		GridCell e;
		OutputStreamWriter writer = new OutputStreamWriter(output);
		Rack r;

		while (itr.hasNext()) {
			element = itr.next();
			e = (GridCell) element;
			writer.write(String.format("%d:%d", e.x, e.y));
			r = (Rack) grid.getItem(e);
			if (r != null) {
				writer.write(String.format(" R:%d", r.getCapacity()));
			}
			writer.write("\n");

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

		FlexGrid<T> g = new FlexGrid<>();


		int r = input.read();
		while (r != -1) {

			c = (char) r;
			s += c;

			if (c == '\n') {

				String[] fg = s.split(" ");
				String gx = fg[0].split(":")[0].trim();
				String gy = fg[0].split(":")[1].trim();

				GridCell gc = GridCell.at(Integer.parseInt(gx), Integer.parseInt(gy));
				g.addcell(gc);

				if (fg.length == 2) {
					int rackcapcity = Integer.parseInt(fg[1].split(":")[1].trim());
					g.addrack((T) new Rack(rackcapcity), gc);
				}

				s = "";
			}
			r = input.read();
		}
		if (!s.equals("")) {

			String[] fg = s.split(" ");
			String gx = fg[0].split(":")[0].trim();
			String gy = fg[0].split(":")[1].trim();

			GridCell gc = GridCell.at(Integer.parseInt(gx), Integer.parseInt(gy));
			g.addcell(gc);

			if (fg.length == 2) {
				int rackcapcity = Integer.parseInt(fg[1].split(":")[1].trim());
				g.addrack((T) new Rack(rackcapcity), gc);
			}
		}

		return g;
	}
}
