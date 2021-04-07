import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import model.AnimatorModel;
import model.AnimatorModelImpl;
import model.ChangeColor;
import model.Color;
import model.IMotion;
import model.IShape;
import model.Move;
import model.Oval;
import model.Position;
import model.Rectangle;
import model.Scale;

import static org.junit.Assert.assertEquals;

/**
 * This class is to test the AnimatorModel interface and the AnimatorModelImpl class.
 */
public class AnimatorModelImplTest {
  private AnimatorModel m;
  private AnimatorModel m2;
  private IShape r;
  private IShape c;
  private IMotion rMove1;
  private IMotion rScale1;
  private IMotion rChangeColor1;
  private IMotion rMove2;
  private IMotion rScale2;
  private IMotion rChangeColor2;
  private IMotion cMove1;
  private IMotion cScale1;
  private IMotion cChangeColor1;
  private IMotion cMove2;
  private IMotion cScale2;
  private IMotion cChangeColor2;

  /**
   * Setups for the test class.
   */
  @Before
  public void setup() {
    m = new AnimatorModelImpl();
    r = new Rectangle("R", new Position(200, 200),
            new Color(1, 0, 0), 1, 100,
            50, 100);
    c = new Oval("C", new Position(500, 100),
            new Color(0, 0, 1), 6, 100,
            60, 30);
    rMove1 = new Move(10, 50, "R", new Position(200, 200),
            new Position(300, 300));
    rScale1 = new Scale(51, 70, "R", new double[]{50, 100},
            new double[]{25, 100});
    rChangeColor1 = new ChangeColor(20, 30, "r",
            new Color(1, 0, 0), new Color(1, 1, 1));
    cMove1 = new Move(20, 70, "C", new Position(500, 100),
            new Position(500, 400));
    cScale1 = new Scale(70, 95, "c",
            new double[]{60, 30}, new double[]{25, 25});
    cChangeColor1 = new ChangeColor(50, 80, "C",
            new Color(0, 0, 1), new Color(0, 1, 0));
    rMove2 = new Move(51, 90, "R", new Position(300, 300),
            new Position(250, 350));
    rScale2 = new Scale(71, 80, "r", new double[]{25, 100},
            new double[]{30, 70});
    rChangeColor2 = new ChangeColor(31, 50, "R",
            new Color(1, 1, 1), new Color(1, 3, 1));
    cMove2 = new Move(71, 75, "c", new Position(500, 400),
            new Position(80, 80));
    cScale2 = new Scale(96, 100, "C",
            new double[]{25, 25}, new double[]{40, 50});
    cChangeColor2 = new ChangeColor(81, 90, "C",
            new Color(0, 1, 0), new Color(20, 20, 100));
    m2 = new AnimatorModelImpl();
    m2.addShape(r);
    m2.addShape(c);
  }

  /**
   * Test the method of adding a shape, while the data of the newly added shape are different
   * from all other shapes in the map.
   */
  @Test
  public void testAddShape() {
    m.addShape(new Rectangle("R_1", new Position(300, 100),
            new Color(0, 0, 255), 20, 80,
            40, 65));
    assertEquals("Shapes: \n"
            + "Name: R_1\n"
            + "Type: rectangle\n"
            + "Min corner: (300.0,100.0), Width: 40.0, Height: 65.0, Color: (0.0,0.0,255.0)\n"
            + "Appears at t=20\n"
            + "Disappears at t=80\n\n", m.toString());
    m.addShape(new Oval("C_1", new Position(150, 370),
            new Color(255, 0, 0), 40, 90,
            100, 120));
    assertEquals("Shapes: \n"
            + "Name: R_1\n"
            + "Type: rectangle\n"
            + "Min corner: (300.0,100.0), Width: 40.0, Height: 65.0, Color: (0.0,0.0,255.0)\n"
            + "Appears at t=20\n"
            + "Disappears at t=80\n\n"
            + "Name: C_1\n"
            + "Type: oval\n"
            + "Center: (150.0,370.0), X radius: 100.0, Y radius: 120.0, Color: (255.0,0.0,0.0)\n"
            + "Appears at t=40\n"
            + "Disappears at t=90\n\n", m.toString());
  }

