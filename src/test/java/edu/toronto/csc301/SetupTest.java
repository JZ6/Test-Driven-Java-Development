package edu.toronto.csc301;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static edu.toronto.csc301.util.Asserts.*;
import edu.toronto.csc301.grid.GridCell;
import edu.toronto.csc301.grid.IGrid;
import edu.toronto.csc301.grid.IGridSerializerDeserializer;

import static edu.toronto.csc301.util.Asserts.assertClassExists;
import static edu.toronto.csc301.util.Asserts.assertClassHasConstructor;
import static edu.toronto.csc301.util.Asserts.assertClassHasDefaultConstructor;
import static edu.toronto.csc301.util.Asserts.assertClassImplementsInterface;


import edu.toronto.csc301.robot.IGridRobot;
import edu.toronto.csc301.warehouse.IPathPlanner;
import edu.toronto.csc301.warehouse.IWarehouse;


public class SetupTest {

	// Define a rule - Each test method has (at most) 2 seconds to run.
	@Rule  
	public Timeout globalTimeout = Timeout.seconds(2);
	
	public static final String  BASIC_ROBOT = "edu.toronto.csc301.BasicRobot";
	public static final String  GRID_ROBOT1 = "edu.toronto.csc301.GridRobot1";
	public static final String  GRID_ROBOT2 = "edu.toronto.csc301.GridRobot2";
	
	@Test
	public void checkExistence_BasicRobot() {
		assertClassExists(BASIC_ROBOT);
	}
	
	@Test
	public void checkExistence_GridRobot1() {
		assertClassExists(GRID_ROBOT1);
	}
	
	@Test
	public void checkExistence_GridRobot2() {
		assertClassExists(GRID_ROBOT2);
	}
	
	
	
	@Test
	public void checkInterface_BasicRobot() throws ClassNotFoundException {
		assertClassImplementsInterface(BASIC_ROBOT, IBasicRobot.class);
	}
	
	@Test
	public void checkInterface_GridRobot1() throws ClassNotFoundException {
		assertClassImplementsInterface(GRID_ROBOT1, IGridRobot.class);
	}
	
	@Test
	public void checkInterface_GridRobot2() throws ClassNotFoundException {
		assertClassImplementsInterface(GRID_ROBOT2, IGridRobot.class);
	}
	
	
	
	@Test
	public void checkConstructor_BasicRobot() throws ClassNotFoundException {
		assertClassHasConstructor(BASIC_ROBOT, Double.TYPE, Double.TYPE, Integer.TYPE);
	}
	

	@Test
	public void checkConstructor_GridRobot1() throws ClassNotFoundException {
		assertClassHasConstructor(GRID_ROBOT1, IBasicRobot.class, Integer.TYPE);
	}
	
	@Test
	public void checkConstructor_GridRobot2() throws ClassNotFoundException {
		assertClassHasConstructor(GRID_ROBOT2, GridCell.class, IGridRobot.Direction.class, Integer.TYPE);
	}
	
	
	@Test
	public void checkParent_GridRobot2() throws ClassNotFoundException {
		assertClassHasParent(GRID_ROBOT2, BASIC_ROBOT);
	}

	private static final String PKG = "edu.toronto.csc301.grid.impl.";
	
	public static final String  RECT_GRID = PKG + "RectangularGrid";
	public static final String  FLEX_GRID = PKG + "FlexGrid";
	public static final String  RECT_GRID_SERIALIZER_DESERIALIZER = PKG + 
			"RectangularGridSerializerDeserializer";
	public static final String  FLEX_GRID_SERIALIZER_DESERIALIZER = PKG + 
			"FlexGridSerializerDeserializer";
	
	
	@Test
	public void checkExistence_RectangularGrid() {
		assertClassExists(RECT_GRID);
	}
	
