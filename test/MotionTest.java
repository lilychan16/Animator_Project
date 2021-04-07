import org.junit.Before;
import org.junit.Test;

import model.ChangeColor;
import model.Color;
import model.IMotion;
import model.Move;
import model.Position;
import model.Scale;

import static org.junit.Assert.assertEquals;

/**
 * Public test class for IMotion interface.
 */
public class MotionTest {
  private IMotion m1;
  private IMotion m2;
  private IMotion s1;
  private IMotion s2;
  private IMotion c1;
  private IMotion c2;

  /**
   * Setups for move, scale, and color changing motions.
   */
  @Before
  public void setup() {
    m1 = new Move(0, 100, "R",
            new Position(0, 100), new Position(0, 200));
    m2 = new Move(20, 85, "e",
            new Position(-350, 20), new Position(100, -150));
    s1 = new Scale(10, 150, "r",
            new double[]{50, 60}, new double[]{20, 25});
    s2 = new Scale(35, 50, "E",
            new double[]{30, 75}, new double[]{100, 200});
    c1 = new ChangeColor(0, 70, "T",
            new Color(1, 2, 1), new Color(0, 1, 0));
    c2 = new ChangeColor(10, 25, "a",
            new Color(35, 40, 40), new Color(100, 100, 255));
  }

  /**
   * Test valid move motion constructor.
   */
  @Test
  public void testValidMove() {
    assertEquals(0, m1.getStartTick());
    assertEquals(100, m1.getEndTick());
    assertEquals("R", m1.getShapeName());
    assertEquals(0, ((Move)m1).getStartPosition().getX(), 0.001);
    assertEquals(100, ((Move)m1).getStartPosition().getY(), 0.001);
    assertEquals(0, ((Move)m1).getEndPosition().getX(), 0.001);
    assertEquals(200, ((Move)m1).getEndPosition().getY(), 0.001);
    assertEquals(20, m2.getStartTick());
    assertEquals(85, m2.getEndTick());
    assertEquals("e", m2.getShapeName());
    assertEquals(-350, ((Move)m2).getStartPosition().getX(), 0.001);
    assertEquals(20, ((Move)m2).getStartPosition().getY(), 0.001);
    assertEquals(100, ((Move)m2).getEndPosition().getX(), 0.001);
    assertEquals(-150, ((Move)m2).getEndPosition().getY(), 0.01);
  }

  /**
   * Test valid scale motion constructor.
   */
  @Test
  public void testValidScale() {
    assertEquals(10, s1.getStartTick());
    assertEquals(150, s1.getEndTick());
    assertEquals("r", s1.getShapeName());
    double[] s1StartDimensions = ((Scale)s1).getStartDimension();
    assertEquals(50, s1StartDimensions[0], 0.001);
    assertEquals(60, s1StartDimensions[1], 0.001);
    double[] s1EndDimensions = ((Scale)s1).getEndDimension();
    assertEquals(20, s1EndDimensions[0], 0.001);
    assertEquals(25, s1EndDimensions[1], 0.001);
    assertEquals(35, s2.getStartTick());
    assertEquals(50, s2.getEndTick());
    assertEquals("E", s2.getShapeName());
    double[] s2StartDimensions = ((Scale)s2).getStartDimension();
    assertEquals(30, s2StartDimensions[0], 0.001);
    assertEquals(75, s2StartDimensions[1], 0.001);
    double[] s2EndDimensions = ((Scale)s2).getEndDimension();
    assertEquals(100, s2EndDimensions[0], 0.001);
    assertEquals(200, s2EndDimensions[1], 0.001);
  }

  /**
   * Test valid color changing motion constructor.
   */
  @Test
  public void testValidChangeColor() {
    assertEquals(0, c1.getStartTick());
    assertEquals(70, c1.getEndTick());
    assertEquals("T", c1.getShapeName());
    assertEquals(1, ((ChangeColor)c1).getStartColor().getRed(), 0.001);
    assertEquals(2, ((ChangeColor)c1).getStartColor().getGreen(), 0.001);
    assertEquals(1, ((ChangeColor)c1).getStartColor().getBlue(), 0.001);
    assertEquals(0, ((ChangeColor)c1).getEndColor().getRed(), 0.001);
    assertEquals(1, ((ChangeColor)c1).getEndColor().getGreen(), 0.001);
    assertEquals(0, ((ChangeColor)c1).getEndColor().getBlue(), 0.001);
    assertEquals(10, c2.getStartTick());
    assertEquals(25, c2.getEndTick());
    assertEquals("a", c2.getShapeName());
    assertEquals(35, ((ChangeColor)c2).getStartColor().getRed(), 0.001);
    assertEquals(40, ((ChangeColor)c2).getStartColor().getGreen(), 0.001);
    assertEquals(40, ((ChangeColor)c2).getStartColor().getBlue(), 0.001);
    assertEquals(100, ((ChangeColor)c2).getEndColor().getRed(), 0.001);
    assertEquals(100, ((ChangeColor)c2).getEndColor().getGreen(), 0.001);
    assertEquals(255, ((ChangeColor)c2).getEndColor().getBlue(), 0.001);
  }

