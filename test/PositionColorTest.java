import org.junit.Before;
import org.junit.Test;

import model.Color;
import model.Position;

import static org.junit.Assert.assertEquals;

/**
 * Public test class for Position and Color classes.
 */
public class PositionColorTest {
  private Position p1;
  private Position p2;
  private Color c1;
  private Color c2;

  /**
   * Setups for positions and colors.
   */
  @Before
  public void setup() {
    p1 = new Position(23, 100);
    p2 = new Position(-50, -60);
    c1 = new Color(30, 50, 55);
    c2 = new Color(255, 200, 180);
  }

  /**
   * Test valid position constructors.
   */
  @Test
  public void testValidPosition() {
    assertEquals(23, p1.getX(), 0.001);
    assertEquals(100, p1.getY(), 0.001);
    assertEquals(-50, p2.getX(), 0.001);
    assertEquals(-60, p2.getY(), 0.001);
  }

  /**
   * Test valid color constructors.
   */
  @Test
  public void testValidColor() {
    assertEquals(30, c1.getRed(), 0.001);
    assertEquals(50, c1.getGreen(), 0.001);
    assertEquals(55, c1.getBlue(), 0.001);
    assertEquals(255, c2.getRed(), 0.001);
    assertEquals(200, c2.getGreen(), 0.001);
    assertEquals(180, c2.getBlue(), 0.001);
  }

  /**
   * Test invalid color inputs.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidColor() {
    new Color(-2, 50, 55);
    new Color(30, -80, 55);
    new Color(30, 50, -100);
    new Color(260, 200, 180);
    new Color(255, 300, 180);
    new Color(255, 200, 550);
  }

  /**
   * Test method for getting the X coordinate of a position.
   */
  @Test
  public void testGetX() {
    assertEquals(23, p1.getX(), 0.001);
    assertEquals(-50, p2.getX(), 0.001);
  }

  /**
   * Test method for getting the Y coordinate of a position.
   */
  @Test
  public void testGetY() {
    assertEquals(100, p1.getY(), 0.001);
    assertEquals(-60, p2.getY(), 0.001);
  }

  /**
   * Test method for getting the red intensity of a color.
   */
  @Test
  public void testGetRed() {
    assertEquals(30, c1.getRed(), 0.001);
    assertEquals(255, c2.getRed(), 0.001);
  }

  /**
   * Test method for getting the green intensity of a color.
   */
  @Test
  public void testGetGreen() {
    assertEquals(50, c1.getGreen(), 0.001);
    assertEquals(200, c2.getGreen(), 0.001);
  }

  /**
   * Test method for getting the blue intensity of a color.
   */
  @Test
  public void testGetBlue() {
    assertEquals(55, c1.getBlue(), 0.001);
    assertEquals(180, c2.getBlue(), 0.001);
  }
}
