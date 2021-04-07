import org.junit.Before;
import org.junit.Test;

import model.Color;
import model.IShape;
import model.Oval;
import model.Position;
import model.Rectangle;

import static org.junit.Assert.assertEquals;

/**
 * This class is to test IShape interface and all sub-type classes
 */
public class ShapeTest {
  private Position p;
  private Position p2;
  private Color c;
  private Color c2;
  private IShape s;
  private IShape s2;

  @Before
  public void setup() {
    p = new Position(200, 200);
    c = new Color(1, 0, 0);
    s = new Rectangle("R", p, c, 1, 100, 50, 100);
    p2 = new Position(500, 100);
    c2 = new Color(0, 0, 1);
    s2 = new Oval("C", p2, c2, 6, 100, 60, 30);
  }

  @Test
  public void testValidRectangle() {
    assertEquals("R", s.getName());
    assertEquals(200, s.getPosition().getX(), 0.001);
    assertEquals(200, s.getPosition().getY(), 0.001);
    assertEquals(1, s.getColor().getRed(), 0.001);
    assertEquals(0, s.getColor().getGreen(), 0.001);
    assertEquals(0, s.getColor().getBlue(), 0.001);
    assertEquals(1, s.getAppearTick());
    assertEquals(100, s.getDisappearTick());
    double[] dimensions = s.getDimensions();
    assertEquals(2, dimensions.length);
    assertEquals(50, dimensions[0], 0.001);
    assertEquals(100, dimensions[1], 0.001);
    assertEquals("Name: R\n"
            + "Type: rectangle\n"
            + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,0.0,0.0)\n"
            + "Appears at t=1\n"
            + "Disappears at t=100", s.toString());
  }

  @Test
  public void testValidOval() {
    assertEquals("C", s2.getName());
    assertEquals(500, s2.getPosition().getX(), 0.001);
    assertEquals(100, s2.getPosition().getY(), 0.001);
    assertEquals(0, s2.getColor().getRed(), 0.001);
    assertEquals(0, s2.getColor().getGreen(), 0.001);
    assertEquals(1, s2.getColor().getBlue(), 0.001);
    assertEquals(6, s2.getAppearTick());
    assertEquals(100, s2.getDisappearTick());
    double[] dimensions = s2.getDimensions();
    assertEquals(2, dimensions.length);
    assertEquals(60, dimensions[0], 0.001);
    assertEquals(30, dimensions[1], 0.001);
    assertEquals("Name: C\n"
            + "Type: oval\n"
            + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,1.0)\n"
            + "Appears at t=6\n"
            + "Disappears at t=100", s2.toString());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidName() {
    new Rectangle(null, p, c, 10, 30, 25, 25);
    new Rectangle("", p, c, 10, 30, 25, 25);
    new Oval(null, p2, c2, 10, 30, 25, 25);
    new Oval("", p2, c2, 10, 30, 25, 25);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidPosition() {
    new Rectangle("R", null, c, 10, 30, 25, 25);
    new Oval("C", null, c2, 10, 30, 25, 25);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidColor() {
    new Rectangle("R", p, null, 10, 30, 25, 25);
    new Oval("R", p2, null, 10, 30, 25, 25);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidAppearOrDisappearTick() {
    new Rectangle("R", p, c, -1, 10, 25, 25);
    new Rectangle("R", p, c, 10, -1, 25, 25);
    new Oval("C", p2, c2, -1, 30, 25, 25);
    new Oval("C", p2, c2, 10, -1, 25, 25);
    new Rectangle("R", p, c, 10, 5, 25, 25);
    new Rectangle("R", p, c, 10, 10, 25, 25);
    new Oval("C", p2, c2, 10, 5, 25, 25);
    new Oval("C", p2, c2, 10, 10, 25, 25);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidDimensions() {
    new Rectangle("R", p, c, 10, 30, -1, 25);
    new Rectangle("R", p, c, 10, 30, 25, -1);
    new Oval("C", p2, c2, 10, 30, -1, 25);
    new Oval("C", p2, c2, 10, 30, 25, -1);
  }

  @Test
  public void testSetPosition() {
    s.setPosition(new Position(-100.5, 250.9999));
    assertEquals(-100.5, s.getPosition().getX(), 0.001);
    assertEquals(250.9999, s.getPosition().getY(), 0.001);
    s2.setPosition(new Position(0, 0));
    assertEquals(0, s2.getPosition().getX(), 0.001);
    assertEquals(0, s2.getPosition().getY(), 0.001);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testSetPositionWithInvalidInput() {
    s.setPosition(null);
    s2.setPosition(null);
  }

  @Test
  public void testSetColor() {
    s.setColor(new Color(230.99, 25.0, 111.8));
    assertEquals(230.99, s.getColor().getRed(), 0.001);
    assertEquals(25.0, s.getColor().getGreen(), 0.001);
    assertEquals(111.8, s.getColor().getBlue(), 0.001);
    s2.setColor(new Color(56, 0.0, 210.50));
    assertEquals(56, s2.getColor().getRed(), 0.001);
    assertEquals(0.0, s2.getColor().getGreen(), 0.001);
    assertEquals(210.50, s2.getColor().getBlue(), 0.001);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testSetColorWithInvalidInput() {
    s.setColor(null);
    s2.setColor(null);
  }

  @Test
  public void testSetDimensions() {
    s.setDimensions(new double[]{0.0, 200.472});
    assertEquals(0.0, s.getDimensions()[0], 0.001);
    assertEquals(200.472, s.getDimensions()[1], 0.001);
    s2.setDimensions(new double[]{50.01, 0});
    assertEquals(50.01, s2.getDimensions()[0], 0.001);
    assertEquals(0, s2.getDimensions()[1], 0.001);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testSetDimensionsWithInvalidInput() {
    s.setDimensions(null);
    s.setDimensions(new double[]{65});
    s.setDimensions(new double[]{60, 30, 100});
    s.setDimensions(new double[]{-20, 50});
    s.setDimensions(new double[]{20, -10});
    s2.setDimensions(null);
    s2.setDimensions(new double[]{65});
    s2.setDimensions(new double[]{60, 30, 100});
    s2.setDimensions(new double[]{-20, 50});
    s2.setDimensions(new double[]{20, -10});
  }

  @Test
  public void testCopyRectangle() {
    IShape copy = s.copy();
    assertEquals(s.toString(), copy.toString());
    copy.setPosition(new Position(100, 300));
    assertEquals(200, s.getPosition().getX(), 0.001);
    assertEquals(200, s.getPosition().getY(), 0.001);
    assertEquals(100, copy.getPosition().getX(), 0.001);
    assertEquals(300, copy.getPosition().getY(), 0.001);
  }

  @Test
  public void testCopyOval() {
    IShape copy = s2.copy();
    assertEquals(s2.toString(), copy.toString());
    s2.setColor(new Color(1, 1, 0));
    assertEquals(1, s2.getColor().getRed(), 0.001);
    assertEquals(1, s2.getColor().getGreen(), 0.001);
    assertEquals(0, s2.getColor().getBlue(), 0.001);
    assertEquals(0, copy.getColor().getRed(), 0.001);
    assertEquals(0, copy.getColor().getGreen(), 0.001);
    assertEquals(1, copy.getColor().getBlue(), 0.001);
  }

  @Test
  public void testToString() {
    IShape s3 = new Rectangle("Rectangle_1", new Position(20.82, -59.0),
            new Color(100.9999, 0, 254.90), 0, 1,
            20, 80.1);
    assertEquals("Name: Rectangle_1\n"
            + "Type: rectangle\n"
            + "Min corner: (20.8,-59.0), Width: 20.0, Height: 80.1, Color: (101.0,0.0,254.9)\n"
            + "Appears at t=0\n"
            + "Disappears at t=1", s3.toString());
    IShape s4 = new Oval("Oval_2", new Position(-300.70, 66),
            new Color(2.23, 167.0, 29.4), 10, 40,
            100.789, 0.0);
    assertEquals("Name: Oval_2\n"
            + "Type: oval\n"
            + "Center: (-300.7,66.0), X radius: 100.8, Y radius: 0.0, Color: (2.2,167.0,29.4)\n"
            + "Appears at t=10\n"
            + "Disappears at t=40", s4.toString());
  }
}