  /**
   * Test invalid start tick for motions.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidStartTick() {
    new Move(-10, 10, "R",
            new Position(0, 100), new Position(0, 200));
    new Scale(-10, 10, "r", new double[]{50, 60}, new double[]{20, 25});
    new ChangeColor(-10, 10, "T",
            new Color(1, 2, 1), new Color(0, 1, 0));
    new Move(10, 10, "a",
            new Position(-350, 20), new Position(100, -150));
    new Move(30, 10, "a",
            new Position(-350, 20), new Position(100, -150));
    new Scale(10, 10, "E", new double[]{30, 75}, new double[]{100, 200});
    new Scale(30, 10, "E", new double[]{30, 75}, new double[]{100, 200});
    new ChangeColor(10, 10, "a",
            new Color(35, 40, 40), new Color(100, 100, 255));
    new ChangeColor(30, 10, "a",
            new Color(35, 40, 40), new Color(100, 100, 255));
  }

  /**
   * Test invalid end tick for motions.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidEndTick() {
    new Move(20, -100, "R",
            new Position(0, 100), new Position(0, 200));
    new Scale(20, -100, "r", new double[]{50, 60}, new double[]{20, 25});
    new ChangeColor(20, -100, "T",
            new Color(1, 2, 1), new Color(0, 1, 0));
  }

  /**
   * Test invalid shape name for motions.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidShapeName() {
    new Move(20, 100, null,
            new Position(0, 100), new Position(0, 200));
    new Move(20, 100, "",
            new Position(0, 100), new Position(0, 200));
    new Scale(20, 100, null,
            new double[]{30, 75}, new double[]{100, 200});
    new Scale(20, 100, "", new double[]{30, 75}, new double[]{100, 200});
    new ChangeColor(20, 100, null,
            new Color(35, 40, 40), new Color(100, 100, 255));
    new ChangeColor(20, 100, "",
            new Color(35, 40, 40), new Color(100, 100, 255));
  }

  /**
   * Test invalid start and end positions for move motion.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidMovePositions() {
    new Move(30, 75, "R", null, new Position(0, 200));
    new Move(30, 75, "R", new Position(0, 100), null);
  }

  /**
   * Test invalid start and end dimensions for scale motion.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidScaleDimensions() {
    new Scale(30, 75, "r", new double[]{-50, 60}, new double[]{20, 25});
    new Scale(30, 75, "r", new double[]{50, -60}, new double[]{20, 25});
    new Scale(30, 75, "r", new double[]{50, 60}, new double[]{-20, 25});
    new Scale(30, 75, "r", new double[]{50, 60}, new double[]{20, -25});
    new Scale(30, 75, "r", null, new double[]{20, 25});
    new Scale(30, 75, "r", new double[]{50, 60}, null);
    new Scale(30, 75, "E",
            new double[]{30, 75, 100}, new double[]{100, 200});
    new Scale(30, 75, "E",
            new double[]{30, 75}, new double[]{100, 200, 55});
  }

  /**
   * Test invalid start and end colors for color changing motion.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidChangeColor() {
    new ChangeColor(30, 75, "T",
            null, new Color(0, 1, 0));
    new ChangeColor(30, 75, "T",
            new Color(1, 2, 1), null);
  }

  /**
   * Test method for getting the start tick of a motion.
   */
  @Test
  public void testGetStartTick() {
    assertEquals(0, m1.getStartTick());
    assertEquals(20, m2.getStartTick());
    assertEquals(10, s1.getStartTick());
    assertEquals(35, s2.getStartTick());
    assertEquals(0, c1.getStartTick());
    assertEquals(10, c2.getStartTick());
  }

  /**
   * Test method for getting the end tick of a motion.
   */
  @Test
  public void testGetEndTick() {
    assertEquals(100, m1.getEndTick());
    assertEquals(85, m2.getEndTick());
    assertEquals(150, s1.getEndTick());
    assertEquals(50, s2.getEndTick());
    assertEquals(70, c1.getEndTick());
    assertEquals(25, c2.getEndTick());
  }