  /**
   * Test the method of adding a shape, while the data of the newly added shape,
   * except the shape name, are identical to another shape in the map.
   */
  @Test
  public void testAddShape2() {
    // As long as the name of the shape to be added is different from the names of all shapes
    // currently in our map, this operation should be successful, regardless of whether or not
    // its other data are exactly the same as one of the shapes in the map.
    m2.addShape(new Rectangle("Rectangle_2", new Position(200, 200),
            new Color(1, 0, 0), 1, 100,
            50, 100));
    assertEquals("Shapes: \n"
            + "Name: R\n"
            + "Type: rectangle\n"
            + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,0.0,0.0)\n"
            + "Appears at t=1\n"
            + "Disappears at t=100\n\n"
            + "Name: C\n"
            + "Type: oval\n"
            + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,1.0)\n"
            + "Appears at t=6\n"
            + "Disappears at t=100\n\n"
            + "Name: Rectangle_2\n"
            + "Type: rectangle\n"
            + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,0.0,0.0)\n"
            + "Appears at t=1\n"
            + "Disappears at t=100\n\n", m2.toString());
  }

  /**
   * Test invalid inputs of adding a shape.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeInvalid() {
    // test by adding null
    m2.addShape(null);
    // test by adding a shape object that already exists in our map
    m2.addShape(r);
    m2.addShape(c);
    // test by adding a new shape object with exactly same data as one of the shapes in our map
    m2.addShape(new Rectangle("R", new Position(200, 200),
            new Color(1, 0, 0), 1, 100,
            50, 100));
    m2.addShape(new Oval("C", new Position(500, 100),
            new Color(0, 0, 1), 6, 100,
            60, 30));
    // test by adding a new shape with only same name but different data
    m2.addShape(new Rectangle("R", new Position(90, -20),
            new Color(0, 255, 0), 30,
            70, 25, 90));
    m2.addShape(new Oval("C", new Position(-110, 99),
            new Color(255, 0, 255), 70,
            85, 90, 120));
    // shape name is case insensitive
    m2.addShape(new Rectangle("r", new Position(90, -20),
            new Color(0, 255, 0), 30,
            70, 25, 90));
    m2.addShape(new Oval("c", new Position(-110, 99),
            new Color(255, 0, 255), 70,
            85, 90, 120));
  }

  /**
   * Test the method of removing a shape. Motions associated with the shape will also be removed.
   */
  @Test
  public void testRemoveShape() {
    m2.addMotion(new Move(10, 50, "R", new Position(200, 200),
            new Position(300, 300)));
    m2.addMotion(new Scale(51, 70, "R", new double[]{50, 100},
            new double[]{25, 100}));
    m2.addMotion(new Move(70, 100, "R", new Position(300, 300),
            new Position(200, 200)));
    m2.addMotion(new Move(20, 70, "C", new Position(500, 100),
            new Position(500, 400)));
    m2.addMotion(new ChangeColor(50, 80, "C",
            new Color(0, 0, 1), new Color(0, 1, 0)));
    assertEquals("Shapes: \n"
                    + "Name: R\n"
                    + "Type: rectangle\n"
                    + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, "
                    + "Color: (1.0,0.0,0.0)\n"
                    + "Appears at t=1\n"
                    + "Disappears at t=100\n\n"
                    + "Name: C\n"
                    + "Type: oval\n"
                    + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, "
                    + "Color: (0.0,0.0,1.0)\n"
                    + "Appears at t=6\n"
                    + "Disappears at t=100\n\n"
                    + "Shape R moves from (200.0,200.0) to (300.0,300.0) from t=10 to t=50\n"
                    + "Shape C moves from (500.0,100.0) to (500.0,400.0) from t=20 to t=70\n"
                    + "Shape C changes color from (0.0,0.0,1.0) to (0.0,1.0,0.0) "
                    + "from t=50 to t=80\n"
                    + "Shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, "
                    + "Height: 100.0 from t=51 to t=70\n"
                    + "Shape R moves from (300.0,300.0) to (200.0,200.0) from t=70 to t=100",
            m2.toString());
    m2.removeShape(r);
    assertEquals("Shapes: \n"
                    + "Name: C\n"
                    + "Type: oval\n"
                    + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, "
                    + "Color: (0.0,0.0,1.0)\n"
                    + "Appears at t=6\n"
                    + "Disappears at t=100\n\n"
                    + "Shape C moves from (500.0,100.0) to (500.0,400.0) from t=20 to t=70\n"
                    + "Shape C changes color from (0.0,0.0,1.0) to (0.0,1.0,0.0) from t=50 to t=80",
            m2.toString());
    m2.removeShape(c);
    assertEquals("", m2.toString());
  }

  /**
   * Test invalid inputs of removing a shape.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testRemoveShapeInvalid() {
    // test by removing null
    m2.removeShape(null);
    // test by removing a new shape object with exactly same name and same data
    // as one of the shape in our map
    m2.removeShape(new Rectangle("R", new Position(200, 200),
            new Color(1, 0, 0), 1, 100,
            50, 100));
    m2.removeShape(new Oval("C", new Position(500, 100),
            new Color(0, 0, 1), 6, 100,
            60, 30));
    // test by removing a new shape object with same name but different data
    m2.removeShape(new Rectangle("R", new Position(-100, 200),
            new Color(1, 1, 1), 8, 10,
            50, 20));
    m2.removeShape(new Oval("c", new Position(50, -100),
            new Color(0, 255, 0), 23, 72,
            0, 10));
    // test by removing a new shape object with different name but same data
    m2.removeShape(new Rectangle("rect", new Position(200, 200),
            new Color(1, 0, 0), 1, 100,
            50, 100));
    m2.removeShape(new Oval("oval", new Position(500, 100),
            new Color(0, 0, 1), 6, 100,
            60, 30));
    // test by removing a new shape object with different name and different data
    m2.removeShape(new Rectangle("R_1", new Position(-100, -200),
            new Color(1, 143, 244), 29, 48,
            10, 90));
    m2.removeShape(new Oval("c_2", new Position(-50, -100),
            new Color(190, 255, 0), 90, 120,
            70, 10));
  }

  /**
   * Test the method of adding a motion for a shape, while the data of the newly added motion are
   * different from all other motions in the list.
   */
  @Test
  public void testAddMotion() {
    m2.addMotion(rMove1);
    m2.addMotion(rScale1);
    // The motion below should be added successfully since same character
    // with upper or lower cases should be treated the same, and we already have shape R in m2.
    m2.addMotion(rChangeColor1);

    m2.addMotion(cMove1);
    m2.addMotion(cScale1);
    m2.addMotion(cChangeColor1);

    // Same type of motions should be added successfully under a shape as long as it is not a
    // duplicated motion and all the inputs are correct
    m2.addMotion(rMove2);
    m2.addMotion(rScale2);
    m2.addMotion(rChangeColor2);

    m2.addMotion(cMove2);
    m2.addMotion(cScale2);
    m2.addMotion(cChangeColor2);

    assertEquals("Shapes: \n"
                    + "Name: R\n"
                    + "Type: rectangle\n"
                    + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, "
                    + "Color: (1.0,0.0,0.0)\n"
                    + "Appears at t=1\n"
                    + "Disappears at t=100\n\n"
                    + "Name: C\n"
                    + "Type: oval\n"
                    + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, "
                    + "Color: (0.0,0.0,1.0)\n"
                    + "Appears at t=6\n"
                    + "Disappears at t=100\n\n"
                    + "Shape R moves from (200.0,200.0) to (300.0,300.0) from t=10 to t=50\n"
                    + "Shape r changes color from (1.0,0.0,0.0) to (1.0,1.0,1.0) "
                    + "from t=20 to t=30\n"
                    + "Shape C moves from (500.0,100.0) to (500.0,400.0) from t=20 to t=70\n"
                    + "Shape R changes color from (1.0,1.0,1.0) to (1.0,3.0,1.0) "
                    + "from t=31 to t=50\n"
                    + "Shape C changes color from (0.0,0.0,1.0) to (0.0,1.0,0.0) "
                    + "from t=50 to t=80\n"
                    + "Shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, "
                    + "Height: 100.0 from t=51 to t=70\n"
                    + "Shape R moves from (300.0,300.0) to (250.0,350.0) from t=51 to t=90\n"
                    + "Shape c scales from Width: 60.0, Height: 30.0 to Width: 25.0, "
                    + "Height: 25.0 from t=70 to t=95\n"
                    + "Shape r scales from Width: 25.0, Height: 100.0 to Width: 30.0, "
                    + "Height: 70.0 from t=71 to t=80\n"
                    + "Shape c moves from (500.0,400.0) to (80.0,80.0) from t=71 to t=75\n"
                    + "Shape C changes color from (0.0,1.0,0.0) to (20.0,20.0,100.0) "
                    + "from t=81 to t=90\n"
                    + "Shape C scales from Width: 25.0, Height: 25.0 to Width: 40.0, "
                    + "Height: 50.0 from t=96 to t=100",
            m2.toString());
  }

  /**
   * Test the method of adding a motion for a shape, while the data of the newly added motion,
   * except the shape name, are identical to another motion in the list.
   */
  @Test
  public void testAddMotion2() {
    // As long as the the newly added motion is different from other motions under the same type of
    // a specific shape, the new motion should be added successfully, regardless of whether or not
    // its other data are exactly the same as one of the motions of another shape in the list.
    m2.addMotion(rMove1);
    m2.addMotion(rScale1);
    m2.addMotion(rChangeColor1);

    m2.addMotion(new Move(51, 70, "C", new Position(500, 100),
            new Position(200, 200)));
    m2.addMotion(new Scale(71, 95, "c",
            new double[]{60, 30}, new double[]{50, 100}));
    m2.addMotion(new ChangeColor(50, 80, "C",
            new Color(0, 0, 1), new Color(1, 0, 0)));

    m2.addMotion(rMove2);
    m2.addMotion(rScale2);
    m2.addMotion(rChangeColor2);

    // The motions below for shape C should be added successfully even though all of their data,
    // except the shape name, are identical to the motions we initially added for shape R
    m2.addMotion(new Move(10, 50, "c", new Position(200, 200),
            new Position(300, 300)));
    m2.addMotion(new Scale(51, 70, "C",
            new double[]{50, 100}, new double[]{25, 100}));
    m2.addMotion(new ChangeColor(20, 30, "C",
            new Color(1, 0, 0), new Color(1, 1, 1)));

    assertEquals("Shapes: \n"
                    + "Name: R\n"
                    + "Type: rectangle\n"
                    + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, "
                    + "Color: (1.0,0.0,0.0)\n"
                    + "Appears at t=1\n"
                    + "Disappears at t=100\n\n"
                    + "Name: C\n"
                    + "Type: oval\n"
                    + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, "
                    + "Color: (0.0,0.0,1.0)\n"
                    + "Appears at t=6\n"
                    + "Disappears at t=100\n\n"
                    + "Shape R moves from (200.0,200.0) to (300.0,300.0) from t=10 to t=50\n"
                    + "Shape c moves from (200.0,200.0) to (300.0,300.0) from t=10 to t=50\n"
                    + "Shape r changes color from (1.0,0.0,0.0) to (1.0,1.0,1.0) "
                    + "from t=20 to t=30\n"
                    + "Shape C changes color from (1.0,0.0,0.0) to (1.0,1.0,1.0) "
                    + "from t=20 to t=30\n"
                    + "Shape R changes color from (1.0,1.0,1.0) to (1.0,3.0,1.0) "
                    + "from t=31 to t=50\n"
                    + "Shape C changes color from (0.0,0.0,1.0) to (1.0,0.0,0.0) "
                    + "from t=50 to t=80\n"
                    + "Shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, "
                    + "Height: 100.0 from t=51 to t=70\n"
                    + "Shape C moves from (500.0,100.0) to (200.0,200.0) from t=51 to t=70\n"
                    + "Shape R moves from (300.0,300.0) to (250.0,350.0) from t=51 to t=90\n"
                    + "Shape C scales from Width: 50.0, Height: 100.0 to Width: 25.0, "
                    + "Height: 100.0 from t=51 to t=70\n"
                    + "Shape c scales from Width: 60.0, Height: 30.0 to Width: 50.0, "
                    + "Height: 100.0 from t=71 to t=95\n"
                    + "Shape r scales from Width: 25.0, Height: 100.0 to Width: 30.0, "
                    + "Height: 70.0 from t=71 to t=80",
            m2.toString());
  }

  /**
   * Test null input of adding a motion.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testAddMotionNull() {
    m2.addMotion(null);
  }

  /**
   * Test invalid shape inputs (shape doesn't exist) of adding a motion.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testAddMotionInvalidShape() {
    m2.addMotion(new Move(51, 70, "C1", new Position(500, 100),
            new Position(200, 200)));
    m2.addMotion(new Scale(71, 95, "e",
            new double[]{60, 30}, new double[]{50, 100}));
    m2.addMotion(new ChangeColor(50, 80, "Rec",
            new Color(0, 0, 1), new Color(1, 0, 0)));
  }

  /**
   * Test invalid start tick and end tick inputs (out of the shape's tick range) of adding a motion.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testAddMotionInvalidTickRange() {
    m2.addMotion((new Move(-1, 50, "R", new Position(200, 200),
            new Position(300, 300))));
    m2.addMotion(new Scale(10, 101, "R", new double[]{50, 100},
            new double[]{25, 100}));
    m2.addMotion(new ChangeColor(100, 100, "r",
            new Color(1, 0, 0), new Color(1, 1, 1)));
    m2.addMotion((new Move(-1, 0, "R", new Position(200, 200),
            new Position(300, 300))));
  }

  /**
   * Test adding a duplicated motion for a shape.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testAddMotionDuplicatedMotion() {
    m2.addMotion(rMove1);
    m2.addMotion((new Move(10, 50, "R", new Position(200, 200),
            new Position(300, 300))));
    m2.addMotion(rScale2);
    m2.addMotion(rChangeColor2);
    m2.addMotion(new Scale(71, 80, "r", new double[]{25, 100},
            new double[]{30, 70}));
    m2.addMotion(new ChangeColor(31, 50, "R",
            new Color(1, 1, 1), new Color(1, 3, 1)));
  }

  /**
   * Test adding an initial motion for a shape with invalid inputs - start parameter is different
   * from the shape's initial values.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testAddMotionInvalidInputs1() {
    m2.addMotion((new Move(10, 50, "R", new Position(201, 200),
            new Position(300, 300))));
    m2.addMotion(new Scale(51, 70, "R", new double[]{50, 102},
            new double[]{25, 100}));
    m2.addMotion(new ChangeColor(50, 80, "C",
            new Color(0, 1, 1), new Color(1, 0, 0)));
  }

  /**
   * Test adding a motion (not initial motion) for a shape with invalid inputs - start parameter is
   * different from the end parameter of the last motion under the same type.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testAddMotionInvalidInputs2() {
    m2.addMotion((new Move(10, 50, "R", new Position(200, 200),
            new Position(300, 300))));
    m2.addMotion(new Scale(51, 70, "R", new double[]{50, 100},
            new double[]{25, 100}));
    m2.addMotion(new ChangeColor(20, 30, "r",
            new Color(1, 0, 0), new Color(1, 1, 1)));

    m2.addMotion(new Move(51, 70, "C", new Position(500, 100),
            new Position(200, 200)));
    m2.addMotion(new Scale(71, 95, "c",
            new double[]{60, 30}, new double[]{50, 100}));
    m2.addMotion(new ChangeColor(50, 80, "C",
            new Color(0, 0, 1), new Color(1, 0, 0)));

    m2.addMotion((new Move(51, 90, "R", new Position(300, 302),
            new Position(250, 350))));
    m2.addMotion(new Scale(71, 80, "r", new double[]{30, 100},
            new double[]{30, 70}));
    m2.addMotion(new ChangeColor(31, 50, "R",
            new Color(1, 2, 1), new Color(1, 3, 1)));

    m2.addMotion(new Move(10, 50, "c", new Position(202, 200),
            new Position(300, 300)));
    m2.addMotion(new Scale(51, 70, "C",
            new double[]{50, 110}, new double[]{25, 100}));
    m2.addMotion(new ChangeColor(20, 30, "C",
            new Color(1, 1, 1), new Color(1, 1, 1)));
  }

  /**
   * Test adding a motion that conflicts with an old motion under the same type.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testAddMotionConflicts() {

    m2.addMotion(rMove1);
    m2.addMotion((new Move(5, 50, "R", new Position(300, 300),
            new Position(250, 350))));
    m2.addMotion((new Move(10, 60, "R", new Position(300, 300),
            new Position(250, 350))));
    m2.addMotion((new Move(10, 50, "R", new Position(300, 300),
            new Position(250, 350))));

    m2.addMotion(rScale1);
    m2.addMotion(new Scale(20, 81, "r", new double[]{25, 100},
            new double[]{30, 70}));
    m2.addMotion(new Scale(65, 67, "r", new double[]{25, 100},
            new double[]{30, 70}));
    m2.addMotion(new Scale(61, 70, "r", new double[]{25, 100},
            new double[]{30, 70}));

    m2.addMotion(cChangeColor1);
    m2.addMotion(new ChangeColor(50, 75, "C",
            new Color(0, 1, 0), new Color(20, 20, 100)));
    m2.addMotion(new ChangeColor(25, 66, "C",
            new Color(0, 1, 0), new Color(20, 20, 100)));
    m2.addMotion(new ChangeColor(65, 100, "C",
            new Color(0, 1, 0), new Color(20, 20, 100)));
  }

  /**
   * Test the method of removing a motion for a shape.
   */
  @Test
  public void testRemoveMotion() {
    m2.addMotion(rMove1);
    m2.addMotion(rScale1);
    m2.addMotion(rChangeColor1);

    m2.addMotion(cMove1);
    m2.addMotion(cScale1);
    m2.addMotion(cChangeColor1);

    m2.addMotion(rMove2);
    m2.addMotion(rScale2);
    m2.addMotion(rChangeColor2);

    m2.addMotion(cMove2);
    m2.addMotion(cScale2);
    m2.addMotion(cChangeColor2);

    m2.removeMotion(rMove1);
    m2.removeMotion(cScale1);
    m2.removeMotion(rScale2);
    m2.removeMotion(cChangeColor2);

    assertEquals("Shapes: \n"
                    + "Name: R\n"
                    + "Type: rectangle\n"
                    + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, "
                    + "Color: (1.0,0.0,0.0)\n"
                    + "Appears at t=1\n"
                    + "Disappears at t=100\n\n"
                    + "Name: C\n"
                    + "Type: oval\n"
                    + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, "
                    + "Color: (0.0,0.0,1.0)\n"
                    + "Appears at t=6\n"
                    + "Disappears at t=100\n\n"
                    + "Shape r changes color from (1.0,0.0,0.0) to (1.0,1.0,1.0) "
                    + "from t=20 to t=30\n"
                    + "Shape C moves from (500.0,100.0) to (500.0,400.0) from t=20 to t=70\n"
                    + "Shape R changes color from (1.0,1.0,1.0) to (1.0,3.0,1.0) "
                    + "from t=31 to t=50\n"
                    + "Shape C changes color from (0.0,0.0,1.0) to (0.0,1.0,0.0) "
                    + "from t=50 to t=80\n"
                    + "Shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, "
                    + "Height: 100.0 from t=51 to t=70\n"
                    + "Shape R moves from (300.0,300.0) to (250.0,350.0) from t=51 to t=90\n"
                    + "Shape c moves from (500.0,400.0) to (80.0,80.0) from t=71 to t=75\n"
                    + "Shape C scales from Width: 25.0, Height: 25.0 to Width: 40.0, "
                    + "Height: 50.0 from t=96 to t=100",
            m2.toString());
  }

  /**
   * Test null input of removing a motion.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testRemoveMotionNull() {
    m2.removeMotion(null);
  }

  /**
   * Test invalid shape inputs (shape doesn't exist) of removing a motion.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testRemoveMotionInvalidShape() {
    m2.removeMotion(new Move(51, 70, "C1", new Position(500, 100),
            new Position(200, 200)));
    m2.removeMotion(new Scale(71, 95, "e",
            new double[]{60, 30}, new double[]{50, 100}));
    m2.removeMotion(new ChangeColor(50, 80, "Rec",
            new Color(0, 0, 1), new Color(1, 0, 0)));
  }

  /**
   * Test removing a motion when no motion in the list is matched with the given shape name.
   */
  @Test (expected = NoSuchElementException.class)
  public void testRemoveMotionNoMotion() {
    m2.addShape(new Rectangle("Rec", new Position(20, 20),
            new Color(35, 40, 40), 25, 60,
            55, 55));
    IMotion testMove = new Move(5, 10, "Rec",
            new Position(50, -100), new Position(20, 20));
    m2.removeMotion(testMove);
  }
}
