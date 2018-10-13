package edu.toronto.csc301.util;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Random;

import edu.toronto.csc301.GridCell;
import edu.toronto.csc301.IBasicRobot;
import edu.toronto.csc301.IGridRobot;
import edu.toronto.csc301.SetupTest;
import edu.toronto.csc301.IGridRobot.Direction;
import edu.toronto.csc301.util.Helpers;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.function.Function;

import edu.toronto.csc301.grid.GridCell;
import edu.toronto.csc301.grid.IGrid;
import edu.toronto.csc301.grid.IGridSerializerDeserializer;
import edu.toronto.csc301.warehouse.Rack;

import edu.toronto.csc301.robot.IGridRobot;
import edu.toronto.csc301.robot.IGridRobot.Direction;
import edu.toronto.csc301.warehouse.IPathPlanner;
import edu.toronto.csc301.warehouse.IWarehouse;


public class TestUtil {

	
	private static Random random = new Random(System.currentTimeMillis());
	
	
	/**
	 * Create a new instance of your BasicRobot implementation.
	 * 
	 * @param x Initial x-coordinate of the robot
	 * @param y Initial y-coordinate of the robot
	 * @param rotation Initial rotation of the robot
	 */
	public static IBasicRobot createBasicRobot(double x, double y, int rotation) throws Exception{
		return Helpers.newInstance(SetupTest.BASIC_ROBOT, 
				new Class<?>[]{Double.TYPE, Double.TYPE, Integer.TYPE}, 
				x, y, rotation);
	}
	
	
	/**
	 * Create a new instance of your GridRobot1 implementation.
	 * 
	 * @param robot A basic robot
	 * @param gridCellSize The size of a grid cell (i.e. the size of each 
	 *           grid cell is gridCellSize x gridCellSize).  
	 */
	public static IGridRobot createGridRobot1(IBasicRobot robot, int gridCellSize) throws Exception{
		return Helpers.newInstance(SetupTest.GRID_ROBOT1, 
				new Class<?>[]{IBasicRobot.class, Integer.TYPE}, 
				robot, gridCellSize);
	}
	
	
	/**
	 * Create a new instance of your GridRobot2 implementation.
	 * 
	 * @param initialLocation The initial location of the robot.
	 * @param initialDirection The direction the robot is facing initially.
	 * @param gridCellSize The size of a grid cell (i.e. the size of each 
	 *           grid cell is gridCellSize x gridCellSize).
	 */
	public static IGridRobot createGridRobot2(GridCell initialLocation, 
			Direction initialDirection, int gridCellSize) throws Exception{
		return Helpers.newInstance(SetupTest.GRID_ROBOT2, 
				new Class<?>[]{GridCell.class, Direction.class, Integer.TYPE}, 
				initialLocation, initialDirection, gridCellSize);
	}
	
	
	// ------------------------------------------------------------------------
	
	
	public static void assertDestinationAfterTakingSteps(IGridRobot robot, 
			List<Direction> steps, GridCell dest) throws Exception {
		
		for (Direction d : steps){
			robot.step(d);
		}
		assertEquals(dest, robot.getLocation());
	}
	
	
	public static void assertBasicRobotLocation(IBasicRobot robot, double x, double y) {
		assertEquals(x, robot.getXCoordinate(), 0.01);
		assertEquals(y, robot.getYCoordinate(), 0.01);
	}
	

	// ------------------------------------------------------------------------	

	public static GridCell randomGridCell() {
		return GridCell.at(randomInt(-10000, 10000), randomInt(-10000, 10000));
	}
	
	public static Direction randomDirection() {
		Direction[] directions = Direction.values();
		return directions[randomInt(0, directions.length)];
	}

	private static Random random = new Random();


	// ------------------------------------------------------------------------
	// Convenience factory methods

	public static <T> IGrid<T> createRectangularGrid(Class<?> T, int width, 
			int height, GridCell southWestCorner) throws Exception{
		return Helpers.newInstance(SetupTest.RECT_GRID, 
				new Class<?>[]{Integer.TYPE, Integer.TYPE, GridCell.class}, 
				width, height, southWestCorner);
	}
	
	public static IGrid<Rack> createRectangularGrid(int width, int height, GridCell sw) throws Exception{
		return createRectangularGrid(Rack.class, width, height, sw);
	}

	public static <T> IGrid<T> createFlexGrid(Class<?> T) throws Exception{
		return Helpers.newInstance(SetupTest.FLEX_GRID);
	}


	public static IGridSerializerDeserializer createRectSerializerDeserializer() throws Exception{
		return Helpers.newInstance(SetupTest.RECT_GRID_SERIALIZER_DESERIALIZER);
	}