  /**
   * Test method for getting the shape name of a motion.
   */
  @Test
  public void testGetShapeName() {
    assertEquals("R", m1.getShapeName());
    assertEquals("e", m2.getShapeName());
    assertEquals("r", s1.getShapeName());
    assertEquals("E", s2.getShapeName());
    assertEquals("T", c1.getShapeName());
    assertEquals("a", c2.getShapeName());
  }

  /**
   * Test method for getting the start position of a move motion.
   */
  @Test
  public void testGetStartPosition() {
    assertEquals(0, ((Move)m1).getStartPosition().getX(), 0.001);
    assertEquals(100, ((Move)m1).getStartPosition().getY(), 0.001);
    assertEquals(-350, ((Move)m2).getStartPosition().getX(), 0.001);
    assertEquals(20, ((Move)m2).getStartPosition().getY(), 0.001);
  }

  /**
   * Test method for getting the end position of a move motion.
   */
  @Test
  public void testGetEndPosition() {
    assertEquals(0, ((Move)m1).getEndPosition().getX(), 0.001);
    assertEquals(200, ((Move)m1).getEndPosition().getY(), 0.001);
    assertEquals(100, ((Move)m2).getEndPosition().getX(), 0.001);
    assertEquals(-150, ((Move)m2).getEndPosition().getY(), 0.01);
  }

  /**
   * Test method for getting the start dimensions of a scale motion.
   */
  @Test
  public void testGetStartDimensions() {
    double[] s1StartDimensions = ((Scale)s1).getStartDimension();
    assertEquals(50, s1StartDimensions[0], 0.001);
    assertEquals(60, s1StartDimensions[1], 0.001);
    double[] s2StartDimensions = ((Scale)s2).getStartDimension();
    assertEquals(30, s2StartDimensions[0], 0.001);
    assertEquals(75, s2StartDimensions[1], 0.001);
  }

  /**
   * Test method for getting the end dimensions of a scale motion.
   */
  @Test
  public void testGetEndDimensions() {
    double[] s1EndDimensions = ((Scale)s1).getEndDimension();
    assertEquals(20, s1EndDimensions[0], 0.001);
    assertEquals(25, s1EndDimensions[1], 0.001);
    double[] s2EndDimensions = ((Scale)s2).getEndDimension();
    assertEquals(100, s2EndDimensions[0], 0.001);
    assertEquals(200, s2EndDimensions[1], 0.001);
  }

  /**
   * Test method for getting the start color of a color changing motion.
   */
  @Test
  public void testGetStartColor() {
    assertEquals(1, ((ChangeColor)c1).getStartColor().getRed(), 0.001);
    assertEquals(2, ((ChangeColor)c1).getStartColor().getGreen(), 0.001);
    assertEquals(1, ((ChangeColor)c1).getStartColor().getBlue(), 0.001);
    assertEquals(35, ((ChangeColor)c2).getStartColor().getRed(), 0.001);
    assertEquals(40, ((ChangeColor)c2).getStartColor().getGreen(), 0.001);
    assertEquals(40, ((ChangeColor)c2).getStartColor().getBlue(), 0.001);
  }

  /**
   * Test method for getting the end color of a color changing motion.
   */
  @Test
  public void testGetEndColor() {
    assertEquals(0, ((ChangeColor)c1).getEndColor().getRed(), 0.001);
    assertEquals(1, ((ChangeColor)c1).getEndColor().getGreen(), 0.001);
    assertEquals(0, ((ChangeColor)c1).getEndColor().getBlue(), 0.001);
    assertEquals(100, ((ChangeColor)c2).getEndColor().getRed(), 0.001);
    assertEquals(100, ((ChangeColor)c2).getEndColor().getGreen(), 0.001);
    assertEquals(255, ((ChangeColor)c2).getEndColor().getBlue(), 0.001);
  }

  /**
   * Test method for getting the string representation of a motion.
   */
  @Test
  public void testToString() {
    assertEquals("Shape R moves from (0.0,100.0) to (0.0,200.0) from t=0 to t=100",
            m1.toString());
    assertEquals("Shape e moves from (-350.0,20.0) to (100.0,-150.0) from t=20 to t=85",
            m2.toString());
    assertEquals("Shape r scales from Width: 50.0, Height: 60.0 to "
            + "Width: 20.0, Height: 25.0 from t=10 to t=150", s1.toString());
    assertEquals("Shape E scales from Width: 30.0, Height: 75.0 to "
            + "Width: 100.0, Height: 200.0 from t=35 to t=50", s2.toString());
    assertEquals("Shape T changes color from (1.0,2.0,1.0) to "
            + "(0.0,1.0,0.0) from t=0 to t=70", c1.toString());
    assertEquals("Shape a changes color from (35.0,40.0,40.0) to "
            + "(100.0,100.0,255.0) from t=10 to t=25", c2.toString());
  }
}