	@Test
	public void checkExistence_FlexGrid() {
		assertClassExists(FLEX_GRID);
	}
	
	
	@Test
	public void checkExistence_RectangularGridSerializerDeserializer() {
		assertClassExists(RECT_GRID_SERIALIZER_DESERIALIZER);
	}
	
	@Test
	public void checkExistence_FlexGridSerializerDeserializer() {
		assertClassExists(FLEX_GRID_SERIALIZER_DESERIALIZER);
	}

	private static final String PKG = "edu.toronto.csc301.";
	
	public static final String  GRID_ROBOT  = PKG + "robot.GridRobot";
	
	public static final String  WAREHOUSE   = PKG + "warehouse.Warehouse";
	public static final String  PATH_PLANNER = PKG + "warehouse.PathPlanner";
	
	
	
	@Test
	public void checkExistence_GridRobot() {
		assertClassExists(GRID_ROBOT);
	}
	
	@Test
	public void checkExistence_Warehouse() {
		assertClassExists(WAREHOUSE);
	}
	
	@Test
	public void checkExistence_PathPlanner() {
		assertClassExists(PATH_PLANNER);
	}
	
	
	
	@Test
	public void checkInterface_RectangularGrid() throws ClassNotFoundException {
		assertClassImplementsInterface(RECT_GRID, IGrid.class);
	}
	
	@Test
	public void checkInterface_FlexGrid() throws ClassNotFoundException {
		assertClassImplementsInterface(FLEX_GRID, IGrid.class);
	}
	
	
	@Test
	public void checkInterface_RectangularGridSerializerDeserializer() 
											throws ClassNotFoundException {
		assertClassImplementsInterface(RECT_GRID_SERIALIZER_DESERIALIZER, 
										IGridSerializerDeserializer.class);
	}
	
	@Test
	public void checkInterface_FlexGridSerializerDeserializer() 
											throws ClassNotFoundException {
		assertClassImplementsInterface(FLEX_GRID_SERIALIZER_DESERIALIZER, 
										IGridSerializerDeserializer.class);
	}
	
	public void checkInterface_GridRobot() throws ClassNotFoundException {
		assertClassImplementsInterface(GRID_ROBOT, IGridRobot.class);
	}
	
	@Test
	public void checkInterface_Warehouse() throws ClassNotFoundException {
		assertClassImplementsInterface(WAREHOUSE, IWarehouse.class);
	}
	
	@Test
	public void checkInterface_PathPlanner() throws ClassNotFoundException {
		assertClassImplementsInterface(PATH_PLANNER, IPathPlanner.class);
	}
	
	
	
	
	@Test
	public void checkConstructor_RectangularGrid() throws ClassNotFoundException {
		assertClassHasConstructor(RECT_GRID, 
									Integer.TYPE, Integer.TYPE, GridCell.class);
	}
	
	@Test
	public void checkConstructor_FlexGrid() throws ClassNotFoundException {
		assertClassHasDefaultConstructor(FLEX_GRID);
	}
	
	@Test
	public void checkConstructor_RectangularGridSerializerDeserializer() 
												throws ClassNotFoundException {
		assertClassHasDefaultConstructor(RECT_GRID_SERIALIZER_DESERIALIZER);
	}
	
	@Test
	public void checkConstructor_FlexGridSerializerDeserializer() 
												throws ClassNotFoundException {
		assertClassHasDefaultConstructor(FLEX_GRID_SERIALIZER_DESERIALIZER);
	}
	
	@Test
	public void checkConstructor_GridRobot() throws ClassNotFoundException {
		assertClassHasConstructor(GRID_ROBOT, GridCell.class);
	}
	
	
	@Test
	public void checkConstructor_Warehouse() throws ClassNotFoundException {
		// The constructor takes an IGrid<Rack> (i.e. a floor-plan) as an argument
		assertClassHasConstructor(WAREHOUSE, IGrid.class);
	}
	
	@Test
	public void checkConstructor_PathPlanner() throws ClassNotFoundException {
		assertClassHasDefaultConstructor(PATH_PLANNER);
	}
}