	public static IGridSerializerDeserializer createFlexGridSerializer() throws Exception {
		return Helpers.newInstance(SetupTest.FLEX_GRID_SERIALIZER_DESERIALIZER);
	}


	// ------------------------------------------------------------------------

	/**
	 * @param path Path to a file under src/test/resources
	 */
	public static <T> IGrid<T> deserializeFromResource(String path, 
			IGridSerializerDeserializer gridDeserializer, 
			Function<byte[], T> itemDeserializer) throws Exception{
		
		try(InputStream input = System.class.getResourceAsStream(resourcePath(path))){
			return gridDeserializer.deserialize(input, itemDeserializer);
		}
	}
	
	
	public static String resourceAsString(String path) throws Exception{
		return new String(Files.readAllBytes(
			Paths.get(
				System.class.getResource(resourcePath(path)).toURI()
			)
		));
	}
	
	
	// Convenience helper
	private static String resourcePath(String path){
		if (path.startsWith("/")){
			return path;
		} else {
			return "/" + path;
		}
	}


	public static <T> String serialize2String(IGrid<T> grid, 
			IGridSerializerDeserializer serializer, 
			Function<T,byte[]> item2bytes) throws IOException{

		try(ByteArrayOutputStream baos = new ByteArrayOutputStream()){
			serializer.serialize(grid, baos, item2bytes);
			baos.flush();
			return new String(baos.toByteArray());
		}
	}




	public static GridCell randomCellOutsideOfRectangle(int width, int height, GridCell sw){
		// Either west or east of the rectangle
		int x = random.nextBoolean() ? 
				randomInt(sw.x - randomInt(1, 1000), sw.x) :
				randomInt(sw.x + width, sw.x + width + randomInt(1, 1000));

		// Either south or north of the rectangle
		int y = random.nextBoolean() ? 
				randomInt(sw.y - randomInt(1, 1000), sw.y) :
				randomInt(sw.y + height, sw.y + height + randomInt(1, 1000));

		return GridCell.at(x, y);
	}



	/**
	 * @return A cell that is different than the given <code>cell</code> (in its 
	 * x-coordinate, y-coordinate or both).
	 */
	public static GridCell randomCellOtherThan(GridCell cell){
		return randomCellOutsideOfRectangle(1, 1, cell);
	}


	public static GridCell randomCell(){
		return GridCell.at(randomInt(-10000, 10000), randomInt(-10000, 10000));
	}

	
	private static Random random = new Random();
	
	
	public static IGridRobot createGridRobot(GridCell initialLocation) throws Exception {
		return Helpers.newInstance(SetupTest.GRID_ROBOT, 
				new Class<?>[]{GridCell.class}, initialLocation);
	}

	
	public static IWarehouse createWarehouse(IGrid<Rack> floorPlan) throws Exception {
		return Helpers.newInstance(SetupTest.WAREHOUSE, 
				new Class<?>[]{IGrid.class}, floorPlan);
	}
	
	public static IPathPlanner createPathPlanner() throws Exception {
		return Helpers.newInstance(SetupTest.PATH_PLANNER);
	}
	
	
	
	//
	// WARNING: Do NOT use this helper function in your application code.
	//          This helper function is part of the testing code, and an application 
	//          should NEVER depend on its testing code.
	//
	//          Feel free to copy-paste it to one of your classes under src/main,
	//          and use it from there.
	//          
	public static GridCell oneCellOver(GridCell location, Direction direction){
		switch (direction) {
		case NORTH:
			return GridCell.at(location.x, location.y + 1);
		case EAST:
			return GridCell.at(location.x + 1, location.y);
		case SOUTH:
			return GridCell.at(location.x, location.y - 1);
		case WEST:
			return GridCell.at(location.x - 1, location.y);
		default:
			return null;
		}
	}
	
	
	
	
	
	public static Direction randomDirection(){
		Direction[] directions = Direction.values();
		return directions[random.nextInt(directions.length)];
	}
	
	public static GridCell randomCell(){
		return GridCell.at(randomInt(-10000, 10000), randomInt(-10000, 10000));
	}
	
	/**
	 * Return a random integer in the range [a, b).
	 * That is, including a and excluding b.
	 */
	public static int randomInt(int a, int b){
		return a + random.nextInt(b - a);
	}		
	

	public static GridCell oneCellOver(GridCell fromCell, Direction direction) {
		switch (direction) {
		case NORTH:
			return GridCell.at(fromCell.x, fromCell.y + 1);
		case EAST:
			return GridCell.at(fromCell.x + 1, fromCell.y);
		case SOUTH:
			return GridCell.at(fromCell.x, fromCell.y - 1);
		case WEST:
			return GridCell.at(fromCell.x - 1, fromCell.y);
		default:
			return null;
		}
	}

}
